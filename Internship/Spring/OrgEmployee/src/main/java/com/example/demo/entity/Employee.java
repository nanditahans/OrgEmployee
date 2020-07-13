package com.example.demo.entity;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="employee2")
public class Employee {
	
	@Id 
	@Column(name="empId",insertable = false, unique = true, nullable = false)
	private UUID empId;
	
	@Column(name="empName",nullable=false)
	private String empName;
	
	@Column(name="emailId",nullable=false,unique=true)
	private String emailId;
	
	@Column(name="active",nullable=false)
	private boolean active;

	@Column(name="currentStatus",nullable =false)
	private String currentStatus;

	@ManyToOne
    @JoinColumn(name="org_id",referencedColumnName="orgId")
	private Org org;

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	
	public UUID getEmpId() {
		return empId;
	}

	public Employee() {
		this.empId=UUID.randomUUID();
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
}
