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
	public @ResponseBody String createNewUser(@Valid UserRegisterForm user, BindingResult bindingResult) {
		List<JsonResult> arr = new ArrayList<JsonResult>();
		JsonResult jr = new JsonResult();
		User userExists = userService.findUserByStudentld(user.getStudentld());
		if (userExists != null) {
			
			jr.setField("user_exist");
			jr.setMessage("用户名已存在，请重新输入");
			arr.add(jr);
		}
		else if (bindingResult.hasErrors() || !user.getNew_password().equals(user.getConfirm_password())) {
			jr.setField("validation_error");
			jr.setMessage("验证错误，请检查后重新输入");
			arr.add(jr);
		} else {
			userService.saveUser(user);
			jr.setMessage("success");
			arr.add(jr);
		}
		return JSON.toJSONString(arr);
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
	public @ResponseBody Map<String, Object> check_vercode(@Valid UserVercodeForm getuser, BindingResult bindingResult) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.err.println(bindingResult.getErrorCount());
		String findEmail = getuser.getFindemail();
		String ThisVerCode = getuser.getVercode();
		String Thisaccount = userService.getEmailUser(findEmail);
		String SerVerCode = userService.getUserCode(Thisaccount);
		if (!ThisVerCode.equals(SerVerCode) || bindingResult.hasErrors()) {
			map.put("msg", "code error");
		} else {
			map.put("account", Thisaccount);
			// 从数据库删除验证码
			User user = userService.get(Thisaccount);
			user.setVercode("");
			userService.update(user);
			map.put("msg", "code success");
		}
		return map;
	}

	// 验证密码
	@PostMapping("/check_updatepwd")
	public @ResponseBody Map<String, Object> check_updatepwd(@Valid UserFindpwdForm getuser, BindingResult bindingResult) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!getuser.getCuppwd().equals(getuser.getCuprepwd()) || bindingResult.hasErrors()) {
			map.put("msg", "update error");
		} else {
			User user = userService.get(getuser.getUpaccount());
			user.setPassword(bCryptPasswordEncoder.encode(getuser.getCuppwd()));
			userService.update(user);
			map.put("msg", "update success");
		}
		return map;
	}
}
