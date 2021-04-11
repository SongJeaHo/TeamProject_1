package getHealth.ui.panel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import getHealth.constant.Colors;
import getHealth.ui.constant.Font;
import getHealth.util.ActionEventUtil;

public class CalPanel extends BasePanel{
	private int lastYear, lastMonth, date;
	private Integer[] years = new Integer[50], months = new Integer[31];
	private JLabel yearLabel, monthLabel;
	private JPanel weekPanel, dayPanel, detailPanel;
	Calendar calendar = Calendar.getInstance();


	public CalPanel() {
		super("캘린더");

		for(int i=0; i<50; i++) {
			years[i] = 2010 + i;
		}
		for(int i=0; i<12; i++) {
			months[i] = i+1;
		}

		lastYear = calendar.get(Calendar.YEAR);
		lastMonth = calendar.get(Calendar.MONTH)+1;
		date = calendar.get(Calendar.DATE);
		System.out.println(lastYear);
		System.out.println(lastMonth);
		update(lastYear, lastMonth);

		JComboBox<Integer> yearList = new JComboBox<>(years);
		yearList.setSelectedIndex(calendar.get(Calendar.YEAR)-2010);
		yearList.addActionListener(this::updateByYear);
		yearList.setBounds(40, 80, 110, 20);
		yearList.setFont(Font.GOTHIC_BOLD_20);
		yearList.setBackground(Color.WHITE);
		yearLabel = addLabel("년", 150, 80, 40, 20, Font.GOTHIC_BOLD_20);

		JComboBox<Integer> monthList = new JComboBox<>(months);
		monthList.setSelectedIndex(calendar.get(Calendar.MONTH));
		monthList.addActionListener(this::updateByMonth);
		monthList.setBounds(180, 80, 80, 20);
		monthList.setFont(Font.GOTHIC_BOLD_20);
		monthList.setBackground(Color.WHITE);
		monthLabel = addLabel("월", 260, 80, 40, 20, Font.GOTHIC_BOLD_20);

		weekPanel = setWeekPanel();
		detailPanel = setDetailPanel(date);

		add(weekPanel);
		add(yearList);
		add(monthList);
		add(yearLabel);
		add(monthLabel);
		add(dayPanel);
		add(detailPanel);
	}

	private void updateByYear(ActionEvent e) {
		dayPanel.setVisible(false);
		lastYear = years[ActionEventUtil.getSource(e).getSelectedIndex()];
		System.out.println(lastYear + " " + lastMonth);
		update(lastYear, lastMonth);
	}

	private void updateByMonth(ActionEvent e) {
		dayPanel.setVisible(false);
		lastMonth = months[ActionEventUtil.getSource(e).getSelectedIndex()];
		System.out.println(lastYear + " " + lastMonth);
		update(lastYear, lastMonth);
	}

	private void update(int lastYear, int lastMonth) {
		dayPanel = setDayPanel(lastYear, lastMonth);
		add(dayPanel);
		dayPanel.setVisible(true);
	}

	private JPanel setWeekPanel() {
		JPanel weekPanel = new JPanel(new GridLayout(1, 7));
		JLabel[] weekLabel = {new JLabel("일"), new JLabel("월"), new JLabel("화"), 
				new JLabel("수"), new JLabel("목"), new JLabel("금"), new JLabel("토")};
		for(int i=0; i<7; i++) {
			weekLabel[i].setFont(Font.GOTHIC_PLAIN_20);
			weekLabel[i].setForeground(Colors.myFontColor);
			weekLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			weekLabel[i].setOpaque(false);
			weekPanel.add(weekLabel[i]);
		}
		weekPanel.setBounds(0, 120, 320, 20);
		weekPanel.setBackground(Colors.myBackGround);

		return weekPanel;
	}

