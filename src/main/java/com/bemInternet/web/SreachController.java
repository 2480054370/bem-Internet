package com.bemInternet.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bemInternet.Service.ChatService;
import com.bemInternet.Service.PhotoService;
import com.bemInternet.Service.SearchService;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;

@Controller
public class SreachController extends BaseController{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SearchService searchService;
	@GetMapping("/search")
	private String search(String keyword,Model model,HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		List<Chat> message = chatService.QueryMessageState(auth.getName());
		model.addAttribute("message", message);
		model.addAttribute("key",keyword);
		model.addAttribute("userlist",searchService.sreachuser(keyword));
		model.addAttribute("photolist",searchService.searchphoto(keyword));
		model.addAttribute("partylist",searchService.searchparty(keyword));
		model.addAttribute("users", user);
		return "searchResult";
	}
	
}
