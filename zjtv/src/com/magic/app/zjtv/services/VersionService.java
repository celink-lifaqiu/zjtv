package com.magic.app.zjtv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.VersionChangesDAO;
import com.magic.app.zjtv.dao.VersionDAO;
import com.magic.app.zjtv.entities.VersionChangesEntity;
import com.magic.app.zjtv.entities.VersionEntity;
import com.magic.app.zjtv.model.Version;
import com.magic.app.zjtv.model.VersionChanges;
import com.magic.commons.utils.BeanUtils;

@Transactional
@Service  //加入这个注解后，Spring会自动加入这个类到Spring上下文中，可被其他类注入
public class VersionService {
	@Autowired
	VersionDAO versionDAO;
	@Autowired
	VersionChangesDAO versionChangesDAO;
	
	
	public List<Version> getVersions() {
		List<VersionEntity> entities = (List<VersionEntity>)versionDAO.findAll();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, Version.class);
		}
		return null;
	}


	public Version findVersionById(Integer id) {
		VersionEntity entity = versionDAO.findOne(id);
		
		return BeanUtils.convertEntityToModel(entity, Version.class);
	}


	public Version saveOrUpdateVersion(Version version) {
		VersionEntity entity = versionDAO.findOne(version.getId());
		entity.setFilename(version.getFilename());
		entity.setPlatform(version.getPlatform());
		entity.setMajorVersion(version.getMajorVersion());
		entity.setMinorVersion(version.getMinorVersion());
		entity.setType(version.getType());
		entity.setRevisionVersion(version.getRevisionVersion());
		entity.setAppstoreDownloadurl(version.getAppstore_downloadurl());
		entity.setAppstoreOnline(version.getAppstore_online());
		entity.setAppstoreVersionCode(version.getAppstore_version_code());
		entity.setDownloadUrl(version.getDownloadUrl());
		entity.setVersionCode(version.getVersionCode());
		entity = versionDAO.save(entity);
		return BeanUtils.convertEntityToModel(entity, Version.class);
	}


	public List<VersionChanges> getVersionChanges() {
		List<VersionChangesEntity> entities = (List<VersionChangesEntity>)versionChangesDAO.findAll();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, VersionChanges.class);
		}
		return null;
	}


	public VersionChanges findVersionChangesById(Integer id) {
		VersionChangesEntity entity = versionChangesDAO.findOne(id);
		
		return BeanUtils.convertEntityToModel(entity, VersionChanges.class);
	}


	public void saveOrUpdateVersionChanges(VersionChanges versionChanges) {
		Integer id = versionChanges.getId();
		VersionChangesEntity entity = null;
		if(id != null){
			entity = versionChangesDAO.findOne(id);
		}else{
			entity = new VersionChangesEntity();
		}
		entity.setContent(versionChanges.getContent());
		entity.setPlatform(versionChanges.getPlatform());
		entity.setVersionCode(versionChanges.getVersionCode());
		versionChangesDAO.save(entity);
	}


	public void deleteVersionChanges(Integer id) {
		versionChangesDAO.delete(id);
	}



}
