package com.netease.shop.meta;

public class IndexInfo {
	
	private int id;//产品 ID
	private String title;  // 标题    
	private String image;   //图片地址 
	private long price; //价格  
	private int productnum;
	private boolean isBuy; // 当前用户是否已经购买 
	private boolean isSell;// 是否已经卖出 
	   
	public IndexInfo(){
		
	}
	


	
	public IndexInfo(int id, String title, String image, long price, int productnum, boolean isBuy, boolean isSell) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.price = price;
		this.productnum = productnum;
		this.isBuy = isBuy;
		this.isSell = isSell;
	}




	public int getProductnum() {
		return productnum;
	}


	public void setProductnum(int productnum) {
		this.productnum = productnum;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public boolean isBuy() {
		return isBuy;
	}

	public void setBuy(boolean isBuy) {
		this.isBuy = isBuy;
	}

	public boolean isSell() {
		return isSell;
	}

	public void setSell(boolean isSell) {
		this.isSell = isSell;
	}
	
}
