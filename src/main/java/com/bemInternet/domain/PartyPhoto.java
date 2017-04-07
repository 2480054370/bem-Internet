package com.bemInternet.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class PartyPhoto {
	@Id
	private String id;
	//图片地址
	private String address;
	//大小
	@Column(name = "`size`")
	private long size;
	//初始名字
	private String name;
	//格式
	private String format;
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JsonBackReference
	private PartyArticle partyArticle;
	
	@JsonBackReference
	public PartyArticle getPartyArticle() {
		return partyArticle;
	}
	public void setPartyArticle(PartyArticle partyArticle) {
		this.partyArticle = partyArticle;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
