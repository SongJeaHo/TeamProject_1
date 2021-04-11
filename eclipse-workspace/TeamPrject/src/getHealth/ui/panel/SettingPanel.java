package getHealth.ui.panel;

import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import getHealth.ui.constant.Font;

public class SettingPanel extends BasePanel{
	public SettingPanel(){
		super("설정");

		JButton initialization = addButton("초기화", 10, 60, 160, 40, Font.GOTHIC_PLAIN_20);
		initialization.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null, "모든 기록이 사라집니다.\n초기화를 진행할까요??", 
					"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(result == JOptionPane.YES_OPTION) {
				initialization();
				JOptionPane.showMessageDialog(null, "초기화가 완료되었습니다!");
			}
		});
		add(initialization);
	}

	private void initialization() {
		File folder = new File("res/data/exercise/");

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File f, String name) {
				return name.startsWith("20");
			}
		};

		File[] folder_list = folder.listFiles(filter);
		
		try {
			for (int i = 0; i < folder_list.length; i++) {
				folder_list[i].delete();
				System.out.println(folder_list[i].getName());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

		folder = new File("res/data/cheating/");

		folder_list = folder.listFiles(filter);

		try {
			for (int i = 0; i < folder_list.length; i++) {
				folder_list[i].delete();
				System.out.println(folder_list[i].getName());
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
