package model;

import java.io.Serializable;
import java.util.HashMap;

public class OrderInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String UserId;
	private HashMap<String, int[]> calculate;
	
	public OrderInfo(String userId, HashMap<String, int[]> calculate) {
		super();
		this.UserId = userId;
		this.calculate = calculate;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public HashMap<String, int[]> getCalculate() {
		return calculate;
	}
	public void setCalculate(HashMap<String, int[]> calculate) {
		this.calculate = calculate;
	}
	@Override
	public String toString() {
		return "OrderInfo [UserId=" + UserId + ", calculate=" + calculate + "]";
	}
}
