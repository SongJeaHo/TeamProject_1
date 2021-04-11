package getHealth.ui.panel;

import java.awt.font.TextAttribute;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import getHealth.constant.Colors;
import getHealth.constant.Resources;
import getHealth.ui.MainFrame;
import getHealth.ui.constant.Font;
import getHealth.ui.constant.Panels;

public class HomePanel extends BasePanel{
	int year, month, date;
	String today;

	public HomePanel() {
		super("홈");
		Calendar calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		month = calendar.get(Calendar.MONTH)+1;
		date = calendar.get(Calendar.DATE);
		today = setFileName(year, month, date);

		JLabel todayIs = addLabel("오늘은", 0, 80, 320, 28,Font.GOTHIC_BOLD_28);
		todayIs.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel dateLabel = addLabel(
				("" + year) + " 년  " + ("" + month) + " 월  " + ("" + date) + " 일",
				0, 120, 320, 28,Font.GOTHIC_BOLD_28);
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel is = addLabel("입니다.", 0, 160, 320, 28,Font.GOTHIC_BOLD_28);
		is.setHorizontalAlignment(SwingConstants.CENTER);

		ImageIcon exerciseStart = new ImageIcon(Resources.EXERCISE_START);
		
		JButton recordExercise = new JButton(exerciseStart);
		recordExercise.setBounds(20, 360, 280, 40);
		recordExercise.setFont(Font.GOTHIC_BOLD_28);
		recordExercise.setForeground(Colors.myFontColor);
		recordExercise.setOpaque(false);
		recordExercise.setBorderPainted(false);
		recordExercise.setContentAreaFilled(false);
		recordExercise.addActionListener(e -> {
			recordExercise();
		});

		JButton recordCheating = addButton("치팅데이 등록하기", 20, 420, 280, 20, Font.GOTHIC_PLAIN_20);
		recordCheating.setHorizontalAlignment(SwingConstants.CENTER);
		java.awt.Font font = recordCheating.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		recordCheating.setFont(font.deriveFont(attributes));
		recordCheating.addActionListener(e -> {
			setCheating();
		});

		add(todayIs);
		add(dateLabel);
		add(is);
		add(recordExercise);
		add(recordCheating);
	}

	private void setCheating() {
		try {
			File file = new File("res/data/cheating/" + today + ".txt");
			FileReader fileReader = new FileReader(file);
			fileReader.close();
			JOptionPane.showMessageDialog(null, "이미 오늘은 치팅데이로 등록되었습니다.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

		} catch (FileNotFoundException e) {
			try {
				int result = JOptionPane.showConfirmDialog(null, "오늘을 치팅데이로 등록할까요?", 
						"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					OutputStream output = new FileOutputStream("res/data/cheating/" + today + ".txt");
					JOptionPane.showMessageDialog(null, "오늘은 치팅데이로 등록되었습니다!");
				}
			} catch (Exception exception) {
				exception.getStackTrace();
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
	
	private void recordExercise() {
		try {
			File file = new File("res/data/exercise/" + today + ".txt");
			FileReader fileReader = new FileReader(file);
			fileReader.close();
			JOptionPane.showMessageDialog(null, "이미 오늘운동 기록이 완료되었습니다.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);

		} catch (FileNotFoundException e) {
			try {
				int result = JOptionPane.showConfirmDialog(null, "오늘 운동을 기록할까요?", 
						"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(result == JOptionPane.YES_OPTION) {
					MainFrame.getInstance().switchPanel(Panels.RECORD);
				}
			} catch (Exception exception) {
				exception.getStackTrace();
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
}
