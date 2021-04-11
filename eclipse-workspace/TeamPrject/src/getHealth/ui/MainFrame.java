package getHealth.ui;

import java.awt.Color;

import javax.swing.JFrame;

import getHealth.ui.constant.Panels;
import getHealth.ui.panel.BasePanel;
import getHealth.ui.panel.CalPanel;
import getHealth.ui.panel.GraphPanel;
import getHealth.ui.panel.GreetingPanel;
import getHealth.ui.panel.HomePanel;
import getHealth.ui.panel.InfoPanel;
import getHealth.ui.panel.MenuPanel;
import getHealth.ui.panel.RecordPanel;
import getHealth.ui.panel.SettingPanel;
import getHealth.ui.panel.TablePanel;

// 메인 프레임 
public class MainFrame extends JFrame {
    private static MainFrame instance = null;

    // 메인 프레임을 메인 함수로 반환할 때 사용 
    public static MainFrame getInstance() {
        if (instance == null)
            instance = new MainFrame();

        return instance;
    }

    // 각 판넬(페이지)를 JPanel 배열로 만들어 저장해놓음 
    private BasePanel[] panels = {
    		new GreetingPanel(), 
    		new HomePanel(), 
    		new MenuPanel(), 
    		new CalPanel(), 
    		new GraphPanel(),
    		new TablePanel(),
    		new InfoPanel(),
    		new SettingPanel(),
    		new RecordPanel()
    		};

    // 첫번째 페이지는 GREETING 페이지임 
    private volatile int currentPanel = Panels.GREETING;

    // 메인 프레임을 생성한 후 크기를 320 x 500으로 맞춤
    // 화면 크기조절을 불가능하게 함 
    // 아까 만들어놓은 판넬배열의 0번지 즉 GREETING페이지를 프레임에 띄워줌 
    private MainFrame() {
        super();
        setBounds(0, 0, 320, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.WHITE);
        setResizable(false);
        add(panels[0]);
        
        setVisible(true);
    }

    // 페에지 변경 메소드 
    // 이전 페이지를 프레임에서 지운 후
    // 새로운 페이지를 프레임에 넣은 뒤 repaint를 하여 새로 프레임을 그려줌 
    public void switchPanel(int id) {
        getContentPane().remove(panels[currentPanel]);
        panels[id].refresh();
        getContentPane().add(panels[id]);
        revalidate();
        repaint();

        currentPanel = id;
    }
}
