package com.magic.app.zjtv.entities;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "order_tb", schema = "", catalog = "buddy_db")
public class OrderEntity {
    private Integer userId;
    private Integer workerId;
    private String orderName;
    private Integer serviceAddressId;
    private String serviceTime;
    private String additionalRequirements; 
    private Integer userCouponId;
    private Float sumPrice;
    private Integer state;
    private Integer isComment;
    private Timestamp updateTime;
    private Timestamp createDate;
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
    @Column(name = "userId")
    public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Basic
    @Column(name = "workerId")
	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}
	@Basic
    @Column(name = "orderName")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	@Basic
    @Column(name = "serviceAddressId")
	public Integer getServiceAddressId() {
		return serviceAddressId;
	}

	public void setServiceAddressId(Integer serviceAddressId) {
		this.serviceAddressId = serviceAddressId;
	}
	@Basic
    @Column(name = "serviceTime")
	public String getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	@Basic
    @Column(name = "additionalRequirements")
	public String getAdditionalRequirements() {
		return additionalRequirements;
	}

	public void setAdditionalRequirements(String additionalRequirements) {
		this.additionalRequirements = additionalRequirements;
	}
	@Basic
    @Column(name = "userCouponId")
	public Integer getUserCouponId() {
		return userCouponId;
	}

	public void setUserCouponId(Integer userCouponId) {
		this.userCouponId = userCouponId;
	}
	@Basic
    @Column(name = "sumPrice")
	public Float getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Float sumPrice) {
		this.sumPrice = sumPrice;
	}
	@Basic
    @Column(name = "state")
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	@Basic
    @Column(name = "isComment")
	public Integer getIsComment() {
		return isComment;
	}

	public void setIsComment(Integer isComment) {
		this.isComment = isComment;
	}
	@Basic
    @Column(name = "updateTime")
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Basic
    @Column(name = "createDate")
	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
    

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (workerId != null ? !workerId.equals(that.workerId) : that.workerId != null) return false;
        if (orderName != null ? !orderName.equals(that.orderName) : that.orderName != null) return false;
        if (serviceAddressId != null ? !serviceAddressId.equals(that.serviceAddressId) : that.serviceAddressId != null) return false;
        if (serviceTime != null ? !serviceTime.equals(that.serviceTime) : that.serviceTime != null) return false;
        if (additionalRequirements != null ? !additionalRequirements.equals(that.additionalRequirements) : that.additionalRequirements != null) return false;
        if (userCouponId != null ? !userCouponId.equals(that.userCouponId) : that.userCouponId != null) return false;
        if (sumPrice != null ? !sumPrice.equals(that.sumPrice) : that.sumPrice != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (isComment != null ? !isComment.equals(that.isComment) : that.isComment != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        return true;
    }


	@Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (workerId != null ? workerId.hashCode() : 0);
        result = 31 * result + (orderName != null ? orderName.hashCode() : 0);
        result = 31 * result + (serviceAddressId != null ? serviceAddressId.hashCode() : 0);
        result = 31 * result + (serviceTime != null ? serviceTime.hashCode() : 0);
        result = 31 * result + (additionalRequirements != null ? additionalRequirements.hashCode() : 0);
        result = 31 * result + (userCouponId != null ? userCouponId.hashCode() : 0);
        result = 31 * result + (sumPrice != null ? sumPrice.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (isComment != null ? isComment.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
