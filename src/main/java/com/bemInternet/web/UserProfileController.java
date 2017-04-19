package com.bemInternet.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bemInternet.Service.ChatService;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;
import com.bemInternet.form.UserProfileForm;
import com.bemInternet.form.UserProfileInfoForm;
import com.bemInternet.form.UserProfilePwdForm;
import com.bemInternet.form.UserRegisterForm;
import com.bemInternet.utils.FileUploadUtils;

@Controller
public class UserProfileController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/profile")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String Profile(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		List<Chat> message = chatService.QueryMessageState(auth.getName());
		model.addAttribute("message", message);
		model.addAttribute("user", user);
		return "myProfile";
	}
	
	//修改手机邮箱
	@PostMapping("/profile_authentication")
	public @ResponseBody Map<String, Object> updateAuthentication(HttpServletRequest request,
			@Valid UserProfileForm profile) {
		Map<String, Object> map = new HashMap<String, Object>();
			user.setPhonenumber(profile.getPhone());
			user.setUsermail(profile.getEmail());
			userService.update(user);
			map.put("phone", profile.getPhone());
			map.put("email", profile.getEmail());
			map.put("data", "success");
			
		return map;
	}
	
	//修改密码
	@PostMapping("/profile_updatepassword")
	public @ResponseBody Map<String, Object> updatepassword(HttpServletRequest request, 
			@Valid UserProfilePwdForm profilepwd) {
			Map<String, Object> map = new HashMap<String, Object>();
			String getUserpwd = userService.getPwd(user.getStudentld());
			if (getUserpwd != null & bCryptPasswordEncoder.matches(profilepwd.getOldpwd(), getUserpwd)) {
				if(!profilepwd.getNewpwd().equals(profilepwd.getConfirmpwd())){
					map.put("data", "error");
				}else{
					user.setPassword(bCryptPasswordEncoder.encode(profilepwd.getNewpwd()));
					userService.update(user);
					map.put("data", "success");
				}
			} else {
				map.put("data", "pwderror");
			}

		return map;
	}
	
	//修改个人信息
	@PostMapping("/profile_basic")
	public @ResponseBody Map<String, Object> updateBasic(HttpServletRequest request, 
			@Valid UserProfileInfoForm profileinfo) {
			Map<String, Object> map = new HashMap<String, Object>();
				user.setAddress(profileinfo.getAddress());
				user.setUsername(profileinfo.getName());
				user.setBirthday(profileinfo.getBirth());
				user.setHobby(profileinfo.getHobby());
				user.setSaying(profileinfo.getSign());
				user.setSex(profileinfo.getSex());
				userService.update(user);
				map.put("data", "success");
		return map;
	}
}
