package com.magic.app.zjtv.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "address_tb", schema = "", catalog = "buddy_db")
public class AddressEntity {
    private Integer userId;
    private String reservation;
    private String phone;
    private String districtInformation;
    private String address;
    private Integer idDefaultServiceAddress;
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
    @Column(name = "reservation")
	public String getReservation() {
		return reservation;
	}

	

	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	@Basic
    @Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Basic
    @Column(name = "districtInformation")
	public String getDistrictInformation() {
		return districtInformation;
	}

	public void setDistrictInformation(String districtInformation) {
		this.districtInformation = districtInformation;
	}
	@Basic
    @Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Basic
    @Column(name = "idDefaultServiceAddress")
	public Integer getIdDefaultServiceAddress() {
		return idDefaultServiceAddress;
	}

	public void setIdDefaultServiceAddress(Integer idDefaultServiceAddress) {
		this.idDefaultServiceAddress = idDefaultServiceAddress;
	}

    
	@Basic
    @Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (reservation != null ? !reservation.equals(that.reservation) : that.reservation != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (districtInformation != null ? !districtInformation.equals(that.districtInformation) : that.districtInformation != null) return false;
        if (idDefaultServiceAddress != null ? !idDefaultServiceAddress.equals(that.idDefaultServiceAddress) : that.idDefaultServiceAddress != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (districtInformation != null ? districtInformation.hashCode() : 0);
        result = 31 * result + (reservation != null ? reservation.hashCode() : 0);
        result = 31 * result + (idDefaultServiceAddress != null ? idDefaultServiceAddress.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
