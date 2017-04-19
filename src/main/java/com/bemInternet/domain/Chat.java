package com.bemInternet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "chat")
public class Chat {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // 主键自增Id
    private String inputname;
    private String outputname;
    private String message;
    private String state;
    private String sendtime;
    private String inputusername;
    private String outputusername;
    private String outputImg;

    public Chat() {
    }

    public Chat(String inputname, String outputname, String message) {
        this.inputname = inputname;
        this.outputname = outputname;
        this.message = message;
    }

	public String getInputname() {
		return inputname;
	}

	public void setInputname(String inputname) {
		this.inputname = inputname;
	}

	public String getOutputname() {
		return outputname;
	}

	public void setOutputname(String outputname) {
		this.outputname = outputname;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSendtime() {
		return sendtime;
	}

	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}

	public String getInputusername() {
		return inputusername;
	}

	public void setInputusername(String inputusername) {
		this.inputusername = inputusername;
	}

	public String getOutputusername() {
		return outputusername;
	}

	public void setOutputusername(String outputusername) {
		this.outputusername = outputusername;
	}

	public String getOutputImg() {
		return outputImg;
	}

	public void setOutputImg(String outputImg) {
		this.outputImg = outputImg;
	}
	

}
