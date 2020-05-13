package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.UserInfo;

public class UserInfoController {
	public static void main(String[] args) {
		UserInfoController uic = new UserInfoController();
		//uic.save();
		//uic.load();
		//uic.delete();
		//uic.change();
	}

	public void save(UserInfo u) {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user= "KH";
		String password= "KH";
		
		Connection con = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO USERINFO VALUES(?,?,?,?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, u.getName());
			pstm.setString(2, u.getId());
			pstm.setString(3, u.getPw());
			pstm.setString(4, u.getPhone());
			res = pstm.executeUpdate();
			
			if(res>0)
				System.out.println("회원정보 DB 저장 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void load() {
		//db 연결 부분
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user= "KH";
		String password= "KH";
		
		Connection con = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//실행 부분
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT NAME, ID, PW, PHONE FROM USERINFO ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+" : ["+rs.getString(2)+"/"+rs.getString(3)+"/"+rs.getString(4)+"]");				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void delete(UserInfo ui) {
		//db 연결 부분
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user= "KH";
		String password= "KH";
		
		Connection con = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//실행 부분
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " DELETE FROM USERINFO WHERE ID=? ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, ui.getId());

			res = pstm.executeUpdate();
			
			if(res>0) {
				con.commit();
				System.out.println("삭제 완료");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	public void change(UserInfo u) {
		//db 연결 부분
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user= "KH";
		String password= "KH";
		
		Connection con = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//실행 부분
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " UPDATE USERINFO SET PW=?, PHONE=? WHERE ID=? ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(3, u.getId());
			pstm.setString(1, u.getPw());
			pstm.setString(2, u.getPhone());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				con.commit();
				System.out.println("수정 완료");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	}
	
	
	public ArrayList<UserInfo> getUserInfoList() {
		ArrayList<UserInfo> arr = new ArrayList<UserInfo>();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user= "KH";
		String password= "KH";
		
		Connection con = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//실행 부분
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT NAME, ID, PW, PHONE FROM USERINFO ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				UserInfo u = new UserInfo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
				arr.add(u);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return arr;
	}
	
	public ArrayList<String> getIDList(){
		
		ArrayList<String> IDList = new ArrayList<String>();
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user= "KH";
		String password= "KH";
		
		Connection con = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url,user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//실행 부분
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT ID FROM USERINFO ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				IDList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return IDList;
	}
}
