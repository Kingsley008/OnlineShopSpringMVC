package com.netease.shop.meta;

public class User {
  private int id;
  private String nickname;
  private String username;
  private String password;
  private int usertype;
  //private List<Productdetail> productList;
  

	public User(int id, String nickname, String username, String password, int usertype) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.usertype = usertype;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
//	public List<Productdetail> getProductList() {
//	return productList;
//}
//public void setProductList(List<Productdetail> productList) {
//	this.productList = productList;
//}
	public User(){
		
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", usertype=" + usertype + "]";
	}
	

	
}
