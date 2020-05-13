package view.user.logIn_logOut;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.TicketingInfo;
import model.UserInfo;
import view.select.SelectMenu;
import view.ticketing.TicketingFinal;

public class LoginSuccess extends JPanel {
	public LoginSuccess(JFrame mf, JPanel tmp, JFrame sp,UserInfo ui) {
		this.setSize(250,180);
		this.setLayout(null);
		
		JLabel label = new JLabel(ui.getName()+"님 환영합니다!");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 35, 250, 30);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 190, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.dispose();	
				replace(mf,tmp,ui);
			}
		});
		
		this.add(label);
		this.add(button);
		
		sp.add(this);
	}
	
	public void replace(JFrame mf,JPanel tmp,UserInfo ui) {
		mf.remove(tmp);
		new SelectMenu(mf,ui);
		mf.revalidate();
	}
}
