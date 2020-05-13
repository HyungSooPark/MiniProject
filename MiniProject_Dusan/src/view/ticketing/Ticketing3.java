package view.ticketing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.BlockController;
import model.TicketingInfo;
import model.UserInfo;

public class Ticketing3 extends JPanel {
	String[] chair = null;
	
	public Ticketing3(JFrame mf,TicketingInfo ti,UserInfo ui) {
		this.setSize(400,600);
		this.setLayout(null);
		
		mf.setTitle("좌석 선택");
		
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
		JComboBox box = new JComboBox(area);
		box.setSelectedItem(ti.getBlock());
		box.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		box.setBounds(77, 10, 156, 50);
		box.setBackground(Color.WHITE);
		
		this.add(box);
		
		box.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ti.setBlock(box.getSelectedItem().toString());
				System.out.println(ti.getBlock());
			}
		});
		
		JLabel seat1 = new JLabel("■ VIP석(1~14): 23000원");
		seat1.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		seat1.setBounds(240, 5, 200, 20);
		seat1.setForeground(Color.PINK);
		this.add(seat1);
		
		JLabel seat2 = new JLabel("■ S석(15~49): 21000원");
		seat2.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		seat2.setBounds(246, 25, 200, 20);
		seat2.setForeground(Color.ORANGE);
		this.add(seat2);
		
		JLabel seat3 = new JLabel("■ R석(50~63): 18000원");
		seat3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		seat3.setBounds(246, 45, 200, 20);
		seat3.setForeground(Color.RED);
		this.add(seat3);
		
		JLabel label2 = new JLabel("선택된 좌석 수:");
		label2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label2.setBounds(10, 390, 100, 30);
		this.add(label2);
		
		JTextField selected = new JTextField(23);
		selected.setEditable(false);
		selected.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		selected.setBounds(115,395,210,20);
		selected.setForeground(Color.BLUE);
		this.add(selected);
		
		JLabel label3 = new JLabel("결제할 금액:");
		label3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label3.setBounds(10, 430, 100, 30);
		this.add(label3);
		
		JTextField selected2 = new JTextField(23);
		selected2.setEditable(false);
		selected2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		selected2.setBounds(95,435,230,20);
		selected2.setForeground(Color.BLUE);
		this.add(selected2);
				
		for(int i=0;i<9;i++) {
			for(int j=0;j<7;j++) {
				JButton button = new JButton(String.valueOf(j+1+(7*i)));
				button.setBounds(11+54*j, 70+34*(i), 50, 30);
				this.add(button);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(Integer.parseInt(button.getText())<15) {
							button.setBackground(Color.PINK);
						}
						else if(Integer.parseInt(button.getText())>49) {
							button.setBackground(Color.RED);
						}
						else {
							button.setBackground(Color.ORANGE);
						}
						
						ti.setSeat(button.getText());
						for(int i=0;i<ti.getSeatList().length;i++) {
							System.out.print(ti.getSeatList()[i]+" ");
						}
						System.out.println();
						ti.setTotalPrice();
						selected.setText("\t           "+String.valueOf(ti.getSeatList().length)+" 석");
						selected2.setText("\t         "+ti.getTotalPrice()+" 원");
					}
				});
			}
		}
		
		
		Image banner = new ImageIcon("images/banner.png").getImage().getScaledInstance(360, 90, 100);
		JLabel image2 = new JLabel(new ImageIcon(banner));
		image2.setBounds(17, 470, 360, 90);
		this.add(image2);
		
		JButton button = new JButton("결제");
		button.setBounds(330, 390, 60, 70);		
		
		JPanel tmp = this;
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Popup(mf,tmp,ti,ui);
			}
		});
		this.add(button);
		
		
		mf.add(this);
		
	}
	
	public void back(JFrame mf,TicketingInfo ti,UserInfo ui) {
		mf.remove(this);
		ti.clearSeat();
		new Ticketing2(mf,ti,ui);
		mf.revalidate();
		mf.repaint();
	}
	
	public void Popup(JFrame mf, JPanel tmp, TicketingInfo ti,UserInfo ui) {
		JFrame pp = new JFrame();
		
		pp.setSize(250,180);
		pp.setLocationRelativeTo(null);
		
		pp.setTitle("결제하기");
		
		try {
			pp.setIconImage(ImageIO.read(new File("images/payment_icon.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(ti.getSeatList().length>0) new PaymentPopup(mf,tmp,pp,ti,ui);
		else new ErrorPopup(pp,"좌석이");
		
		pp.setResizable(false);
		pp.setVisible(true);
		
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
