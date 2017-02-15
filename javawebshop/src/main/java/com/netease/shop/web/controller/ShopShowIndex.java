package com.netease.shop.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.netease.shop.dao.Contentdao;
import com.netease.shop.meta.IndexInfo;
import com.netease.shop.meta.User;

@Controller
public class ShopShowIndex {
	
	@RequestMapping(value = "/abc")
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
}
