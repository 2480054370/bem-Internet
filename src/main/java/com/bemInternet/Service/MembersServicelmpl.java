package com.bemInternet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemInternet.dao.MembersDao;
import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;

@Service("membersService")
public class MembersServicelmpl implements MembersService{
	@Autowired
	private MembersDao membersDao;
	
	public List<User> QueryFriendsList(User user){
		return membersDao.QueryFriendsList(user);
	}
	
}
