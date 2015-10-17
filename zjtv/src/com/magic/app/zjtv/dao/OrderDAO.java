package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.OrderEntity;

public interface OrderDAO extends CrudRepository<OrderEntity, Integer>{
	@Query(value="SELECT * from order_tb ORDER BY createDate Desc", nativeQuery = true)
	public List<OrderEntity> getAllOrders();

	@Query(value="SELECT * from order_tb WHERE userId=?1 AND state==3 LIMIT ?2,?3", nativeQuery = true)
	public List<OrderEntity> findEndOrders(Integer userId, int startNum,
			int limitNum);
	
	@Query(value="SELECT * from order_tb WHERE userId=?1 AND state!=3 LIMIT ?2,?3", nativeQuery = true)
	public List<OrderEntity> findOrders(Integer userId, int startNum,
			int limitNum);
}
