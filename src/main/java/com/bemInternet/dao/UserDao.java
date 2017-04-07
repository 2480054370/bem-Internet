package com.bemInternet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.Cookie;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bemInternet.domain.User;





@Component
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;	//实体管理器
	
	public Session getSession(){
		return entityManager.unwrap(Session.class);
	}
	
//	public List<String> check(String uname, String upwd) {
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<String> criteria = builder.createQuery(String.class);
//		Root<User> root = criteria.from(User.class);
//		criteria.select(root.get("username"));
//		List<String> list = entityManager.createQuery(criteria).getResultList();
//		for(String item : list){
//			if(item.equals(uname)){
//				criteria.select(root.get("username"));
//				criteria.where(builder.equal(root.get("userpwd"), upwd));
//				List<String> list2 =  entityManager.createQuery(criteria).getResultList();
//				if(list2 == null){
//					System.out.println("pwd is error");
//				}else{
//					System.out.println("login success");
//				}
//				break;
//			}else{
//				System.out.println("account is null");
//			}
//		}
//		return list;
//	}
	
	public Integer getFindById(String studentlD){
		Integer id = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Integer> criteria = builder.createQuery(Integer.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root.get("id"));
		criteria.where(builder.equal(root.get("studentld"), studentlD));
		List<Integer> list2 =  entityManager.createQuery(criteria).getResultList();
		for(Integer item : list2){
			id = item;
		}
		return id;
	}
	
	public List<String> getStudentlD(){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root.get("studentld"));
		List<String> list = entityManager.createQuery(criteria).getResultList();
		System.out.println(list);
		return list;
	}
	
	public String getPwd(String studentld){
		String password = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root.get("password"));
		criteria.where(builder.equal(root.get("studentld"), studentld));
		List<String> list2 =  entityManager.createQuery(criteria).getResultList();
		for(String item : list2){
			password = item;
		}
		return password;
	}
	
	public List<User> findProfile(String studentld){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.where(builder.equal(root.get("studentld"), studentld));
		List<User> list3 =  entityManager.createQuery(criteria).getResultList();
		return list3;
	}
	
	public User get(String studentlD){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		Root<User> root = criteria.from(User.class);
		criteria.where(builder.equal(root.get("studentld"), studentlD));
		User user =  entityManager.createQuery(criteria).getSingleResult();
		return user;
	}
	
	public void update(User user){
		getSession().update(user);
	}
	
	public void register(User user){
		getSession().save(user);
	}
	
	public void saveVerCode(String user){
		
	}
	
	//得到当前邮箱的用户
	public String getEmailUser(String email){
		String account = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root.get("studentld"));
		criteria.where(builder.equal(root.get("usermail"), email));
		List<String> list2 =  entityManager.createQuery(criteria).getResultList();
		for(String item : list2){
			account = item;
		}
		return account;
	} 
	
	//得到当前用户的验证码
	public String getUserCode(String user){
		String VerCode = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> criteria = builder.createQuery(String.class);
		Root<User> root = criteria.from(User.class);
		criteria.select(root.get("vercode"));
		criteria.where(builder.equal(root.get("studentld"), user));
		List<String> list2 =  entityManager.createQuery(criteria).getResultList();
		for(String item : list2){
			VerCode = item;
		}
		return VerCode;
	}
	
}
