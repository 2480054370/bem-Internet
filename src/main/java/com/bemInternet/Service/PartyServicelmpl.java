package com.bemInternet.Service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemInternet.dao.ChatDao;
import com.bemInternet.dao.PartyDao;
import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.PartyContact;

@Service("partyService")
public class PartyServicelmpl implements PartyService{
	@Autowired
	private PartyDao partyDao;
	
	public List<PartyArticle> search(String KeyWord,String Key){
		return partyDao.search(KeyWord, Key);
	}
	public void saveArticle(PartyArticle pa){
		partyDao.saveArticle(pa);
	}
	public List<PartyArticle> fillAllPersonal(Integer id){
		return partyDao.fillAllPersonal(id);
	}
	
	public List<PartyArticle> fillAll(){
		return partyDao.fillAll();
	}
	
	public void update(PartyArticle article){
		partyDao.update(article);
	}
	
	public PartyArticle fillPartyArticleById(String id){
		return partyDao.fillPartyArticleById(id);
	}
	
	public PartyContact findContact(String id){
		return partyDao.findContact(id);
	}
	public void deleteArticle(String id){
		partyDao.deleteArticle(id);
	}
}
