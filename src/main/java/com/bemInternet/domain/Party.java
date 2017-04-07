package com.bemInternet.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Party {
	@Id
	private String id;
	//标题
	private String title;
	//次数
	private String num;
	//主题
	private String topic;
	//联系方式
	@OneToOne
	@JoinColumn(name ="contact_id")
	private PartyContact contact;
	//图片集
	public void setId(String id) {
		this.id = id;	
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public PartyContact getContact() {
		return contact;
	}
	public void setContact(PartyContact contact) {
		this.contact = contact;
	}
	
	
	
}
