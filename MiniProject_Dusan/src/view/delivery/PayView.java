package view.delivery;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controller.Pay;
import model.UserInfo;
import view.select.SelectMenu;

public class PayView extends JPanel{
	private JFrame mf;
	private HashMap<String, int[]> calculate;
	private int tPrice;
	private JPanel panel;
	private JButton btn1;
	private JButton btn2;
	private DefaultTableModel model;
	private JTable orderList;
	private JScrollPane scroller;
	private JLabel totalLabel;
	private JLabel imgLabel;
	
	public PayView(JFrame mf, HashMap<String, int[]> calculate, int tPrice,UserInfo ui) {
		this.mf = mf;
		this.calculate = calculate;
		this.tPrice = tPrice;
		setLayout(null);
		setBackground(Color.white);
		imgLabel = new JLabel(new ImageIcon("images/complete.PNG"));
		panel = new JPanel();
		btn1 = new JButton("뒤로가기");
		btn2 = new JButton("결제하기");
		totalLabel = new JLabel("총 주문금액 : " + tPrice + "원");
		model = new DefaultTableModel();
		model.addColumn("메뉴");
		model.addColumn("수량");
		model.addColumn("금액");
		orderList = new JTable(model) {
			@Override
			public boolean isCellEditable(int rowIndox, int mColIndex) {
				return false;
			}
		};
		
		//테이블 선 제거
		orderList.setShowHorizontalLines(false);
		orderList.setShowVerticalLines(false);
		
		//2번째 3번째 셀 우측정렬
		DefaultTableCellRenderer dtcr2 = new DefaultTableCellRenderer();
		dtcr2.setHorizontalAlignment(SwingConstants.RIGHT);
		TableColumnModel tcm2 = orderList.getColumnModel();
		tcm2.getColumn(1).setCellRenderer(dtcr2);
		tcm2.getColumn(2).setCellRenderer(dtcr2);
		
		orderList.getColumnModel().getColumn(0).setPreferredWidth(180);
		orderList.getColumnModel().getColumn(1).setPreferredWidth(85);
		orderList.getColumnModel().getColumn(2).setPreferredWidth(85);
		orderList.getTableHeader().setReorderingAllowed(false);	//컬럼 이동불가
		orderList.getTableHeader().setResizingAllowed(false);	//컬럼 크기 조절불가
		orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//주문테이블에 주문목록 추가
		showList();
		
		scroller = new JScrollPane(orderList);
		
		btn1.setBounds(10, 15, 90, 25);
		btn2.setBounds(155, 530, 90, 25);
		scroller.setBounds(20, 30, 350, 280);
		totalLabel.setBounds(245, 315, 140, 20);
		panel.setBounds(0, 60, 400, 380);
		
		panel.setLayout(null);
		panel.add(scroller);
		panel.add(totalLabel);
		panel.setOpaque(false);
		add(btn1);
		add(btn2);
		add(panel);
		actionEvent(ui);
	}

	private void showList() {
		Iterator<String> keys = calculate.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			model.addRow(new Object[]{key, calculate.get(key)[1] + "개",calculate.get(key)[0] + "원"});
		}
	}
	
	//버튼 이벤트들
	private void actionEvent(UserInfo ui) {
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = 1;
				res = JOptionPane.showConfirmDialog(null, "뒤로가시겠습니까?", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if( res==0 ) {
					back(ui);
				}
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = 1;
				res = JOptionPane.showConfirmDialog(null, "결제하시겠습니까?", "결제확인", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if( res==0 ) {
					new Pay().payment(calculate);
					JOptionPane.showMessageDialog(null, imgLabel, "결제확인", JOptionPane.PLAIN_MESSAGE, null);
					selectService(ui);
				}
			}
		});
	}
	
	//주문화면으로 돌아가기
	private void back(UserInfo ui) {
		mf.remove(this);
		mf.add(new DeliveryView(mf,ui));
		mf.revalidate();
		mf.repaint();
	}
	
	//서비스 선택화면으로
	private void selectService(UserInfo ui) {
		mf.remove(this);
		mf.add(new SelectMenu(mf,ui));
		mf.revalidate();
		mf.repaint();
	}
}
