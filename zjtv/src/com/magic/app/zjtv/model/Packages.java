package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.PackagesEntity;

public class Packages extends PackagesEntity {
	public Boolean getContinue() {
        return isContinue==null?false:isContinue;
    }

    public void setContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }
    private Boolean isContinue;
    
    private String iconHash;
	
	
    public String getIconHash() {
		return iconHash;
	}

	public void setIconHash(String iconHash) {
		this.iconHash = iconHash;
	}
}
