package com.bemInternet.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemInternet.dao.ChatDao;
import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;

@Service("chatService")
public class ChatServicelmpl implements ChatService{
	@Autowired
	private ChatDao chatDao;

	public void SaveChat(Chat message){
		chatDao.SaveChat(message);
	}
	
	public List<User> QueryFriendsList(User user){
		return chatDao.QueryFriendsList(user);
	}
	
	public List<Chat> QueryMessageList(String inputUser, String outputUser){
		return chatDao.QueryMessageList(inputUser, outputUser);
	}
	
	public List<Chat> SaveMessageState(String inputUser, String outputUser){
		return chatDao.SaveMessageState(inputUser, outputUser);
	}
	
	public List<Chat> QueryMessageState(String outputname){
		return chatDao.QueryMessageState(outputname);
	}
	
}
