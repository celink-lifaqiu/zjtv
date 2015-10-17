package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.entities.AddressEntity;

public interface AddressDAO extends CrudRepository<AddressEntity, Integer>{
	public List<AddressEntity> findByUserId(Integer userId);
	@Modifying
    @Transactional
    @Query(value = "UPDATE address_tb SET idDefaultServiceAddress=0 WHERE userId=?1", nativeQuery = true)
    public void setAddressIdDefaultServiceAddressToZero(Integer userId);
}
