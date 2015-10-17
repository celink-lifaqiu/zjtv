package com.magic.app.zjtv.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "order_package_service", schema = "", catalog = "buddy_db")
public class OrderPackageServiceEntity {
    private Integer orderId;
    private Integer packageServiceId;
    private Integer cnt;
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
    @Column(name = "orderId")
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	@Basic
    @Column(name = "packageServiceId")
	public Integer getPackageServiceId() {
		return packageServiceId;
	}

	public void setPackageServiceId(Integer packageServiceId) {
		this.packageServiceId = packageServiceId;
	}
	@Basic
    @Column(name = "cnt")
	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPackageServiceEntity that = (OrderPackageServiceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (packageServiceId != null ? !packageServiceId.equals(that.packageServiceId) : that.packageServiceId != null) return false;
        if (cnt != null ? !cnt.equals(that.cnt) : that.cnt != null) return false;
        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return true;
    }

    

	@Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (packageServiceId != null ? packageServiceId.hashCode() : 0);
        result = 31 * result + (cnt != null ? cnt.hashCode() : 0);
        return result;
    }
}
