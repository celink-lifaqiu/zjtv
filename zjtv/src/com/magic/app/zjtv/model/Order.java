package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.OrderEntity;
import com.magic.commons.utils.DateUtils;

public class Order extends OrderEntity {
	public String getUpdateTimeStr(){
        return DateUtils.dateToInputStrWOTime(this.getUpdateTime());
    }
	
	public String getCreateDateStr(){
        return DateUtils.dateToInputStrWOTime(this.getCreateDate());
    }
	
	public String getIsUseVoucherStr(){
		return this.getUserCouponId()==0?"否":"是";
	}
	
	public String getStateStr(){
		String stateStr = "未知状态";
		switch (this.getState()) {
		case 1:
			stateStr = "已支付";
			break;
		case 2:
			stateStr = "已派人上门";
			break;
		case 3:
			stateStr = "已完成";
			break;

		default:
			break;
		}
		return stateStr;
	}
}
