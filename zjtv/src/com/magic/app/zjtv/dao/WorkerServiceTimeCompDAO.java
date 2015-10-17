package com.magic.app.zjtv.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magic.app.zjtv.entities.WorkerServiceTimeCompEntity;

public interface WorkerServiceTimeCompDAO extends CrudRepository<WorkerServiceTimeCompEntity, Integer>{

	@Query(name = "findAllWorkerServiceTimeCompQuery", nativeQuery = true)
	public List<WorkerServiceTimeCompEntity> findAllWorkerServiceTimeComp();
}
