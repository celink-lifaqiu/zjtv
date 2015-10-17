package com.magic.app.zjtv.model;

/**
 * Created by YinJianFeng on 14-5-7.
 */
public class UserRole {
    private Integer userId;
    private Integer roleId;
    private Integer[] roles;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer[] getRoles() {
        return roles;
    }

    public void setRoles(Integer[] roles) {
        this.roles = roles;
    }
}
