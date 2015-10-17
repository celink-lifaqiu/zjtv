package com.magic.app.zjtv.model;

/**
 * Created by yunchunnan on 14-5-7.
 */
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;
    private Integer[] permissions;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer[] getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer[] permissions) {
        this.permissions = permissions;
    }
}
