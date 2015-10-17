package com.magic.app.zjtv.model;

import com.magic.app.zjtv.entities.UserCouponEntity;
import com.magic.commons.utils.DateUtils;

public class UserCoupon extends UserCouponEntity {
	private Coupon coupon;
	
	
	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public String getUpdateTimeStr(){
        return DateUtils.dateToInputStrAppendTime(this.getUpdateTime());
    }
	
	public String getCreateDateStr(){
        return DateUtils.dateToInputStrAppendTime(this.getCreateDate());
    }
}
