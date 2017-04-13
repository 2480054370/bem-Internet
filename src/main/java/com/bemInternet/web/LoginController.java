package com.bemInternet.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.User;
import com.bemInternet.form.GetUserForm;
import com.bemInternet.form.UserFindpwdForm;
import com.bemInternet.form.UserRegisterForm;
import com.bemInternet.form.UserVercodeForm;
import com.bemInternet.utils.JsonResult;



@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	// 邮箱发送
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/login")
	public String login(){
		return "login";
	}
	
	@PostMapping("/registration_post")
	public @ResponseBody Map<String, Object> createNewUser(@Valid UserRegisterForm user) {
		Map<String, Object> map = new HashMap<String, Object>();
//		List<JsonResult> arr = new ArrayList<JsonResult>();
//		JsonResult jr = new JsonResult();
		User userExists = userService.findUserByStudentld(user.getStudentld());
		if (userExists != null) {
			map.put("data", "userExists");
		}
		else if (!user.getNew_password().equals(user.getConfirm_password())) {
			map.put("data", "error");
		} else {
			userService.saveUser(user);
			map.put("data", "success");
		}
		return map;
	}
	
	// 发送邮箱验证
	@PostMapping("/send_email")
	public @ResponseBody Map<String, Object> send_email(@Valid GetUserForm getuser) {
		Map<String, Object> map = new HashMap<String, Object>();
		String findEmail = getuser.getFindemail();
		// 随机生成六位字符串
		String randomSt = "";
		String a = "0123456789abcdefghijklmnopqrstuvwxyz";
		char[] rands = new char[6];
		for (int i = 0; i < rands.length; i++) {
			int rand = (int) (Math.random() * a.length());
			rands[i] = a.charAt(rand);
		}
		for (int i = 0; i < rands.length; i++) {
			randomSt += rands[i];
		}
		String Thisaccount = userService.getEmailUser(findEmail);
		if (Thisaccount == null) {
			map.put("msg", "account not exist");
		} else {
			// 发送邮件
			try {
				
				SimpleMailMessage message = new SimpleMailMessage();
				message.setFrom("breadem@aliyun.com");
				message.setTo(findEmail);
				message.setSubject("Bem  找回密码");
				message.setText("您的验证码为：" + randomSt + "，请将此验证码巴拉巴拉巴拉。");
				mailSender.send(message);

				// 保存到数据库
				User user = userService.get(Thisaccount);
				user.setVercode(randomSt);
				userService.update(user);
				map.put("msg", "send success");
			} catch (Exception ex) {
				System.err.println(ex);
				map.put("msg", "send error");
			}
		}
		return map;
	}
	
	// 验证验证码
	@PostMapping("/check_vercode")
	public @ResponseBody Map<String, Object> check_vercode(@Valid UserVercodeForm getuser) {
		Map<String, Object> map = new HashMap<String, Object>();
		String findEmail = getuser.getFindemail();
		String ThisVerCode = getuser.getVercode();
		String Thisaccount = userService.getEmailUser(findEmail);
		String SerVerCode = userService.getUserCode(Thisaccount);
		if (!ThisVerCode.equals(SerVerCode)) {
			map.put("data", "error");
		} else {
			map.put("account", Thisaccount);
			// 从数据库删除验证码
			User user = userService.get(Thisaccount);
			user.setVercode("");
			userService.update(user);
			map.put("data", "success");
		}
		return map;
	}

	// 验证密码
	@PostMapping("/check_updatepwd")
	public @ResponseBody Map<String, Object> check_updatepwd(@Valid UserFindpwdForm getuser) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!getuser.getCuppwd().equals(getuser.getCuprepwd())) {
			map.put("data", "error");
		} else {
			User user = userService.get(getuser.getUpaccount());
			user.setPassword(bCryptPasswordEncoder.encode(getuser.getCuppwd()));
			userService.update(user);
			map.put("data", "success");
		}
		return map;
	}
}
