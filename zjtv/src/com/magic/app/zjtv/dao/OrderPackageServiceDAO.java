package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.OrderPackageServiceEntity;

public interface OrderPackageServiceDAO extends CrudRepository<OrderPackageServiceEntity, Integer>{

	public List<OrderPackageServiceEntity> findByOrderId(Integer orderId) ;
}
