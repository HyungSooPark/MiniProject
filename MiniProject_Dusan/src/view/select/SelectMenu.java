package view.select;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.TicketingController;
import model.TicketingInfo;
import model.UserInfo;
import view.delivery.DeliveryView;
import view.ticketing.ErrorPopup;
import view.ticketing.Ticketing;
import view.ticketing.TicketingFinal;
import view.user.changeInfo.ChangeInfo;
import view.user.logIn_logOut.LoginSuccess;
import view.user.logIn_logOut.LogoutCheck;

public class SelectMenu extends JPanel {
	public SelectMenu(JFrame mf,UserInfo ui) {
		this.setSize(400,600);
		this.setLayout(null);
		
		mf.setTitle("메뉴 선택");
		
		Image user = new ImageIcon("images/user.png").getImage().getScaledInstance(60, 60, 100);
		JLabel image = new JLabel(new ImageIcon(user));
		image.setBounds(40, 35, 60, 60);
		this.add(image);
		
		JLabel name = new JLabel(ui.getName()+"님");
		name.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		name.setBounds(120, 30, 350, 40);
		this.add(name);
		
		TicketingController tc = new TicketingController();
		ArrayList<TicketingInfo> arr = tc.getTicketingInfo(ui.getId());
		JLabel ticket = new JLabel("예매내역: "+arr.size()+"건");
		ticket.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ticket.setBounds(120, 60, 350, 40);
		this.add(ticket);
		
		JButton change = new JButton("정보수정");
		change.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		change.setBounds(250,35,100,25);
		this.add(change);
		change.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Change(mf,ui);
			}
		});
		
		JLabel ticketing = new JLabel("■ 예매내역");
		ticketing.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		ticketing.setBounds(20, 120, 350, 40);
		this.add(ticketing);
		
		
		TicketingInfo[] tarr = (TicketingInfo []) arr.toArray(new TicketingInfo[arr.size()]);
		
		String[] TicketList = new String[tarr.length];
		for(int i=0;i<arr.size();i++) {
			TicketList[i] = tarr[i].toString();
		}
		
		JList list = new JList(TicketList);
		list.setBorder(BorderFactory.createLineBorder(Color.black,1));
		list.setFont(new Font("맑은 고딕",Font.PLAIN, 14));
				
		JScrollPane scroller = new JScrollPane(list);
		scroller.setBounds(20, 160, 355, 120);
		this.add(scroller);
		
		JLabel label = new JLabel("선택된 티켓:");
		label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		label.setBounds(20,300,100,16);
		this.add(label);
		
		JTextField selected = new JTextField(20);
		selected.setEditable(false);
		selected.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		selected.setBounds(105,299,193,20);
		selected.setForeground(Color.BLUE);
		selected.setHorizontalAlignment(JTextField.CENTER);
		this.add(selected);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String str = (String)list.getSelectedValue();
				
				selected.setText(str.substring(0, 16));
			}
		});
		
		JButton button = new JButton("확인");
		button.setFont(new Font("맑은 고딕",Font.BOLD,14));
		button.setBounds(305,294,65,30);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = (String)list.getSelectedValue();
				
				if(str==null) {
					NullPopup();
				}
				else {
					String ticketno = str.substring(7, 15);
					for(int i=0;i<arr.size();i++) {
						if(Integer.parseInt(ticketno) == arr.get(i).getTicketingNo()) {
							CheckTicket(mf,arr.get(i),ui);
						}
					}
				}
				
			}
		});
		this.add(button);
		
		JButton back = new JButton("로그아웃");
		back.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		back.setBounds(250,70,100,25);
		back.setBackground(Color.gray);
		back.setForeground(Color.white);
		this.add(back);
			
		JPanel tmp = this;
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckPopup(mf,tmp);
			}
		});
		
		JTable table = new JTable();
		table.setBounds(20, 20, 355, 90);
		table.setBorder(BorderFactory.createLineBorder(Color.black,1));
		this.add(table);
		
		Image ticketicon = new ImageIcon("images/ticket.png").getImage().getScaledInstance(80, 80, 100);
		JLabel ticketimage = new JLabel(new ImageIcon(ticketicon));
		ticketimage.setBounds(70, 340, 80, 80);
		this.add(ticketimage);
		
		JButton btn1 = new JButton("예매하기");
		btn1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn1.setBounds(60,418,100,30);
		this.add(btn1);
		
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TicketingInfo ti = new TicketingInfo(ui.getId(),ui.getPw(),ui.getName());
				ticketing(mf,ti,ui);
			}
		});
		
		Image chickenicon = new ImageIcon("images/chicken.png").getImage().getScaledInstance(70, 70, 100);
		JLabel chickenimage = new JLabel(new ImageIcon(chickenicon));
		chickenimage.setBounds(255, 335, 70, 70);
		this.add(chickenimage);
		
		JButton btn2 = new JButton("주문하기");
		btn2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		btn2.setBounds(240,418,100,30);
		this.add(btn2);
	
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delivery(mf,ui);
			}
		});
		
		
		Image banner = new ImageIcon("images/banner.png").getImage().getScaledInstance(360, 90, 100);
		JLabel image2 = new JLabel(new ImageIcon(banner));
		image2.setBounds(17, 470, 360, 90);
		this.add(image2);
		
		mf.add(this);
	}
	
	public void Change(JFrame mf,UserInfo ui) {
		mf.remove(this);
		new ChangeInfo(mf,ui);
		mf.revalidate();
		mf.repaint();
	}
	
	public void CheckTicket(JFrame mf,TicketingInfo ti,UserInfo ui) {
		mf.remove(this);
		new TicketingFinal(mf,ti,ui);
		mf.revalidate();
		mf.repaint();
	}
	
	public void ticketing(JFrame mf,TicketingInfo ti,UserInfo ui) {
		mf.remove(this);
		new Ticketing(mf,ti,ui);
		mf.revalidate();
		mf.repaint();
	}
	
	public void delivery(JFrame mf,UserInfo ui) {
		mf.remove(this);
		mf.add(new DeliveryView(mf,ui));
		mf.revalidate();
		mf.repaint();
	}
	
	public void CheckPopup(JFrame mf,JPanel tmp) {
		JFrame cp = new JFrame();
		
		try {
			cp.setIconImage(ImageIO.read(new File("images/logout.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cp.setTitle("로그아웃");
		cp.setSize(250,180);
		cp.setLocationRelativeTo(null);
				
		new LogoutCheck(mf,tmp,cp);
		
		cp.setResizable(false);
		cp.setVisible(true);
		
		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		new ErrorPopup(pp,"티켓이");
		
		pp.setResizable(false);
		pp.setVisible(true);
		
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
