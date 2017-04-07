package com.bemInternet.Service;

import java.util.List;

import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;

public interface ChatService {
	public void SaveChat(Chat message);
	
	public List<User> QueryFriendsList(User user);
	
	public List<Chat> QueryMessageList(String inputUser, String outputUser);
	
	public List<Chat> SaveMessageState(String inputUser, String outputUser);
	
	public List<String> QueryMessageState(String outputname);
	
	public List<String> QueryMessageStateNull(String outputname);
}
