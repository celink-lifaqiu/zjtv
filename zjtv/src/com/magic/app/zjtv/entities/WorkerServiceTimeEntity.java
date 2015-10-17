package com.magic.app.zjtv.entities;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "workerservicetime_tb", schema = "", catalog = "buddy_db")
public class WorkerServiceTimeEntity {
    private String workerServiceTime;
    private Integer workerId;
    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Basic
    @Column(name = "workerServiceTime")
	public String getWorkerServiceTime() {
		return workerServiceTime;
	}

	public void setWorkerServiceTime(String workerServiceTime) {
		this.workerServiceTime = workerServiceTime;
	}
	@Basic
    @Column(name = "workerId")
	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

    

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkerServiceTimeEntity that = (WorkerServiceTimeEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (workerServiceTime != null ? !workerServiceTime.equals(that.workerServiceTime) : that.workerServiceTime != null) return false;
        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null) return false;
       return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (workerServiceTime != null ? workerServiceTime.hashCode() : 0);
        result = 31 * result + (workerId != null ? workerId.hashCode() : 0);
        return result;
    }
}
