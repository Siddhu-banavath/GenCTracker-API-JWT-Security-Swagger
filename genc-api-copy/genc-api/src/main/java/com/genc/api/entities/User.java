package com.genc.api.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    
    private String asso_id;

    private String asso_name;

    private String proj_id;

    private String proj_name;

    private String cust_name;

    private String skill;

    private String asso_city;

    private String hcm_name;

    private String mentor_name;

    private String current_status;

	public User(int id, String asso_id, String asso_name, String proj_id, String proj_name, String cust_name,
			String skill, String asso_city, String hcm_name, String mentor_name, String current_status) {
		super();
		this.id = id;
		this.asso_id = asso_id;
		this.asso_name = asso_name;
		this.proj_id = proj_id;
		this.proj_name = proj_name;
		this.cust_name = cust_name;
		this.skill = skill;
		this.asso_city = asso_city;
		this.hcm_name = hcm_name;
		this.mentor_name = mentor_name;
		this.current_status = current_status;
	}
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAsso_id() {
		return asso_id;
	}

	public void setAsso_id(String asso_id) {
		this.asso_id = asso_id;
	}

	public String getAsso_name() {
		return asso_name;
	}

	public void setAsso_name(String asso_name) {
		this.asso_name = asso_name;
	}

	public String getProj_id() {
		return proj_id;
	}

	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}

	public String getProj_name() {
		return proj_name;
	}

	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getAsso_city() {
		return asso_city;
	}

	public void setAsso_city(String asso_city) {
		this.asso_city = asso_city;
	}

	public String getHcm_name() {
		return hcm_name;
	}

	public void setHcm_name(String hcm_name) {
		this.hcm_name = hcm_name;
	}

	public String getMentor_name() {
		return mentor_name;
	}

	public void setMentor_name(String mentor_name) {
		this.mentor_name = mentor_name;
	}

	public String getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", asso_id=" + asso_id + ", asso_name=" + asso_name + ", proj_id=" + proj_id
				+ ", proj_name=" + proj_name + ", cust_name=" + cust_name + ", skill=" + skill + ", asso_city="
				+ asso_city + ", hcm_name=" + hcm_name + ", mentor_name=" + mentor_name + ", current_status="
				+ current_status + "]";
	}
    
    
    

}