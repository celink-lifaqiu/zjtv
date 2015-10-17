package com.magic.app.zjtv.model;

import java.util.Date;
import com.magic.commons.utils.DateUtils;

/**
 * Created by lifaqiu on 14-10-25.
 */
public class UserAdminComp {
	private Integer id;

	private Date createdDate;

	private Date lastLoginDate;

	private String password;

	private Date updatedDate;

	private String username;
    private String usertype;
    private Integer uId;
    private Integer exchangeCnt;


	public Integer getExchangeCnt() {
		return exchangeCnt;
	}

	public void setExchangeCnt(Integer exchangeCnt) {
		this.exchangeCnt = exchangeCnt;
	}

	public Boolean getIsContinue() {
		return isContinue;
	}

	public void setIsContinue(Boolean isContinue) {
		this.isContinue = isContinue;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    
	
	
	
	
	
	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}







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
