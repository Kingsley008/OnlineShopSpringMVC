package com.netease.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
	
	
	
	@Results({
		   @Result(property="id", column="id"),
		   @Result(property="price", column="price"),
		   @Result(property="title", column="title"),
		   @Result(property="image", column="icon"),
		   @Result(property="detail", column="text"),
		   @Result(property="summary", column="abstract"),
		   
	})
	@Update("update content set price=#{price},title=#{title},icon=#{image},text=#{detail},abstract=#{summary} where id=#{id}")
	void updateContent(@Param(value = "id")int id,@Param(value = "price")long price,@Param(value = "title")String title,@Param(value = "image")String image,
			@Param(value = "detail")String detail,@Param(value = "summary")String summary);
	
	@Delete("delete from content where id=#{id}")
	int deleteContent(@Param(value = "id")int id);
	
	
	@Insert("insert into content values(0,#{price},#{title},#{image},#{summary},#{detail},100);")
	int insertConetnt(@Param(value = "price")long price,@Param(value = "title")String title,@Param(value = "image")String image,
			@Param(value = "detail")String detail,@Param(value = "summary")String summary);
	
	//查最后一条记录 SELECT * FROM 表名 ORDER BY id DESC LIMIT 0,1 ;
	@Select("select * from content order by id desc limit 0,1")
	Productdetail showSubmitDetail();
}
