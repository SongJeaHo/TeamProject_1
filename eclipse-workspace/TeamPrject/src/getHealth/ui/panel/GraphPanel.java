package getHealth.ui.panel;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import getHealth.constant.Colors;
import getHealth.constant.Resources;
import getHealth.ui.constant.Font;
import getHealth.util.ActionEventUtil;

public class GraphPanel extends BasePanel {
	int flag=0;
	String[] exercises = {"팔굽혀펴기", "윗몸일으키기", "스쿼트", "플랭크" };
	JPanel graph;
	JLabel ERROR;
	
	public GraphPanel() {
		super("그래프");

		JComboBox<String> exerciseList = new JComboBox<>(exercises);
		exerciseList.setSelectedIndex(0);
		exerciseList.addActionListener(e -> {
			flag = ActionEventUtil.getSource(e).getSelectedIndex();
			graph.setVisible(false);
			update(flag);
			refresh();
		});
		
		ERROR = addLabel("표시할 데이터가 없습니다.", 0, 100, 320, 360, Font.GOTHIC_BOLD_28);
		ERROR.setHorizontalAlignment(SwingConstants.CENTER);
		
		exerciseList.setBounds(20, 80, 200, 20);
		exerciseList.setFont(Font.GOTHIC_BOLD_16);
		exerciseList.setBackground(Color.WHITE);
		add(exerciseList);
		add(ERROR);
		update(0);
		add(graph);
	}

	private void update(int flag) {
		remove(ERROR);
		graph = new JPanel(null);
		JLabel[] contents;
		JLabel[] dates;
		
		graph.setBackground(Colors.myBackGround);
		graph.setBounds(0, 80, 320, 360);

		File folder = new File("res/data/exercise/");

		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File f, String name) {
				return name.startsWith("20");
			}
		};

		File[] fileList = folder.listFiles(filter);
		Arrays.sort(fileList);

		int len = fileList.length;
		int num = fileList.length;

		if(num >= 5) {
			num = 5;
		} else if(num == 0) {
			add(ERROR);
		}

		System.out.println(fileList.length);
		System.out.println(num);

		contents = new JLabel[num];
		dates = new JLabel[num];
		String[] date = new String[num];
		int[][] data = new int[num][12];

		for(int start = len - num, i=0 ; i < num ; i++, start++) {
			FileReader fileReader;
			String buffer="";
			try {
				fileReader = new FileReader(fileList[start]);
				int cur = 0;
				while((cur = fileReader.read())!=-1) {
					buffer += (char)cur;
				}
				System.out.println(buffer);
			} catch (IOException e1) {
				// TODO 자동 생성된 catch 블록
				e1.printStackTrace();
			}
			date[i] = fileList[start].getName().substring(4, 8);
			data[i] = getExerciseInfo(buffer);
			buffer="";
			System.out.println(date[i]);
		}

		int max = 0;
		for(int i=0; i<num; i++) {		
			if(max < data[i][2+flag*3])
				max = data[i][2+flag*3];
		}

		int tab = (300-(30*num)) / (num + 1);
		
		for(int i=0; i<num; i++) {
			int height = data[i][2 + flag*3] * 240 / max;
			System.out.println(flag);
			contents[i] = new JLabel(new ImageIcon(Resources.DATA_BACKGROUND));
			contents[i].setBounds((i+1) * tab + 30 * i + 5, 320 - height, 30, height);

			graph.add(contents[i]);
		}

		for(int i=0; i<num; i++) {
			dates[i] = new JLabel(date[i]);
			dates[i].setBounds((i+1) * tab + 30 * i - 10, 340, 60, 20);
			dates[i].setFont(Font.GOTHIC_PLAIN_16);
			dates[i].setForeground(Colors.myFontColor);
			dates[i].setOpaque(false);
			dates[i].setHorizontalAlignment(SwingConstants.CENTER);

			graph.add(dates[i]);
		}
		
		graph.setVisible(true);
		add(graph);
	}

	@Override
	public void refresh() {
		graph.setVisible(false);
		update(flag);
	}
}

