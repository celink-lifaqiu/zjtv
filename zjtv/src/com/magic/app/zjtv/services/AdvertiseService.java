package com.magic.app.zjtv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.AdvertiseDAO;
import com.magic.app.zjtv.entities.AdvertiseEntity;
import com.magic.app.zjtv.model.Advertise;
import com.magic.commons.utils.BeanUtils;
import com.magic.qiniu.QiniuHelper;

@Transactional
@Service  //加入这个注解后，Spring会自动加入这个类到Spring上下文中，可被其他类注入
public class AdvertiseService {
	@Autowired
	AdvertiseDAO advertiseDAO;


	public List<Advertise> getAdvertises(String type){
		List<AdvertiseEntity> activities = advertiseDAO.findByType(type);
		return BeanUtils.convertEntityToModelList(activities, Advertise.class);
	}


	public Advertise findAdvertiseById(Integer id) {
		AdvertiseEntity entity = advertiseDAO.findOne(id);
		return entity==null?null:BeanUtils.convertEntityToModel(entity, Advertise.class);
	}


	public void saveOrUpdateAdvertise(Advertise advertise) {
		Integer id = advertise.getId();
		AdvertiseEntity entity = null;
		if(id==null){
			entity = new AdvertiseEntity();
		}else{
			entity = advertiseDAO.findOne(id);
		}
		
		String imageHash = advertise.getIconHash();
		String imageUrl = entity.getImage();
		if (org.apache.commons.lang3.StringUtils.isEmpty(imageHash)) {
            entity.setImage(imageUrl);
        }else{
            entity.setImage(QiniuHelper.QINIU_IMAGE_HOST + imageHash);
        }
		entity.setTitle(advertise.getTitle());
		entity.setType("mainTop");
		
		advertiseDAO.save(entity);
	}


	public List<Advertise> getAdvertises() {
		List<AdvertiseEntity> entities = (List<AdvertiseEntity>)advertiseDAO.findAll();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, Advertise.class);
		}
		return null;
	}



}
