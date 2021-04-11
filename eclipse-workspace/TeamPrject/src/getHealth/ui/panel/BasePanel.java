package getHealth.ui.panel;

import javax.swing.*;

import getHealth.constant.Colors;
import getHealth.constant.Resources;
import getHealth.ui.MainFrame;
import getHealth.ui.constant.Font;
import getHealth.ui.constant.Panels;

// 모든 페이지에 기본적으로 들어갈 내용들을 포함. 추후 다른 페이지들이 이 클래스를 상속받음 
public class BasePanel extends JPanel{
	JButton menuBar;
	JLabel title;
	// 기본생성자 (비워놓음) 
	public BasePanel() {};

	// 생성자 이름을 인자로 받음 
	public BasePanel(String name) {
		super();
		setLayout(null);
		setBackground(Colors.myBackGround);

		// 메뉴아이콘을 이미지파일로 받아 넣어줌 
		ImageIcon menuIcon = new ImageIcon(Resources.MENU_BAR);
		menuBar = new JButton(menuIcon);
		menuBar.setBounds(20, 20, 36, 28);
		menuBar.setOpaque(false);
		menuBar.setBorderPainted(false);
		menuBar.setContentAreaFilled(false);
		menuBar.addActionListener(e -> MainFrame.getInstance().switchPanel(Panels.MENUBAR));	     
		add(menuBar);

		// 페이지에 들어갈 이름을 JLabel로 넣어
		title = addLabel(name, 68, 14, 280, 40, Font.GOTHIC_BOLD_20);	     
		title.setHorizontalAlignment(SwingConstants.LEFT);
		add(title);
	}

	// 각 버튼과 라벨은 같은 디자인으로 들어가기에 기본 디폴트값을 미리 넣어둔 메소드 
	protected JButton addButton(String name, int xpos, int ypos, int width, int height, java.awt.Font font) {
		JButton button = new JButton(name);
		button.setBounds(xpos, ypos, width, height);
		button.setFont(font);
		button.setForeground(Colors.myFontColor);
		button.setHorizontalAlignment(SwingConstants.LEFT);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);

		return button;
	}

	protected JLabel addLabel(String name, int xpos, int ypos, int width, int height, java.awt.Font font) {
		JLabel label = new JLabel(name);
		label.setBounds(xpos, ypos, width, height);
		label.setFont(font);
		label.setForeground(Colors.myFontColor);
		label.setOpaque(false);

		return label;
	}
	
	protected String setFileName(int year, int month, int date) {
		String fileName = "";

		fileName += year;
		if(month < 10) {
			fileName += '0';
		}
		fileName += month;
		if(date < 10) {
			fileName += '0';
		}
		fileName += date;
		return fileName;
	}
	
	protected int[] getExerciseInfo(String contents) {
		int data[] = new int[12];
		String[] buffer = contents.split(",");

		int bufferCount = 0;
		for(int count=0; count<12; count++) {
			if((count+1)%3 != 0) {
				data[count] =  Integer.parseInt(buffer[bufferCount]);
				bufferCount++;
			}
			else {
				data[count] = data[count-1] * data[count-2];
			}
		}

		return data;
	}

	public void refresh() {
		revalidate();
		repaint();
	}
}
