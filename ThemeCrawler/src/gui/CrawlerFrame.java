package gui;

import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.*;
import org.jvnet.substance.skin.*;

import search.*;

public class CrawlerFrame extends JFrame implements ActionListener {
	JTabbedPane tabbedPane;
	JPanel panel, panel1, panel2, panel3, panel4, panel5, panel6, panel7,
			panel8;
	JLabel label1, label2, label3, label4, label5, label6, label7, label8,
			label9, label10, label11, label12, label13, label14, label15,
			label16;
	JTextField textfield, textfield2, textfield3, textfield4, textfield5,
			textfield6, textfield7, textfield8;
	JTextPane textpane;
	JTextArea textarea;
	JButton button, button1, button2, button3, button4;

	JEditorPane editorpane;
	GridLayout gridlayout, gridlayout2;
	GridBagLayout gridbaglayout;
	GridBagConstraints constraints;
	FlowLayout flowlayout;

	Crawler crawler;
	boolean changed = false;

	private String deftitle = "云计算";
	private int defurlCount = 1000;
	private int defthreadCount = 5;// 表示最多同时允许运行多少个线程
	private double defthreshold = 0.91;
	private String defstartURL = "http://cloud.csdn.net/";

	public CrawlerFrame() {
		textpane = new JTextPane();
		label5 = new JLabel("访问总数：");
		button = new JButton("开始");
		crawler = new Crawler("云计算", "http://cloud.csdn.net/", textpane,
				label5, button);
		crawler.addKeyWord("云计算", 0);
		crawler.addKeyWord("数据中心", 0);
		crawler.addKeyWord("平台", 0);
		crawler.addKeyWord("架构", 0);
		crawler.addKeyWord("数据库", 0);
		crawler.addKeyWord("安全", 0);
		crawler.addKeyWord("Hadoop", 0);
		crawler.addKeyWord("存储", 0);
		crawler.addKeyWord("虚拟化", 0);
		crawler.addKeyWord("隐私", 0);
		crawler.addKeyWord("黑客", 0);
		crawler.addKeyWord("分布式", 0);
		crawler.addKeyWord("MapReduce", 0);
		crawler.addKeyWord("cloud", 0);

		setTitle("主题爬虫");
		setBounds(300, 300, 600, 400);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		tabbedPane = new JTabbedPane();
		// panel1是首页的底层面板
		panel1 = new JPanel(new BorderLayout());

		// textpane.setContentType("texthtml");
		// textpane.addHyperlinkListener(new
		// javax.swing.event.HyperlinkListener() {
		// public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent e) {
		//
		// if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
		// System.out.println("sssss");
		// try {
		// //System.out.println(e.getURL().toString());
		// String command = "cmd /c start " + e.getDescription();
		// Runtime.getRuntime().exec(command);
		//
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }
		// }
		// }
		// });
		// try
		// {
		// SimpleAttributeSet set=new SimpleAttributeSet();
		// StyleConstants.setUnderline(set, true);
		// textpane.getDocument().insertString(0,
		// "<a href=http://www.baidu.com>nihao</a>", set);
		// }
		// catch (BadLocationException e)
		// {
		// e.printStackTrace();
		// }
		panel1.add(new JScrollPane(textpane), BorderLayout.CENTER);

		// panel3是首页右侧按钮的面板
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

		button.addActionListener(this);
		button1 = new JButton("访问");
		button1.addActionListener(this);

		button2 = new JButton("停止");
		button2.addActionListener(this);
		panel3.add(button);
		panel3.add(button1);
		panel3.add(button2);
		panel1.add(panel3, BorderLayout.EAST);

		gridlayout = new GridLayout(1, 2);
		panel4 = new JPanel(gridlayout);

		// label6 = new JLabel("用时：");
		panel4.add(label5);
		// panel4.add(label6);
		panel1.add(panel4, BorderLayout.SOUTH);

		gridbaglayout = new GridBagLayout();
		constraints = new GridBagConstraints();

		panel2 = new JPanel(gridbaglayout);

		panel5 = new JPanel();
		label7 = new JLabel("主      题:");
		textfield = new JTextField(44);
		textfield.setText(crawler.getTitle());
		panel5.add(label7);
		panel5.add(textfield);

		panel6 = new JPanel();
		label8 = new JLabel("初始网页:");
		textfield2 = new JTextField(44);
		textfield2.setText(crawler.getStartURL());
		panel6.add(label8);
		panel6.add(textfield2);

		panel7 = new JPanel();
		label9 = new JLabel("访问总数:");
		textfield3 = new JTextField(11);
		textfield3.setText("" + crawler.getUrlCount());
		label10 = new JLabel("线程个数:");
		textfield4 = new JTextField(11);
		textfield4.setText("" + crawler.getThreadCount());
		label11 = new JLabel("阈值:");
		textfield5 = new JTextField(11);
		textfield5.setText("" + crawler.getThreshold());
		panel7.add(label9);
		panel7.add(textfield3);
		panel7.add(label10);
		panel7.add(textfield4);
		panel7.add(label11);
		panel7.add(textfield5);

		label12 = new JLabel(" 关键词:");

		textarea = new JTextArea();
		textarea.setColumns(50);
		textarea.setRows(10);
		textarea.setLineWrap(true);
		JScrollPane scrollane = new JScrollPane(textarea);

