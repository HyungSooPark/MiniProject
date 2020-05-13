package view.user.deleteUser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UserInfo;

public class PWError extends JPanel {
	public PWError(JFrame mf, JPanel tmp, JFrame dp,UserInfo ui) {
		this.setSize(250,180);
		this.setLayout(null);
		
		JLabel label = new JLabel("비밀번호가 일치하지 않습니다.");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 35, 250, 30);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 190, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replace(mf,tmp,dp,ui);	
			}
		});
		
		this.add(label);
		this.add(button);
		
		dp.add(this);
	}
	
	public void replace(JFrame mf, JPanel tmp, JFrame dp, UserInfo ui) {
		dp.remove(this);
		new PWCheck(mf,tmp,dp,ui);
		dp.revalidate();
	}
}
