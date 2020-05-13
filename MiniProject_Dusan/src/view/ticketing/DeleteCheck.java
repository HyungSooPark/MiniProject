package view.ticketing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TicketingController;
import model.TicketingInfo;
import model.UserInfo;
import view.select.SelectMenu;

public class DeleteCheck extends JPanel {
	public DeleteCheck(JFrame mf,JPanel tmp,JFrame cp,TicketingInfo ti,UserInfo ui) {
		this.setSize(250,180);
		this.setLayout(null);
		
		JLabel label = new JLabel("예매내역을 취소 하시겠습니까?");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 35, 250, 30);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 80, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				} finally {
					cp.dispose();
					TicketingController tc = new TicketingController();
					tc.delete(ti);
					replace(mf,tmp,ui);
				}
				
			}
		});
		
		JButton button2 = new JButton("취소");
		button2.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button2.setBounds(140, 90, 80, 30);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cp.dispose();
			}
		});
		
		this.add(label);
		this.add(button);
		this.add(button2);
		
		cp.add(this);
	}
	
	public void replace(JFrame mf,JPanel tmp,UserInfo ui) {
		mf.remove(tmp);
		new SelectMenu(mf,ui);
		mf.revalidate();
	}
}
