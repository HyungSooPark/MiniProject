package view.user.logIn_logOut;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.TicketingController;
import view.MainViewPanel;

public class LogoutCheck extends JPanel{
	public LogoutCheck(JFrame mf, JPanel tmp, JFrame cp) {
		this.setSize(250,180);
		this.setLayout(null);
		
		JLabel label = new JLabel("정말로 로그아웃 하시겠습니까?");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 35, 250, 30);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 80, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cp.dispose();
				logout(mf,tmp);
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
	
	public void logout(JFrame mf,JPanel tmp) {
		mf.remove(tmp);
		new MainViewPanel(mf);
		mf.revalidate();
		mf.repaint();
	}
}
