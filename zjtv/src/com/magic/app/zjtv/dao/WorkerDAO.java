package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.WorkerEntity;
import com.magic.app.zjtv.entities.WorkerServiceTimeEntity;

public interface WorkerDAO extends CrudRepository<WorkerEntity, Integer>{
	@Query(value="SELECT * from workerservicetime_tb ORDER BY workerId", nativeQuery = true)
	public List<WorkerServiceTimeEntity> findWorkerServiceTimes();

}
