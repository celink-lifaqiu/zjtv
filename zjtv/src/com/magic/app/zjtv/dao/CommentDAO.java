package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.CommentEntity;

public interface CommentDAO extends CrudRepository<CommentEntity, Integer>{

	public CommentEntity findByUserIdAndOrderId(Integer userId, Integer orderId);

	
	@Query(value="SELECT c.* FROM comment_tb c " +
			" WHERE c.orderId IN(SELECT ops.orderId FROM order_package_service ops " +
			" WHERE ops.packageServiceId IN(SELECT p.id FROM package_tb p " +
			" WHERE p.packageTypeId=?1))", nativeQuery = true)
	public List<CommentEntity> findAllCommentsByPackageTypeId(Integer packageTypeId);

	@Query(value="SELECT c.* FROM comment_tb c " +
			" WHERE c.orderId IN(SELECT ops.orderId FROM order_package_service ops " +
			" WHERE ops.packageServiceId IN(SELECT p.id FROM package_tb p " +
			" WHERE p.packageTypeId=?1)) ORDER BY c.id DESC LIMIT ?2,?3", nativeQuery = true)
	public List<CommentEntity> getCommentByPackageType(Integer packageType,
			int startNum, int limitNum);
}
