package com.bemInternet.web;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bemInternet.Service.ChatService;
import com.bemInternet.Service.PhotoService;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.Chat;
import com.bemInternet.domain.ClassPhoto;
import com.bemInternet.domain.User;
import com.bemInternet.utils.FileUploadUtils;



@Controller
public class PhotoController extends BaseController{
	@Autowired  
    private HttpServletRequest request;  
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private ChatService chatService;
	
	
	@GetMapping("/classPhoto")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String photowall(Model model){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		model.addAttribute("user", user);
		
		
		List<ClassPhoto> list = photoService.fillAll();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        String datetoday = formatter.format(date);
        model.addAttribute("date",datetoday);
		model.addAttribute("list",list);
		List<Chat> message = chatService.QueryMessageState(auth.getName());
		model.addAttribute("message", message);
		
		return "classPhoto";
	}
	
	/*** 
     * 保存文件 
     * @param file 
     * @return 
     */  
    /**
     * @param file	图片文件
     * @return
     */
    private boolean saveFile(MultipartFile file,ClassPhoto photo,Long time) {  
        // 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
            	FileUploadUtils fileUpload = new FileUploadUtils(photo.getAddress(),file.getBytes());
            	fileUpload.upload();
            	
            	photoService.savePhoto(photo);
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return false;  
    }  
	
    
    @PostMapping("/classPhoto/uploadFile")  
    @ResponseBody
    public List<ClassPhoto> filesUpload(@RequestParam("files") MultipartFile[] files,
    		@RequestParam("title") String title,@RequestParam("detail") String detail) {  
    	
        //判断file数组不能为空并且长度大于0  
        List<ClassPhoto> listphoto = new ArrayList();
        if(files!=null&&files.length>0){  
            //循环获取file数组中得文件  
            for(int i = 0;i<files.length;i++){  
                MultipartFile file = files[i];  
                Date date = new Date();
                long time = date.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                String dateString = formatter.format(date);
                ClassPhoto photo = new ClassPhoto(); 
                photo.setId(UUID.randomUUID().toString().replace("-", ""));
                photo.setTitle(title);
                photo.setRemarks(detail);
                photo.setP_time(dateString);
                photo.setSize((int) file.getSize());
                photo.setAddress(time+file.getOriginalFilename());
                photo.setName(file.getOriginalFilename());
                photo.setSort(time);
                photo.setUser(user);
                photo.setUser_name(user.getUsername());
                listphoto.add(photo);
                //保存文件  
                saveFile(file,photo,time);  
            }  
          
        }  
        
        
        return listphoto;  
    }  
}