	private JPanel setDayPanel(int lastYear, int lastMonth) {
		JPanel dayPanel = new JPanel(new GridLayout(6, 7));
		JButton[] dayButton = new JButton[42];
		int START_DAY_OF_WEEK = 0;
		int END_DAY = 0;

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();

		start.set(lastYear, lastMonth-1, 1);
		end.set(lastYear, lastMonth, 1);
		end.add(Calendar.DATE, -1);

		START_DAY_OF_WEEK = start.get(Calendar.DAY_OF_WEEK);
		END_DAY = end.get(Calendar.DATE);

		for(int i=0; i < (START_DAY_OF_WEEK-1); i++) {
			dayButton[i] = new JButton(" ");
			dayButton[i].setEnabled(false);
		}

		for(int i=0; i < END_DAY; i++) {
			dayButton[START_DAY_OF_WEEK +i-1] = new JButton(("" + (i+1)));
			dayButton[START_DAY_OF_WEEK +i-1].addActionListener(e -> {
				detailPanel.setVisible(false);

				JButton button = (JButton)(e.getSource());
				detailPanel = setDetailPanel(Integer.parseInt(button.getText()));
				add(detailPanel);
				detailPanel.setVisible(true);
			});
		}
		System.out.println(END_DAY+START_DAY_OF_WEEK);
		for(int i=END_DAY+START_DAY_OF_WEEK-1; i<42; i++) {
			dayButton[i] = new JButton(" ");
			dayButton[i].setEnabled(false);
		}

		for(int i=0; i<42; i++) {
			dayButton[i].setFont(Font.GOTHIC_PLAIN_16);
			dayButton[i].setMargin(new Insets(-10, -10, -10, -10));
			dayButton[i].setOpaque(false);
			dayButton[i].setBorderPainted(false);
			dayButton[i].setContentAreaFilled(false);
			dayButton[i].setForeground(Colors.myFontColor);
			if(!dayButton[i].getText().equals(" ")) {
				try {
					File file = new File("res/data/cheating/" + setFileName(lastYear, lastMonth, Integer.parseInt(dayButton[i].getText())) + ".txt");
					FileReader fileReader = new FileReader(file);
					fileReader.close();
					dayButton[i].setForeground(new Color(43, 140, 227));
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
					e.getStackTrace();				
				}
			}
			dayPanel.add(dayButton[i]);
		}

		dayPanel.setBounds(0, 150, 320, 160);
		dayPanel.setBackground(Colors.myBackGround);
		return dayPanel;
	}

	private JPanel setDetailPanel(int date) {
		JPanel dayDetail = new JPanel();
		dayDetail.setBounds(0, 320, 320, 160);
		dayDetail.setBackground(Colors.myBackGround);
		dayDetail.setLayout(null);

		JLabel titleLabel = new JLabel("오늘은 " + lastYear + "년 " + lastMonth + "월 " + date + "일 입니다.");
		titleLabel.setBounds(20, 0, 280, 20);
		titleLabel.setFont(Font.GOTHIC_PLAIN_18);
		titleLabel.setForeground(Colors.myFontColor);
		titleLabel.setOpaque(false);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		dayDetail.add(titleLabel);

		String contents = "";
		int[] data = new int[12];

		try {
			File file = new File("res/data/exercise/" + setFileName(lastYear, lastMonth, date) + ".txt");
			FileReader fileReader = new FileReader(file);
			int cur = 0;
			while((cur = fileReader.read())!=-1) {
				contents += (char)cur;
			}
			data = getExerciseInfo(contents);


			JPanel detailTitle = new JPanel(new GridLayout(4, 1));
			detailTitle.setBounds(30, 50, 100, 80);
			detailTitle.setBackground(Colors.myBackGround);

			JLabel[] title = new JLabel[4];

			title[0] = new JLabel("팔굽혀펴기");
			title[1] = new JLabel("윗몸일으키기");
			title[2] = new JLabel("스쿼트");
			title[3] = new JLabel("플랭크");

			for(int i=0;i<4;i++) {
				title[i].setHorizontalAlignment(SwingConstants.LEFT);
				title[i].setFont(Font.GOTHIC_PLAIN_16);
				title[i].setForeground(Colors.myFontColor);
				title[i].setOpaque(false);

				detailTitle.add(title[i]);
			}

			dayDetail.add(detailTitle);

			JPanel detailContents = new JPanel(new GridLayout(4, 3));
			detailContents.setBounds(140, 50, 150, 80);
			detailContents.setBackground(Colors.myBackGround);

			JLabel[] ExerciseLabel = new JLabel[12];

			for(int count=0; count<12;count++) {
				ExerciseLabel[count] = new JLabel(""+data[count]);
				ExerciseLabel[count].setHorizontalAlignment(SwingConstants.RIGHT);
				ExerciseLabel[count].setFont(Font.GOTHIC_PLAIN_16);
				ExerciseLabel[count].setForeground(Colors.myFontColor);
				ExerciseLabel[count].setOpaque(false);

				detailContents.add(ExerciseLabel[count]);
			}

			dayDetail.add(detailContents);
			fileReader.close();
		} catch (FileNotFoundException e) {
			JLabel detailLabel = new JLabel("아직 운동기록이 없습니다.");
			detailLabel.setBounds(20, 40, 280, 20);
			detailLabel.setFont(Font.GOTHIC_PLAIN_16);
			detailLabel.setForeground(Colors.myFontColor);
			detailLabel.setOpaque(false);
			detailLabel.setHorizontalAlignment(JLabel.CENTER);

			dayDetail.add(detailLabel);
		} catch (IOException e) {
			e.getStackTrace();
		}
		return dayDetail;
	}


	@Override
	public void refresh() {
		dayPanel.setVisible(false);
		lastYear = calendar.get(Calendar.YEAR);
		lastMonth = calendar.get(Calendar.MONTH)+1;
		update(lastYear, lastMonth);
		detailPanel.setVisible(false);
		setDetailPanel(date);
		detailPanel.setVisible(true);
	}
}
