package com.magic.app.zjtv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.PackageDAO;
import com.magic.app.zjtv.dao.PackageTypeDAO;
import com.magic.app.zjtv.dao.PackagesCompDAO;
import com.magic.app.zjtv.entities.PackageTypeEntity;
import com.magic.app.zjtv.entities.PackagesCompEntity;
import com.magic.app.zjtv.entities.PackagesEntity;
import com.magic.app.zjtv.entities.UserEntity;
import com.magic.app.zjtv.model.PackageType;
import com.magic.app.zjtv.model.Packages;
import com.magic.app.zjtv.model.PackagesComp;
import com.magic.app.zjtv.model.User;
import com.magic.commons.utils.BeanUtils;

@Transactional
@Service  //加入这个注解后，Spring会自动加入这个类到Spring上下文中，可被其他类注入
public class PackageService {
	@Autowired
	PackageDAO packageDAO;

	@Autowired
	PackageTypeDAO packageTypeDAO;
	
	@Autowired
	PackagesCompDAO packagesCompDAO;

	public List<PackageType> findAllPackageTypes() {

		List<PackageTypeEntity> entities = (List<PackageTypeEntity>) packageTypeDAO.findAll();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, PackageType.class);
		}
		return null;
	}

	public PackageType findPackageTypeByPackageTypeId(Integer packageTypeId) {
		PackageTypeEntity entity = packageTypeDAO.findOne(packageTypeId);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, PackageType.class);
	}

	public void deletePackageType(Integer packageTypeId) {
		packageTypeDAO.delete(packageTypeId);
	}

	public PackageType saveOrUpdatePackageType(PackageType packageType) {

		Integer id = packageType.getId();
		PackageTypeEntity entity = new PackageTypeEntity();
        
        if (id != null) {
            entity = packageTypeDAO.findOne(id);
        }
        entity.setType(packageType.getType());
		entity = packageTypeDAO.save(entity);
		
		return BeanUtils.convertEntityToModel(entity, PackageType.class);
	}

	public void deletePackages(Integer id) {
		packageDAO.delete(id);
	}

	public Packages saveOrUpdatePackages(Packages packages) {
		Integer id = packages.getId();
		PackagesEntity entity = new PackagesEntity();
        
        if (id != null) {
            entity = packageDAO.findOne(id);
        }
        entity.setPackageServiceDesc(packages.getPackageServiceDesc());
        entity.setPackageServiceName(packages.getPackageServiceName());
        entity.setPackageServicePrice(packages.getPackageServicePrice());
        entity.setPackageTypeId(packages.getPackageTypeId());
		entity = packageDAO.save(entity);
		
		return BeanUtils.convertEntityToModel(entity, Packages.class);
	}

	public Packages findPackagesByPackageId(Integer id) {
		PackagesEntity  entity = packageDAO.findOne(id);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Packages.class);
	}

	public List<PackagesComp> findAllPackagesComp() {
		List<PackagesCompEntity> entities =  packagesCompDAO.findAllPackagesComp();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, PackagesComp.class);
		}
		return null;
	}
	

}
