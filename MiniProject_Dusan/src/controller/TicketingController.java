package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TicketingInfo;

public class TicketingController {
	
	public static void main(String[] args) {
		TicketingController tc = new TicketingController();
//		tc.save();
//		tc.load();
	}
	
	public int createKey() {
		int key=0;
		
		ArrayList<Integer> keyList = new ArrayList<Integer>();
		
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
		
		String sql = " SELECT TICKETNO FROM TICKETDATA ";
		
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				keyList.add(rs.getInt(1));
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
		
		while(true) {
			key = (int)(Math.random()*89999999)+10000000;
			
			if(!keyList.contains(key)) break;
		}
		
		System.out.println("키 생성 완료: "+key);
		
		return key;
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
		
		String sql = " SELECT TICKETNO, NAME, ID, PW, GAME, BLOCK, SEAT, PRICE, PAYTIME FROM TICKETDATA ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+", " + rs.getString(2)+", " + rs.getString(3)+", " + rs.getString(4)
				+", " + rs.getString(5)+", " + rs.getString(6)+", " + rs.getString(7)+", " + rs.getInt(8)+", " + rs.getString(9));
				
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
	
	
	public void save(TicketingInfo ti) {
		
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
		String sql = " INSERT INTO TICKETDATA VALUES(?,?,?,?,?,?,?,?,?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, ti.getTicketingNo());
			pstm.setString(2, ti.getName());
			pstm.setString(3, ti.getID());
			pstm.setString(4, ti.getPW());
			pstm.setString(5, ti.getGame());
			pstm.setString(6, ti.getBlock());
			pstm.setString(7, ti.SeatToString());
			pstm.setInt(8, ti.getTotalPrice());
			pstm.setString(9, ti.getTicketingDate());
			res = pstm.executeUpdate();
			
			if(res>0)
				System.out.println("INSERT 성공");
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
	
	public void delete(TicketingInfo ti) {
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
				
				String sql = " DELETE FROM TICKETDATA WHERE TICKETNO=? ";
				
				try {
					
					pstm = con.prepareStatement(sql);
					pstm.setInt(1,ti.getTicketingNo());

					res = pstm.executeUpdate();
					
					if(res>0) {
						con.commit();
						System.out.println("삭제 완료");
					}
					
					/*while(rs.next()) {
						System.out.println(rs.getString(1)+" : ["+rs.getString(2)+"/"+rs.getString(3)+"/"+rs.getString(4)+"]");				
					}*/
					
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

	public ArrayList<TicketingInfo> getTicketingInfo(String id){
		ArrayList<TicketingInfo> arr = new ArrayList<TicketingInfo>();
		
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
		
		String sql = " SELECT TICKETNO, NAME, ID, PW, GAME, BLOCK, SEAT, PRICE, PAYTIME FROM TICKETDATA ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				if(id.equals(rs.getString(3))) {
					TicketingInfo t = new TicketingInfo(rs.getString(3),rs.getString(4),rs.getString(2));
					t.setGame(rs.getString(5));
					t.setTicketingNo(rs.getInt(1));
					t.setBlock(rs.getString(6));
					t.setTicketingDate2(rs.getString(9));
					
					String[] sarr = rs.getString(7).split(", ");
					for(String s : sarr) {
						t.setSeat(s);
					}
					t.setTotalPrice();
					
					arr.add(t);
				}
				
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
	
	
}
