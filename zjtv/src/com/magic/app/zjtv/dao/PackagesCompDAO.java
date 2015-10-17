package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.PackagesCompEntity;

public interface PackagesCompDAO extends CrudRepository<PackagesCompEntity, Integer>{

	@Query(name = "findAllPackagesCompQuery", nativeQuery = true)
	public List<PackagesCompEntity> findAllPackagesComp();
}
