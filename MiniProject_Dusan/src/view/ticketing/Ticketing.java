package view.ticketing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.GameListController;
import model.TicketingInfo;
import model.UserInfo;
import view.select.SelectMenu;

public class Ticketing extends JPanel{
	public Ticketing(JFrame mf,TicketingInfo ti,UserInfo ui) {
		this.setSize(400,600);
		this.setLayout(null);
		
		mf.setTitle("경기 선택");
		
		Image icon = new ImageIcon("images/dusan_logo.png").getImage().getScaledInstance(340, 270, 100);
		JLabel image = new JLabel(new ImageIcon(icon));
		image.setBounds(50, 10, 300, 280);
		
		JButton back = new JButton("←");
		back.setBounds(10, 10, 50, 30);
		this.add(back);
		
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back(mf,ui);
			}
		});
		
		GameListController glc = new GameListController();
		String[] slist = glc.getGameList();
		
		JList list = new JList(slist);
		list.setBorder(BorderFactory.createLineBorder(Color.black,1));
		list.setFont(new Font("맑은 고딕",Font.PLAIN, 14));
				
		JScrollPane scroller = new JScrollPane(list);
		scroller.setBounds(10, 290, 380, 120);
	
		JLabel label = new JLabel("선택된 경기:");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label.setBounds(5,430,100,16);
		JTextField selected = new JTextField(20);
		selected.setEditable(false);
		selected.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		selected.setBounds(92,429,225,20);
		selected.setForeground(Color.BLUE);
		selected.setHorizontalAlignment(JTextField.CENTER);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String str = (String)list.getSelectedValue();
				
				selected.setText(str.substring(0,19)+str.substring(23,30));
			}
		});
		
		JButton button = new JButton("선택");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(325,424,65,30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {			
				ti.setGame((String)list.getSelectedValue());
				System.out.println(ti.getGame());
				if(ti.getGame()!=null) replace(mf,ti,ui);
				else NullPopup();
			}
		});
		
		Image banner = new ImageIcon("images/banner.png").getImage().getScaledInstance(360, 90, 100);
		JLabel image2 = new JLabel(new ImageIcon(banner));
		image2.setBounds(17, 470, 360, 90);
		
		
		this.add(image);
		this.add(scroller);
		this.add(label);
		this.add(selected);
		this.add(button);
		this.add(image2);
		
		mf.add(this);
	}
	
	public void replace(JFrame mf,TicketingInfo ti,UserInfo ui) {
		mf.remove(this);
		new Ticketing2(mf,ti,ui);
		mf.revalidate();
		mf.repaint();
	}
	
	public void back(JFrame mf,UserInfo ui) {
		mf.remove(this);
		new SelectMenu(mf,ui);
		mf.revalidate();
		mf.repaint();
	}
	
	public void NullPopup() {
		JFrame pp = new JFrame();
		
		pp.setSize(250,180);
		pp.setLocationRelativeTo(null);
		
		pp.setTitle("선택 오류");
		
		try {
			pp.setIconImage(ImageIO.read(new File("images/error.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		new ErrorPopup(pp,"경기가");
		
		pp.setResizable(false);
		pp.setVisible(true);
		
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
