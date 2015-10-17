package com.magic.core.springframework;

import com.magic.commons.service.CommonService;
import com.magic.commons.utils.KeywordFilter;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.Permission;
import com.magic.core.compontents.menu.MenuContainer;
import com.magic.core.compontents.menu.MenuItem;
import com.magic.core.exception.MenuException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-11
 * Time: 上午2:06
 * To change this template use File | Settings | File Templates.
 */
public class JFengWebAppContext extends AnnotationConfigWebApplicationContext {
    private MenuContainer menuContainer = null;
    private Map<String, Collection<ConfigAttribute>> permissionResMap;

    @Override
    protected void finishRefresh() {
        super.finishRefresh();
        try {
            loadPermissionMap();
            refreshMenu();
        } catch (MenuException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void loadPermissionMap(){
        if (permissionResMap == null) {
            permissionResMap = new HashMap<String, Collection<ConfigAttribute>>();
        }
        Map<String, Object> map = getBeansWithAnnotation(Controller.class);
        for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> entry = iterator.next();
            Object obj = entry.getValue();
            RequestMapping classRm = AnnotationUtils.findAnnotation(obj.getClass(), RequestMapping.class);
            Permission classP = AnnotationUtils.findAnnotation(obj.getClass(), Permission.class);
            Method[] methods = obj.getClass().getMethods();
            String classUrl = "";
            if (classRm != null) {
                if (classRm.value().length>0){
                    classUrl = classRm.value()[0];
//                    List<String> permissions = new ArrayList<String>();
//                    permissions.add("P_COMMON");
//                    if (classP != null) {
//                        String[] pArr = classP.value();
//                        permissions.addAll(Arrays.asList(pArr));
//                    }
//                    addPermissionRes(classUrl, permissions.toArray(new String[]{}));
                    if (classP != null) {
                        String[] pArr = classP.value();
                        addPermissionRes(classUrl, pArr);
                    }else{
                        addPermissionRes(classUrl, new String[]{"P_COMMON"});
                    }
                }
            }
            for (Method method : methods) {
                RequestMapping methodRm = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                Permission methodP = AnnotationUtils.findAnnotation(method, Permission.class);
                if (methodRm != null) {
                    if (methodRm.value().length>0){
                        String uri = methodRm.value()[0];
//                        uri = uri.replaceAll("\\{(.*?)\\}", "*");
//                        List<String> permissions = new ArrayList<String>();
//                        permissions.add("P_COMMON");
//                        if (methodP != null) {
//                            String[] pArr = methodP.value();
//                            permissions.addAll(Arrays.asList(pArr));
//                        }
//                        addPermissionRes(classUrl + uri, permissions.toArray(new String[]{}));
                        if (methodP != null) {
                            String[] pArr = methodP.value();
                            addPermissionRes(classUrl + uri, pArr);
                        }else{
                            addPermissionRes(classUrl + uri, new String[]{"P_COMMON"});
                        }
                    }
                }
            }
        }
        logger.debug(permissionResMap);
    }
    private void addPermissionRes(String key, String[] permissions){
        Collection<ConfigAttribute> list = permissionResMap.get(key);
        if (list == null) {
            list = new ArrayList<ConfigAttribute>();
            permissionResMap.put(key, list);
        }
        if (permissions != null && permissions.length > 0) {
            for (String permission : permissions) {
                SecurityConfig config = new SecurityConfig(permission);
                list.add(config);
            }
        }
    }

    public Map<String, Collection<ConfigAttribute>> getPermissionResMap(){
        if (permissionResMap == null) {
            permissionResMap = new HashMap<String, Collection<ConfigAttribute>>();
        }
        return permissionResMap;
    }

    public MenuContainer getMenuContainer(){
        return menuContainer;
    }

    /**
     * @throws MenuException
     * Load all menu from all Controllers
     */
    public void refreshMenu() throws MenuException {
        validMenu();
        if (menuContainer == null) menuContainer = new MenuContainer();
//      attempt to retrive database configuration of menu
//      TODO

        Map<String, Object> map = getBeansWithAnnotation(Controller.class);
        int i = 0;
        for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> next = iterator.next();

            Menu classMenuAnno = AnnotationUtils.findAnnotation(next.getValue().getClass(), Menu.class);
            RequestMapping classReqAnno = AnnotationUtils.findAnnotation(next.getValue().getClass(), RequestMapping.class);
            MenuItem headMenuItem = null;
            String domainUri = "";
            if (classReqAnno != null&&classMenuAnno!=null) {
                domainUri = classReqAnno.value()[0];
                headMenuItem = new MenuItem();
                headMenuItem.setId(classMenuAnno.id());
                headMenuItem.setIndex(i++);
                headMenuItem.setSerialNumber(classMenuAnno.serialNumber());
                headMenuItem.setMenuType(MenuContainer.MENU_TYPE.HEAD_MENU);
                headMenuItem.setMenuId(classMenuAnno.id());
                headMenuItem.setLabel(classMenuAnno.label());
                headMenuItem.setIcon(classMenuAnno.icon());
                headMenuItem.setUri(domainUri + "/");
                headMenuItem.setVisible(classMenuAnno.visible());
                headMenuItem.setPermissions(permissionResMap.get(domainUri));
                menuContainer.addMenuItem(headMenuItem);
            }
            Method[] methods = next.getValue().getClass().getMethods();
            int j = 0;
            if (headMenuItem!=null)
                for (Method method : methods) {
                    Menu methodMenuAnno = AnnotationUtils.findAnnotation(method, Menu.class);
                    RequestMapping methodReqAnno = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                    if (methodMenuAnno!=null&&methodReqAnno!=null){
                        String uri = domainUri + methodReqAnno.value()[0];
                        if (!uri.startsWith("/")){
                            uri = "/"+uri;
                        }
                        MenuItem menuItem = new MenuItem();
                        menuItem.setId(methodMenuAnno.id());
                        menuItem.setParent(headMenuItem);
                        menuItem.setIndex(j++);
                        menuItem.setSerialNumber(methodMenuAnno.serialNumber());
                        menuItem.setMenuType(MenuContainer.MENU_TYPE.SUB_MENU);
                        menuItem.setMenuId(methodMenuAnno.id());
                        menuItem.setLabel(methodMenuAnno.label());
                        menuItem.setIcon(methodMenuAnno.icon());
                        menuItem.setUri(uri);
                        menuItem.setVisible(methodMenuAnno.visible());
                        menuItem.setPermissions(permissionResMap.get(uri));
                        headMenuItem.addMenuItem(menuItem);
                    }
                }
        }
        logger.info("Menu Loaded...");
    }

