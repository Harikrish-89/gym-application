package com.app.gym.data.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="MEMBER_DAILY_LOGIN")
public class MemberDailyLogin implements Serializable {
	
	@Id
	//@SequenceGenerator(name="mem_daily_login_seq", sequenceName="MEMBER_DAILY_LOGIN_SEQ",allocationSize=1,initialValue=1)
	@GeneratedValue()
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	@Column(name="LOGIN_TIME")
	private Date logInTime;
	
	@Column(name="LOGOUT_TIME")
	private Date logOutTime;
	
	@Column(name="LOGIN_DATE")
	private Date logInDate;
	
	public Date getLogInDate() {
		return logInDate;
	}

	public void setLogInDate(Date logInDate) {
		this.logInDate = logInDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getLogInTime() {
		return logInTime;
	}

	public void setLogInTime(Date logInTime) {
		this.logInTime = logInTime;
	}

	public Date getLogOutTime() {
		return logOutTime;
	}

	public void setLogOutTime(Date logOutTime) {
		this.logOutTime = logOutTime;
	}

	
	

}
