package com.bemInternet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.bemInternet.domain.ClassPhoto;
import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.User;
import com.fasterxml.classmate.util.ResolvedTypeCache.Key;

@Component
@Transactional
public class SearchDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession(){
		return entityManager.unwrap(Session.class);
	}

	public List<User> sreachuser(String key){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User>  query  = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.where(builder.like(root.get("username"), key));
		List<User> list = entityManager.createQuery(query).setFirstResult(0).setMaxResults(5).getResultList();
		return list;
	}
	
	public List<ClassPhoto> searchphoto(String key){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ClassPhoto> query = builder.createQuery(ClassPhoto.class);
		Root<ClassPhoto> root = query.from(ClassPhoto.class);
		query.select(root).where(
				builder.like(root.get("title"), key)
		).orderBy(builder.desc(root.get("p_time")));
		List<ClassPhoto> mlist = entityManager.createQuery(query).setFirstResult(0).setMaxResults(5).getResultList();
		return mlist;
	}
	public List<PartyArticle> searchparty(String key){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PartyArticle> query = builder.createQuery(PartyArticle.class);
		Root<PartyArticle> root = query.from(PartyArticle.class);
		query.where(
			builder.or(builder.like(root.get("topic"), key),builder.like(root.get("note"), key))
				).orderBy(builder.asc(root.get("ideatime")));
		List<PartyArticle> mlist = entityManager.createQuery(query).setFirstResult(0).setMaxResults(5).getResultList();
		return mlist;
	}
}
