package com.bemInternet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;

@Component
@Transactional
public class MembersDao {
	@PersistenceContext
	private EntityManager entityManager;	//实体管理器
	
	public Session getSession(){
		return entityManager.unwrap(Session.class);
	}
	
	//查找好友列表
	public List<User> QueryFriendsList(User user){
		List<User> friendList = user.getFriends();	
		return friendList;
	}
	
}
