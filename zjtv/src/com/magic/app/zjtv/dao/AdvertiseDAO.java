package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.AdvertiseEntity;

public interface AdvertiseDAO extends CrudRepository<AdvertiseEntity, Integer>{

	public List<AdvertiseEntity> findByType(String type);
}