    /**
     * @throws MenuException
     * Check duplicated menu ids.
     */
    private void validMenu() throws MenuException {
        List<String> menuIdList = new ArrayList<String>();
        int emptyMenuIdSize = 0;
        Map<String, Object> map = getBeansWithAnnotation(Controller.class);
        for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> next = iterator.next();
            Menu classMenuAnno = AnnotationUtils.findAnnotation(next.getValue().getClass(), Menu.class);
            if (classMenuAnno == null) continue;
            menuIdList.add(classMenuAnno.id());
            Method[] methods = next.getValue().getClass().getMethods();
            for (Method method : methods) {
                Menu methodMenuAnno = AnnotationUtils.findAnnotation(method, Menu.class);
                if (methodMenuAnno == null) continue;
                if (StringUtils.isEmpty(methodMenuAnno.id())) emptyMenuIdSize ++;
                menuIdList.add(methodMenuAnno.id());
            }
        }
        Set<String> menuIdSet = new HashSet<String>(menuIdList);
        int totSize = menuIdList.size();    //here is total menuId size in application include empty menuId
        int distinctSize = menuIdSet.size();
        if (totSize!=(distinctSize+emptyMenuIdSize)) {
            for (String menuId : menuIdSet) {
                for (Iterator<String> iterator = menuIdList.iterator(); iterator.hasNext(); ) {
                    String next = iterator.next();
                    if (menuId.equals(next)){
                        iterator.remove();break;
                    }
                }
            }
            throw new MenuException("got duplicated menu id(s):"+menuIdList.toString());
        }
    }
}
