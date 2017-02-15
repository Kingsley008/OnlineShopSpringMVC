package com.netease.shop.meta;

public class Productdetail {

    private int id; 
    private long price;
    private String title; 
    private String image; 
    private String summary; //摘要
    private String detail;  //正文 
	private int buyNum;
    private boolean isBuy;
    private boolean isSell;


	public int getBuyNum() {
		return buyNum;
	}



	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
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



	public Productdetail(){
    	
    }
    


	public Productdetail(int id, long price, String title, String image, String summary, String detail, int buynum) {
		super();
		this.id = id;
		this.price = price;
		this.title = title;
		this.image = image;
		this.summary = summary;
		this.detail = detail;
		this.buyNum = buynum;
	}



	public int getBuynum() {
		return buyNum;
	}



	public void setBuynum(int buynum) {
		this.buyNum = buynum;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

}
