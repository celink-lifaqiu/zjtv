package com.magic.commons.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.PermissionDAO;
import com.magic.app.zjtv.dao.RoleDAO;
import com.magic.app.zjtv.dao.UserAdminDAO;
import com.magic.app.zjtv.entities.PermissionEntity;
import com.magic.app.zjtv.entities.RoleEntity;
import com.magic.app.zjtv.entities.UserAdminEntity;
import com.magic.app.zjtv.model.Permission;
import com.magic.app.zjtv.model.Role;
import com.magic.app.zjtv.model.RolePermission;
import com.magic.app.zjtv.model.UserAdmin;
import com.magic.app.zjtv.model.UserAdminComp;
import com.magic.app.zjtv.model.UserRole;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.DateUtils;

/**
 * Created by YinJianFeng on 14-5-6.
 */
@Transactional
@Service
public class AdminService {
    @Autowired
    UserAdminDAO userAdminDAO;
    @Autowired
    RoleDAO roleDAO;
    @Autowired
    PermissionDAO permissionDAO;
    @Autowired


    public List<UserAdmin> getAllUsers(){
        List<UserAdminEntity> entities = userAdminDAO.loadAllAdminUsers();
        return BeanUtils.convertEntityToModelList(entities, UserAdmin.class, "roles");
    }

    public UserAdmin getUserById(Integer id){
        UserAdminEntity entity = userAdminDAO.findOne(id);
        return BeanUtils.convertEntityToModel(entity, UserAdmin.class);
    }

    public boolean isUserNameExisted(String username){
        UserAdminEntity entity = userAdminDAO.findByUsername(username);
        return (entity != null);
    }

    public UserAdmin queryUserAdminByName(String username){
        UserAdminEntity entity = userAdminDAO.findByUsername(username);
        return BeanUtils.convertEntityToModel(entity, UserAdmin.class);
    }

    public UserAdmin saveOrUpdateAccount(UserAdmin userAdmin){
        Integer id = userAdmin.getId();
        UserAdminEntity entity = new UserAdminEntity();
        if (id != null) {
            entity = userAdminDAO.findOne(id);
            entity.setUpdatedDate(DateUtils.getDateTimestamp());
        }else{
            entity.setLastLoginDate(DateUtils.getDate());
            entity.setUpdatedDate(DateUtils.getDateTimestamp());
            entity.setCreatedDate(DateUtils.getDateTimestamp());
        }
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPs = encoder.encodePassword(userAdmin.getPassword(), userAdmin.getUsername());
        entity.setUsername(userAdmin.getUsername());
        entity.setPassword(encodedPs);
        entity.setUsertype(userAdmin.getUsertype());
        if(userAdmin.getUsertype().trim().equals("business")){
            entity.setuId(userAdmin.getuId());
        }else{
        	entity.setuId(0);
        }

        entity = userAdminDAO.save(entity);
        return BeanUtils.convertEntityToModel(entity, UserAdmin.class);
    }

    public List<Role> getRolesByUserId(Integer userId){
        UserAdminEntity userAdminEntity = userAdminDAO.findOne(userId);
        List<RoleEntity> roleEntities = userAdminEntity.getRoles();
        return BeanUtils.convertEntityToModelList(roleEntities, Role.class, "permissions");
    }

    public List<Role> getAllRoles(){
        List<RoleEntity> roleEntities = (List<RoleEntity>) roleDAO.findAll();
        return BeanUtils.convertEntityToModelList(roleEntities, Role.class, "permissions");
    }

    public void deleteAccount(Integer id){
        UserAdminEntity entity = userAdminDAO.findOne(id);
        List<RoleEntity> roleEntities = entity.getRoles();
        if (roleEntities != null && !roleEntities.isEmpty()) {
            for (RoleEntity roleEntity : roleEntities) {
                roleEntity.getUserAdmins().remove(entity);
            }
        }
        userAdminDAO.delete(entity);
    }

