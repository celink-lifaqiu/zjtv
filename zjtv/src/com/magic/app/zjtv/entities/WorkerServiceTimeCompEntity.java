package com.magic.app.zjtv.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;

/**
 * Created by YinJianFeng on 14-5-8.
 */
@Entity
@SqlResultSetMapping(name="WorkerServiceTimeCompResult",
        entities={
                @EntityResult(entityClass = WorkerServiceTimeCompEntity.class,
                        fields = {  //数据库字段和对象属性的映射
                                @FieldResult(name = "id", column="id"),
                                @FieldResult(name = "workerId", column = "workerId"),
                                @FieldResult(name = "workerName", column = "workerName"),
                                @FieldResult(name = "workerServiceTime", column = "workerServiceTime")
                        }
                )
        })
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findAllWorkerServiceTimeCompQuery",
                resultSetMapping = "WorkerServiceTimeCompResult",
                query = "SELECT ws.id,ws.workerId,w.`name` as workerName,ws.workerServiceTime \n" +
                		"from workerservicetime_tb ws,worker_tb w \n" +
                		"where ws.workerId=w.id \n" +
                		"ORDER BY ws.workerId"
        )
})
public class WorkerServiceTimeCompEntity {
    private Integer id;
    private Integer workerId;
    private String workerName;
    private String workerServiceTime;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getWorkerServiceTime() {
		return workerServiceTime;
	}

	public void setWorkerServiceTime(String workerServiceTime) {
		this.workerServiceTime = workerServiceTime;
	}
    
}
