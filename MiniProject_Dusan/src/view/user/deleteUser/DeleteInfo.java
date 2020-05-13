package view.user.deleteUser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.UserInfo;

public class DeleteInfo extends JPanel {
	public DeleteInfo(JFrame mf, JPanel tmp, JFrame dp,UserInfo ui) {
		this.setSize(250,180);
		this.setLayout(null);
		dp.setTitle("회원탈퇴");
		
		JLabel label = new JLabel("정말로 탈퇴 하시겠습니까?");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 35, 250, 30);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 80, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//dp.dispose();
				replace(mf,tmp,dp,ui);
			}
		});
		
		JButton button2 = new JButton("취소");
		button2.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button2.setBounds(140, 90, 80, 30);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.dispose();
			}
		});
		
		this.add(label);
		this.add(button);
		this.add(button2);
		
		dp.add(this);
	}
	
	public void replace(JFrame mf, JPanel tmp, JFrame dp, UserInfo ui) {
		dp.remove(this);
		new PWCheck(mf,tmp,dp,ui);
		dp.revalidate();
	}
}
