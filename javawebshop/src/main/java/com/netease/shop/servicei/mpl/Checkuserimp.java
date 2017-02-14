package com.netease.shop.servicei.mpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.netease.shop.meta.User;
import com.netease.shop.service.CheckUser;



@Service
public class Checkuserimp implements CheckUser {
	
	private JdbcTemplate jdbcTemplate;
    boolean b = false;
    User u = new User();
	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean checkuser(String username, String password) {

		
		String sql ="select * from person where userName=? and password=?";
		this.jdbcTemplate.queryForList(sql, username,password);	
		// 执行查询,将动态参数放到object 数组里
		List <User> users = this.jdbcTemplate.query(sql, new Object[]{username,password}
		,new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("userName"));
				user.setNickname(rs.getString("nickName"));
				user.setPassword(rs.getString("password"));
				user.setUsertype(rs.getInt("userType"));
				return user;
			}
		});
		//定义返回值
	
		// 判断集合对象是否为null 并且长度大于0
		if(users !=null && users.size()>0){
			u = users.get(0);
			b = true;
		}
				
		return b;
	 
	}
	
	public User getUser(){
		return u;
	}
	


}
