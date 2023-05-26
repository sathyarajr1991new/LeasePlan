package leaseplan.pojo.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product{

	public String provider;
	public String url;
	public String brand;
	public double price;
	public String unit;
	public boolean isPromo;
	public String promoDetails;
	public String image;


	@JsonProperty("title")
	public String title;
	public String getTitle() { 
		return this.title; } 
	public void setTitle(String title) { 
		this.title = title; } 

}

