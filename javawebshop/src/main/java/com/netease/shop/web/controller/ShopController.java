package com.netease.shop.web.controller;

import java.io.IOException;
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

import com.netease.shop.dao.Contentdao;
import com.netease.shop.dao.GetAccount;
import com.netease.shop.meta.Buylist;
import com.netease.shop.meta.IndexInfo;
import com.netease.shop.meta.Productdetail;
import com.netease.shop.meta.Productlist;
import com.netease.shop.meta.User;
import com.netease.shop.service.CheckUser;
import com.netease.shop.service.Trxinfo;

@Controller
public class ShopController {

	
	@RequestMapping(value = "/logincheck" ,produces="application/json")
	public String checklg(ModelMap map,@RequestParam("userName")String username,@RequestParam("password")String password,HttpServletResponse response,HttpSession session,HttpServletRequest request) throws IOException, ServletException{
		//调用用户验证方法
		 ApplicationContext context = new ClassPathXmlApplicationContext("application-context-service.xml");	
	     CheckUser  dao = (CheckUser)context.getBean("checkuserimp");
	     boolean b =dao.checkuser(username, password);
	     System.out.println(b);
	     User user = dao.getUser();
	     session.setAttribute("user", user);
	     int code =response.getStatus();
	     String message ="帐号密码错误";
	     map.addAttribute("code",code);
	     map.addAttribute("message",message);
	     map.addAttribute("result",b);
	     ((ConfigurableApplicationContext)context).close();
        return "login";
      
	}
	
	@RequestMapping(value = "/login")
	public String sendWelcome(ModelMap map,HttpSession session) throws IOException{
		return "login";

	}
	
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
	
	
	@RequestMapping(value = "/show")
	public String Showdetail(@RequestParam("id")int id, ModelMap map,HttpSession session,HttpServletRequest request) throws IOException{
		ApplicationContext context =new ClassPathXmlApplicationContext("application-context-dao.xml");
		 Contentdao dao = context.getBean("contentdao",Contentdao.class); 
		 Productdetail product = dao.showdetail(id);
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
	@RequestMapping(value="/settleAccount")
	public String settleAccount(){
		return"settleAccount";
	}
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
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute("user");
		return"login";
	}
}
