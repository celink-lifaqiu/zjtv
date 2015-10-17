package com.magic.app.zjtv.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magic.app.zjtv.dao.WorkerDAO;
import com.magic.app.zjtv.dao.WorkerServiceTimeCompDAO;
import com.magic.app.zjtv.dao.WorkerServiceTimeDAO;
import com.magic.app.zjtv.entities.PackageTypeEntity;
import com.magic.app.zjtv.entities.WorkerEntity;
import com.magic.app.zjtv.entities.WorkerServiceTimeCompEntity;
import com.magic.app.zjtv.entities.WorkerServiceTimeEntity;
import com.magic.app.zjtv.model.PackageType;
import com.magic.app.zjtv.model.Worker;
import com.magic.app.zjtv.model.WorkerServiceTime;
import com.magic.app.zjtv.model.WorkerServiceTimeComp;
import com.magic.commons.utils.BeanUtils;
import com.magic.commons.utils.DateUtils;

@Transactional
@Service  //加入这个注解后，Spring会自动加入这个类到Spring上下文中，可被其他类注入
public class WorkerService {
	@Autowired
	WorkerDAO workerDAO;

	@Autowired
	WorkerServiceTimeDAO workerServiceTimeDAO;
	
	@Autowired
	WorkerServiceTimeCompDAO workerServiceTimeCompDAO;

	public void deleteWorker(Integer id) {
		workerDAO.delete(id);
	}

	public Worker saveOrUpdateWorker(Worker worker) {
		Integer id = worker.getId();
		WorkerEntity entity = new WorkerEntity();
        
        if (id != null) {
            entity = workerDAO.findOne(id);
        }
        entity.setAddress(worker.getAddress());
        entity.setGender(worker.getGender());
        entity.setIcon(worker.getIcon());
        entity.setIdCard(worker.getIdCard());
        entity.setName(worker.getName());
        entity.setPhone(worker.getPhone());
        entity.setRegistDate(DateUtils.getDateTimestamp());
		entity = workerDAO.save(entity);
		
		return BeanUtils.convertEntityToModel(entity, Worker.class);
	}

	public Worker findWorkerByWorkerId(Integer id) {
		WorkerEntity entity = workerDAO.findOne(id);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, Worker.class);
	}

	public List<Worker> findAllWorkers() {
		List<WorkerEntity> entities = (List<WorkerEntity>) workerDAO.findAll();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, Worker.class);
		}
		return null;
	}

	public List<WorkerServiceTime> findAllWorkerServiceTimes() {
		List<WorkerServiceTimeEntity> entities =  workerDAO.findWorkerServiceTimes();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, WorkerServiceTime.class);
		}
		return null;
	}
	
	public List<WorkerServiceTimeComp> findAllWorkerServiceTimeComp() {
		List<WorkerServiceTimeCompEntity> entities =  workerServiceTimeCompDAO.findAllWorkerServiceTimeComp();
		if(entities != null && entities.size() > 0){
			return BeanUtils.convertEntityToModelList(entities, WorkerServiceTimeComp.class);
		}
		return null;
	}

	public WorkerServiceTime findWorkerServiceTimeById(Integer id) {
		WorkerServiceTimeEntity entity = workerServiceTimeDAO.findOne(id);
		return entity == null ? null : BeanUtils.convertEntityToModel(entity, WorkerServiceTime.class);
	}

	public WorkerServiceTime saveOrUpdateWorkerServiceTime(
			WorkerServiceTime workerServiceTime) {
		Integer id = workerServiceTime.getId();
		WorkerServiceTimeEntity entity = new WorkerServiceTimeEntity();
        
        if (id != null) {
            entity = workerServiceTimeDAO.findOne(id);
        }
        entity.setWorkerId(workerServiceTime.getWorkerId());
        entity.setWorkerServiceTime(workerServiceTime.getWorkerServiceTime());
		entity = workerServiceTimeDAO.save(entity);
		
		return BeanUtils.convertEntityToModel(entity, WorkerServiceTime.class);
	}

	public void deleteWorkerServiceTime(Integer id) {
		workerServiceTimeDAO.delete(id);
	}
	

}
