package com.bemInternet.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bemInternet.dao.UserDao;
import com.bemInternet.domain.User;
import com.bemInternet.form.UserRegisterForm;

public interface UserService {
	public User findUserByStudentld(String studentld);
	public void saveUser(UserRegisterForm user);
	public User get(String studentld);
	public void update(User user);
	public String getEmailUser(String email);
	public String getUserCode(String user);
	public String getPwd(String studentld);
	public List<User> findProfile(String studentld);
}
