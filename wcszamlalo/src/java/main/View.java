package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;

public class View extends GridWorldView {

	TestEnv environment;
	
	private JPanel panel;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	JButton ManToiletTakenButton;
	
	public View(GridWorldModel model, String title, int windowSize) {
		super(model, title, windowSize);
		setVisible(true);
        repaint();
	}
	
	@Override
    public void initComponents(int width) {
		super.initComponents(width);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        setContentPane(panel);
        
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel UIPanel = new JPanel();
		tabbedPane.addTab("UI", null, UIPanel, null);
		UIPanel.setLayout(null);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"-2", "-1", "0", "1", "2", "3", "4"}));
		comboBox_1.setBounds(106, 25, 48, 20);
		UIPanel.add(comboBox_1);
		
		JButton button = new JButton("+");
		button.setBounds(49, 111, 41, 23);
		UIPanel.add(button);
		
		JButton button_1 = new JButton("Urinal");
		button_1.setBounds(176, 111, 59, 23);
		UIPanel.add(button_1);
		
		JButton button_2 = new JButton("Toilet");
		button_2.setBounds(176, 144, 59, 23);
		UIPanel.add(button_2);
		
		JButton button_3 = new JButton("+");
		button_3.setBounds(301, 111, 41, 23);
		UIPanel.add(button_3);
		
		JLabel label = new JLabel("Level");
		label.setBounds(65, 28, 25, 14);
		UIPanel.add(label);
		
		textField_3 = new JTextField();
		textField_3.setBounds(106, 192, 86, 20);
		textField_3.setColumns(10);
		UIPanel.add(textField_3);
		
		JLabel label_1 = new JLabel("Result:");
		label_1.setBounds(28, 195, 34, 14);
		UIPanel.add(label_1);
		
		JLabel lblWing = new JLabel("Wing");
		lblWing.setBounds(189, 28, 46, 14);
		UIPanel.add(lblWing);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"B", "L", "E"}));
		comboBox_2.setBounds(259, 25, 41, 20);
		UIPanel.add(comboBox_2);
		
		JLabel lblWonam = new JLabel("Woman");
		lblWonam.setBounds(44, 70, 46, 14);
		UIPanel.add(lblWonam);
		
		JLabel lblMan = new JLabel("Man");
		lblMan.setBounds(182, 70, 46, 14);
		UIPanel.add(lblMan);
		
		JLabel lblDisabled = new JLabel("Disabled");
		lblDisabled.setBounds(301, 70, 46, 14);
		UIPanel.add(lblDisabled);
		
		JPanel ControlPanel = new JPanel();
		tabbedPane.addTab("Control", null, ControlPanel, null);
		ControlPanel.setLayout(null);
		
		JButton button_4 = new JButton("+");
		button_4.setBounds(60, 103, 41, 23);
		ControlPanel.add(button_4);
		
		JButton button_5 = new JButton("Urinal");
		button_5.setBounds(169, 103, 59, 23);
		ControlPanel.add(button_5);
		
		JLabel label_2 = new JLabel("Number:");
		label_2.setBounds(43, 11, 58, 14);
		ControlPanel.add(label_2);
		
		ManToiletTakenButton = new JButton("Toilet");
		ManToiletTakenButton.setBounds(169, 137, 80, 23);
		ControlPanel.add(ManToiletTakenButton);
		
		JButton button_7 = new JButton("+");
		button_7.setBounds(283, 103, 41, 23);
		ControlPanel.add(button_7);
		
		JLabel lblWoman = new JLabel("Woman");
		lblWoman.setBounds(60, 66, 46, 14);
		ControlPanel.add(lblWoman);
		
		JLabel lblMan_1 = new JLabel("Man");
		lblMan_1.setBounds(182, 66, 46, 14);
		ControlPanel.add(lblMan_1);
		
		JLabel lblDisabled_1 = new JLabel("Disabled");
		lblDisabled_1.setBounds(283, 66, 46, 14);
		ControlPanel.add(lblDisabled_1);
		
		JLabel label_3 = new JLabel("#");
		label_3.setBounds(10, 174, 46, 14);
		ControlPanel.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(43, 171, 86, 20);
		ControlPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(155, 171, 86, 20);
		ControlPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(260, 171, 86, 20);
		ControlPanel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(155, 202, 86, 20);
		ControlPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"IE222", "IB135"}));
		comboBox.setBounds(141, 8, 59, 20);
		ControlPanel.add(comboBox);
		
		ManToiletTakenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.ManToiletTaken();
			}
		});
	}

	public void setEnv(TestEnv testEnv) {
		environment=testEnv;
	}

}
