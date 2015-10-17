package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.CommentEntity;
import com.magic.commons.utils.DateUtils;

public class Comment extends CommentEntity {
	private String nickName;
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCommentTimeStr(){
        return DateUtils.dateToInputStrAppendTime(this.getCommentTime());
    }
}
