package com.bemInternet.Service;

import java.util.List;

import com.bemInternet.domain.ClassPhoto;
import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.User;

public interface SearchService {
	public List<User> sreachuser(String key);
	
	public List<ClassPhoto> searchphoto(String key);
	
	public List<PartyArticle> searchparty(String key);
}
