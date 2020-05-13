package model;

public class UserInfo {
	private String name;
	private String id;
	private String pw;
	private String phone;
	
	public UserInfo() {}

	public UserInfo(String name, String id, String pw, String phone) {
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIDPW() {
		return id+"/"+pw;
	}
	
	@Override
	public String toString() {
		return id+"/"+pw;
	}
	
}
