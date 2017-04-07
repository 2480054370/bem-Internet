package com.bemInternet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PartyContact {
	@Id
	@GeneratedValue
	private Integer id;
	private String phone;
	//国内email
	private String email;
	//公用email
	private String globalemail;
	private String address;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGlobalemail() {
		return globalemail;
	}
	public void setGlobalemail(String globalemail) {
		this.globalemail = globalemail;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
