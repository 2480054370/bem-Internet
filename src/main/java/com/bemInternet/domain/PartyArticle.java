package com.bemInternet.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity(name = "party_article")
public class PartyArticle {
	@Id
	private String id;
	//组织者
	private String organizer;
	//发起时间
	private long ideatime;
	//标题
	//主题
	private String topic;
	//开始时间
	private String starttime;
	//结束时间
	private String endtime;
	//地址
	private String address;
	//消费金钱
	
	private Integer money;
	//备注
	private String note;
	@ManyToOne(cascade = CascadeType.DETACH)
	@JsonBackReference
	private User user;
	//图片集
	@OneToMany(mappedBy = "partyArticle",cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("size")
	@JsonManagedReference
	private List<PartyPhoto> photos;
	@JsonManagedReference
	public List<PartyPhoto> getPhotos() {
		return photos;
	}
	public void setPhotos(List<PartyPhoto> photos) {
		this.photos = photos;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public long getIdeatime() {
		return ideatime;
	}
	public void setIdeatime(long ideatime) {
		this.ideatime = ideatime;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getMoney() {
		return money;
	}
	public void setMoney(Integer money) {
		this.money = money;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	            
	
}
