package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.PackageTypeEntity;

public class PackageType extends PackageTypeEntity {
	public Boolean getContinue() {
        return isContinue==null?false:isContinue;
    }

    public void setContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }
    private Boolean isContinue;
}
