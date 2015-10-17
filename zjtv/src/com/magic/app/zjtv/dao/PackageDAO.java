package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.PackagesEntity;

public interface PackageDAO extends CrudRepository<PackagesEntity, Integer>{
	public List<PackagesEntity> findByPackageTypeId(Integer packageTypeId);
	
	
	@Query(value="SELECT * from package_tb ORDER BY packageTypeId", nativeQuery = true)
	public List<PackagesEntity> findAllPackages();

}
