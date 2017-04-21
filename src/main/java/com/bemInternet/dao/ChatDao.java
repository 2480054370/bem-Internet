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
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import com.bemInternet.domain.Chat;
import com.bemInternet.domain.User;


@Component
@Transactional
public class ChatDao {
	@PersistenceContext
	private EntityManager entityManager;	//实体管理器
	
	public Session getSession(){
		return entityManager.unwrap(Session.class);
	}
	
	//保存信息
	public void SaveChat(Chat message){
		getSession().save(message);
	}
	
	//查找好友列表
	public List<User> QueryFriendsList(User user){
//		Criteria c = getSession().createCriteria(User.class);
//		c.createAlias("friends", "friend");
		List<User> friendList = user.getFriends();	
		return friendList;
	}
	
	//查询消息列表
	public List<Chat> QueryMessageList(String inputUser, String outputUser){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
		Root<Chat> root = criteria.from(Chat.class);
		Predicate personRestriction = builder.and(
				builder.equal(root.get("inputname"), inputUser),
				builder.equal(root.get("outputname"), outputUser)
			);
		Predicate partnerRestriction = builder.and(
			    builder.equal(root.get("inputname"), outputUser),
			    builder.equal(root.get("outputname"), inputUser)
			);
		criteria.where(builder.or( personRestriction, partnerRestriction ));
		criteria.orderBy(builder.asc(root.get("sendtime")));
		List<Chat> list = entityManager.createQuery(criteria).getResultList();
		return list;
	}
	
	//保存消息状态
	public List<Chat> SaveMessageState(String inputUser, String outputUser){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
		Root<Chat> root = criteria.from(Chat.class);
		Predicate personRestriction = builder.and(
				builder.equal(root.get("inputname"), inputUser)
			);
		Predicate partnerRestriction = builder.and(
			    builder.equal(root.get("outputname"), outputUser)
			);
		criteria.where(builder.and( personRestriction, partnerRestriction ));
		List<Chat> list = entityManager.createQuery(criteria).getResultList();
		return list;
	}
	
	//查询当前的状态
	public List<Chat> QueryMessageState(String outputname){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Chat> criteria = builder.createQuery(Chat.class);
		Root<Chat> root = criteria.from(Chat.class);
		Predicate personRestriction = builder.and(
				builder.equal(root.get("state"), "-1")
			);
		Predicate partnerRestriction = builder.and(
			    builder.equal(root.get("outputname"), outputname)
			);
		criteria.where(builder.and( personRestriction, partnerRestriction ));
		criteria.orderBy(builder.desc(root.get("id")));
		List<Chat> list = entityManager.createQuery(criteria).getResultList();
		return list;
	}
	
}

