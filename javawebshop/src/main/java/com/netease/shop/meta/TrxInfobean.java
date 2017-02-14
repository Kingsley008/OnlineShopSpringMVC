package com.netease.shop.meta;


public class TrxInfobean {
private int contentId;
private int pId;
private long price;
private String goodsC_date;
private int num;
public int getContentId() {
	return contentId;
}
public void setContentId(int contentId) {
	this.contentId = contentId;
}
public int getpId() {
	return pId;
}
public void setpId(int pId) {
	this.pId = pId;
}
public long getPrice() {
	return price;
}
public void setPrice(long price) {
	this.price = price;
}
public String getGoodsC_date() {
	return goodsC_date;
}
public void setGoodsC_date(String goodsC_date) {
	this.goodsC_date = goodsC_date;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}

@Override
public String toString() {
	return "TrxInfo [contentId=" + contentId + ", pId=" + pId + ", price=" + price + ", goodsC_date=" + goodsC_date
			+ ", num=" + num + "]";
}
	
}
