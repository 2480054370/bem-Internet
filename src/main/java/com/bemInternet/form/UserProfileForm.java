package com.bemInternet.form;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserProfileForm {
	@Pattern(regexp="^1[3|5|8]{1}[0-9]{9}$")
	private String phone;
	@NotEmpty(message = "NotEmpty")
	@Email(message = "*Please provide a valid Email")
	private String email;
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
	
}
