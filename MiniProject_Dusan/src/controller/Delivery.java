package controller;

import java.util.ArrayList;

import model.FoodInfo;
import model.FoodInfoDao;

public class Delivery {
	ArrayList<FoodInfo> foodInfo;
	
	public Delivery() {
		this.foodInfo = new FoodInfoDao().foodInfo();
	}
	
	//메뉴종류
	public ArrayList<String> getMenuName() {
		ArrayList<String> sName = new ArrayList<String>();
		for(int i=0; i<foodInfo.size(); i++) {
			if(i==0) {
				sName.add(foodInfo.get(i).getsName());
			}else if(!foodInfo.get(i).getsName().equals(foodInfo.get(i-1).getsName())) {
				sName.add(foodInfo.get(i).getsName());
			}
		}
		return sName;
	}
	
	//메뉴정보 
	public ArrayList<ArrayList<String>> getMenuInfo(String str){
		ArrayList<ArrayList<String>> menu = new ArrayList<ArrayList<String>>();
		for(int i=0; i<foodInfo.size(); i++) {
			ArrayList<String> menu2 = null;
			if(foodInfo.get(i).getsName().equals(str)) {
				menu2 = new ArrayList<String>();
				menu2.add(foodInfo.get(i).getMenu());
				menu2.add(Integer.toString(foodInfo.get(i).getPrice()));
				menu2.add(Integer.toString(foodInfo.get(i).getStock()));
				menu.add(menu2);
			}
		}
		return menu;
	}
	
}
