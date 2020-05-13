package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.UserInfoController;
import model.UserInfo;
import view.user.logIn_logOut.LoginError;
import view.user.logIn_logOut.LoginSuccess;
import view.user.signIn.SignIn;

public class MainViewPanel extends JPanel{
	public MainViewPanel(JFrame mf) {
		this.setSize(400,600);
		this.setLayout(null);
		
		mf.setTitle("잠실 야구장 이용 서비스");
		
		Image icon = new ImageIcon("images/dusan_logo.png").getImage().getScaledInstance(340, 270, 100);
		JLabel image = new JLabel(new ImageIcon(icon));
		image.setBounds(50, 10, 300, 280);
		this.add(image);
		
		JLabel id = new JLabel("아 이 디  :");
		id.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		id.setBounds(20,310,90,16);
		this.add(id);
		
		JTextField idInput = new JTextField(20);
		idInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		idInput.setBounds(112,305,260,30);
		idInput.setHorizontalAlignment(JTextField.CENTER);
		this.add(idInput);
		
		JLabel pw = new JLabel("비밀번호 :");
		pw.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pw.setBounds(20,360,90,16);
		this.add(pw);
		
		JPasswordField pwInput = new JPasswordField();
		pwInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pwInput.setBounds(112,355,260,30);
		pwInput.setEchoChar('*');
		pwInput.setHorizontalAlignment(JTextField.CENTER);
		this.add(pwInput);
		
		JButton login = new JButton("로그인");
		login.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		login.setBounds(80,408,100,30);
		this.add(login);
		JPanel tmp = this;
		
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfoController uic = new UserInfoController();
				ArrayList<UserInfo> userList = uic.getUserInfoList();
				String idpw = idInput.getText()+"/"+pwInput.getText();
				UserInfo ui = null;
				
				if(userList.size()>0) {
					for(int i=0;i<userList.size();i++) {
						if(userList.get(i).toString().equals(idpw)) {
							ui = userList.get(i);
						}
					}
					
					if(ui!=null) SuccessPopup(mf,tmp,ui);
					else {
						ErrorPopup();
						pwInput.setText(null);
					}
				}
				else {
					ErrorPopup();
					pwInput.setText(null);
				}
				
			}
		});
		
		JButton signin = new JButton("회원가입");
		signin.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		signin.setBounds(220,408,100,30);
		this.add(signin);
		signin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replace(mf);
				
			}
		});
		
		Image banner = new ImageIcon("images/banner.png").getImage().getScaledInstance(360, 90, 100);
		JLabel image2 = new JLabel(new ImageIcon(banner));
		image2.setBounds(17, 470, 360, 90);
		this.add(image2);
		
		mf.add(this);
	}
	
	public void replace(JFrame mf) {
		mf.remove(this);
		new SignIn(mf);
		mf.revalidate();
		//mf.repaint();
	}
	
	public void ErrorPopup() {
		JFrame ep = new JFrame();
		ep.setTitle("로그인 실패");
		ep.setSize(250,180);
		ep.setLocationRelativeTo(null);
				
		try {
			ep.setIconImage(ImageIO.read(new File("images/error.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new LoginError(ep);
		
		ep.setResizable(false);
		ep.setVisible(true);
		
		ep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void SuccessPopup(JFrame mf,JPanel tmp,UserInfo ui) {
		JFrame sp = new JFrame();
		
		try {
			sp.setIconImage(ImageIO.read(new File("images/success.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sp.setTitle("로그인 성공");
		sp.setSize(250,180);
		sp.setLocationRelativeTo(null);
				
		new LoginSuccess(mf,tmp,sp,ui);
		
		sp.setResizable(false);
		sp.setVisible(true);
		
		sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
