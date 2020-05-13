package controller;

import java.util.HashMap;

import model.FoodInfoDao;
import model.OrderInfo;
import model.OrderInfoDao;

public class Pay {
	HashMap<String, int[]> calculate;
	//결제
	public void payment(HashMap<String, int[]> calculate) {
		this.calculate = calculate;
		new FoodInfoDao().ModifyFoodInfo(this.calculate);
		if(new OrderInfoDao().insertOrder(new OrderInfo(CheckLogin.getInstance().getUserId(), calculate)) == 0) {
			this.payment(calculate);
		}
	}
}
