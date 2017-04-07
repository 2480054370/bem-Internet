package com.bemInternet.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserFindpwdForm {
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	@NotEmpty(message = "*Please provide your password")
	private String cuppwd;
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	@NotEmpty(message = "*Please provide your password")
	private String cuprepwd;
	private String upaccount;
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
	
	
}
