package view.delivery;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controller.Delivery;
import model.UserInfo;
import view.select.SelectMenu;

public class DeliveryView extends JPanel{
	private ArrayList<String> sMenu = new ArrayList<String>();					//메뉴 종류
	private JComboBox<String> sMenuList = new JComboBox<String>();				//메뉴 종류 리스트
	private ArrayList<ArrayList<String>> menuInfo;								//메뉴 정보
	private HashMap<String, int[]> calculate = new HashMap<String, int[]>();	//총 주문한 메뉴,수량,금액
	
	private String menuName;		//선택한 메뉴의 이름
	private String selectList;		//리스트에서 선택한 메뉴
	private int[] calculateInfo;	//계산서에 저장할 수량과 재고
	private int tPrice;				//총 주문금액
	private int menuPrice;			//선택한 메뉴의 가격
	private int menuStock;			//선택한 메뉴의 재고
	private int selNum;				//선택한 주문목록의 인덱스
	private int rowNum;				//선택한 메뉴의 인덱스
	
	private JFrame mf;				//메인프레임
	
	private JPanel panel1;			//상단 패널
	private JPanel panel2;			//중단 패널
	private JPanel panel3;			//하단 패널
	
	private JButton backBtn;		//뒤로가기 버튼
	private JButton payBtn;			//결제 버튼
	private JButton add;			//물품추가 버튼
	private JButton sub;			//물품감소 버튼
	private JTable model;			//메뉴정보
	private JTable orderList;		//주문한메뉴리스트
	private JLabel totalLabel;		//총 주문가격 표시
	private JLabel imageLabel;		//메뉴선택시 이미지표시
	private JScrollPane scroller1 = null;
	private JScrollPane scroller2 = null;
	private DefaultListModel<String> totalList = new DefaultListModel<String>();
	private DefaultTableModel model2;
	private JList<String> total = new JList<String>();	//주문할 목록
	private String[][] menuArray;
	
	public DeliveryView(JFrame mf,UserInfo ui) {
		this.mf = mf;
		setLayout(null);
		setBounds(0, 0, 500, 500);
		setBackground(Color.white);

		//가게목록 가져오기
		sMenu = new Delivery().getMenuName();
		for(int i=0; i<sMenu.size(); i++) {
			sMenuList.addItem(sMenu.get(i));
		}
		
		model2 = new DefaultTableModel();
		//테이블 수정불가
		orderList = new JTable(model2) {
			@Override
			public boolean isCellEditable(int rowIndox, int mColIndex) {
				return false;
			}
		};
		//테이블 선 지우기
		orderList.setShowHorizontalLines(false);	
		orderList.setShowVerticalLines(false);
		
		model2.addColumn("메뉴");
		model2.addColumn("수량");
		model2.addColumn("금액");
		
		panel1= new JPanel();
		panel2= new JPanel();
		panel3= new JPanel();
		
		panel1.setLayout(null);
		panel2.setLayout(null);
		panel3.setLayout(null);
		panel1.setBounds(0, 0, 400, 60);
		panel2.setBounds(0, 60, 400, 450);
		panel3.setBounds(0, 520, 400, 70);
		
		backBtn = new JButton("뒤로가기");
		payBtn = new JButton("주문하기"); 
		add = new JButton("추가");
		sub = new JButton("삭제");
		
		sMenuList.setBounds(150, 15, 100, 25);
		backBtn.setBounds(10, 15, 90, 25);
		payBtn.setBounds(155, 10, 90, 25);

		panel1.setOpaque(false);	//투명화
		panel2.setOpaque(false);	//투명화
		panel3.setOpaque(false);	//투명화
		panel1.add(backBtn);
		panel1.add(sMenuList);
		panel3.add(payBtn);
		add(panel1);
		add(panel2);
		add(panel3);
		
		actionEvent(ui);
	}
	
