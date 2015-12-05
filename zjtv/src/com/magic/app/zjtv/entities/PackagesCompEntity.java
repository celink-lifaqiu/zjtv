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
@SqlResultSetMapping(name="PackagesCompResult",
        entities={
                @EntityResult(entityClass = PackagesCompEntity.class,
                        fields = {  //数据库字段和对象属性的映射
                                @FieldResult(name = "id", column="id"),
                                @FieldResult(name = "packageTypeId", column = "packageTypeId"),
                                @FieldResult(name = "packageType", column = "packageType"),
                                @FieldResult(name = "packageServiceName", column = "packageServiceName"),
                                @FieldResult(name = "packageServicePrice", column = "packageServicePrice"),
                                @FieldResult(name = "packageServiceDesc", column = "packageServiceDesc"),
                                @FieldResult(name = "packageServiceIcon", column = "packageServiceIcon")
                        }
                )
        })
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "findAllPackagesCompQuery",
                resultSetMapping = "PackagesCompResult",
                query = "SELECT p.id,p.packageTypeId,pt.type as packageType,p.packageServiceName,p.packageServicePrice,p.packageServiceDesc,p.packageServiceIcon \n" +
                		"from package_tb as p, packagetype_tb as pt \n" +
                		"where p.packageTypeId=pt.id \n" +
                		"ORDER BY packageTypeId"
        )
})
public class PackagesCompEntity {
    private Integer id;
    private Integer packageTypeId;
    private String packageType;
    private String packageServiceName;
    private Float packageServicePrice;
    private String packageServiceDesc;
    private String packageServiceIcon;

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getPackageTypeId() {
		return packageTypeId;
	}

	public void setPackageTypeId(Integer packageTypeId) {
		this.packageTypeId = packageTypeId;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getPackageServiceIcon() {
		return packageServiceIcon;
	}

	public void setPackageServiceIcon(String packageServiceIcon) {
		this.packageServiceIcon = packageServiceIcon;
	}

	public String getPackageServiceName() {
		return packageServiceName;
	}

	public void setPackageServiceName(String packageServiceName) {
		this.packageServiceName = packageServiceName;
	}

	public Float getPackageServicePrice() {
		return packageServicePrice;
	}

	public void setPackageServicePrice(Float packageServicePrice) {
		this.packageServicePrice = packageServicePrice;
	}

	public String getPackageServiceDesc() {
		return packageServiceDesc;
	}

	public void setPackageServiceDesc(String packageServiceDesc) {
		this.packageServiceDesc = packageServiceDesc;
	}
    
}