    public void saveUserRole(UserRole userRole){
        UserAdminEntity userAdminEntity = userAdminDAO.findOne(userRole.getUserId());
        Integer[] roleIds = userRole.getRoles();
        List<RoleEntity> roleEntities = new ArrayList<RoleEntity>();
        if (roleIds != null && roleIds.length>0) {
            for (Integer roleId : roleIds) {
                RoleEntity roleEntity = roleDAO.findOne(roleId);
                roleEntity.getUserAdmins().add(userAdminEntity);
                roleEntities.add(roleEntity);
            }
        }
        roleDAO.save(roleEntities);
    }

    public void removeUserRole(Integer userId, Integer roleId){
        RoleEntity roleEntity = roleDAO.findOne(roleId);
        List<UserAdminEntity> userAdminEntities = roleEntity.getUserAdmins();
        for (Iterator<UserAdminEntity> iterator = userAdminEntities.iterator(); iterator.hasNext(); ) {
            UserAdminEntity next = iterator.next();
            if (next.getId() == userId){
                iterator.remove();
            }
        }
        roleDAO.save(roleEntity);
    }

    public void saveRolePermission(RolePermission rolePermission){
        RoleEntity roleEntity = roleDAO.findOne(rolePermission.getRoleId());
        Integer[] pIds = rolePermission.getPermissions();
//        List<PermissionEntity> permissionEntities = new ArrayList<PermissionEntity>();
        if (pIds != null && pIds.length>0) {
            for (Integer pId : pIds) {
                PermissionEntity permissionEntity = permissionDAO.findOne(pId);
//                permissionEntity.getRoles().add(roleEntity);
//                permissionEntities.add(permissionEntity);
                roleEntity.getPermissions().add(permissionEntity);
            }
        }
        roleDAO.save(roleEntity);
    }

    public void removeRolePermission(Integer roleId, Integer pId){
        RoleEntity roleEntity = roleDAO.findOne(roleId);
        List<PermissionEntity> permissionEntities = roleEntity.getPermissions();
        for (Iterator<PermissionEntity> iterator = permissionEntities.iterator(); iterator.hasNext(); ) {
            PermissionEntity next = iterator.next();
            if (next.getId().equals(pId)){
                iterator.remove();
            }
        }
        roleDAO.save(roleEntity);

        /*PermissionEntity permissionEntity = permissionDAO.findOne(pId);
        List<RoleEntity> roleEntities = permissionEntity.getRoles();
        for (Iterator<RoleEntity> iterator = roleEntities.iterator(); iterator.hasNext(); ) {
            RoleEntity next = iterator.next();
            if (next.getId() == roleId){
                iterator.remove();
            }
        }
        permissionDAO.save(permissionEntity);*/
    }



    public boolean checkAccount(Integer id, String password){
        UserAdminEntity userAdminEntity = userAdminDAO.findOne(id);

        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPs = encoder.encodePassword(password, userAdminEntity.getUsername());

        String pwd = userAdminEntity.getPassword();
        return (pwd.equals(encodedPs));
    }

    public String resetPassword(Integer id){
        String newPwd = "123456";
        UserAdminEntity userAdminEntity = userAdminDAO.findOne(id);
        String username = userAdminEntity.getUsername();
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPs = encoder.encodePassword(newPwd, username);
        userAdminEntity.setPassword(encodedPs);
        userAdminDAO.save(userAdminEntity);
        return newPwd;
    }

    public void updatePassword(Integer id , String newPwd){
        UserAdminEntity userAdminEntity = userAdminDAO.findOne(id);
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPs = encoder.encodePassword(newPwd, userAdminEntity.getUsername());
        userAdminEntity.setPassword(encodedPs);
        userAdminDAO.save(userAdminEntity);
    }

    public List<Permission> getPermissionsByRoleId(Integer roleId){
        RoleEntity roleEntity = roleDAO.findOne(roleId);
        List<PermissionEntity> permissionEntities = roleEntity.getPermissions();
        return BeanUtils.convertEntityToModelList(permissionEntities, Permission.class, "roles", "permissions");
    }
    public List<Permission> getAllPermissions(){
        List<PermissionEntity> permissionEntities = (List<PermissionEntity>) permissionDAO.findAll();
        return BeanUtils.convertEntityToModelList(permissionEntities, Permission.class, "roles", "permissions");
    }

