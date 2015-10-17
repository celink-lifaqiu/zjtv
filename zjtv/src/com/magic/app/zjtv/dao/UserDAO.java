package com.magic.app.zjtv.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.UserEntity;

public interface UserDAO extends CrudRepository<UserEntity, Integer>{

	public UserEntity findByAccount(String account);
	public UserEntity findByNickName(String nickName);
	
	@Query(value="SELECT nickName FROM user_tb WHERE id=?1", nativeQuery = true)
	public String findUserNickName(Integer userId);
}
