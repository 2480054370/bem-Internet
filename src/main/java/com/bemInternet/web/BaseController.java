package com.bemInternet.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.bemInternet.Service.UserService;
import com.bemInternet.domain.User;


public abstract class BaseController {
	
	protected User user;

}
