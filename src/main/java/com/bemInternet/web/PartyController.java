package com.bemInternet.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bemInternet.Service.ChatService;
import com.bemInternet.Service.PartyService;
import com.bemInternet.Service.PhotoService;
import com.bemInternet.Service.UserService;
import com.bemInternet.domain.Chat;
import com.bemInternet.domain.PartyArticle;
import com.bemInternet.domain.PartyContact;
import com.bemInternet.domain.PartyPhoto;
import com.bemInternet.domain.User;
import com.bemInternet.form.GetPartyForm;
import com.bemInternet.utils.FileUploadUtils;

@Controller
public class PartyController extends BaseController{
	@Autowired
	private UserService userService;
	
	@Autowired
	private PartyService partyService;
	
	@Autowired
	private ChatService chatService;

	@PostMapping("/party_tab")
	public @ResponseBody Map<String, Object> party_tab(Model model, @Valid GetPartyForm getparty){
		Map<String,Object> map = new HashMap<String,Object>();
		if(getparty.getTab_attribute().equals("0")){
			map.put("msg", "all");
		}else if(getparty.getTab_attribute().equals("1")){
			map.put("msg", "join");
		}else if(getparty.getTab_attribute().equals("2")){
			map.put("msg", "personal");
		}
		
		return map;
	}
	
	@GetMapping("/party")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String show(Model model, HttpServletRequest request){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user = userService.findUserByStudentld(auth.getName());
		List<Chat> message = chatService.QueryMessageState(auth.getName());
		model.addAttribute("message", message);
		model.addAttribute("users", user);
		List<PartyArticle> list = partyService.fillAllPersonal(user.getId());
		PartyContact contact = partyService.findContact("123");
		List<PartyArticle> findAll = partyService.fillAll();
		model.addAttribute("list",list);
		model.addAttribute("contact", contact);
		model.addAttribute("size", list.size());
		model.addAttribute("list",list);
		model.addAttribute("lists", findAll);
		return "party";
	}
	
	@PostMapping("/DeleteArticle")
	@ResponseBody
	public String deleteArticle(@RequestParam("id")String id){
		partyService.deleteArticle(id);
		return "party";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public void update(
			@RequestParam("topic")String topic,
			@RequestParam("starttime")String starttime,
			@RequestParam("endtime")String endtime,
			@RequestParam("money")Integer money,
			@RequestParam("note")String note,
			@RequestParam("address")String address,
			@RequestParam("id")String id){
		PartyArticle article = partyService.fillPartyArticleById(id);
		article.setAddress(address);
		article.setTopic(topic);
		article.setStarttime(starttime);
		article.setEndtime(endtime);
		article.setMoney(money);
		article.setNote(note);
		partyService.update(article);
	}
	
	@PostMapping("/edit")
	@ResponseBody
	public PartyArticle edit(@RequestParam("id")String id){
		PartyArticle article = partyService.fillPartyArticleById(id);
		return article;
	}
	
	@PostMapping("/party/addArticle")
	@ResponseBody
	public PartyArticle addArticle(
			@RequestParam("topic")String topic,
			@RequestParam("starttime")String starttime,
			@RequestParam("endtime")String endtime,
			@RequestParam("money")Integer money,
			@RequestParam("note")String note,
			@RequestParam("address")String address,
			Model model,@RequestParam("file")MultipartFile[] listfile, HttpServletRequest request) throws IllegalStateException, IOException{
		
		String thislD = null;
		if(request.getCookies() != null){
			Cookie[] cookies = request.getCookies();
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("test")){
					thislD = cookies[i].getValue();
				}else{
					thislD = null;
				}
			}
		}
		
		List<PartyPhoto> Photos = new ArrayList<>();
		//设置图片缓存地址
		/*String Path =this.getClass().getClassLoader().getResource("").getPath()+"static/upload/";
		File file = new File(Path);
		//判断文件夹是否存在，不存在就创建文件夹
		if(!file.isDirectory()){
			file.mkdir();
		}*/
		
		if(null==topic&&null==starttime&&null==endtime&&null==money&&null==note&&null==address){
			return new PartyArticle();
		}
		
		PartyArticle partyArticle = SaveArticle(topic, starttime, endtime, money, note, address, thislD);
		for(MultipartFile mfile:listfile){
			String FileName ="party-"+new Date().getTime()+"-" +mfile.getOriginalFilename();
			FileUploadUtils fileUpload = new FileUploadUtils(FileName, mfile.getBytes());
			System.out.println(11111);
         	fileUpload.upload();
			//File fPath = new File(Path+FileName);
			//mfile.transferTo(fPath);
			PartyPhoto pt = new PartyPhoto();
			pt.setId(UUID.randomUUID().toString().replace("-", ""));
			//pt.setAddress(Path+FileName);
			pt.setName(FileName);
			pt.setSize(mfile.getSize());
			pt.setPartyArticle(partyArticle);
			pt.setFormat(mfile.getContentType());
			Photos.add(pt);
		}
		//Photo pt = new Photo();
		//pt.setId(UUID.randomUUID().toString().replace("-", ""));
		//设置随机id
		partyArticle.setPhotos(Photos);
		partyArticle.setIdeatime(new Date().getTime());
		partyService.saveArticle(partyArticle);
		return partyArticle;
	}
	public PartyArticle SaveArticle(String topic,String starttime,String endtime,Integer money,String note,String address, String thislD){
		PartyArticle partyArticle = new PartyArticle();
		partyArticle.setAddress(address);
		partyArticle.setEndtime(endtime);
		partyArticle.setMoney(money);
		partyArticle.setNote(note);
		partyArticle.setTopic(topic);
		partyArticle.setStarttime(starttime);
		partyArticle.setId(UUID.randomUUID().toString().replace("-", ""));
		partyArticle.setUser(user);
		return partyArticle;
	}
}