	private void actionEvent(UserInfo ui) {
		//서비스 선택 화면으로
		backBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back(ui);
			}
		});
		
		//결제 화면으로
		payBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pay(ui);
			}
		});
		
		//가게이름 선택후 메뉴 표시
		sMenuList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showMenu(e);
			}
		});
		
		//메뉴 추가
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMenu();				
			}
		});

		//메뉴 삭제
		sub.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				subMenu();
			}
		});
		
	}
	
	
	public void back(UserInfo ui) {
		mf.remove(this);
		mf.add(new SelectMenu(mf,ui));
		mf.revalidate();
		mf.repaint();
	}
	
	public void pay(UserInfo ui) {
		if(calculate.isEmpty()) {
			JOptionPane.showMessageDialog(null, "주문목록이 비어있습니다.");
		}else {
			mf.remove(this);
			mf.add(new PayView(mf, calculate, tPrice,ui));
			mf.revalidate();
			mf.repaint();
		}
	}
	
	//추가시 메뉴를 주문테이블에 추가
	public void addMenu() {
		if(!(menuName == null)) {
			if(menuStock==0) {
				JOptionPane.showMessageDialog(null, "재고없음");
			}else {
				calculateInfo = new int[2];
				if(!calculate.containsKey(menuName)) {
					calculateInfo[0] = menuPrice;
					calculateInfo[1] = 1;
					calculate.put(menuName, calculateInfo);
					tPrice += menuPrice;
					model2.addRow(new Object[]{menuName, 1 + "개", calculateInfo[0] + "원"});
				}else {
					calculateInfo[0] = calculate.get(menuName)[0] + menuPrice;
					calculateInfo[1] = calculate.get(menuName)[1] + 1;
					if(menuStock>=calculateInfo[1]) {
						calculate.put(menuName, calculateInfo);
						tPrice += menuPrice;
						for(int i=0; i<model2.getRowCount(); i++) {
							if(menuName.equals(model2.getValueAt(i, 0))){
								model2.setValueAt(calculateInfo[1] + "개", i, 1);
								model2.setValueAt(calculateInfo[0] + "원", i, 2);
								break;
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "재고부족");
					}
				}
			}
		}
		
		totalLabel.setText("총 주문금액 : " + tPrice + "원");
		total.setModel(totalList);
	}
	
	//삭제시 주문테이블에 메뉴 수량 1감소
	public void subMenu() {
		int selPrice = 0;
		if(!(selectList == null)) {
			for(int i=0; i<model2.getRowCount(); i++) {
				if(selectList.equals(model2.getValueAt(i, 0))){
					selPrice = Integer.parseInt(model2.getValueAt(i, 2).toString().replaceAll("[^0-9]", ""))/
							Integer.parseInt(model2.getValueAt(i, 1).toString().replaceAll("[^0-9]", ""));
					break;
				}
			}
			calculateInfo = new int[2];
			if(calculate.containsKey(selectList)) {
				if(calculate.get(selectList)[1] > 1) {
					calculateInfo[0] = calculate.get(selectList)[0] - selPrice;
					calculateInfo[1] = calculate.get(selectList)[1] - 1;
					calculate.put(selectList, calculateInfo);
					tPrice -= selPrice;
					for(int i=0; i<model2.getRowCount(); i++) {
						if(selectList.equals(model2.getValueAt(i, 0))){
							model2.setValueAt(calculateInfo[1] + "개", i, 1);
							model2.setValueAt(calculateInfo[0] + "원", i, 2);
							break;
						}
					}
				}else {
					calculate.remove(selectList);
					tPrice -= selPrice;
					for(int i=0; i<model2.getRowCount(); i++) {
						if(selectList.equals(model2.getValueAt(i, 0))){
							model2.removeRow(i);
							selectList = null;
							break;
						}
					}
				}
			}
		}
		
		totalLabel.setText("총 주문금액 : " + tPrice + "원");
		total.setModel(totalList);
		total.setSelectedIndex(selNum);
	}
	
	//메뉴 선택시 이미지 띄우기
	public void showImage(String sMenu) {
		int num = rowNum + 1;
		Image image;
		if(sMenu.equals("메인 메뉴")) {
			image = new ImageIcon("images/menu/main/"+num+".jpg").getImage().getScaledInstance(150, 150, 0);
		}else if(sMenu.equals("사이드 메뉴")){
			image = new ImageIcon("images/menu/side/"+num+".jpg").getImage().getScaledInstance(150, 150, 0);
		}else {
			image = new ImageIcon("images/menu/drink/"+num+".jpg").getImage().getScaledInstance(150, 150, 0);
		}
		
		imageLabel.setIcon(new ImageIcon(image));
	}
	
	public void showMenu(ActionEvent e) {
		panel2.removeAll();
		selectList = null;
		menuName = null;
		imageLabel = new JLabel();
		//메뉴테이블 컬럼
		String[] columnType = { "메뉴", "가격", "재고"};
		//메뉴정보 불러와서 저장
		JComboBox<String> cb = (JComboBox<String>)e.getSource();
		String sMenu = (String) cb.getSelectedItem();
		menuInfo = new Delivery().getMenuInfo(sMenu);
		menuArray = new String[menuInfo.size()][];
		//메뉴 테이블에 들어갈 정보들
		for (int i=0; i<menuInfo.size(); i++) {
		    ArrayList<String> row = menuInfo.get(i);
		    row.set(1, row.get(1) + "원");
		    row.set(2, row.get(2) + "개");
		    menuArray[i] = row.toArray(new String[row.size()]);
		}
		
		//내용 수정 불가
		model = new JTable(menuArray, columnType) {
			@Override
			public boolean isCellEditable(int rowIndox, int mColIndex) {
				return false;
			}
		};
		
		//메뉴목록테이블 설정
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);	//셀 우측정렬
		TableColumnModel tcm = model.getColumnModel();
		tcm.getColumn(1).setCellRenderer(dtcr);				//2번째 셀 우측정렬
		tcm.getColumn(2).setCellRenderer(dtcr);				//3번째 셀 우측정렬
		//컬럼 크기 설정
		model.getColumnModel().getColumn(0).setPreferredWidth(200);	
		model.getColumnModel().getColumn(1).setPreferredWidth(120);
		model.getColumnModel().getColumn(2).setPreferredWidth(80);
		model.getTableHeader().setReorderingAllowed(false);	//컬럼 이동불가
		model.getTableHeader().setResizingAllowed(false);	//컬럼 크기 조절불가
		model.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//주문목록테이블 설정
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm2 = orderList.getColumnModel();
		tcm2.getColumn(1).setCellRenderer(dtcr2);
		tcm2.getColumn(2).setCellRenderer(dtcr2);
		//컬럼 크기 설정
		orderList.getColumnModel().getColumn(0).setPreferredWidth(240);
		orderList.getColumnModel().getColumn(1).setPreferredWidth(100);
		orderList.getColumnModel().getColumn(2).setPreferredWidth(100);
		orderList.getTableHeader().setReorderingAllowed(false);	//컬럼 이동불가
		orderList.getTableHeader().setResizingAllowed(false);	//컬럼 크기 조절불가
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		model.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				rowNum = model.getSelectedRow();
				menuName = (String)model.getValueAt(rowNum, 0);
				menuPrice = Integer.parseInt(((String)model.getValueAt(rowNum, 1)).replaceAll("[^0-9]", ""));
				menuStock = Integer.parseInt(((String)model.getValueAt(rowNum, 2)).replaceAll("[^0-9]", ""));
				showImage(sMenu);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		orderList.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int rowNum2 = orderList.getSelectedRow();
				selectList = (String) model2.getValueAt(rowNum2, 0);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		totalLabel = new JLabel("총 주문금액 : " + tPrice + "원");
		scroller1 = new JScrollPane(model);
		scroller2 = new JScrollPane(orderList);
		
		scroller1.setBounds(10, 25, 220, 150);
		scroller2.setBounds(25, 240, 340, 150);
		add.setBounds(120, 200, 70, 20);
		sub.setBounds(220, 200, 70, 20);
		totalLabel.setBounds(245, 395, 140, 20);
		imageLabel.setBounds(235, 25, 150, 150);
		
		panel2.add(scroller1);
		panel2.add(add);
		panel2.add(sub);
		panel2.add(scroller2);
		panel2.add(totalLabel);
		panel2.add(imageLabel);
		panel2.revalidate();
		panel2.repaint();
	}
}
