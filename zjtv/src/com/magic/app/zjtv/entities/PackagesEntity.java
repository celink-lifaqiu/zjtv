package com.magic.app.zjtv.entities;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "package_tb", schema = "", catalog = "buddy_db")
public class PackagesEntity {
    private String packageServiceName;
    private String packageServiceDesc;
    private Float packageServicePrice;
    private Integer packageTypeId;
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
    @Column(name = "packageServiceName")
	public String getPackageServiceName() {
		return packageServiceName;
	}

	public void setPackageServiceName(String packageServiceName) {
		this.packageServiceName = packageServiceName;
	}
	@Basic
    @Column(name = "packageServicePrice")
	public Float getPackageServicePrice() {
		return packageServicePrice;
	}

	public void setPackageServicePrice(Float packageServicePrice) {
		this.packageServicePrice = packageServicePrice;
	}
	@Basic
    @Column(name = "packageServiceDesc")
	public String getPackageServiceDesc() {
		return packageServiceDesc;
	}

	public void setPackageServiceDesc(String packageServiceDesc) {
		this.packageServiceDesc = packageServiceDesc;
	}
	@Basic
    @Column(name = "packageTypeId")
	public Integer getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

    

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PackagesEntity that = (PackagesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (packageServiceName != null ? !packageServiceName.equals(that.packageServiceName) : that.packageServiceName != null) return false;
        if (packageServiceDesc != null ? !packageServiceDesc.equals(that.packageServiceDesc) : that.packageServiceDesc != null) return false;
        if (packageTypeId != null ? !packageTypeId.equals(that.packageTypeId) : that.packageTypeId != null) return false;
        if (packageServicePrice != null ? !packageServicePrice.equals(that.packageServicePrice) : that.packageServicePrice != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (packageServiceDesc != null ? packageServiceDesc.hashCode() : 0);
        result = 31 * result + (packageServiceName != null ? packageServiceName.hashCode() : 0);
        result = 31 * result + (packageTypeId != null ? packageTypeId.hashCode() : 0);
        result = 31 * result + (packageServicePrice != null ? packageServicePrice.hashCode() : 0);
        return result;
    }
}
