package view.user.signIn;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IDError extends JPanel {
	public IDError(JFrame ep) {
		this.setSize(250,180);
		this.setLayout(null);
		ep.setTitle("가입 실패");
		
		JLabel label = new JLabel("이미 존재하는 ID입니다.");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 35, 250, 30);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 190, 30);
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
