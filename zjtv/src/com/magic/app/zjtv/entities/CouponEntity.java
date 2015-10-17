package com.magic.app.zjtv.entities;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "coupon_tb", schema = "", catalog = "buddy_db")
public class CouponEntity {
    private String couponName;
    private String couponDesc;
    private String couponIcon;
    private Integer couponNum;
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
    @Column(name = "couponName")
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	@Basic
    @Column(name = "couponDesc")
	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	@Basic
    @Column(name = "couponIcon")
	public String getCouponIcon() {
		return couponIcon;
	}

	public void setCouponIcon(String couponIcon) {
		this.couponIcon = couponIcon;
	}
	@Basic
    @Column(name = "couponNum")
	public Integer getCouponNum() {
		return couponNum;
	}

	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}

    

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CouponEntity that = (CouponEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (couponName != null ? !couponName.equals(that.couponName) : that.couponName != null) return false;
        if (couponIcon != null ? !couponIcon.equals(that.couponIcon) : that.couponIcon != null) return false;
        if (couponNum != null ? !couponNum.equals(that.couponNum) : that.couponNum != null) return false;
        if (couponDesc != null ? !couponDesc.equals(that.couponDesc) : that.couponDesc != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (couponIcon != null ? couponIcon.hashCode() : 0);
        result = 31 * result + (couponName != null ? couponName.hashCode() : 0);
        result = 31 * result + (couponNum != null ? couponNum.hashCode() : 0);
        result = 31 * result + (couponDesc != null ? couponDesc.hashCode() : 0);
        return result;
    }
}
