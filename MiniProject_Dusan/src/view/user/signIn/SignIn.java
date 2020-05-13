package view.user.signIn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;

import controller.UserInfoController;
import model.UserInfo;
import view.MainViewPanel;
import view.ticketing.ErrorPopup;

public class SignIn extends JPanel{
	public SignIn(JFrame mf) {
		this.setSize(400,600);
		this.setLayout(null);
		
		mf.setTitle("회원 가입");
		
		Image icon = new ImageIcon("images/icon.png").getImage().getScaledInstance(75, 60, 100);
		JLabel image = new JLabel(new ImageIcon(icon));
		image.setBounds(158, 20, 75, 60);
		this.add(image);
		
		Image banner = new ImageIcon("images/banner.png").getImage().getScaledInstance(360, 90, 100);
		JLabel image2 = new JLabel(new ImageIcon(banner));
		image2.setBounds(17, 470, 360, 90);
		this.add(image2);
	
		JLabel name = new JLabel("이     름  :");
		name.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		name.setBounds(20,120,90,16);
		this.add(name);
		
		JTextField nameInput = new JTextField(20);
		nameInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		nameInput.setBounds(112,115,260,30);
		nameInput.setHorizontalAlignment(JTextField.CENTER);
		this.add(nameInput);
		
		JLabel id = new JLabel("아 이 디  :");
		id.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		id.setBounds(20,180,90,16);
		this.add(id);
		
		JTextField idInput = new JTextField(20);
		idInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		idInput.setBounds(112,175,260,30);
		idInput.setHorizontalAlignment(JTextField.CENTER);
		this.add(idInput);
		
		JLabel pw = new JLabel("비밀번호 :");
		pw.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pw.setBounds(20,240,90,16);
		this.add(pw);
		
		JPasswordField pwInput = new JPasswordField();
		pwInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pwInput.setBounds(112,235,260,30);
		pwInput.setEchoChar('*');
		pwInput.setHorizontalAlignment(JPasswordField.CENTER);
		this.add(pwInput);
		
		JLabel check = new JLabel("확     인  :");
		check.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		check.setBounds(20,280,90,16);
		this.add(check);
		
		JPasswordField checkInput = new JPasswordField(20);
		checkInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		checkInput.setBounds(112,275,260,30);
		checkInput.setEchoChar('*');
		checkInput.setHorizontalAlignment(JPasswordField.CENTER);
		this.add(checkInput);
		
		JLabel phone = new JLabel("전화번호 :");
		phone.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		phone.setBounds(20,340,90,16);
		this.add(phone);
		
		JTextField phoneInput = new JTextField();
		phoneInput.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		phoneInput.setBounds(112,335,260,30);
		phoneInput.setHorizontalAlignment(JTextField.CENTER);
		this.add(phoneInput);
		
		JTable table = new JTable();
		table.setBounds(10, 100, 375, 280);
		table.setBorder(BorderFactory.createLineBorder(Color.black,1));
		this.add(table);
		
		
		JButton save = new JButton("저장");
		save.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		save.setBounds(80,408,100,30);
		this.add(save);
		
		JPanel tmp = this;
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserInfoController uic = new UserInfoController();
				ArrayList<String> IDList = uic.getIDList();
				
				if(IDList.contains(idInput.getText())) {
					ErrorPopup(3);
					idInput.setText(null);
				}
				else {
					if(nameInput.getText().equals("")||idInput.getText().equals("")||phoneInput.getText().equals("")
							|pwInput.getText().equals("")||checkInput.getText().equals("")) {
						ErrorPopup(1);
					}
					else {
						if(pwInput.getText().equals(checkInput.getText())) {
							UserInfo u = new UserInfo();
							u.setId(idInput.getText());
							u.setName(nameInput.getText());
							u.setPw(pwInput.getText());
							u.setPhone(phoneInput.getText());
							uic.save(u);
							SuccessPopup(mf,tmp);
						}
						else{
							ErrorPopup(2);
							pwInput.setText(null);
							checkInput.setText(null);
						}
					}
				}
				
			}
		});
		
		
		JButton back = new JButton("취소");
		back.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		back.setBounds(220,408,100,30);
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replace(mf);
			}
		});
		
		mf.add(this);
	}
	
	public void replace(JFrame mf) {
		mf.remove(this);
		new MainViewPanel(mf);
		mf.revalidate();
	}
	
	public void ErrorPopup(int i) {
		JFrame ep = new JFrame();
		
		ep.setSize(250,180);
		ep.setLocationRelativeTo(null);
				
		try {
			ep.setIconImage(ImageIO.read(new File("images/error.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(i==1) {
			new NullError(ep);
		}
		else if(i==2) {
			new PasswordError(ep);
		}
		else if(i==3) {
			new IDError(ep);
		}
		
		ep.setResizable(false);
		ep.setVisible(true);
		
		ep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void SuccessPopup(JFrame mf,JPanel tmp) {
		JFrame sp = new JFrame();
		
		sp.setSize(250,180);
		sp.setLocationRelativeTo(null);
				
		try {
			sp.setIconImage(ImageIO.read(new File("images/success.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new SuccessPanel(mf,tmp,sp);
		
		sp.setResizable(false);
		sp.setVisible(true);
		
		sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
