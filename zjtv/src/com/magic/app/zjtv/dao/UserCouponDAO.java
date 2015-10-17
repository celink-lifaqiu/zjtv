package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.UserCouponEntity;
import com.magic.app.zjtv.model.UserCoupon;

public interface UserCouponDAO extends CrudRepository<UserCouponEntity, Integer>{

	public List<UserCouponEntity> findByUserIdAndIsUsed(Integer userId, Integer isUsed);
}
