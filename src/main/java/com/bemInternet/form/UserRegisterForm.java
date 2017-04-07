package com.bemInternet.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegisterForm {
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")	
	private String usermail;
	
	@NotEmpty(message = "*Please provide your studentld")
	@Length(min = 6, message = "too_little")
	private String studentld;
	
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	@NotEmpty(message = "*Please provide your password")
	private String new_password;
	
	@Length(min = 6, message = "*Your password must have at least 6 characters")
	@NotEmpty(message = "*Please provide your password")
	private String confirm_password;

	public String getUsermail() {
		return usermail;
	}

	public void setUsermail(String usermail) {
		this.usermail = usermail;
	}

	public String getStudentld() {
		return studentld;
	}

	public void setStudentld(String studentld) {
		this.studentld = studentld;
	}

	public String getNew_password() {
		return new_password;
	}

	public void setNew_password(String new_password) {
		this.new_password = new_password;
	}

	public String getConfirm_password() {
		return confirm_password;
	}

	public void setConfirm_password(String confirm_password) {
		this.confirm_password = confirm_password;
	}
	
	
}
