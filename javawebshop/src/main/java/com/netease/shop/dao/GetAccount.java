package com.netease.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.netease.shop.meta.Buylist;



public interface GetAccount {
	@Results({
		   @Result(property="id", column="contentId"),
		   @Result(property="buyPrice", column="price"),
		   @Result(property="image", column="icon"),
		   @Result(property="title", column="title"),
		   @Result(property="buyNum", column="buynum"),
		   @Result(property="buyTime", column="time"),
		})
	
	@Select("select  person.id as personId ,content.id as contentId,icon,title,trx.price,time,buynum from person left join trx on person.id "
			+ "= trx.personId left join content on trx.contentId = content.id Where person.id =#{id}")
	
	List <Buylist> getBuylist(int id);
}
