package controller;

//로그인하면 정보 저장후 예매나 주문할시 정보 확인
public class CheckLogin {
//	private String UserId;
	private String UserId = "아무개";
	
	private static CheckLogin chl = new CheckLogin();
	
	private CheckLogin() {
	}
	
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}

	public static CheckLogin getInstance() {
		return chl;
	}
}
