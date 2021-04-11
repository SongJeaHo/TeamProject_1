package getHealth.ui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import getHealth.constant.Colors;
import getHealth.constant.Resources;
import getHealth.ui.MainFrame;
import getHealth.ui.constant.Font;
import getHealth.ui.constant.Panels;

public class RecordPanel extends BasePanel {
	JTextField input[];
	String today;

	public RecordPanel() {
		super("");
		remove(menuBar);	

		Calendar calendar = Calendar.getInstance();
		today = setFileName(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
		JPanel Contents = new JPanel();
		Contents.setBounds(0, 0, 320, 800);
		Contents.setLayout(null);
		Contents.setBackground(Colors.myBackGround);
		Contents.setPreferredSize(new Dimension(320, 960));

		JLabel pushUpTitle = addLabel("팔굽혀펴기", 20, 20, 280, 40, Font.GOTHIC_BOLD_36);
		JLabel sitUpTitle = addLabel("윗몸일으키기", 20, 180, 280, 40, Font.GOTHIC_BOLD_36);
		JLabel squatTitle = addLabel("스쿼트", 20, 340, 280, 40, Font.GOTHIC_BOLD_36);
		JLabel plankTitle = addLabel("플랭크", 20, 500, 280, 40, Font.GOTHIC_BOLD_36);

		JLabel[] division = new JLabel[8];
		for(int i=0; i<8; i++) {
			if(i%2==0) {
				division[i] = addLabel("갯수\t\t\t : ", 20, 60 + (i/2)*160, 80, 40, Font.GOTHIC_BOLD_20);
			}
			else {
				division[i] = addLabel("세트 수\t:", 20, 100 +(i/2)*160, 80, 40, Font.GOTHIC_BOLD_20);
			}

			Contents.add(division[i]);
		}

		input = new JTextField[8];

		for(int i=0;i<8;i++) {
			input[i] = new JTextField(10);

			if(i%2==0) {
				input[i].setBounds(100, 64 + (i/2)*160, 180, 32);
			}
			else {
				input[i].setBounds(100, 104 +(i/2)*160, 180, 32);
			}
			input[i].setBackground(Color.darkGray);
			input[i].setForeground(Colors.myFontColor);
			Contents.add(input[i]);
		}

		Contents.add(pushUpTitle);
		Contents.add(sitUpTitle);
		Contents.add(squatTitle);
		Contents.add(plankTitle);

		ImageIcon recordIcon = new ImageIcon(Resources.RECORD_BUTTON);
		JButton recordExercise = new JButton(recordIcon);
		recordExercise.setBounds(10, 760, 280, 40);
		recordExercise.setFont(Font.GOTHIC_BOLD_28);
		recordExercise.setForeground(Colors.myFontColor);
		recordExercise.setOpaque(false);
		recordExercise.setBorderPainted(false);
		recordExercise.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null, "오늘의 기록을 기록할까요?", 
					"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(result == JOptionPane.YES_OPTION) {
				record();		
			}
		});

		ImageIcon cancelIcon = new ImageIcon(Resources.CANCEL_BUTTON);
		JButton cancelRecord = new JButton(cancelIcon);
		cancelRecord.setBounds(10, 820, 280, 40);
		cancelRecord.setFont(Font.GOTHIC_BOLD_28);
		cancelRecord.setForeground(Colors.myFontColor);
		cancelRecord.setOpaque(false);
		cancelRecord.setBorderPainted(false);
		cancelRecord.addActionListener(e -> {
			int result = JOptionPane.showConfirmDialog(null, "기록을 취소할까요?", 
					"", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(result == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "기록이 취소되었습니다!");
				MainFrame.getInstance().switchPanel(Panels.HOME);		
			}
		});

		Contents.add(recordExercise);
		Contents.add(cancelRecord);

		final JScrollPane sc = new JScrollPane(Contents);
		sc.setBounds(0, 0, 320, 480);
		sc.setBorder(null);
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		add(sc);		
	}

	private void record() {
		String buffer = "";

		for(int i=0; i<8; i++) {
			if(!checkDigit(input[i].getText())) {
				JOptionPane.showMessageDialog(null, "숫자만 입력이 가능합니다.");
				input[i].setBackground(Colors.errorColor);
				input[i].setText("숫자만 입력해주세요");
				input[i].setHorizontalAlignment(SwingConstants.CENTER);
				input[i].setForeground(Colors.myBackGround);
				return;
			} else {
				if(!checkFilled(input[i].getText())){
					if(i%2==0) {
						input[i].setText("0");
					} else {
						if(input[i-1].getText().equals("0"))
							input[i].setText("0");
						else
							input[i].setText("1");
					}
				}
				buffer += ("" + input[i].getText());
				if(i!=7) {
					buffer += ",";
				}
			}
		}

		System.out.println(buffer);

		FileWriter writer;
		try {
			writer = new FileWriter("res/data/exercise/" + today + ".txt");
			writer.write(buffer);
			writer.close();

			JOptionPane.showMessageDialog(null, "오늘의 운동기록이 기록되었습니다!");
			MainFrame.getInstance().switchPanel(Panels.HOME);
		} catch (IOException e) {
			// TODO 자동 생성된 catch 블록
			e.printStackTrace();
		}
	}

	private boolean checkDigit(String str) {
		for(int i=0; i<str.length();i++) {
			if(!Character.isDigit(str.charAt(i)))
				return false;
		}
		return true;
	}

	private boolean checkFilled(String str) {
		if(str.equals(""))
			return false;

		return true;
	}
}