    public void saveOrUpdateAccountAndRole(UserAdmin userAdmin){
        UserAdminEntity adminEntity = new UserAdminEntity();
        
        adminEntity.setUpdatedDate(DateUtils.getDateTimestamp());
        adminEntity.setLastLoginDate(DateUtils.getDate());
        adminEntity.setCreatedDate(DateUtils.getDateTimestamp());
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPs = encoder.encodePassword(userAdmin.getPassword(), userAdmin.getUsername());
        adminEntity.setUsername(userAdmin.getUsername());
        adminEntity.setPassword(encodedPs);
        adminEntity.setUsertype(userAdmin.getUsertype());
        adminEntity = userAdminDAO.save(adminEntity);
        Integer roleId=userAdmin.getRoleId();
        RoleEntity roleEntity=roleDAO.findOne(roleId);
        roleEntity.getUserAdmins().add(adminEntity);
        roleDAO.save(roleEntity);
    }
/*
    //  Start Permission Maintainance
    public List<Permission> getAllPermissions(){
        List<Permission> permissionList = new ArrayList<Permission>();
        List<TblPermission> entities = (List<TblPermission>) permissionDAO.findAll();
        for (TblPermission entity : entities) {
            Permission permission = new Permission();
            BeanUtils.copyProperties(entity, permission);
            Set<TblResource> resourceEntities = entity.getTblResources();
            for (TblResource resourceEntity : resourceEntities) {
                Resource resource = new Resource();
                BeanUtils.copyProperties(resourceEntity, resource);
                permission.addResource(resource);
            }
            permissionList.add(permission);
        }
        return permissionList;
    }
    public Permission getPermission(Integer id){
        Permission permission = new Permission();
        TblPermission entity = permissionDAO.findOne(id);
        BeanUtils.copyProperties(entity, permission);
        Set<TblResource> resourceSet = entity.getTblResources();
        List<Resource> resourceList = new ArrayList<Resource>();
        for (TblResource resourceEntity : resourceSet) {
            Resource resource = new Resource();
            BeanUtils.copyProperties(resourceEntity, resource);
            resourceList.add(resource);
        }
        permission.setResourceList(resourceList);
        return permission;
    }

    public List<Resource> getAllResources(){
        List<Resource> resources = new ArrayList<Resource>();
        List<TblResource> tblResources = (List<TblResource>) resourceDAO.findAll();
        for (TblResource tblResource : tblResources) {
            Resource resource = new Resource();
            BeanUtils.copyProperties(tblResource, resource);
            TblPermission tblPermission = tblResource.getTblPermission();
            resource.setPermissionCode(tblPermission.getPermissionCode());
            resources.add(resource);
        }
        return resources;
    }
    public void assignResource(Integer permissionId, String[] urls){
        TblPermission permission = permissionDAO.findOne(permissionId);
        resourceDAO.deleteResourceByPermission(permissionId);
        List<TblResource> resources = new ArrayList<TblResource>();
        for (String url : urls) {
            TblResource resource = new TblResource();
            resource.setUrl(url);
            resource.setTblPermission(permission);
            resources.add(resource);
        }
        resourceDAO.save(resources);
        securityMetadataSource.loadResourceDefine();
    }
    public void removeResource(Integer id){
        resourceDAO.delete(id);
    }
    public void savePermissions(List<Permission> permissionList){
        List<TblPermission> entities = new ArrayList<TblPermission>();
        for (Permission permission : permissionList) {
            TblPermission entity = new TblPermission();
            BeanUtils.copyProperties(permission, entity);
            entities.add(entity);
        }
        permissionDAO.save(entities);
    }
    public void delPermission(Integer id){
        TblPermission tblPermission = permissionDAO.findOne(id);
        resourceDAO.deleteResourceByPermission(id);
        tblPermission.setTblRoles(null);
        permissionDAO.save(tblPermission);
        permissionDAO.delete(tblPermission);
    }
    //  Start Role maintainance
    public List<Role> getAllRoles(){
        List<Role> roleList = new ArrayList<Role>();
        List<TblRole> entities = (List<TblRole>) roleDAO.findAll();
        for (TblRole entity : entities) {
            Role role = new Role();
            BeanUtils.copyProperties(entity, role);
            Set<TblPermission> permissionEntities = entity.getTblPermissions();
            for (TblPermission permissionEntity : permissionEntities) {
                Permission permission = new Permission();
                BeanUtils.copyProperties(permissionEntity, permission);
                role.addPermission(permission);
            }
            roleList.add(role);
        }
        return roleList;
    }
    public Role getRole(Integer id){
        Role role = new Role();
        TblRole entity = roleDAO.findOne(id);
        BeanUtils.copyProperties(entity, role);
        Set<TblPermission> permissionSet = entity.getTblPermissions();
        List<Permission> permissionList = new ArrayList<Permission>();
        for (TblPermission permissionEntity : permissionSet) {
            Permission permission = new Permission();
            BeanUtils.copyProperties(permissionEntity, permission);
            permissionList.add(permission);
        }
        role.setPermissionList(permissionList);
        return role;
    }
    public void assignPermission(Integer roleId, Integer[] permissionIds){
        TblRole role = roleDAO.findOne(roleId);
        Set<TblPermission> permissionSet = new HashSet<TblPermission>();
        for (Integer id : permissionIds) {
            TblPermission permissionEntity = permissionDAO.findOne(id);
            permissionSet.add(permissionEntity);
        }
        role.setTblPermissions(permissionSet);
        roleDAO.save(role);
    }
    public void removePermission(Integer roleId, Integer permissionId){
        roleDAO.deleteRolePermission(roleId, permissionId);
    }
    public void saveRole(List<Role> roleList){
        List<TblRole> entities = new ArrayList<TblRole>();
        for (Role role : roleList) {
            TblRole entity = new TblRole();
            BeanUtils.copyProperties(role, entity);
            entities.add(entity);
        }
        roleDAO.save(entities);
    }
    public void delRole(Integer id){
        roleDAO.delete(id);
    }
//  End Role maintainance*/

