package com.magic.app.zjtv.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name = "role", schema = "", catalog = "buddy_db")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="role_code")
	private String roleCode;

	@Column(name="role_name")
	private String roleName;

	//bi-directional many-to-many association to Permission
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="role_permission"
		, joinColumns={
			@JoinColumn(name="role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="permission_id")
			}
		)
	private List<PermissionEntity> permissions;

	//bi-directional many-to-many association to UserAdmin
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="role_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="user_id")
			}
		)
	private List<UserAdminEntity> userAdmins;

	public RoleEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<PermissionEntity> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<PermissionEntity> permissions) {
		this.permissions = permissions;
	}

	public List<UserAdminEntity> getUserAdmins() {
		return this.userAdmins;
	}

	public void setUserAdmins(List<UserAdminEntity> userAdmins) {
		this.userAdmins = userAdmins;
	}

}