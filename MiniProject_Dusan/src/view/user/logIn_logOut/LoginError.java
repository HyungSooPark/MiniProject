package view.user.logIn_logOut;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginError extends JPanel{
	public LoginError(JFrame ep) {
		this.setSize(250,180);
		this.setLayout(null);
		
		JLabel label = new JLabel("로그인에 실패하였습니다.");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setBounds(0, 35, 250, 30);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		JButton button = new JButton("확인");
		button.setBounds(30, 90, 190, 30);
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ep.dispose();	
			}
		});
		
		this.add(label);
		this.add(button);
		
		ep.add(this);
	}
}
