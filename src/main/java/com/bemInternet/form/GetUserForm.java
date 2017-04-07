package com.bemInternet.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class GetUserForm {
	private String account;
	private String pwd;
	private String phone;
	private String email;
	private String address;
	private String name;
	private String birth;
	private String hobby;
	private String sign;
	private String sex;
	private String new_password;
	private String old_password;
	//register
	private String remail;
	private String reaccount;
	private String repwd;
	private String rerepwd;
	//findpwd
	private String findemail;
	private String vercode;
	//findpwdupdate
	private String cuppwd;
	private String cuprepwd;
	private String upaccount;
	
	//userconfig
	private String cfphonenumber;
	private String cfaddress;
	private String cfbirthday;
	private String cfsex;
	private String cfhobby;
	private String cfinfo;
	private String cfphoto;
	private String cfreport;
	private String cfmail;
	

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNew_password() {
		return new_password;
	}
	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}
	public String getOld_password() {
		return old_password;
	}
	public void setOld_password(String old_password) {
		this.old_password = old_password;
	}
	
	
	//register
	public String getRemail() {
		return remail;
	}
	public void setRemail(String remail) {
		this.remail = remail;
	}
	public String getReaccount() {
		return reaccount;
	}
	public void setReaccount(String reaccount) {
		this.reaccount = reaccount;
	}
	public String getRepwd() {
		return repwd;
	}
	public void setRepwd(String repwd) {
		this.repwd = repwd;
	}
	public String getRerepwd() {
		return rerepwd;
	}
	public void setRerepwd(String rerepwd) {
		this.rerepwd = rerepwd;
	}
	
	
	//findpwd
	public String getFindemail() {
		return findemail;
	}
	public void setFindemail(String findemail) {
		this.findemail = findemail;
	}
	public String getVercode() {
		return vercode;
	}
	public void setVercode(String vercode) {
		this.vercode = vercode;
	}

	
	
	//findpwdupdate
	public String getCuppwd() {
		return cuppwd;
	}
	public void setCuppwd(String cuppwd) {
		this.cuppwd = cuppwd;
	}
	public String getCuprepwd() {
		return cuprepwd;
	}
	public void setCuprepwd(String cuprepwd) {
		this.cuprepwd = cuprepwd;
	}
	public String getUpaccount() {
		return upaccount;
	}
	public void setUpaccount(String upaccount) {
		this.upaccount = upaccount;
	}
	
	
	//userconfig
	public String getCfphonenumber() {
		return cfphonenumber;
	}
	public void setCfphonenumber(String cfphonenumber) {
		this.cfphonenumber = cfphonenumber;
	}
	public String getCfaddress() {
		return cfaddress;
	}
	public void setCfaddress(String cfaddress) {
		this.cfaddress = cfaddress;
	}
	public String getCfbirthday() {
		return cfbirthday;
	}
	public void setCfbirthday(String cfbirthday) {
		this.cfbirthday = cfbirthday;
	}
	public String getCfsex() {
		return cfsex;
	}
	public void setCfsex(String cfsex) {
		this.cfsex = cfsex;
	}
	public String getCfhobby() {
		return cfhobby;
	}
	public void setCfhobby(String cfhobby) {
		this.cfhobby = cfhobby;
	}
	public String getCfinfo() {
		return cfinfo;
	}
	public void setCfinfo(String cfinfo) {
		this.cfinfo = cfinfo;
	}
	public String getCfphoto() {
		return cfphoto;
	}
	public void setCfphoto(String cfphoto) {
		this.cfphoto = cfphoto;
	}
	public String getCfreport() {
		return cfreport;
	}
	public void setCfreport(String cfreport) {
		this.cfreport = cfreport;
	}
	public String getCfmail() {
		return cfmail;
	}
	public void setCfmail(String cfmail) {
		this.cfmail = cfmail;
	}
	
	


	
	
	
}
