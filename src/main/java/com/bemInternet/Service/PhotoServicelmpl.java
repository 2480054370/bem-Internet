package com.bemInternet.Service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemInternet.dao.ClassPhotoDao;
import com.bemInternet.dao.UserDao;
import com.bemInternet.domain.ClassPhoto;

@Service("photoService")
public class PhotoServicelmpl implements PhotoService{
	@Autowired
	private ClassPhotoDao photoDao;
	
	public void savePhoto(ClassPhoto photo){
		photoDao.savePhoto(photo);
	}
	
	public List<ClassPhoto> fillAll(){
		return photoDao.fillAll();
	}
	
}
