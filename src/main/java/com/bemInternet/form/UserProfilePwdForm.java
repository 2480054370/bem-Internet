package com.bemInternet.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserProfilePwdForm {
	@NotEmpty(message = "NotEmpty")
	private String oldpwd;
	@NotEmpty(message = "NotEmpty")
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	private String newpwd;
	@NotEmpty(message = "NotEmpty")
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	private String confirmpwd;
	public String getOldpwd() {
		return oldpwd;
	}
	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getConfirmpwd() {
		return confirmpwd;
	}
	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
	
}
