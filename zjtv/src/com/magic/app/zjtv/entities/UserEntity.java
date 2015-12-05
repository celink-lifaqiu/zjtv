package com.magic.app.zjtv.entities;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.*;

/**
 * Created by lifaqiu on 15-01-23.
 */
@Entity
@Table(name = "user_tb", schema = "", catalog = "buddy_db")
public class UserEntity {
    private Integer id;
    private String account;
    private String password;
    private String nickName;
    private String icon;
    private String email;
    private String address;
    private Long birthday;
    private Long pwdAnswer;
    private Timestamp registDate;
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
    @Column(name = "account")
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Basic
    @Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
    @Column(name = "nickName")
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Basic
    @Column(name = "icon")
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	@Basic
    @Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
    @Column(name = "birthday")
	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	@Basic
    @Column(name = "pwdAnswer")
	public Long getPwdAnswer() {
		return pwdAnswer;
	}

	public void setPwdAnswer(Long pwdAnswer) {
		this.pwdAnswer = pwdAnswer;
	}

	@Basic
    @Column(name = "registDate")
	public Timestamp getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Timestamp registDate) {
		this.registDate = registDate;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (pwdAnswer != null ? !pwdAnswer.equals(that.pwdAnswer) : that.pwdAnswer != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (registDate != null ? !registDate.equals(that.registDate) : that.registDate != null) return false;

        return true;
    }

    

	@Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pwdAnswer != null ? pwdAnswer.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (registDate != null ? registDate.hashCode() : 0);

        return result;
    }
}
