package com.magic.commons.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magic.app.zjtv.model.Role;
import com.magic.app.zjtv.model.RolePermission;
import com.magic.app.zjtv.model.UserAdmin;
import com.magic.app.zjtv.model.UserRole;
import com.magic.commons.service.AdminService;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.JsonUtils;
import com.magic.core.annotation.Menu;
import com.magic.core.annotation.Permission;
import com.magic.core.annotation.layout.LayoutNone;

/**
 * Created by YinJianFeng on 14-5-7.
 */
@Controller
@RequestMapping("/admin")
@Menu(id = "admin", label = "系统设置", visible = false, serialNumber = 12)
@Permission("P_SETTING")
public class AdminController {
    @Autowired
    AdminService service;

    @RequestMapping
    public String index(){
        return "index";
    }

    @RequestMapping("/usermanager")
    @Menu(id = "usermanager", label = "账户管理", serialNumber = 1)
    @Permission("P_SETTING_ACCOUNT_MGN")
    public String usermanager(){
        return "usermanager";
    }

    @RequestMapping("/rolemanager")
    @Menu(id = "rolemanager", label = "角色 & 权限", serialNumber = 2)
    @Permission("P_SETTING_ROLE_MGN")
    public String rolemanager(HttpServletRequest request){
        List<Role> roles = service.getAllRoles();
        request.setAttribute("roles", roles);
        return "rolemanager";
    }


    @RequestMapping("/deleteAccount")
    @ResponseBody
    public String deleteAccount(Integer id){
        service.deleteAccount(id);
        return "success";
    }

    @RequestMapping("/loadalluser")
    @ResponseBody
    public String loadalluser(){
        List<UserAdmin> list = service.getAllUsers();
        return JsonUtils.aaData(list);
    }
    

    @RequestMapping("/editAccount/{id}")
    public String editAccount(HttpServletRequest request,@PathVariable Integer id, @ModelAttribute UserAdmin userAdmin){
        userAdmin.setId(null);
        if (id != 0){
            UserAdmin userTmp = service.getUserById(id);
            BeanUtils.copyProperties(userTmp, userAdmin);
        }else{
            userAdmin.setUsertype("normal");
        }
        return "account_edit";
    }

    @RequestMapping("/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username){
        return service.isUserNameExisted(username);
    }

    @RequestMapping("/saveOrUpdateAccount")
    public String saveOrUpdateAccount(@ModelAttribute UserAdmin userAdmin){
        Boolean isContinue = userAdmin.getContinue();
        service.saveOrUpdateAccount(userAdmin);
        if (isContinue){
            return "redirect:/admin/editAccount/0";
        }
        return "redirect:/admin/usermanager";
    }


    @RequestMapping("/getrolebyuser/{userId}")
    @LayoutNone
    public String getrolebyuser(HttpServletRequest request, @PathVariable Integer userId){
        List<Role> roles = service.getRolesByUserId(userId);
        request.setAttribute("userRoles", roles);
        return "user_role";
    }

    @RequestMapping("/loadavaliableroles/{userId}")
    @LayoutNone
    public String loadavaliableroles(HttpServletRequest request, @ModelAttribute UserRole userRole,
                                     @PathVariable Integer userId){
        List<Role> role1 = service.getAllRoles();
        List<Role> role2 = service.getRolesByUserId(userId);
        List<Integer> roleIds = new ArrayList<Integer>();
        for (Role role : role2) {
            roleIds.add(role.getId());
        }
        for (Iterator<Role> iterator = role1.iterator(); iterator.hasNext(); ) {
            Role next = iterator.next();
            Integer id = next.getId();
            if (roleIds.contains(id)){
                iterator.remove();
            }
        }
        userRole.setUserId(userId);
        request.setAttribute("avaRoles", role1);
        return "avaliable_roles";
    }

    @RequestMapping("/saveUserRole")
    @ResponseBody
    public Integer saveUserRole(@ModelAttribute UserRole userRole){
        service.saveUserRole(userRole);
        return userRole.getUserId();
    }
    @RequestMapping("/removeUserRole")
    @ResponseBody
    public Integer removeUserRole(Integer userId, Integer roleId){
        service.removeUserRole(userId, roleId);
        return userId;
    }

    @RequestMapping("/saveRolePermission")
    @ResponseBody
    public Integer saveRolePermission(@ModelAttribute RolePermission rolePermission){
        service.saveRolePermission(rolePermission);
        return rolePermission.getRoleId();
    }
    @RequestMapping("/removeRolePermission")
    @ResponseBody
    public Integer removeRolePermission(Integer roleId, Integer pId){
        service.removeRolePermission(roleId, pId);
        return roleId;
    }

    @RequestMapping("/resetPassword")
    @ResponseBody
    public String resetPassword(Integer id){
        String newPwd = service.resetPassword(id);
        return "{\"state\":true, \"msg\":\"新密码已重置为["+newPwd+"]\"}";
    }

    @RequestMapping("/showUserDialog/{id}")
    @LayoutNone
    public String showUserDialog(@PathVariable Integer id, @ModelAttribute UserAdmin userAdmin){
        UserAdmin userAdminTmp = service.getUserById(id);
        userAdmin.setUsername(userAdminTmp.getUsername());
        return "change_pwd_dialog";
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public String updatePassword(@ModelAttribute UserAdmin userAdmin){
        Integer id = userAdmin.getId();
        String username = userAdmin.getUsername();
        String oldPwd = userAdmin.getOldPwd();
        String newPwd = userAdmin.getNewPwd();
        if(!service.checkAccount(id, oldPwd)){
            return "{\"state\":false, \"msg\":\"旧密码无效！\"}";
        }
        service.updatePassword(id, newPwd);
        return "{\"state\":true, \"msg\":\"密码已更改！\", \"userId\":\""+id+"\"}";
    }

    @RequestMapping("/getpermissionbyrole/{roleId}")
    @LayoutNone
    public String getpermissionbyrole(HttpServletRequest request, @PathVariable Integer roleId){
        List<com.magic.app.zjtv.model.Permission> permissions = service.getPermissionsByRoleId(roleId);
        request.setAttribute("rolePermissions", permissions);
        return "role_permission";
    }
    @RequestMapping("/loadavaliablepermissions/{roleId}")
    @LayoutNone
    public String loadavaliablepermissions(HttpServletRequest request, @ModelAttribute RolePermission rolePermission,
                                           @PathVariable Integer roleId) {
        List<com.magic.app.zjtv.model.Permission> permission1 = service.getAllPermissions();
        List<com.magic.app.zjtv.model.Permission> permission2 = service.getPermissionsByRoleId(roleId);
        List<Integer> pIds = new ArrayList<Integer>();
        for (com.magic.app.zjtv.model.Permission p : permission2) {
            pIds.add(p.getId());
        }
        for (Iterator<com.magic.app.zjtv.model.Permission> iterator = permission1.iterator(); iterator.hasNext(); ) {
            com.magic.app.zjtv.model.Permission next = iterator.next();
            Integer id = next.getId();
            if (pIds.contains(id)) {
                iterator.remove();
            }
        }
        rolePermission.setRoleId(roleId);
        Collections.sort(permission1, new Comparator<com.magic.app.zjtv.model.Permission>() {
            @Override
            public int compare(com.magic.app.zjtv.model.Permission o1, com.magic.app.zjtv.model.Permission o2) {
                return o1.getPermissionName().compareTo(o2.getPermissionName());
            }
        });
        request.setAttribute("avaPermissions", permission1);
        return "avaliable_permissions";
    }

}
