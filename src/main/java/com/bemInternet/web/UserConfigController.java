package com.bemInternet.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bemInternet.Service.ChatService;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.Chat;
import com.bemInternet.form.UserConfigInfoForm;
import com.bemInternet.form.UserConfigMsgForm;
import com.bemInternet.form.UserRegisterForm;

@Controller
public class UserConfigController extends BaseController{

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/config")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String Config(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		List<Chat> message = chatService.QueryMessageState(auth.getName());
		model.addAttribute("message", message);
		model.addAttribute("user", user);
		return "myConfig";
	}
	
	@PostMapping("/config_infoconfig")
	public @ResponseBody Map<String, Object> config_infoconfig(HttpServletRequest request,
			@Valid UserConfigInfoForm configInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
			user.setCfphonenumber(configInfo.getCfphonenumber());
			user.setCfaddress(configInfo.getCfaddress());
			user.setCfbirthday(configInfo.getCfbirthday());
			user.setCfsex(configInfo.getCfsex());
			user.setCfhobby(configInfo.getCfhobby());
			userService.update(user);
			map.put("data", "success");
		return map;
	}
	
	@PostMapping("/config_messagenotify")
	public @ResponseBody Map<String, Object> config_messagenotify(HttpServletRequest request,
			@Valid UserConfigMsgForm configMsg) {
		Map<String, Object> map = new HashMap<String, Object>();
			user.setCfinfo(configMsg.getCfinfo());
			user.setCfphoto(configMsg.getCfphoto());
			user.setCfreport(configMsg.getCfreport());
			user.setCfmail(configMsg.getCfmail());
			userService.update(user);
			map.put("data", "success");
		return map;
	}
}
