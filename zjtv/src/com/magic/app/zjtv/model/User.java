package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.UserEntity;
import com.magic.commons.utils.DateUtils;

public class User extends UserEntity {

	
    public String getRegistDateStr(){
        return DateUtils.dateToInputStrAppendTime(this.getRegistDate());
    }
    
    
    public String getBirthdayStr(){
    	return DateUtils.dateToInputStrWOTime(this.getBirthday());
    }
}