		Enumeration<String> keywords = crawler.getKeyWords();
		while (keywords.hasMoreElements()) {
			textarea.append(keywords.nextElement() + "|");
		}

		panel8 = new JPanel();
		button3 = new JButton("应用");
		button3.addActionListener(this);
		button4 = new JButton("恢复默认");
		button4.addActionListener(this);
		panel8.add(button3);
		panel8.add(button4);

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		// constraints.
		constraints.anchor = GridBagConstraints.WEST; // 当组件没有空间大时，使组件处在北部
		constraints.insets = new Insets(0, 10, 0, 10);
		gridbaglayout.setConstraints(panel5, constraints);
		panel2.add(panel5);
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST; // 当组件没有空间大时，使组件处在北部
		constraints.insets = new Insets(0, 10, 0, 10);
		gridbaglayout.setConstraints(panel6, constraints);
		panel2.add(panel6);
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST; // 当组件没有空间大时，使组件处在北部
		constraints.insets = new Insets(0, 10, 0, 10);
		gridbaglayout.setConstraints(panel7, constraints);
		panel2.add(panel7);
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST; // 当组件没有空间大时，使组件处在北部
		constraints.insets = new Insets(0, 10, 0, 10);
		gridbaglayout.setConstraints(label12, constraints);
		panel2.add(label12);
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.WEST; // 当组件没有空间大时，使组件处在北部
		constraints.insets = new Insets(0, 10, 0, 10);
		gridbaglayout.setConstraints(scrollane, constraints);
		panel2.add(scrollane);
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.weightx = 1.0;
		constraints.anchor = GridBagConstraints.EAST; // 当组件没有空间大时，使组件处在北部
		constraints.insets = new Insets(0, 10, 0, 10);
		gridbaglayout.setConstraints(panel8, constraints);
		panel2.add(panel8);
		tabbedPane.addTab("首页", panel1);

		tabbedPane.addTab("设置", panel2);

		panel.add(tabbedPane, BorderLayout.CENTER);
		this.add(panel);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new SubstanceFieldOfWheatLookAndFeel());
		} catch (Exception e) {
			System.out.println("Substance Raven Graphite failed to initialize");
		}
		new CrawlerFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			textpane.setText("");
			crawler.initialize();
			crawler.parallelhandle();
			button.setEnabled(false);
		} else if (e.getSource() == button1) {
			String web = textpane.getSelectedText();
			URI uri;
			try {
				uri = new URI(web);
				Desktop.getDesktop().browse(uri);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Wrong URL address!",
						"Error", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == button2) {
			crawler.stopSearch();
			button.setEnabled(true);
		} else if (e.getSource() == button3) {
			crawler.setTitle(textfield.getText());
			crawler.setStartURL(textfield2.getText());
			try {
				int urlcount = Integer.parseInt(textfield3.getText());
				crawler.setUrlCount(urlcount);
			} catch (NumberFormatException ee) {
				JOptionPane.showMessageDialog(null,
						"Format Error of Url Count!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			try {
				int threadCount = Integer.parseInt(textfield4.getText());
				crawler.setThreadCount(threadCount);
			} catch (NumberFormatException ee) {
				JOptionPane.showMessageDialog(null,
						"Format Error of thread Count!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			try {
				double threshold = Double.parseDouble(textfield5.getText());
				crawler.setThreshold(threshold);
			} catch (NumberFormatException ee) {
				JOptionPane.showMessageDialog(null,
						"Format Error of threshold!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			crawler.removeAllKeyWords();
			String[] keywords = textarea.getText().split("\\|");
			for (int i = 0; i < keywords.length; i++) {
				// System.out.println(keywords[i]);
				crawler.addKeyWord(keywords[i], 0);
			}
			JOptionPane.showMessageDialog(null,
					"Succeed to set the configuration!", "Set",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == button4) {
			crawler.setStartURL(defstartURL);
			crawler.setTitle(deftitle);
			crawler.setThreadCount(defthreadCount);
			crawler.setThreshold(defthreshold);
			crawler.setUrlCount(defurlCount);
			crawler.removeAllKeyWords();
			crawler.addKeyWord("云计算", 0);
			crawler.addKeyWord("数据中心", 0);
			crawler.addKeyWord("平台", 0);
			crawler.addKeyWord("架构", 0);
			crawler.addKeyWord("数据库", 0);
			crawler.addKeyWord("安全", 0);
			crawler.addKeyWord("Hadoop", 0);
			crawler.addKeyWord("存储", 0);
			crawler.addKeyWord("虚拟化", 0);
			crawler.addKeyWord("隐私", 0);
			crawler.addKeyWord("黑客", 0);
			crawler.addKeyWord("分布式", 0);
			crawler.addKeyWord("MapReduce", 0);
			crawler.addKeyWord("cloud", 0);

			textarea.setText("");
			Enumeration<String> keywords = crawler.getKeyWords();
			while (keywords.hasMoreElements()) {
				textarea.append(keywords.nextElement() + "|");
			}

			textfield.setText(deftitle);
			textfield2.setText(defstartURL);
			textfield3.setText("" + defurlCount);
			textfield4.setText("" + defthreadCount);
			textfield5.setText("" + defthreshold);

			JOptionPane.showMessageDialog(null,
					"Set to the default configuration!", "Default",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
