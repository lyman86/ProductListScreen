package com.ly.commodityclassficationlist.bean;

public class CatalogProductListBean {
	   //商品图片链接
	   public String commodityPicLink;
	   //商品名称
	   public String commodityName;
	   //品牌型号
	   public String brandModel;
	   //订货号
	   public String OrderNumber;
	   //货期
	   public String deliveryTime;
	   //旧的价格
	   public String oldPrice;
	   //新的价格
	   public String newPrice;
	public CatalogProductListBean(String commodityName, String brandModel,
			String orderNumber, String deliveryTime, String oldPrice, String newPrice) {
		super();
		this.commodityName = commodityName;
		this.brandModel = brandModel;
		OrderNumber = orderNumber;
		this.deliveryTime = deliveryTime;
		this.oldPrice = oldPrice;
		this.newPrice = newPrice;
	}
	   
	   
	   
	   

}
