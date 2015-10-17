package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.VersionEntity;

public class Version extends VersionEntity {
	
	public String getTypeStr(){
		return this.getType()==1?"正式版本":"测试版本";
	}
	
    private String[] changes;

    public String[] getChanges() {
        return changes;
    }

    public void setChanges(String[] changes) {
        this.changes = changes;
    }

    public Integer getAppstore_online(){
        return super.getAppstoreOnline();
    }

    public String getAppstore_downloadurl(){
        return super.getAppstoreDownloadurl();
    }

    public Integer getAppstore_version_code(){
        return super.getAppstoreVersionCode();
    }
}
