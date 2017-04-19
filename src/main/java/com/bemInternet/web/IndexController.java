package com.bemInternet.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bemInternet.Service.UserService;
import com.bemInternet.domain.User;
import com.bemInternet.utils.FileUploadUtils;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = {"/", "/index"})
	public String loginHtml(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		model.addAttribute("user", user);
		return "index";
	}
	
	@GetMapping("/oProfile/{studentld}")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String findOne(@PathVariable String studentld, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(studentld);
		model.addAttribute("user", user);
		return "profile";
	}
	
	@GetMapping("/403")
    public String forbidden(){
        return "403";
    }
	
	
	// 上传头像
	@PostMapping("/uploadAvatar")
	public @ResponseBody Map<String, Object> handleFileUpload(
			@RequestParam(value = "file", required = false) MultipartFile file) {
		Date date = new Date();
		long time = date.getTime();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!file.isEmpty()) {
			try {
				FileUploadUtils fileUpload = new FileUploadUtils(
						"avatar" + "-" + time + "-" + file.getOriginalFilename(), file.getBytes());
				fileUpload.upload();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				map.put("msg", "上传失败");
				return map;
			} catch (IOException e) {
				e.printStackTrace();
				map.put("msg", "上传失败");
				return map;
			}
			user.setHeadimgfile("avatar" + "-" + time + "-" + file.getOriginalFilename());
			userService.update(user);
			map.put("msg", "上传成功");
			map.put("file", "avatar" + "-" + time + "-" + file.getOriginalFilename());
			return map;
		} else {
			map.put("msg", "上传失败，因为文件是空的.");
			return map;
		}
	}
	
	//上传背景
	@PostMapping("/uploadbanner")
	public @ResponseBody Map<String, Object> uploadbanner(
			@RequestParam(value = "file2", required = false) MultipartFile file) {
		Date date = new Date();
		long time = date.getTime();
		Map<String, Object> map = new HashMap<String, Object>();
		if (!file.isEmpty()) {
			try {
				FileUploadUtils fileUpload = new FileUploadUtils(
						"banner" + "-" + time + "-" + file.getOriginalFilename(), file.getBytes());
				fileUpload.upload();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				map.put("msg", "上传失败");
				return map;
			} catch (IOException e) {
				e.printStackTrace();
				map.put("msg", "上传失败");
				return map;
			}
			user.setBackimgfile("banner" + "-" + time + "-" + file.getOriginalFilename());
			userService.update(user);
			map.put("msg", "上传成功");
			map.put("file", "banner" + "-" + time + "-" + file.getOriginalFilename());
			return map;
		} else {
			map.put("msg", "上传失败，因为文件是空的.");
			return map;
		}
	}
	
}
