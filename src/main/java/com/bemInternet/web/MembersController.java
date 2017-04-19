package com.bemInternet.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bemInternet.Service.ChatService;
import com.bemInternet.Service.MembersService;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.User;
import com.bemInternet.form.GetChatForm;

@Controller
public class MembersController extends BaseController{
	
	@Autowired
	private UserService userService;

	@Autowired
	private MembersService membersService;
	
	@GetMapping("members")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String membersHtml(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		List<User> Members = membersService.QueryFriendsList(user);
		model.addAttribute("user", user);
		model.addAttribute("posts", Members);
		return "member";
	}
	
}
