package com.bemInternet.Service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemInternet.dao.ClassPhotoDao;
import com.bemInternet.dao.SearchDao;
import com.bemInternet.domain.ClassPhoto;
import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.User;

@Service("searchService")
public class SearchServicelmpl implements SearchService{
	
	@Autowired
	private SearchDao searchDao;
	
	public List<User> sreachuser(String key){
		return searchDao.sreachuser(key);
	}
	
	public List<ClassPhoto> searchphoto(String key){
		return searchDao.searchphoto(key);
	}
	public List<PartyArticle> searchparty(String key){
		return searchDao.searchparty(key);
	}

}
