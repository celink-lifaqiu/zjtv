package com.magic.core.security;

import com.magic.app.zjtv.dao.UserAdminDAO;
import com.magic.app.zjtv.entities.PermissionEntity;
import com.magic.app.zjtv.entities.RoleEntity;
import com.magic.app.zjtv.entities.UserAdminEntity;
import com.magic.commons.service.CommonService;
import com.magic.core.compontents.menu.MenuContainer;
import com.magic.core.compontents.menu.MenuItem;
import com.magic.core.springframework.JFengWebAppContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-20
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional(readOnly = true)
public class JFengUserDetailsService implements UserDetailsService {
    @Autowired
    UserAdminDAO userAdminDAO;
    @Autowired
    CommonService commonService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAdminEntity userEntity = userAdminDAO.findByUsername(username);
        if (userEntity == null){
            throw new UsernameNotFoundException(username);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getGrantedAuthority(userEntity));
    }
    private Set<GrantedAuthority> getGrantedAuthority(UserAdminEntity user){
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        Set<String> permissionCodes = new HashSet<String>();
        List<RoleEntity> roles = user.getRoles();
        for (RoleEntity role : roles) {
            List<PermissionEntity> permissionSet = role.getPermissions();
            for (PermissionEntity permission : permissionSet) {
                permissionCodes.add(permission.getPermissionCode());
            }
        }
        for (String permissionCode : permissionCodes) {
            authSet.add(new SimpleGrantedAuthority(permissionCode));
        }
//        authSet.add(new SimpleGrantedAuthority("P_COMMON"));
        invalidateMenu(authSet);
        return authSet;
    }

//  判断哪些菜单是可以显示
    private void invalidateMenu(Set<GrantedAuthority> authSet){
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.currentRequestAttributes()).getRequest();
        JFengWebAppContext appContext = (JFengWebAppContext) WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        MenuContainer menuContainer = appContext.getMenuContainer();
        List<MenuItem> menuItemList = menuContainer.getMenuItemList();
        updateMenuVisible(menuItemList, authSet);
    }

    private void updateMenuVisible(List<MenuItem> menuItemList, Set<GrantedAuthority> authSet){
        if (menuItemList.isEmpty()) return;
        for (MenuItem menuItem : menuItemList) {
            boolean hasPermission = false;
            Collection<ConfigAttribute> config = menuItem.getPermissions();
            for (ConfigAttribute configAttribute : config) {
                String p1 = configAttribute.getAttribute();
                for (GrantedAuthority authority : authSet) {
                    String p2 = authority.getAuthority();
                    if (p1.equals(p2) || "P_ADMIN".equals(p2)){
                        hasPermission = true;break;
                    }
                }
            }
//            if (menuItem.getVisible())
            if(!"admin".equals(menuItem.getId()))
            	menuItem.setVisible(hasPermission);
            updateMenuVisible(menuItem.getMenuItemList(), authSet);
        }
    }
}
