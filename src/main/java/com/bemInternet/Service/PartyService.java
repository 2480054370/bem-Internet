package com.bemInternet.Service;

import java.util.List;

import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.PartyContact;

public interface PartyService {
	public List<PartyArticle> search(String KeyWord,String Key);
	
	public void saveArticle(PartyArticle pa);
	
	public List<PartyArticle> fillAllPersonal(Integer id);
	
	public List<PartyArticle> fillAll();
	
	public void update(PartyArticle article);
	
	public PartyArticle fillPartyArticleById(String id);
	
	public PartyContact findContact(String id);
	
	public void deleteArticle(String id);
}
