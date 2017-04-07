package com.bemInternet.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bemInternet.dao.UserDao;
import com.bemInternet.domain.User;
import com.bemInternet.form.UserRegisterForm;
import com.bemInternet.repository.UserRepository;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public User findUserByStudentld(String studentld) {
		return userRepository.findByStudentld(studentld);
	}

	@Override
	public void saveUser(UserRegisterForm Ruser) {
		User user = new User();
		user.setUsermail(Ruser.getUsermail());
		user.setStudentld(Ruser.getStudentld());
		user.setPassword(bCryptPasswordEncoder.encode(Ruser.getNew_password()));
		user.setRole(user.getRole().ADMIN);
		// 设置config默认值
		user.setCfaddress("公开");
		user.setCfbirthday("公开");
		user.setCfhobby("公开");
		user.setCfphonenumber("公开");
		user.setCfsex("公开");
		user.setCfreport("false");
		user.setCfphoto("false");
		user.setCfmail("false");
		user.setCfinfo("false");
		// 设置头像背景默认值
		user.setHeadimgfile("default-avatar");
		user.setBackimgfile("default-banner");
		userRepository.save(user);
	}
	
	public User get(String studentld){
		return userDao.get(studentld);
	}
	
	public void update(User user){
		userDao.update(user);
	}
	
	public String getEmailUser(String email){
		return userDao.getEmailUser(email);
	}
	
	public String getUserCode(String user){
		return userDao.getUserCode(user);
	}
	
	public String getPwd(String studentld){
		return userDao.getPwd(studentld);
	}
	
	public List<User> findProfile(String studentld){
		return userDao.findProfile(studentld);
	}

}
