package com.bemInternet.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVercodeForm {
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")	
	private String findemail;
	@NotEmpty(message = "not empty")
	private String vercode;
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
	
	
}
