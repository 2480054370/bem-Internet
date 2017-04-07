package com.bemInternet.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.PartyContact;


@Component
@Transactional
public class PartyDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	public List<PartyArticle> search(String KeyWord,String Key){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PartyArticle> query = builder.createQuery(PartyArticle.class);
		Root<PartyArticle> root = query.from(PartyArticle.class);
		query.select(root).where(
				builder.or(
				builder.like(root.get("topic"), KeyWord),
				builder.like(root.get("topic"), Key)
				)
		);
		List<PartyArticle> mlist = entityManager.createQuery(query).getResultList();
		return mlist;
	}
	public void saveArticle(PartyArticle pa){
		getSession().save(pa);
	}
	public List<PartyArticle> fillAllPersonal(Integer id){
		Criteria c = getSession().createCriteria(PartyArticle.class,"partyarticle");
		c.createAlias("partyarticle.user", "user");
		c.add(Restrictions.eq("user.id", id));
		c.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		List<PartyArticle> pa  =  c.list();
		return pa;
	}
	
	public List<PartyArticle> fillAll(){

		List<PartyArticle> list = new ArrayList<PartyArticle>();
		try {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PartyArticle> query = builder.createQuery(PartyArticle.class);
		Root<PartyArticle> root = query.from(PartyArticle.class);
		query.orderBy(builder.desc(root.get("ideatime")));
		list = entityManager.createQuery(query).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	public void update(PartyArticle article){
		getSession().update(article);
	}
	
	public PartyArticle fillPartyArticleById(String id){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PartyArticle> query  = builder.createQuery(PartyArticle.class);
		Root<PartyArticle> root =query.from(PartyArticle.class);
		query.where(builder.equal(root.get("id"), id));
		PartyArticle  pa = entityManager.createQuery(query).getSingleResult();
		return pa;
	}
	
	public PartyContact findContact(String id){
		PartyContact co =new PartyContact();
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<PartyContact> query  = builder.createQuery(PartyContact.class);
			Root<PartyContact> root =query.from(PartyContact.class);
			query.where(builder.equal(root.get("id"), id));
			co = entityManager.createQuery(query).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return co;
	}
	public void deleteArticle(String id){
		PartyArticle  pa =fillPartyArticleById(id);
		getSession().delete(pa);
	}
}
