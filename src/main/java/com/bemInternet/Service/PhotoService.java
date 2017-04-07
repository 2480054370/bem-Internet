package com.bemInternet.Service;

import java.util.List;

import com.bemInternet.domain.ClassPhoto;

public interface PhotoService {
	public void savePhoto(ClassPhoto photo);
	
	public List<ClassPhoto> fillAll();
}
