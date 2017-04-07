package com.bemInternet.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "class_photo")
public class ClassPhoto {
	@Id
	private String id;
	//标题
	private String title;
	//备注
	private String remarks;
	//发起时间
	private String p_time;
	//图片地址
	private String address;
	//大小
	private Integer size;
	//初始名字
	private String name;
	//sort
	private long sort;
	//发布用户
	private String user_name;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonBackReference
	private User user;
	
	public ClassPhoto(){
		
	}
	
	public ClassPhoto(String id, String title, String remarks, String p_time, String address, Integer size,
			String name,long sort) {
		super();
		this.id = id;
		this.title = title;
		this.remarks = remarks;
		this.p_time = p_time;
		this.address = address;
		this.size = size;
		this.name = name;
		this.sort = sort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getP_time() {
		return p_time;
	}
	public void setP_time(String p_time) {
		this.p_time = p_time;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public long getSort() {
		return sort;
	}

	public void setSort(long sort) {
		this.sort = sort;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
}
