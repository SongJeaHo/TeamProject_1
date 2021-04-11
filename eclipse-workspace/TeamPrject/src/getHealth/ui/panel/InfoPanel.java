package getHealth.ui.panel;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import getHealth.constant.Colors;
import getHealth.ui.constant.Font;

public class InfoPanel extends BasePanel {
	public InfoPanel(){
		super("정보");

		JScrollPane pushUp = new JScrollPane(new PushUp(), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pushUp.setBounds(0, 0, 320, 400);
		pushUp.setBorder(null);

		JScrollPane sitUp = new JScrollPane(new SitUp(), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		sitUp.setBounds(0, 0, 320, 400);
		sitUp.setBorder(null);

		JScrollPane squat = new JScrollPane(new Squat(), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		squat.setBounds(0, 0, 320, 400);
		squat.setBorder(null);

		JScrollPane plank = new JScrollPane(new Plank(), 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		plank.setBounds(0, 0, 320, 400);
		plank.setBorder(null);

		JTabbedPane pane = new JTabbedPane();
		pane.setBounds(0, 60, 320, 390);
		pane.setFont(Font.GOTHIC_BOLD_12);

		JLabel source = new JLabel("츨처 - [네이버 지식백과]");
		source.setBackground(Colors.myBackGround);
		source.setForeground(Colors.myFontColor);
		source.setFont(Font.GOTHIC_BOLD_16);
		source.setBounds(10, 450, 180, 20);

		pane.addTab("팔굽혀펴기", pushUp);
		pane.addTab("윗몸일으키기", sitUp);
		pane.addTab("스쿼트", squat);
		pane.addTab("플랭크", plank);

		add(pane);
		add(source);
	}

	private class BaseTab extends JPanel{
		protected JLabel title, summary, procedure, caution, tip;
		protected JTextArea summaryContents, procedureContents, cautionContents, tipContents;

		BaseTab(String name) {
			super();
			setLayout(null);
			setBackground(Colors.myBackGround);

			title = new JLabel(name);
			title.setBackground(Colors.myBackGround);
			title.setForeground(Colors.myFontColor);
			title.setFont(Font.GOTHIC_BOLD_28);
			title.setBounds(0, 0, 300, 70);

			add(title);
		}
	}

	private class PushUp extends BaseTab {
		private PushUp() {
			super("팔굽혀펴기 push up");
			setPreferredSize(new Dimension(320, 960));

			summary = setTitle("개요", 0, 80);

			summaryContents = setContents(
					"가장 널리 알려진 대흉근 운동이다.\n"
							+"대흉근뿐만 아니라 상완삼두근과 전면\n"
							+"삼각근 발달에도 도움이 되며\n"
							+"초보자들에게는 안전하고 효과적으로\n" 
							+"상체를 단련할 수 있는 최고의 운동이다.\n" 
							+"의자나 상자 위에 다리를 올려놓고\n"
							+"실시하면 조금 더 큰 힘을 발휘해야 하므로\n"
							+"대흉근 발달에는 더욱 효과적이다.\n",
							0, 120, 8);


			procedure = setTitle("운동 순서", 0, 300);
			procedureContents = setContents(
					"1. 엎드린 자세에서 어깨너비 두 배 정도로\n"
							+ "두 손을 바닥에 짚고,\n"
							+ "발뒤꿈치를 든 상태에서 팔과 무릎을\n"
							+ "곧게 편다.\n\n"
							+ "2. 가슴을 바닥 쪽으로 내미는 느낌으로\n"
							+ "팔꿈치를 구부려 바닥에 닿기 전까지\n"
							+ "몸을 내린다.\n\n"
							+ "3. 겨드랑이에 힘을 주면서 가슴을\n"
							+ "모아주는 느낌으로 팔을 편다.\n",
							0, 340, 11);


			caution = setTitle("주의 사항", 0, 600);
			cautionContents = setContents(
					"손목이 많이 꺾이지 않도록 한다.\n\n" 
							+ "여성의 경우 푹신한 쿠션에\n"
							+ "무릎을 꿇어 실시하도록 한다.",
							0, 640, 4);


			tip = setTitle("운동 팁", 0 , 760);
			tipContents = setContents(
					"어깨너비보다 약간 좁게 실시하면,\n"
							+ "상완삼두근의 발달에 효과적일 뿐\n"
							+ "아니라 가슴 근육에\n"
							+ "다른 자극을 줄 수 있다.\n" + 
							"\n" + 
							"다리를 벤치 위에 올려놓고 실시하면\n"
							+ "부하가 증대되어 가슴 상부의 발달에\n"
							+ "더 큰 효과가 있다.\n",
							0, 800, 8);


			add(title);
			add(summary);
			add(summaryContents);
			add(procedure);
			add(procedureContents);
			add(caution);
			add(cautionContents);
			add(tip);
			add(tipContents);
		}
	}

	private class SitUp extends BaseTab {
		private SitUp() {
			super("윗몸일으키기 sit up");
			setPreferredSize(new Dimension(320, 960));

			summary = setTitle("개요", 0, 80);
			summaryContents = setContents(
					"가장 일반적으로 할 수 있는 복근 운동이다.\n"
							+ "앉았다 누웠다를 반복하는 동작이므로\n"
							+ "복근과 다리를 들어올리는 근육을\n"
							+ "발달시킬 수 있다.",
							0, 120, 4);


			procedure = setTitle("운동 순서", 0, 240);
			procedureContents = setContents(
					"1. 바닥에 누워 무릎을 구부리고 발이\n"
							+ "바닥과 떨어지지 않도록 한다.\n\n" 
							+ "2. 양손을 귀에 대고 복부에 힘을 주면서\n"
							+ "고개를 살짝 든다.\n\n" 
							+ 	"3. 팔꿈치가 무릎에 닿을 정도까지 등을\n"
							+ "둥글게 구부리면서 상체를 일으킨다.\n\n"
							+ "4. 복근에 힘이 풀어지지 않도록 천천히\n"
							+ "긴장하면서 원위치한다.",
							0, 280, 11);


			caution = setTitle("주의 사항", 0, 540);
			cautionContents = setContents(
					"복근의 힘이 충분하지 못한 상태에서 실시하게\n"
							+ "되면 허리에 통증을 유발할 수 있다.",
							0, 580, 2);


			tip = setTitle("운동 팁", 0 , 660);
			tipContents = setContents(
					"디클라인 벤치에서 실시할 수 있으며\n"
							+ "외복사근 강화를 위해 몸을 비틀면서\n"
							+ "실시할 수 있다.",
							0, 700, 3);


			add(title);
			add(summary);
			add(summaryContents);
			add(procedure);
			add(procedureContents);
			add(caution);
			add(cautionContents);
			add(tip);
			add(tipContents);
		}
	}

	private class Squat extends BaseTab{
		private Squat() {
			super("스쿼트 squat");
			setPreferredSize(new Dimension(320, 960));

			summary = setTitle("개요", 0, 80);
			summaryContents = setContents(
					"대퇴사두근과 둔근의 근력과 크기 증가를\n"
							+ "위한 운동이다.\n"
							+ "등을 곧게 편 상태에서 전방을 주시하면서\n"
							+ "의자에 앉듯이 천천히 몸을 낮추면서\n"
							+ "실시하면 더욱 균형감 있게 실시할 수 있다.\n",
							0, 120, 5);


			procedure = setTitle("운동 순서", 0, 260);
			procedureContents = setContents(
					"1. 어깨너비로 서서 발끝이 약간 바깥쪽을\n"
					+ "향하도록 한다.시선은 정면을 향하고\n"
					+ "복근에 힘을 주어 허리를 단단히 조여준다.\n\n" 
							+ "2. 무릎이 발끝보다 앞으로 나오지 않도록\n"
							+ "하면서 허벅지와 수평이 될 때까지 앉는다.\n\n" 
							+ "3. 발뒤꿈치로 민다는 느낌으로 허벅지에\n"
							+ "힘을 주면서 일어선다.",
							0, 300, 9);


			caution = setTitle("주의 사항", 0, 520);
			cautionContents = setContents(
					"안정성을 위해 허리는 항상 곧게 펴고,\n"
					+ "척추의 곡선을 그대로 유지하면서\n"
					+ "대퇴, 고관절, 아킬레스건의 유연성이\n"
					+ "사전에 확보되도록 스트레칭한다.\n\n"
					+ "무릎을 바깥쪽 또는 안쪽으로 굽히지 말고,\n"
					+ "일정하게 수평을 이루며 동작을 실시한다.",
							0, 560, 7);

			tip = setTitle("운동 팁", 0 , 740);
			tipContents = setContents(
					"다리를 좀 더 넓게 벌리고 발끝을 약 45도\n"
					+ "벌린 상태에서 덤벨의 원판을 잡고\n"
					+ "다리 사이에 위치시킨 후 스쿼트 자세를하면\n"
					+ "내전근을 더욱 동원시키면서 운동할 수 있다.",
							0, 780, 4);


			add(title);
			add(summary);
			add(summaryContents);
			add(procedure);
			add(procedureContents);
			add(caution);
			add(cautionContents);
			add(tip);
			add(tipContents);
		}
	}

	private class Plank extends BaseTab{
		private Plank() {
			super("플랭크 plank");
			setPreferredSize(new Dimension(320, 800));

			summary = setTitle("개요", 0, 80);
			summaryContents = setContents(
					"허리나 기타 관절, 힘줄, 인대를 사용하지\n"
					+ "않는 코어머슬 운동으로 각광받는 맨손 \n"
					+ "운동의 일종으로서 땅과 몸만 있으면\n"
					+ "어디서나 가능한 운동이다.\n\n"
					+ "자세를 널빤지처럼 평평하게 엎드린 상태로\n"
					+ "실행하는 운동이다.",
							0, 120, 7);


			procedure = setTitle("운동 순서", 0, 300);
			procedureContents = setContents(
					"1. 엎드린 자세에서 팔뚝으로 중심을 잡고\n"
					+ "다리는 약간 벌린 상태로 일자를 유지한다.\n"
					+ "몸은 전체에 걸쳐 일자상태를 유지한다.\n\n"
					+ "2. 팔꿈치와 어깨는 일자를 유지한다.\n\n"
					+ "3. 이 자세를 15초 이상 유지한다.",
							0, 340, 7);


			caution = setTitle("주의 사항", 0, 520);
			cautionContents = setContents(
					"자세를 유지하는 것이 가장 중요하다.\n\n" 
							+ "자세를 유지하지 못 할경우\n"
							+ "다른 부위에 무리가 갈 수 있다.",
							0, 560, 4);

			tip = setTitle("운동 팁", 0 , 680);
			tipContents = setContents(
					"등에 하중을 가하거나 한 팔을 앞으로\n"
					+ "뻗는 등의 변형 동작을 취해주면\n"
					+ "더 큰 자극을 줄 수 있다.\n",
							0, 720, 3);


			add(title);
			add(summary);
			add(summaryContents);
			add(procedure);
			add(procedureContents);
			add(caution);
			add(cautionContents);
			add(tip);
			add(tipContents);
		}
	}

	private JLabel setTitle(String contents, int xpos, int ypos) {
		JLabel label = new JLabel(contents);
		label.setBackground(Colors.myBackGround);
		label.setForeground(Colors.myFontColor);
		label.setFont(Font.GOTHIC_BOLD_20);
		label.setBounds(xpos, ypos, 300, 40);

		return label;
	}

	private JTextArea setContents(String contents, int xpos, int ypos, int line) {
		JTextArea textArea = new JTextArea(contents);
		textArea.setBackground(Colors.myBackGround);
		textArea.setForeground(Colors.myFontColor);
		textArea.setFont(Font.GOTHIC_PLAIN_16);
		textArea.setEditable(false);
		textArea.setBounds(xpos, ypos, 300, 20*line);

		return textArea; 
	}
}
