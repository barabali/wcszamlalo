package main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

	private TestEnv environment;
	
	private JPanel panel;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	
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
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("UI", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0,0.0, 0.0, 0.0};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label = new JLabel("Level");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel_1.add(label, gbc_label);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"-2", "-1", "0", "1", "2", "3", "4"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 0;
		panel_1.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblWing = new JLabel("Wing");
		GridBagConstraints gbc_lblWing = new GridBagConstraints();
		gbc_lblWing.fill = GridBagConstraints.VERTICAL;
		gbc_lblWing.insets = new Insets(0, 0, 5, 5);
		gbc_lblWing.gridx = 3;
		gbc_lblWing.gridy = 0;
		panel_1.add(lblWing, gbc_lblWing);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"B", "L", "E"}));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.gridx = 4;
		gbc_comboBox_2.gridy = 0;
		panel_1.add(comboBox_2, gbc_comboBox_2);
		
		JLabel label_15 = new JLabel("");
		GridBagConstraints gbc_label_15 = new GridBagConstraints();
		gbc_label_15.fill = GridBagConstraints.BOTH;
		gbc_label_15.insets = new Insets(0, 0, 5, 5);
		gbc_label_15.gridx = 5;
		gbc_label_15.gridy = 0;
		panel_1.add(label_15, gbc_label_15);
		
		JLabel label_16 = new JLabel("");
		GridBagConstraints gbc_label_16 = new GridBagConstraints();
		gbc_label_16.fill = GridBagConstraints.BOTH;
		gbc_label_16.insets = new Insets(0, 0, 5, 0);
		gbc_label_16.gridx = 9;
		gbc_label_16.gridy = 0;
		panel_1.add(label_16, gbc_label_16);
		
		JLabel lblWonam = new JLabel("Woman");
		GridBagConstraints gbc_lblWonam = new GridBagConstraints();
		gbc_lblWonam.fill = GridBagConstraints.VERTICAL;
		gbc_lblWonam.insets = new Insets(0, 0, 5, 5);
		gbc_lblWonam.gridx = 0;
		gbc_lblWonam.gridy = 1;
		panel_1.add(lblWonam, gbc_lblWonam);
		
		JLabel label_17 = new JLabel("");
		GridBagConstraints gbc_label_17 = new GridBagConstraints();
		gbc_label_17.fill = GridBagConstraints.BOTH;
		gbc_label_17.insets = new Insets(0, 0, 5, 5);
		gbc_label_17.gridx = 1;
		gbc_label_17.gridy = 1;
		panel_1.add(label_17, gbc_label_17);
		
		JLabel lblMan = new JLabel("Man");
		GridBagConstraints gbc_lblMan = new GridBagConstraints();
		gbc_lblMan.fill = GridBagConstraints.VERTICAL;
		gbc_lblMan.insets = new Insets(0, 0, 5, 5);
		gbc_lblMan.gridx = 3;
		gbc_lblMan.gridy = 1;
		panel_1.add(lblMan, gbc_lblMan);
		
		JLabel lblDisabled = new JLabel("Disabled");
		GridBagConstraints gbc_lblDisabled = new GridBagConstraints();
		gbc_lblDisabled.fill = GridBagConstraints.VERTICAL;
		gbc_lblDisabled.insets = new Insets(0, 0, 5, 5);
		gbc_lblDisabled.gridx = 4;
		gbc_lblDisabled.gridy = 1;
		panel_1.add(lblDisabled, gbc_lblDisabled);
		
		JLabel label_19 = new JLabel("");
		GridBagConstraints gbc_label_19 = new GridBagConstraints();
		gbc_label_19.fill = GridBagConstraints.BOTH;
		gbc_label_19.insets = new Insets(0, 0, 5, 5);
		gbc_label_19.gridx = 8;
		gbc_label_19.gridy = 1;
		panel_1.add(label_19, gbc_label_19);
		
		JButton button = new JButton("+");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 0;
		gbc_button.gridy = 2;
		panel_1.add(button, gbc_button);
		
		JLabel label_20 = new JLabel("");
		GridBagConstraints gbc_label_20 = new GridBagConstraints();
		gbc_label_20.fill = GridBagConstraints.BOTH;
		gbc_label_20.insets = new Insets(0, 0, 5, 5);
		gbc_label_20.gridx = 1;
		gbc_label_20.gridy = 2;
		panel_1.add(label_20, gbc_label_20);
		
		JButton button_1 = new JButton("Urinal");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 3;
		gbc_button_1.gridy = 2;
		panel_1.add(button_1, gbc_button_1);
		
		JButton button_3 = new JButton("+");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 5, 5);
		gbc_button_3.gridx = 4;
		gbc_button_3.gridy = 2;
		panel_1.add(button_3, gbc_button_3);
		
		JLabel label_21 = new JLabel("");
		GridBagConstraints gbc_label_21 = new GridBagConstraints();
		gbc_label_21.fill = GridBagConstraints.BOTH;
		gbc_label_21.insets = new Insets(0, 0, 5, 5);
		gbc_label_21.gridx = 5;
		gbc_label_21.gridy = 2;
		panel_1.add(label_21, gbc_label_21);
		
		JLabel label_22 = new JLabel("");
		GridBagConstraints gbc_label_22 = new GridBagConstraints();
		gbc_label_22.fill = GridBagConstraints.BOTH;
		gbc_label_22.insets = new Insets(0, 0, 5, 5);
		gbc_label_22.gridx = 8;
		gbc_label_22.gridy = 2;
		panel_1.add(label_22, gbc_label_22);
		
		JLabel label_18 = new JLabel("");
		GridBagConstraints gbc_label_18 = new GridBagConstraints();
		gbc_label_18.fill = GridBagConstraints.BOTH;
		gbc_label_18.insets = new Insets(0, 0, 5, 5);
		gbc_label_18.gridx = 2;
		gbc_label_18.gridy = 3;
		panel_1.add(label_18, gbc_label_18);
		
		JLabel label_23 = new JLabel("");
		GridBagConstraints gbc_label_23 = new GridBagConstraints();
		gbc_label_23.fill = GridBagConstraints.BOTH;
		gbc_label_23.insets = new Insets(0, 0, 5, 5);
		gbc_label_23.gridx = 0;
		gbc_label_23.gridy = 4;
		panel_1.add(label_23, gbc_label_23);
		
		JLabel label_24 = new JLabel("");
		GridBagConstraints gbc_label_24 = new GridBagConstraints();
		gbc_label_24.fill = GridBagConstraints.BOTH;
		gbc_label_24.insets = new Insets(0, 0, 5, 5);
		gbc_label_24.gridx = 1;
		gbc_label_24.gridy = 4;
		panel_1.add(label_24, gbc_label_24);
		
		JLabel label_25 = new JLabel("");
		GridBagConstraints gbc_label_25 = new GridBagConstraints();
		gbc_label_25.gridheight = 2;
		gbc_label_25.fill = GridBagConstraints.BOTH;
		gbc_label_25.insets = new Insets(0, 0, 5, 5);
		gbc_label_25.gridx = 5;
		gbc_label_25.gridy = 3;
		panel_1.add(label_25, gbc_label_25);
		
		JButton button_2 = new JButton("Toilet");
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 3;
		gbc_button_2.gridy = 4;
		panel_1.add(button_2, gbc_button_2);
		
		JLabel label_26 = new JLabel("");
		GridBagConstraints gbc_label_26 = new GridBagConstraints();
		gbc_label_26.fill = GridBagConstraints.BOTH;
		gbc_label_26.insets = new Insets(0, 0, 5, 5);
		gbc_label_26.gridx = 8;
		gbc_label_26.gridy = 4;
		panel_1.add(label_26, gbc_label_26);
		
		JLabel label_27 = new JLabel("");
		GridBagConstraints gbc_label_27 = new GridBagConstraints();
		gbc_label_27.fill = GridBagConstraints.BOTH;
		gbc_label_27.insets = new Insets(0, 0, 5, 0);
		gbc_label_27.gridx = 9;
		gbc_label_27.gridy = 4;
		panel_1.add(label_27, gbc_label_27);
		
		JLabel label_1 = new JLabel("Result:");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.VERTICAL;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 5;
		panel_1.add(label_1, gbc_label_1);
		
		JLabel label_28 = new JLabel("");
		GridBagConstraints gbc_label_28 = new GridBagConstraints();
		gbc_label_28.fill = GridBagConstraints.BOTH;
		gbc_label_28.insets = new Insets(0, 0, 0, 5);
		gbc_label_28.gridx = 1;
		gbc_label_28.gridy = 5;
		panel_1.add(label_28, gbc_label_28);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.gridx = 3;
		gbc_textField_3.gridy = 5;
		panel_1.add(textField_3, gbc_textField_3);
		
		JLabel label_29 = new JLabel("");
		GridBagConstraints gbc_label_29 = new GridBagConstraints();
		gbc_label_29.fill = GridBagConstraints.BOTH;
		gbc_label_29.insets = new Insets(0, 0, 0, 5);
		gbc_label_29.gridx = 7;
		gbc_label_29.gridy = 5;
		panel_1.add(label_29, gbc_label_29);
		
		JLabel label_30 = new JLabel("");
		GridBagConstraints gbc_label_30 = new GridBagConstraints();
		gbc_label_30.fill = GridBagConstraints.BOTH;
		gbc_label_30.insets = new Insets(0, 0, 0, 5);
		gbc_label_30.gridx = 8;
		gbc_label_30.gridy = 5;
		panel_1.add(label_30, gbc_label_30);
		
		JLabel label_31 = new JLabel("");
		GridBagConstraints gbc_label_31 = new GridBagConstraints();
		gbc_label_31.fill = GridBagConstraints.BOTH;
		gbc_label_31.gridx = 9;
		gbc_label_31.gridy = 5;
		panel_1.add(label_31, gbc_label_31);
		
		JPanel ControlPanel = new JPanel();
		tabbedPane.addTab("Control", null, ControlPanel, null);
		GridBagLayout gbl_ControlPanel = new GridBagLayout();
		gbl_ControlPanel.columnWidths = new int[] {0};
		gbl_ControlPanel.rowHeights = new int[] {0};
		gbl_ControlPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_ControlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		ControlPanel.setLayout(gbl_ControlPanel);
		
		JLabel label_2 = new JLabel("Number:");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0,0,0);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 0;
		ControlPanel.add(label_2, gbc_label_2);
		
		JLabel label_4 = new JLabel("");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 0;
		ControlPanel.add(label_4, gbc_label_4);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"IE222", "IB135"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 0;
		ControlPanel.add(comboBox, gbc_comboBox);
		
		JLabel label_5 = new JLabel("");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.BOTH;
		gbc_label_5.insets = new Insets(0, 0, 5, 0);
		gbc_label_5.gridx = 3;
		gbc_label_5.gridy = 0;
		ControlPanel.add(label_5, gbc_label_5);
		
		JLabel label_6 = new JLabel("");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.BOTH;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 0;
		gbc_label_6.gridy = 1;
		ControlPanel.add(label_6, gbc_label_6);
		
		JLabel lblWoman = new JLabel("Woman");
		GridBagConstraints gbc_lblWoman = new GridBagConstraints();
		gbc_lblWoman.fill = GridBagConstraints.BOTH;
		gbc_lblWoman.insets = new Insets(0, 0, 5, 5);
		gbc_lblWoman.gridx = 1;
		gbc_lblWoman.gridy = 1;
		ControlPanel.add(lblWoman, gbc_lblWoman);
		
		JLabel lblMan_1 = new JLabel("Man");
		GridBagConstraints gbc_lblMan_1 = new GridBagConstraints();
		gbc_lblMan_1.fill = GridBagConstraints.BOTH;
		gbc_lblMan_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblMan_1.gridx = 2;
		gbc_lblMan_1.gridy = 1;
		ControlPanel.add(lblMan_1, gbc_lblMan_1);
		
		JLabel lblDisabled_1 = new JLabel("Disabled");
		GridBagConstraints gbc_lblDisabled_1 = new GridBagConstraints();
		gbc_lblDisabled_1.fill = GridBagConstraints.BOTH;
		gbc_lblDisabled_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblDisabled_1.gridx = 3;
		gbc_lblDisabled_1.gridy = 1;
		ControlPanel.add(lblDisabled_1, gbc_lblDisabled_1);
		
		JLabel label_7 = new JLabel("");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.fill = GridBagConstraints.BOTH;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 2;
		ControlPanel.add(label_7, gbc_label_7);
		
		JButton ManUrinalTakenButton = new JButton("Urinal take");
		
		JButton button_4 = new JButton("+");
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 1;
		gbc_button_4.gridy = 2;
		ControlPanel.add(button_4, gbc_button_4);
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 2;
		gbc_button_5.gridy = 2;
		ControlPanel.add(ManUrinalTakenButton, gbc_button_5);
		
		JButton button_7 = new JButton("+");
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.insets = new Insets(0, 0, 5, 0);
		gbc_button_7.gridx = 3;
		gbc_button_7.gridy = 2;
		ControlPanel.add(button_7, gbc_button_7);
		
		JLabel label_8 = new JLabel("");
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.fill = GridBagConstraints.BOTH;
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 3;
		ControlPanel.add(label_8, gbc_label_8);
		
		JLabel label_9 = new JLabel("");
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.fill = GridBagConstraints.BOTH;
		gbc_label_9.insets = new Insets(0, 0, 5, 5);
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 3;
		ControlPanel.add(label_9, gbc_label_9);
		
		JButton ManUrinalFreeButton = new JButton("Urinal free");
		GridBagConstraints gbc_btnUrinalFree = new GridBagConstraints();
		gbc_btnUrinalFree.insets = new Insets(0, 0, 5, 5);
		gbc_btnUrinalFree.gridx = 2;
		gbc_btnUrinalFree.gridy = 3;
		ControlPanel.add(ManUrinalFreeButton, gbc_btnUrinalFree);
		
		JLabel label_10 = new JLabel("");
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.fill = GridBagConstraints.BOTH;
		gbc_label_10.insets = new Insets(0, 0, 5, 0);
		gbc_label_10.gridx = 3;
		gbc_label_10.gridy = 3;
		ControlPanel.add(label_10, gbc_label_10);
		
		JLabel label_11 = new JLabel("");
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.fill = GridBagConstraints.BOTH;
		gbc_label_11.insets = new Insets(0, 0, 5, 5);
		gbc_label_11.gridx = 0;
		gbc_label_11.gridy = 4;
		ControlPanel.add(label_11, gbc_label_11);
		
		JButton ManToiletTakenButton = new JButton("Toilet take");
		GridBagConstraints gbc_btnToiletTake = new GridBagConstraints();
		gbc_btnToiletTake.insets = new Insets(0, 0, 5, 5);
		gbc_btnToiletTake.gridx = 2;
		gbc_btnToiletTake.gridy = 4;
		ControlPanel.add(ManToiletTakenButton, gbc_btnToiletTake);
		
		JLabel label_12 = new JLabel("");
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.fill = GridBagConstraints.BOTH;
		gbc_label_12.insets = new Insets(0, 0, 5, 0);
		gbc_label_12.gridx = 3;
		gbc_label_12.gridy = 4;
		ControlPanel.add(label_12, gbc_label_12);
		
		JLabel label_13 = new JLabel("");
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.fill = GridBagConstraints.BOTH;
		gbc_label_13.insets = new Insets(0, 0, 5, 5);
		gbc_label_13.gridx = 0;
		gbc_label_13.gridy = 5;
		ControlPanel.add(label_13, gbc_label_13);
		
		JLabel label_14 = new JLabel("");
		GridBagConstraints gbc_label_14 = new GridBagConstraints();
		gbc_label_14.fill = GridBagConstraints.BOTH;
		gbc_label_14.insets = new Insets(0, 0, 5, 5);
		gbc_label_14.gridx = 1;
		gbc_label_14.gridy = 5;
		ControlPanel.add(label_14, gbc_label_14);
		
		JButton ManToiletFreeButton = new JButton("Toilet free");
		GridBagConstraints gbc_btnToiletFree = new GridBagConstraints();
		gbc_btnToiletFree.insets = new Insets(0, 0, 5, 5);
		gbc_btnToiletFree.gridx = 2;
		gbc_btnToiletFree.gridy = 5;
		ControlPanel.add(ManToiletFreeButton, gbc_btnToiletFree);
		
		JLabel label_3 = new JLabel("#");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 6;
		ControlPanel.add(label_3, gbc_label_3);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 6;
		ControlPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 6;
		ControlPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 3;
		gbc_textField_4.gridy = 6;
		ControlPanel.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 7;
		ControlPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		ManToiletTakenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.ManToiletTaken("IB404");
			}
		});
		
		ManToiletFreeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.ManToiletFree("IB404");
			}
		});
		
		ManUrinalTakenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.ManUrinalTaken("IE-210");
			}
		});
		
		ManUrinalFreeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				environment.ManUrinalFree("IE-210");
			}
		});
	}

	public void setEnv(TestEnv testEnv) {
		environment=testEnv;
	}

}
