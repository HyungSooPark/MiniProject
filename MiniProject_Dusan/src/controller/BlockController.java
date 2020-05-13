package controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Block;
import model.Game;

public class BlockController {

	public static void main(String[] args) {
		BlockController bc = new BlockController();
		//bc.save();
		bc.load();
	}

	public void save() {
		ArrayList<Block> arr = new ArrayList<Block>();
		
		arr.add(new Block("중앙테이블"));
		for(int i=301;i<335;i++) {
			arr.add(new Block(Integer.toString(i)+" 블록"));
		}
		for(int i=101;i<106;i++) {
			arr.add(new Block(Integer.toString(i)+" 블록"));
		}
		arr.add(new Block("외야자유석"));
		
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
		String sql = " INSERT INTO BLOCK VALUES(?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i=0;i<arr.size();i++) {
				pstm.setString(1, arr.get(i).getBlock());
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
		
		String sql = " SELECT BLOCK FROM BLOCK ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString(1));				
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
	
	public String[] getBlockList() {
		ArrayList<Block> arr = new ArrayList<Block>();
		
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
		
		String sql = " SELECT BLOCK FROM BLOCK ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				Block b = new Block(rs.getString(1));
				arr.add(b);
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
		
		Block[] block_list = (Block[]) arr.toArray(new Block[arr.size()]);
		
		String[] list = new String[block_list.length];
		
		for(int i=0;i<list.length;i++) {
			list[i] = block_list[i].toString();
		}
		
		return list;
	}
}
