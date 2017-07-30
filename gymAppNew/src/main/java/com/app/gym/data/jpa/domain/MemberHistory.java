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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="MEMBER_HISTORY")
public class MemberHistory implements Serializable {
	
	@Id
	//@SequenceGenerator(name="mem_hist_seq", sequenceName="MEMBER_HIST_SEQ",allocationSize=1,initialValue=1)
	@GeneratedValue()
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch=FetchType.EAGER)	
	@JoinColumn(name="MEMBER_ID")
	//@JsonBackReference
	private Member member;
	
	@Column(name="PACKAGE_TYPE")
	private String packageType;
	
	@Column(name="PACKAGE_RECHARGE_DATE")
	private Date packReachDate;
	
	@Column(name="PACKAGE_END_DATE")
	private Date packageEndDate;
	
	@Column(name="RECHARGE_TYPE")
	private String rechargeType;

	public String getRechargeType() {
		return rechargeType;
	}

	public void setRechargeType(String rechargeType) {
		this.rechargeType = rechargeType;
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

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public Date getPackReachDate() {
		return packReachDate;
	}

	public void setPackReachDate(Date packReachDate) {
		this.packReachDate = packReachDate;
	}

	public Date getPackageEndDate() {
		return packageEndDate;
	}

	public void setPackageEndDate(Date packageEndDate) {
		this.packageEndDate = packageEndDate;
	}
	
	
	
	
	
}
