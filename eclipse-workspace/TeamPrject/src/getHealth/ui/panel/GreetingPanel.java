package getHealth.ui.panel;

import java.awt.*;
import javax.swing.*;
import getHealth.constant.Resources;
import getHealth.ui.MainFrame;
import getHealth.ui.constant.Panels;
import getHealth.ui.constant.Font;
import getHealth.constant.Colors;

public class GreetingPanel extends BasePanel {
    public GreetingPanel() {
        super("");
        
        remove(menuBar);
        remove(title);
        // 이미지를 넣어주고 크기를 320 x 640으로 조절 
        setBackground(Colors.myBackGround);
        ImageIcon background = new ImageIcon(Resources.BACKGROUND_GREETING);
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 320, 480);

        // 시작하기 버튼 
        JButton start = new JButton("시작하기");
        start.setBounds(20, 320, 280, 60);
        start.setFont(Font.GOTHIC_BOLD_36);
        start.setForeground(Colors.myFontColor);
        start.setOpaque(false);
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);

        // start버튼을 누르면 이벤트가 발생하는데 데이터를 읽어오고 홈화면으로 이동함 
        start.addActionListener(e -> {
        	// 기존 데이터베이스 불러오기 
        	System.out.println("DATABASE LOADING");
        	MainFrame.getInstance().switchPanel(Panels.HOME);
        });
        
        add(start);
        add(backgroundLabel);
    }
}
