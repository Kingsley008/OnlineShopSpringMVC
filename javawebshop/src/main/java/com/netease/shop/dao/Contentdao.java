package com.netease.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.shop.meta.IndexInfo;
import com.netease.shop.meta.Productdetail;

public interface Contentdao {
	@Results({
		   @Result(property="id", column="id"),
		   @Result(property="price", column="price"),
		   @Result(property="image", column="icon"),
		   @Result(property="title", column="title"),
		})
	@Select("select id,price,title,icon from content")
  List <IndexInfo> getIndexInfo();
	@Results({
		   @Result(property="id", column="id"),
		   @Result(property="price", column="price"),
		   @Result(property="image", column="icon"),
		   @Result(property="summary", column="abstract"),
		   @Result(property="detail", column="text"),
		   @Result(property="title", column="title"),
		   @Result(property="buyNum", column="productnum"),
		   
		   
		})
	@Select("select * from content where id=#{id} ")
	Productdetail showdetail(int id);
	
	
	@Results({
		   @Result(property="price", column="price"),
	})
	@Select("select price from content where id=#{id} ")
	long GetpriceById(int id);
	
	
	@Results({
		   @Result(property="productnum", column="productnum"),
	})
	
	@Update("update content set productnum = productnum - #{num} where id=#{id} ")
	void updateProductNum(@Param(value = "num") int num,@Param(value = "id")int id);
	
}
