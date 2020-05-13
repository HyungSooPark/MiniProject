package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Game;

public class GameListController {

	public static void main(String[] args) {
		GameListController glc = new GameListController();
		//glc.save();
		//glc.load();
	}
	
	public void save() {
		ArrayList<Game> arr = new ArrayList<Game>();
		
		arr.add(new Game("2020/05/14 18:30","롯데 자이언츠"));
		arr.add(new Game("2020/05/15 18:30","KIA 타이거즈"));
		arr.add(new Game("2020/05/16 17:00","삼성 라이온즈"));
		arr.add(new Game("2020/05/17 14:00","키움 히어로즈"));
		arr.add(new Game("2020/05/19 18:30","NC 다이노스"));
		arr.add(new Game("2020/05/20 18:30","LG 트윈스"));
		arr.add(new Game("2020/05/21 18:30","SK 와이번스"));
		arr.add(new Game("2020/05/22 18:30","KT 위즈"));
		arr.add(new Game("2020/05/23 17:00","한화 이글스"));
		
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
		String sql = " INSERT INTO GAMELIST VALUES(?,?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i=0;i<arr.size();i++) {
				pstm.setString(1, arr.get(i).getDate());
				pstm.setString(2, arr.get(i).getAway());
				res = pstm.executeUpdate();
				
				if(res>0)
					System.out.println("INSERT 성공");
			}
			
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
		
		String sql = " SELECT GAMEDATE, AWAYTEAM FROM GAMELIST ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+" 두산 베어스 VS " + rs.getString(2));				
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
	
	public String[] getGameList() {
		ArrayList<Game> arr = new ArrayList<Game>();
		
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
		
		String sql = " SELECT GAMEDATE, AWAYTEAM FROM GAMELIST ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Game g = new Game(rs.getString(1),rs.getString(2));
				arr.add(g);
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
		
		Game[] gamelist = (Game[]) arr.toArray(new Game[arr.size()]);
		
		String[] list = new String[gamelist.length];
		
		for(int i=0;i<list.length;i++) {
			list[i] = gamelist[i].toString();
		}
		
		return list;
	}
	
}
