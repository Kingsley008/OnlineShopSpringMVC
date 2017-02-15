package com.netease.shop.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.netease.shop.dao.Contentdao;
import com.netease.shop.dao.GetAccount;
import com.netease.shop.meta.Buylist;
import com.netease.shop.meta.IndexInfo;
import com.netease.shop.meta.Productdetail;
import com.netease.shop.meta.Productlist;
import com.netease.shop.meta.User;
import com.netease.shop.service.CheckUser;
import com.netease.shop.service.Trxinfo;
import com.netease.shop.utils.Tool;

@Controller
public class ShopController {

	//验证用户合法性
	@RequestMapping(value = "/logincheck" ,produces="application/json")
	public String checklg(ModelMap map,@RequestParam("userName")String username,@RequestParam("password")String password,HttpServletResponse response,HttpSession session,HttpServletRequest request) throws IOException, ServletException{
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context-service.xml");	
	     CheckUser  dao = (CheckUser)context.getBean("checkuserimp");
	     boolean b =dao.checkuser(username, password);
	     System.out.println(b);
	     User user = dao.getUser();
	     if(b){
	     session.setAttribute("user", user);
	     }
	     int code =response.getStatus();
	     String message ="帐号密码错误";
	     map.addAttribute("code",code);
	     map.addAttribute("message",message);
	     map.addAttribute("result",b);
	     ((ConfigurableApplicationContext)context).close();
        return "login";
      
	}
	
	//点击登入后，返回登入页面
	@RequestMapping(value = "/login")
	public String sendWelcome(ModelMap map,HttpSession session) throws IOException{
		return "login";

	}
	
	//返回主页面
	@RequestMapping(value = "/bookonline")
	public String sendInfoToIndex(ModelMap map,HttpSession session,HttpServletRequest request) throws IOException{
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		Contentdao dao = context.getBean("contentdao",Contentdao.class); 
		List <IndexInfo> productList = dao.getIndexInfo();
		String type = request.getParameter("type");	
		User user =(User) session.getAttribute("user");
		
		//暂时处理freemarker boolean 异常 
		 boolean isBuy =productList.get(0).isBuy();
		 boolean isSell =productList.get(0).isSell();
		
	    map.addAttribute("user",user);
		map.addAttribute("type",type);
		map.addAttribute("productList",productList);
		map.addAttribute("isBuy", isBuy);
		map.addAttribute("isSell", isSell);
		 ((ConfigurableApplicationContext)context).close();	
		return"index";
	}
	
	//返回商品详情页
	@RequestMapping(value = "/show")
	public String Showdetail(@RequestParam("id")int id, ModelMap map,HttpSession session,HttpServletRequest request) throws IOException{
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		 Contentdao dao = context.getBean("contentdao",Contentdao.class); 
		 
		 Productdetail product = dao.showdetail(id);
		 
		// System.out.println(product.getBuynum());
		 
		User user =(User) session.getAttribute("user");
		 //boolean 报错 临时方法
	    boolean isBuy = product.isBuy();
	    boolean isSell = product.isSell();
	    
	    map.addAttribute("user",user);
	    map.addAttribute("product", product);
	    map.addAttribute("isBuy", isBuy);
	    map.addAttribute("isSell", isSell);
	   
		 ((ConfigurableApplicationContext)context).close();	
		return"show";
	}
	
	//处理订单
	@RequestMapping(value = "/buy" ,produces="application/json",method=RequestMethod.POST)
	public String checkBuy(@RequestBody List<Productlist> products,ModelMap map,HttpServletResponse response,HttpSession session,HttpServletRequest request) throws IOException, ServletException{
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context-service.xml");	
	     Trxinfo  dao = (Trxinfo)context.getBean("addTrxinfo");
	     User user = (User) session.getAttribute("user");
	     dao.addTrx(products, user.getId());
	     int code = response.getStatus();
	     String message ="购买失败";
	     boolean b = true;
	     map.addAttribute("code",code);
	     map.addAttribute("message",message);
	     map.addAttribute("result",b);
	   
	     ((ConfigurableApplicationContext)context).close();
	     return"settleAccount";
      
	}
	
	//点击购物车，返回购物车页面
	@RequestMapping(value="/settleAccount")
	public String settleAccount(){
		return"settleAccount";
	}
	
