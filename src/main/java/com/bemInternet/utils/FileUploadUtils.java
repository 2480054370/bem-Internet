package com.bemInternet.utils;

import java.io.File;
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

public class FileUploadUtils {
	 //设置好账号的ACCESS_KEY和SECRET_KEY
    public String ACCESS_KEY = "nTPO_xWFx7fBug9YzGCKT-UucndcVTGKgEq_XBNm";
    public String SECRET_KEY = "XRfr6jE8TV-iIqPk6LcQ6v-wUVEZuG5gV_lnfyrw";
    //要上传的空间
    public String bucketname = "breadem";
    //上传到七牛后保存的文件名
    public String key = "my-java.png";
    //上传文件的路径
    public String FilePath = "/.../...";
    //上传的图片
    public byte[] photo;
    
    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    
    //华东地区
    Zone z = Zone.zone0();
    
    Configuration c = new Configuration(z);
    
    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);
    
    
    
    public FileUploadUtils(String key, byte[] photo) {
		super();
		this.key = key;
		this.photo = photo;
	}

	public String getUpToken(){
    	return auth.uploadToken(bucketname);
    }
    
    public void upload() throws IOException{
    	try{
    		
    		Response res = uploadManager.put(photo, key, getUpToken());
    		//打印返回的信息
    		System.out.println(res.bodyString());
    	} catch (QiniuException e) {
    		Response r = e.response;
    		
    		//请求失败时打印的异常的信息
    		System.out.println(r.bodyString());
    		try {
    			System.out.println(r.bodyString());
    		}catch (QiniuException e1){
    			//ignore
    		}
    	}
    }
}
