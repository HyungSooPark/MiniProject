package model;

import java.io.Serializable;

public class FoodInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sMenu;
	private String menu;
	private int price;
	private int stock;
	
	public FoodInfo(String sName, String menu, int price, int stock) {
		super();
		this.sMenu = sName;
		this.menu = menu;
		this.price = price;
		this.stock = stock;
	}

	public String getsName() {
		return sMenu;
	}

	public void setsName(String sName) {
		this.sMenu = sName;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "FoodInfo [sMenu=" + sMenu + ", menu=" + menu + ", price=" + price + ", stock=" + stock + "]";
	}
	
	
}
