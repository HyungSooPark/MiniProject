package view.user.deleteUser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.UserInfo;
import view.MainViewPanel;

public class PWCheck extends JPanel {
	public PWCheck(JFrame mf, JPanel tmp, JFrame dp,UserInfo ui) {
		this.setSize(250,180);
		this.setLayout(null);
		dp.setTitle("회원탈퇴");
		
		JLabel label = new JLabel("비밀번호를 입력해주십시오.");
		label.setFont(new Font("맑은 고딕",Font.BOLD,14));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setBounds(0, 10, 250, 30);

		JPasswordField pwInput = new JPasswordField();
		pwInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pwInput.setBounds(25,45,200,30);
		pwInput.setEchoChar('*');
		pwInput.setHorizontalAlignment(JTextField.CENTER);
		this.add(pwInput);
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(30, 90, 80, 30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//dp.dispose();
				//replace(mf,tmp);
				
				if(ui.getPw().equals(pwInput.getText())) {
					success(mf,tmp,dp,ui);
				}
				else {
					error(mf,tmp,dp,ui);
				}
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
	
	public void error(JFrame mf, JPanel tmp, JFrame dp, UserInfo ui) {
		dp.remove(this);
		new PWError(mf,tmp,dp,ui);
		dp.revalidate();
	}
	
	public void success(JFrame mf, JPanel tmp, JFrame dp, UserInfo ui) {
		dp.remove(this);
		new DeleteSuccess(mf,tmp,dp,ui);
		dp.revalidate();
	}
	
}
