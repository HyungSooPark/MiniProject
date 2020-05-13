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
import javax.swing.JPanel;
import javax.swing.JTable;

import model.TicketingInfo;
import model.UserInfo;
import view.MainViewPanel;
import view.select.SelectMenu;
import view.user.logIn_logOut.LogoutCheck;

public class TicketingFinal extends JPanel {
	public TicketingFinal(JFrame mf,TicketingInfo ti,UserInfo ui) {
		this.setSize(400,600);
		this.setLayout(null);
		mf.setTitle("예매 내역 확인");
		
		JLabel label1 = new JLabel("예매가 완료되었습니다.");
		label1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label1.setBounds(100, 0, 350, 40);
		this.add(label1);
		
		JLabel home = new JLabel("두산 베어스");
		home.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		home.setBounds(95, 45, 200, 20);
		this.add(home);
		
		Image icon = new ImageIcon("images/icon.png").getImage().getScaledInstance(75, 60, 100);
		JLabel image = new JLabel(new ImageIcon(icon));
		image.setBounds(75, 50, 100, 100);
		this.add(image);
		
		insertAwayIcon(this,ti.getAwayTeam());
		
		JLabel label2 = new JLabel("VS");
		label2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		label2.setForeground(Color.gray);
		label2.setBounds(185, 90, 200, 20);
		this.add(label2);
		
		//경기 일시 출력
		JLabel label3 = new JLabel(ti.getGameDate());
		label3.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label3.setForeground(Color.blue);
		label3.setBounds(150, 135, 200, 20);
		this.add(label3);
		
		JLabel label4 = new JLabel("잠실종합운동장 야구장");
		label4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		label4.setForeground(Color.black);
		label4.setBounds(130, 155, 200, 20);
		this.add(label4);
		
		JLabel label5 = new JLabel("별도의 발권절차 없이 경기장 입장 가능");
		label5.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		label5.setForeground(Color.black);
		label5.setBounds(90, 200, 250, 20);
		this.add(label5);
		
		//바코드 이미지 출력
		Image barcode = new ImageIcon("images/barcode.jpg").getImage().getScaledInstance(300, 100, 100);
		JLabel image3 = new JLabel(new ImageIcon(barcode));
		image3.setBounds(50, 220, 300, 100);
		this.add(image3);
		
		//바코드 번호(예매번호 출력)
		JLabel ticketno = new JLabel(ti.getTicketingNoString());
		ticketno.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		ticketno.setForeground(Color.black);
		ticketno.setBounds(160, 315, 200, 20);
		this.add(ticketno);
		
		
		//중간 배경 테두리
		JTable table = new JTable();
		table.setBounds(20, 193, 360, 148);
		table.setBorder(BorderFactory.createLineBorder(Color.black,1));
		this.add(table);
		//맨위 배경 테두리
		JTable table2 = new JTable();
		table2.setBounds(20, 40, 360, 143);
		table2.setBorder(BorderFactory.createLineBorder(Color.black,1));
		this.add(table2);
		
		//예매자 정보 출력
		JLabel name = new JLabel("예매자");
		name.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		name.setForeground(Color.black);
		name.setBounds(30, 360, 200, 20);
		this.add(name);
		
		JLabel getname = new JLabel(ti.getName());	//객체에 저장된 이름 호출
		getname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		getname.setForeground(Color.black);
		getname.setBounds(140, 360, 200, 20);
		this.add(getname);
		
		//예약번호 출력
		JLabel ticketNum = new JLabel("예약번호");
		ticketNum.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		ticketNum.setForeground(Color.black);
		ticketNum.setBounds(30, 385, 200, 20);
		this.add(ticketNum);
		
		JLabel getTicketNum = new JLabel(ti.getTicketingNo()+" (총 "+ti.getSeatList().length+"매)");	//객체에 저장된 예약번호 호출
		getTicketNum.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		getTicketNum.setForeground(Color.black);
		getTicketNum.setBounds(140, 385, 200, 20);
		this.add(getTicketNum);
		
		//예매일 출력
		JLabel Date = new JLabel("예매일");
		Date.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		Date.setForeground(Color.black);
		Date.setBounds(30, 410, 200, 20);
		this.add(Date);
		
		JLabel getDate = new JLabel(ti.getTicketingDate());	
		getDate.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		getDate.setForeground(Color.black);
		getDate.setBounds(140, 410, 300, 20);
		this.add(getDate);
		
		//블록 출력
		JLabel Block = new JLabel("블록");
		Block.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		Block.setForeground(Color.black);
		Block.setBounds(30, 435, 200, 20);
		this.add(Block);
		
		JLabel getBlock = new JLabel(ti.getBlock());	
		getBlock.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		getBlock.setForeground(Color.black);
		getBlock.setBounds(140, 435, 200, 20);
		this.add(getBlock);
		
		//좌석 출력
		JLabel Seat = new JLabel("좌석");
		Seat.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		Seat.setForeground(Color.black);
		Seat.setBounds(30, 460, 200, 20);
		this.add(Seat);
		
		JLabel getSeat = new JLabel(ti.SeatToString());	
		getSeat.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		getSeat.setForeground(Color.black);
		getSeat.setBounds(140, 460, 200, 20);
		this.add(getSeat);
		
		//금액 출력
		JLabel Price = new JLabel("결제 금액");
		Price.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		Price.setForeground(Color.black);
		Price.setBounds(30, 485, 200, 20);
		this.add(Price);
		
		JLabel getPrice = new JLabel(String.valueOf(ti.getTotalPrice())+"원");	
		getPrice.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		getPrice.setForeground(Color.blue);
		getPrice.setBounds(140, 485, 200, 20);
		this.add(getPrice);
		
		//맨 아래 배경 테두리
		JTable table3 = new JTable();
		table3.setBounds(120, 355, 260, 157);
		table3.setBorder(BorderFactory.createLineBorder(Color.black,1));
		this.add(table3);
		
		JTable table4 = new JTable();
		table4.setBounds(20, 355, 100, 157);
		table4.setBorder(BorderFactory.createLineBorder(Color.black,1));
		table4.setBackground(Color.LIGHT_GRAY);
		this.add(table4);
		
		JButton btn = new JButton("확       인");
		btn.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btn.setBounds(70, 525, 100, 35);
		this.add(btn);
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				replace(mf,ui);
			}
		});
		
		JPanel tmp = this;
		JButton btn2 = new JButton("예매 취소");
		btn2.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		btn2.setBounds(230, 525, 100, 35);
		this.add(btn2);
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CheckPopup(mf,tmp,ti,ui);
			}
		});
		
		mf.add(this);
	}
	
	public void replace(JFrame mf,UserInfo ui) {
		mf.remove(this);
		new SelectMenu(mf,ui);
		mf.revalidate();
	}
	
	public void insertAwayIcon(JPanel pane,String away) {
		JLabel label;
		Image icon;
		JLabel image;
		
		switch(away.charAt(0)) {
		case '삼':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(225, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/samsung.png").getImage().getScaledInstance(80, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case '롯':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(225, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/lotte.png").getImage().getScaledInstance(75, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case '키':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(225, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/kiwoom.png").getImage().getScaledInstance(80, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case 'K':
			if(away.charAt(1)=='T') {
				label = new JLabel(away);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				label.setBounds(243, 45, 200, 20);
				pane.add(label);
				
				icon = new ImageIcon("images/KT.png").getImage().getScaledInstance(70, 60, 100);
				image = new JLabel(new ImageIcon(icon));
				image.setBounds(215, 50, 100, 100);
				pane.add(image);
				break;
			}
			else if(away.charAt(1)=='I') {
				label = new JLabel(away);
				label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
				label.setBounds(228, 45, 200, 20);
				pane.add(label);
				
				icon = new ImageIcon("images/KIA.png").getImage().getScaledInstance(70, 60, 100);
				image = new JLabel(new ImageIcon(icon));
				image.setBounds(215, 50, 100, 100);
				pane.add(image);
				break;
			}
		case 'N':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(228, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/nc.png").getImage().getScaledInstance(88, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case '현':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(225, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/hyundai.png").getImage().getScaledInstance(65, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case 'S':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(231, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/SK.png").getImage().getScaledInstance(80, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case '한':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(235, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/hanwha.png").getImage().getScaledInstance(95, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		case 'L':
			label = new JLabel(away);
			label.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			label.setBounds(235, 45, 200, 20);
			pane.add(label);
			
			icon = new ImageIcon("images/LG.png").getImage().getScaledInstance(68, 60, 100);
			image = new JLabel(new ImageIcon(icon));
			image.setBounds(215, 50, 100, 100);
			pane.add(image);
			break;
		}
	}
	
	public void CheckPopup(JFrame mf,JPanel tmp,TicketingInfo ti,UserInfo ui) {
		JFrame cp = new JFrame();
		
		try {
			cp.setIconImage(ImageIO.read(new File("images/delete.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cp.setTitle("예매 취소");
		cp.setSize(250,180);
		cp.setLocationRelativeTo(null);
				
		//new LogoutCheck(mf,tmp,cp);
		new DeleteCheck(mf,tmp,cp,ti,ui);
		
		cp.setResizable(false);
		cp.setVisible(true);
		
		cp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
