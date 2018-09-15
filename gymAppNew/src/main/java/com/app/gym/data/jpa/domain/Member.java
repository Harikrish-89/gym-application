package com.app.gym.data.jpa.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "MEMBERS")
public class Member implements Serializable {

	@Id
	// @SequenceGenerator(name="mem_seq",
	// sequenceName="MEMBER_SEQ",allocationSize=1,initialValue=1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String Address;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PACKAGE_TYPE")
	private String packageType;

	@Column(name = "CURRENT_PACKAGE_RECHARGE_DATE")
	private Date currPackRechargeDate;

	@Column(name = "CURRENT_PACKAGE_END_DATE")
	private Date currPackageEndDate;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "PHONE")
	private Long phone;
	/*
	 * @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL,mappedBy="member")
	 * 
	 * @JsonManagedReference private Set<MemberHistory> memberHistorySet;
	 * 
	 * public Set<MemberHistory> getMemberHistorySet() { return memberHistorySet; }
	 * 
	 * public void setMemberHistorySet(Set<MemberHistory> memberHistorySet) {
	 * this.memberHistorySet = memberHistorySet; }
	 */

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public Date getCurrPackRechargeDate() {
		return currPackRechargeDate;
	}

	public void setCurrPackRechargeDate(Date currPackRechargeDate) {
		this.currPackRechargeDate = currPackRechargeDate;
	}

	public Date getCurrPackageEndDate() {
		return currPackageEndDate;
	}

	public void setCurrPackageEndDate(Date currPackageEndDate) {
		this.currPackageEndDate = currPackageEndDate;
	}

}
