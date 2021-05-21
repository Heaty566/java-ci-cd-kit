/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author heaty566
 */
public class Mobile {

	String mobileId;
	String description;
	Float price;
	String mobileName;
	Integer quantity;
	Integer yearOfProduction;
	boolean notSale;

	public Mobile(String mobileId, String description, Float price, String mobileName, Integer quantity, Integer yearOfProduction, boolean notSale) {
		this.mobileId = mobileId;
		this.description = description;
		this.price = price;
		this.mobileName = mobileName;
		this.quantity = quantity;
		this.yearOfProduction = yearOfProduction;
		this.notSale = notSale;
	}

	public Mobile() {
	}

	public String getMobileId() {
		return mobileId;
	}

	public void setMobileId(String mobileId) {
		this.mobileId = mobileId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getMobileName() {
		return mobileName;
	}

	public void setMobileName(String mobileName) {
		this.mobileName = mobileName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Integer yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public boolean isNotSale() {
		return notSale;
	}

	public void setNotSale(boolean notSale) {
		this.notSale = notSale;
	}
	
}