	//点击财务，返回财务页面
	@RequestMapping(value = "/account")
    public String showAccount(HttpSession session, ModelMap map){
    ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
    GetAccount dao = context.getBean("getAccount",GetAccount.class);
    User user = (User)session.getAttribute("user");
    List<Buylist> list =dao.getBuylist(user.getId());
    map.addAttribute("buyList", list);
    ((ConfigurableApplicationContext)context).close();
	return "account";
	}
	//点击推出返回到登入页面
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return"login";
	}
	@RequestMapping(value="/edit")
	public String edit(@RequestParam("id")int id,ModelMap map){
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		Contentdao dao = context.getBean("contentdao",Contentdao.class); 
		Productdetail product = dao.showdetail(id);
		map.addAttribute("product", product);
	//	User user =(User) session.getAttribute("user");
	  ((ConfigurableApplicationContext)context).close();
		return "edit";
	}
	
	@RequestMapping(value="/editSubmit")
	public String editSubmit(@RequestParam("id")int id,@RequestParam("title")String title,@RequestParam("image")String image,
    @RequestParam("detail")String detail,@RequestParam("price")long price,@RequestParam("summary")String summary,
	ModelMap map,HttpServletResponse response){
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		Contentdao dao = context.getBean("contentdao",Contentdao.class); 
		String title2 = Tool.getNewString(title);
		String image2  = Tool.getNewString(image);
		String detail2 = Tool.getNewString(detail);
		String summary2 = Tool.getNewString(summary);
		dao.updateContent(id, price, title2, image2, detail2, summary2);
		Productdetail product = dao.showdetail(id);
		map.addAttribute("product", product);
	  ((ConfigurableApplicationContext)context).close();
		return "editSubmit";
	}
	
	//管理员主页删除商品
	@RequestMapping(value="/delete",produces="application/json",method=RequestMethod.POST)
	public String deleteGood(@RequestParam("id")int id,HttpServletResponse response,ModelMap map){
	ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
	Contentdao dao = context.getBean("contentdao",Contentdao.class);
	
	int i =dao.deleteContent(id);
	boolean b = false;
	
	if(i==1){
		b=true;
	}
	
	int code = response.getStatus();
	String message ="删除";
   
    map.addAttribute("code", code);
    map.addAttribute("message",message);
    map.addAttribute("result",b);
    ((ConfigurableApplicationContext)context).close();
	return"index";
	}
	
	@RequestMapping(value="/public")
	public String showPublic(){
		
		return"public";
	}
	
	@RequestMapping(value="/publicSubmit")
	public String publicSubmit(@RequestParam("productnum")int productnum, @RequestParam("title")String title,@RequestParam("image")String image,
		    @RequestParam("detail")String detail,@RequestParam("price")long price,@RequestParam("summary")String summary,
			ModelMap map,HttpServletResponse response){
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		Contentdao dao = context.getBean("contentdao",Contentdao.class);
	//	System.out.println(image);
		String title2 = Tool.getNewString(title);
		String image2  = Tool.getNewString(image);
		String detail2 = Tool.getNewString(detail);
		String summary2 = Tool.getNewString(summary);
		//1.insert 
	     int i=	dao.insertConetnt(productnum, price, title2, image2, detail2, summary2);
	    //插入成功 返回product
		if(i==1){
			Productdetail product = dao.showSubmitDetail();
			map.addAttribute("product", product);
		}
		//2.select 
	
		((ConfigurableApplicationContext)context).close();
		return"publicSubmit";
	}
	
	@RequestMapping(value="/upload",produces="application/json",method=RequestMethod.POST)
	public String Upload(@RequestParam("file")MultipartFile file,HttpServletResponse response,ModelMap map) throws IllegalStateException, IOException{
		 String fileName = file.getOriginalFilename();
	   //  System.out.println(fileName);
	     String fileName2 = Tool.getNewString(fileName);
	     if(file!=null && !file.isEmpty()){
	     file.transferTo(new File("C:/Users/ASUS/Desktop/javawebonlineshop/javawebshop/src/main/webapp/image/"+file.getOriginalFilename()));
	     }
	     int code = response.getStatus(); 
	     String message = "上传";
	     String address = "../image/"+fileName2;
	     map.addAttribute("code",code);
	     map.addAttribute("message",message);
	     map.addAttribute("result",address);
	    return"public";
	
	}
	
   
	
    
}
