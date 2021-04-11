package getHealth.ui.panel;

import javax.swing.*;

import getHealth.constant.Colors;
import getHealth.ui.MainFrame;
import getHealth.ui.constant.Font;
import getHealth.ui.constant.Panels;

public class MenuPanel extends BasePanel{
	public MenuPanel() {
        super("메뉴");
        
        // 각 메뉴들에 들어갈 버튼들을 생성하고 기능을 넣어줌 
        JButton homeButton = addButton("홈", 10, 60, 280, 40, Font.GOTHIC_PLAIN_20);
        JButton calButton = addButton("캘린더", 10, 120, 280, 40, Font.GOTHIC_PLAIN_20);
        JButton graphButton = addButton("그래프", 10, 180, 280, 40, Font.GOTHIC_PLAIN_20);
        JButton tableButton = addButton("표", 10, 240, 280, 40, Font.GOTHIC_PLAIN_20);
        JButton infoButton = addButton("정보", 10, 300, 280, 40, Font.GOTHIC_PLAIN_20);
        JButton settingButton = addButton("설정", 10, 360, 280, 40, Font.GOTHIC_PLAIN_20);
        
        homeButton.addActionListener(e -> MainFrame.getInstance().switchPanel(Panels.HOME));
        calButton.addActionListener(e -> MainFrame.getInstance().switchPanel(Panels.CALENDAR));
        graphButton.addActionListener(e-> MainFrame.getInstance().switchPanel(Panels.GRAPH));
        tableButton.addActionListener(e-> MainFrame.getInstance().switchPanel(Panels.TABLE));
        infoButton.addActionListener(e-> MainFrame.getInstance().switchPanel(Panels.INFO));
        settingButton.addActionListener(e -> MainFrame.getInstance().switchPanel(Panels.SETTING));

        add(homeButton);
        add(calButton);
        add(graphButton);
        add(tableButton);
        add(infoButton);
        add(settingButton);
	}
}
