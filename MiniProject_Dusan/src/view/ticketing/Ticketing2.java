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

import controller.BlockController;
import model.TicketingInfo;
import model.UserInfo;
import view.select.SelectMenu;

public class Ticketing2 extends JPanel{
	public Ticketing2(JFrame mf,TicketingInfo ti,UserInfo ui) {
		this.setSize(400,600);
		this.setLayout(null);
		
		mf.setTitle("블록 선택");
		
		Image icon = new ImageIcon("images/block.PNG").getImage().getScaledInstance(400, 320, 0);
		JLabel image = new JLabel(new ImageIcon(icon));
		image.setBounds(0, 0, 400, 320);
		
		JButton back = new JButton("←");
		back.setBounds(10, 10, 50, 30);
		this.add(back);
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				back(mf,ti,ui);
			}
		});
		
		BlockController bc = new BlockController();
		String[] area = bc.getBlockList();
		
		JList list = new JList(area);
		list.setBorder(BorderFactory.createLineBorder(Color.black,1));
		list.setFont(new Font("맑은 고딕",Font.PLAIN, 14));
		
		JScrollPane scroller = new JScrollPane(list);
		scroller.setBounds(10, 330, 380, 100);
		
		JLabel label = new JLabel("블록:");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label.setBounds(5, 443,100,16);
		
		JTextField selected = new JTextField(23);
		selected.setEditable(false);
		selected.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		selected.setBounds(45,442,270,20);
		selected.setForeground(Color.BLUE);
		selected.setHorizontalAlignment(JTextField.CENTER);
		
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selected.setText((String)list.getSelectedValue());
			}
		});
		
		JButton button = new JButton("선택");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(325,437,65,30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ti.setBlock((String)list.getSelectedValue());
				System.out.println(ti.getBlock());
				if(ti.getBlock()!=null) replace(mf,ti,ui);
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
		new Ticketing3(mf,ti,ui);
		mf.revalidate();
	}
	
	public void back(JFrame mf,TicketingInfo ti,UserInfo ui) {
		mf.remove(this);
		ti.setBlock(null);
		new Ticketing(mf,ti,ui);
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
		
		new ErrorPopup(pp,"블록이");
		
		pp.setResizable(false);
		pp.setVisible(true);
		
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
