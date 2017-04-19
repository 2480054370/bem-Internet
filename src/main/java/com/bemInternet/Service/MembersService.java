package com.bemInternet.Service;

import java.util.List;

import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;

public interface MembersService {
	
	public List<User> QueryFriendsList(User user);
	
}
