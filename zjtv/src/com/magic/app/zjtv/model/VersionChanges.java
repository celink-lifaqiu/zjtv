package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.VersionChangesEntity;

public class VersionChanges extends VersionChangesEntity {

	public Boolean getContinue() {
        return isContinue==null?false:isContinue;
    }

    public void setContinue(Boolean isContinue) {
        this.isContinue = isContinue;
    }

    private Boolean isContinue;
}
