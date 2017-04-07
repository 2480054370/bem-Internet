package com.bemInternet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bemInternet.domain.ClassPhoto;


@Component
@Transactional
public class ClassPhotoDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	public void savePhoto(ClassPhoto photo){
		getSession().save(photo);
	}
	
	public List<ClassPhoto> fillAll(){
		List<ClassPhoto> list = getSession().createCriteria(ClassPhoto.class)
				.addOrder(Order.desc("sort"))
				.list();
		
//		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<ClassPhoto> query = builder.createQuery(ClassPhoto.class);
//		
//		Root<ClassPhoto> root = query.from(ClassPhoto.class);
//		List<ClassPhoto> list = entityManager.createQuery(query).getResultList();
		return list;
	}
	
//	public List<ClassPhoto> findUser(Integer id){
//		Criteria c = getSession().createCriteria(ClassPhoto.class,"classphoto");
//		c.createAlias("classphoto.user", "user");
//		c.add(Restrictions.eq("user.id", id));
//		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
//		List<ClassPhoto> pa  =  c.list();
//		return pa;
//	}
	
}
