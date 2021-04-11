package getHealth.ui.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import getHealth.constant.Colors;
import getHealth.ui.constant.Font;
import getHealth.util.ActionEventUtil;

public class TablePanel extends BasePanel {
	int flag=0;
	String[] exercises = {"팔굽", "윗몸", "스쿼트", "플랭크" };
	String[] options = {"기본", "평균", "발전량" };
	JPanel table;
	JLabel ERROR;

	public TablePanel() {
		super("표");

		JComboBox<String> exerciseList = new JComboBox<>(options);
		exerciseList.setSelectedIndex(0);
		exerciseList.addActionListener(e -> {
			flag = ActionEventUtil.getSource(e).getSelectedIndex();
			table.setVisible(false);
			update(flag);
			refresh();
		});
		exerciseList.setBounds(20, 80, 200, 20);
		exerciseList.setFont(Font.GOTHIC_BOLD_16);
		exerciseList.setBackground(Color.WHITE);
		add(exerciseList);
		
		ERROR = addLabel("표시할 데이터가 없습니다.", 0, 100, 320, 360, Font.GOTHIC_BOLD_28);
		ERROR.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(ERROR);
		update(0);
		add(table);
	}

	private void update(int flag) {
		remove(ERROR);
		table = new JPanel(null);
		
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

		JLabel[] contents;

		if(num == 0) {
			add(ERROR);
		} else{
			if(num >= 6)
				num = 6;
			table = new JPanel(new GridLayout(num+3, 4));
		}

		table.setBackground(Colors.myBackGround);
		table.setBounds(0, 105, 320, 360);

		System.out.println(fileList.length);
		System.out.println(num);

		contents = new JLabel[(num+3) * 5];
		String[] date = new String[num];
		int[][] data = new int[num][12];
		int[] avg = {0, 0, 0, 0};
		int[] devel = new int[4];

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

			for(int j=0; j<4; j++) {
				avg[j] += data[i][3*(j+1)-1] / num;
				if(i == num-1) {
					devel[j] = avg[j] - data[i][3*(j+1)-1];
				}
			}

			buffer="";
			System.out.println(date[i]);
		}

		for(int i=0; i<(num+3)*5; i++){
			contents[i] = new JLabel();
			contents[i].setFont(Font.GOTHIC_BOLD_16);

			if(i<5) {
				if(i==0) {
					contents[i].setText("날짜");
				} else {
					contents[i].setText(exercises[i-1]);
				}
			} else if(i >= ((num+2)*5)) {
				if(i % 5 == 0) {
					contents[i].setText("발전량");
				} else {
					contents[i].setText("" + devel[i%5 -1]);
				}
			} else if(i >= (num + 1)*5) {
				if(i % 5 == 0) {
					contents[i].setText("평균");
				} else {
					contents[i].setText("" + avg[i%5 -1]);
				}
			} else {
				if(i%5!=0) {
					contents[i].setText("" + data[i/5-1][(i%5) * 3 - 1]);
					contents[i].setFont(Font.GOTHIC_PLAIN_16);
				} else {
					contents[i].setText("" + date[i/5-1]);
				}
			}
			contents[i].setForeground(Colors.myFontColor);
			contents[i].setHorizontalAlignment(SwingConstants.CENTER);
			contents[i].setOpaque(false);
		}

		quickSort(contents, 1, 4, num);
		
		for(int i=0; i<(num+3)*5; i++){
			table.add(contents[i]);
			System.out.print(contents[i].getText());
			if(i%5==4)
				System.out.println();
		}
		
		table.setVisible(true);
		add(table);
	}

	private void quickSort(JLabel[] arr, int start, int end, int num) {
		if(flag==0) {
			return;
		}
		int part = partition(arr, start, end, num);
		if(start<part-1) {
			quickSort(arr, start, part-1, num);
		}
		if(part < end) {
			quickSort(arr, part, end, num);
		}
	}

	private int partition(JLabel[] arr, int start, int end, int num) {
		String temp;
		int pivot = Integer.parseInt(arr[(5 * (num+flag)) + ((start+end)/2)].getText());

		while(start<=end) {
			while(Integer.parseInt(arr[5 * (num+flag) + start].getText()) > pivot) start++;
			while(Integer.parseInt(arr[5 * (num+flag) + end].getText()) < pivot) end--;   
			if(start <= end) {
				for(int i=0; i< (num+3); i++) {
					temp = arr[(5 * i) + start].getText();
					arr[(5 * i) + start].setText(arr[(5 * i) + end].getText());
					arr[(5 * i) + end].setText(temp);
				}
				start++;
				end--;
			}
		}
		return start;
	}

	@Override
	public void refresh() {
		table.setVisible(false);
		update(flag);
	}
}