	public List<UserAdminComp> queryUserAdminByShopIdAndWithoutUserAdminId(
			Integer shopid, Integer userAdminId) {
		List<UserAdminEntity> entities = userAdminDAO.queryUserAdminByShopIdAndWithoutUserAdminId(shopid, userAdminId);
		List<UserAdminComp> returnList = null;
		UserAdminComp userAdminComp = null;
		if(entities != null && entities.size() > 0){
			returnList = new ArrayList<UserAdminComp>();
			for(UserAdminEntity entity: entities){
				userAdminComp = new UserAdminComp();
				userAdminComp.setExchangeCnt(0);
				userAdminComp.setId(entity.getId());
				userAdminComp.setuId(entity.getuId());
				userAdminComp.setCreatedDate(entity.getCreatedDate());
				userAdminComp.setLastLoginDate(entity.getLastLoginDate());
				userAdminComp.setUpdatedDate(entity.getUpdatedDate());
				userAdminComp.setPassword(entity.getPassword());
				userAdminComp.setUsername(entity.getUsername());
				userAdminComp.setUsertype(entity.getUsertype());
				returnList.add(userAdminComp);
			}
		}
		return returnList;
	}

	public void saveOrUpdatePassword(String username, UserAdmin userAdmin) {
        UserAdminEntity adminEntity = userAdminDAO.findByUsername(username);
        adminEntity.setUpdatedDate(DateUtils.getDateTimestamp());
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encodedPs = encoder.encodePassword(userAdmin.getPassword(), userAdmin.getUsername());
        adminEntity.setUsername(userAdmin.getUsername());
        adminEntity.setPassword(encodedPs);
        adminEntity = userAdminDAO.save(adminEntity);
	}

}
