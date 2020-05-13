package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.TicketingInfo;
import model.UserInfo;
import oracle.net.jdbc.TNSAddress.Address;
import view.ticketing.Ticketing;
import view.ticketing.Ticketing2;
import view.ticketing.Ticketing3;
import view.ticketing.TicketingFinal;
import view.user.signIn.SignIn;

public class MainFrame extends JFrame{
	
	public MainFrame() {
		this.setSize(400,600);
		this.setLocationRelativeTo(null);
		//프레임 상단에 타이틀 설정
		this.setTitle("잠실 야구장 이용 서비스");
		
		this.setLocationRelativeTo(null);
		
		try {
			this.setIconImage(ImageIO.read(new File("images/ball.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new MainViewPanel(this);
		
		this.setResizable(false);
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
