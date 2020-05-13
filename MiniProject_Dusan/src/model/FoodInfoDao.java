package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FoodInfoDao {
	private ArrayList<FoodInfo> info = new ArrayList<FoodInfo>();
	private ArrayList<FoodInfo> info3 = new ArrayList<FoodInfo>();
	
	public static void main(String[] args) {
		FoodInfoDao fi = new FoodInfoDao();
		fi.addMenu();
	}
	//음식가게,메뉴정보
	public ArrayList<FoodInfo> foodInfo() {
		try(ObjectInputStream foodin = new ObjectInputStream(new FileInputStream("FoodInfo.dat"));) {
			info = (ArrayList<FoodInfo>) foodin.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	//재고수정
	public void ModifyFoodInfo(HashMap<String, int[]> calculate) {
		try(ObjectInputStream foodin = new ObjectInputStream(new FileInputStream("FoodInfo.dat"));) {
			info = (ArrayList<FoodInfo>) foodin.readObject();
			
			Iterator<String> keys = calculate.keySet().iterator();
			while(keys.hasNext()) {
				String key = keys.next();
				for(int i=0; i<info.size(); i++) {
					if(info.get(i).getMenu().equals(key)) {
						info.get(i).setStock(info.get(i).getStock() - calculate.get(key)[1]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try(ObjectOutputStream foodout = new ObjectOutputStream(new FileOutputStream("FoodInfo.dat"))) {
			foodout.writeObject(info);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addMenu() {
		info3.add(new FoodInfo("메인 메뉴", "황금올리브", 18000, 9));
		info3.add(new FoodInfo("메인 메뉴", "황금올리브(순살)", 20000, 150));
		info3.add(new FoodInfo("메인 메뉴", "황금올리브(닭다리)", 19000, 150));
		info3.add(new FoodInfo("메인 메뉴", "황금올리브(핫윙)", 18000, 150));
		info3.add(new FoodInfo("메인 메뉴", "시크릿 양념", 19500, 150));
		info3.add(new FoodInfo("메인 메뉴", "황금올리브 양념(순살)", 21500, 150));
		info3.add(new FoodInfo("메인 메뉴", "마라핫치킨", 18900, 150));
		info3.add(new FoodInfo("메인 메뉴", "치즐링", 16500, 150));
		info3.add(new FoodInfo("메인 메뉴", "치즐링순살크래커", 18500, 150));
		info3.add(new FoodInfo("메인 메뉴", "극한왕갈비치킨", 21000, 150));
		info3.add(new FoodInfo("메인 메뉴", "황금올리브반반", 19000, 150));
		info3.add(new FoodInfo("메인 메뉴", "황금올리브반반(순살)", 21000, 150));
		info3.add(new FoodInfo("사이드 메뉴", "뿌링소떡", 3000, 170));
		info3.add(new FoodInfo("사이드 메뉴", "빨간소떡", 3000, 170));
		info3.add(new FoodInfo("사이드 메뉴", "케이준프라이", 3000, 170));
		info3.add(new FoodInfo("사이드 메뉴", "뿌링감자", 4000, 170));
		info3.add(new FoodInfo("사이드 메뉴", "뿌링치즈볼", 5500, 170));
		info3.add(new FoodInfo("사이드 메뉴", "콜팝", 2500, 170));
		info3.add(new FoodInfo("사이드 메뉴", "빅콜팝", 11000, 170));
		info3.add(new FoodInfo("음료", "사이다", 2000, 2));
		info3.add(new FoodInfo("음료", "코카콜라", 2000, 200));
		info3.add(new FoodInfo("음료", "제로콜라", 2000, 200));
		info3.add(new FoodInfo("음료", "펩시", 1700, 200));
		info3.add(new FoodInfo("음료", "환타", 1700, 200));
		info3.add(new FoodInfo("음료", "마운틴듀", 1700, 200));
		info3.add(new FoodInfo("음료", "맥주", 5000, 500));
		info3.add(new FoodInfo("음료", "소주", 4000, 500));
		
		
		
		try(ObjectOutputStream foodout = new ObjectOutputStream(new FileOutputStream("FoodInfo.dat"))) {
			foodout.writeObject(info3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
