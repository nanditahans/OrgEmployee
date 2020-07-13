package com.example.demo.entity;



import java.util.UUID;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name="org")
public class Org {
	
	@Id 
	@GeneratedValue
	@Column(name="orgId", unique = true, nullable = false)
	private UUID orgId;
	
	@Column(name="orgName",nullable = false,unique=true)
	private String orgName;
	

	public Org() {
		this.orgId=UUID.randomUUID();
		
	}

	public UUID getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
