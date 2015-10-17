package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.UserAdminEntity;
import com.magic.commons.utils.DateUtils;

/**
 * Created by yunchunnan on 14-5-7.
 */
public class UserAdmin extends UserAdminEntity {
    private String oldPwd;
    private String newPwd;
    private Integer roleId;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getLastLoginDateStr(){
        return DateUtils.dateToInputStr(getLastLoginDate());
    }

    public Boolean getContinue() {
        return isContinue==null?false:isContinue;
    }

    public void setContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    private Boolean isContinue;

    public String getUsertypeStr(){
        if("business".equals(getUsertype())){
            return "商户账号";
        } else {
            return "普通账号";
        }
    }
}
