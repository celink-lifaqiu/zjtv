package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.AdvertiseEntity;

public class Advertise extends AdvertiseEntity {
	private String iconHash;
	
	
    public String getIconHash() {
		return iconHash;
	}

	public void setIconHash(String iconHash) {
		this.iconHash = iconHash;
	}

	public Boolean getContinue() {
        return isContinue==null?false:isContinue;
    }

    public void setContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }

    private Boolean isContinue;
}
