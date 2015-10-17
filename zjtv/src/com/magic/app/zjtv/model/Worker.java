package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.WorkerEntity;
import com.magic.commons.utils.DateUtils;

public class Worker extends WorkerEntity {
	public String getRegistDateStr(){
        return DateUtils.dateToInputStrAppendTime(this.getRegistDate());
    }
	public String getGenderStr(){
		return this.getGender()==0?"男":"女";
	}
	public Boolean getContinue() {
        return isContinue==null?false:isContinue;
    }

    public void setContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }
    private Boolean isContinue;
}
