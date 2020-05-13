package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderInfoDao {
	private ArrayList<OrderInfo> info = new ArrayList<OrderInfo>();
	//사용자 주문내역 저장
	public int insertOrder(OrderInfo orderInfo) {
		
		try(ObjectInputStream orderin = new ObjectInputStream(new FileInputStream("OrderInfo.dat"));) {
			info = (ArrayList<OrderInfo>) orderin.readObject();
			info.add(orderInfo);
		} catch (IOException e) {
			makeInfo();
			return 0;
		} catch (ClassNotFoundException e) {
			makeInfo();
			return 0;
		}
		
		try(ObjectOutputStream orderout = new ObjectOutputStream(new FileOutputStream("OrderInfo.dat"))) {
			orderout.writeObject(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(info);
		return 1;
	}
	
	public void makeInfo() {
		HashMap<String, int[]> calculate = null;
		try(ObjectOutputStream orderout = new ObjectOutputStream(new FileOutputStream("OrderInfo.dat"))) {
			orderout.writeObject(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
