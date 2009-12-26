package com.ontko.moss;

import java.applet.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ControlPanel extends JFrame {
	private static final long serialVersionUID = 134294234284236234L;
	Kernel kernel;
	JButton runButton = new JButton("run");
	JButton stepButton = new JButton("step");
	JButton resetButton = new JButton("reset");
	JButton exitButton = new JButton("exit");
	int pagesNum = 64;
	JButton[] buttons = new JButton[pagesNum];
	JLabel statusValueLabel = new JLabel("STOP", JLabel.LEFT);
	JLabel timeValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel instructionValueLabel = new JLabel("NONE", JLabel.LEFT);
	JLabel addressValueLabel = new JLabel("NULL", JLabel.LEFT);
	JLabel pageFaultValueLabel = new JLabel("NO", JLabel.LEFT);
	JLabel virtualPageValueLabel = new JLabel("x", JLabel.LEFT);
	JLabel physicalPageValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel RValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel MValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel inMemTimeValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel lastTouchTimeValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel lowValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel highValueLabel = new JLabel("0", JLabel.LEFT);
	JLabel[] labels = new JLabel[64];
    JComboBox algorithms = new JComboBox();
	ButtonActionListener bal = new ButtonActionListener();
	int topOffset = 5;

	public ControlPanel() {
		this("");
	}

	public ControlPanel(String title) {
		super(title);
	}

	public void init(Kernel useKernel, String commands, String config) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			try {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		kernel = useKernel;
		kernel.setControlPanel(this);
        pagesNum = Kernel.getVirtPageNum();
		setLayout(null);
		setSize(635, 545);
		setFont(new Font("Courier", 0, 12));

		for (int i = 0; i < pagesNum; i++)
			buttons[i] = new JButton("page " + i);
		for (int i = 0; i < pagesNum; i++)
			labels[i] = new JLabel("", JLabel.CENTER);

		runButton.setForeground(Color.blue);
		runButton.setBackground(Color.lightGray);
		runButton.setBounds(0, topOffset, 70, 15);
		runButton.addActionListener(bal);
		add(runButton);

		stepButton.setForeground(Color.blue);
		stepButton.setBackground(Color.lightGray);
		stepButton.setBounds(70, topOffset, 70, 15);
		stepButton.addActionListener(bal);
		add(stepButton);

		resetButton.setForeground(Color.blue);
		resetButton.setBackground(Color.lightGray);
		resetButton.setBounds(140, topOffset, 70, 15);
		resetButton.addActionListener(bal);
		add(resetButton);

		exitButton.setForeground(Color.blue);
		exitButton.setBackground(Color.lightGray);
		exitButton.setBounds(210, topOffset, 70, 15);
		exitButton.addActionListener(bal);
		add(exitButton);
		for (int i = 0; i < pagesNum; i++) {
			buttons[i].setBounds(i < pagesNum / 2 ? 0 : 140,
					((i % (pagesNum / 2)) + 2) * 15 + topOffset, 70, 15);
			buttons[i].setForeground(Color.magenta);
			buttons[i].setBackground(Color.lightGray);
			buttons[i].addActionListener(bal);
			add(buttons[i]);
		}

		statusValueLabel.setBounds(345, 0 + topOffset, 100, 15);
		add(statusValueLabel);

		timeValueLabel.setBounds(345, 15 + topOffset, 100, 15);
		add(timeValueLabel);

		instructionValueLabel.setBounds(385, 45 + topOffset, 100, 15);
		add(instructionValueLabel);

		addressValueLabel.setBounds(385, 60 + topOffset, 230, 15);
		add(addressValueLabel);

		pageFaultValueLabel.setBounds(385, 90 + topOffset, 100, 15);
		add(pageFaultValueLabel);

		virtualPageValueLabel.setBounds(395, 120 + topOffset, 200, 15);
		add(virtualPageValueLabel);

		physicalPageValueLabel.setBounds(395, 135 + topOffset, 200, 15);
		add(physicalPageValueLabel);

		RValueLabel.setBounds(395, 150 + topOffset, 200, 15);
		add(RValueLabel);

		MValueLabel.setBounds(395, 165 + topOffset, 200, 15);
		add(MValueLabel);

		inMemTimeValueLabel.setBounds(395, 180 + topOffset, 200, 15);
		add(inMemTimeValueLabel);

		lastTouchTimeValueLabel.setBounds(395, 195 + topOffset, 200, 15);
		add(lastTouchTimeValueLabel);

		lowValueLabel.setBounds(395, 210 + topOffset, 230, 15);
		add(lowValueLabel);

		highValueLabel.setBounds(395, 225 + topOffset, 230, 15);
		add(highValueLabel);

        JLabel algLabel = new JLabel("Algorithm: ");
        algLabel.setBounds(285, 245 + topOffset, 100, 20);
        add(algLabel);

        for (ReplacementAlgorithm.Names name : ReplacementAlgorithm.Names.values())
            algorithms.addItem(name);
        algorithms.setBounds(395, 245 + topOffset, 230, 20);
        algorithms.setSelectedItem(ReplacementAlgorithm.Names.DEFAULT);
        algorithms.addActionListener(new RACBActionListener());
        add(algorithms);
        

		JLabel virtualOneLabel = new JLabel("virtual", JLabel.CENTER);
		virtualOneLabel.setBounds(0, 15 + topOffset, 70, 15);
		add(virtualOneLabel);

		JLabel virtualTwoLabel = new JLabel("virtual", JLabel.CENTER);
		virtualTwoLabel.setBounds(140, 15 + topOffset, 70, 15);
		add(virtualTwoLabel);

		JLabel physicalOneLabel = new JLabel("physical", JLabel.CENTER);
		physicalOneLabel.setBounds(70, 15 + topOffset, 70, 15);
		add(physicalOneLabel);

		JLabel physicalTwoLabel = new JLabel("physical", JLabel.CENTER);
		physicalTwoLabel.setBounds(210, 15 + topOffset, 70, 15);
		add(physicalTwoLabel);

		JLabel statusLabel = new JLabel("status: ", JLabel.LEFT);
		statusLabel.setBounds(285, 0 + topOffset, 65, 15);
		add(statusLabel);

		JLabel timeLabel = new JLabel("time: ", JLabel.LEFT);
		timeLabel.setBounds(285, 15 + topOffset, 50, 15);
		add(timeLabel);

		JLabel instructionLabel = new JLabel("instruction: ", JLabel.LEFT);
		instructionLabel.setBounds(285, 45 + topOffset, 100, 15);
		add(instructionLabel);

		JLabel addressLabel = new JLabel("address: ", JLabel.LEFT);
		addressLabel.setBounds(285, 60 + topOffset, 85, 15);
		add(addressLabel);

		JLabel pageFaultLabel = new JLabel("page fault: ", JLabel.LEFT);
		pageFaultLabel.setBounds(285, 90 + topOffset, 100, 15);
		add(pageFaultLabel);

		JLabel virtualPageLabel = new JLabel("virtual page: ", JLabel.LEFT);
		virtualPageLabel.setBounds(285, 120 + topOffset, 110, 15);
		add(virtualPageLabel);

		JLabel physicalPageLabel = new JLabel("physical page: ", JLabel.LEFT);
		physicalPageLabel.setBounds(285, 135 + topOffset, 110, 15);
		add(physicalPageLabel);

		JLabel RLabel = new JLabel("R: ", JLabel.LEFT);
		RLabel.setBounds(285, 150 + topOffset, 110, 15);
		add(RLabel);

		JLabel MLabel = new JLabel("M: ", JLabel.LEFT);
		MLabel.setBounds(285, 165 + topOffset, 110, 15);
		add(MLabel);

		JLabel inMemTimeLabel = new JLabel("inMemTime: ", JLabel.LEFT);
		inMemTimeLabel.setBounds(285, 180 + topOffset, 110, 15);
		add(inMemTimeLabel);

		JLabel lastTouchTimeLabel = new JLabel("lastTouchTime: ", JLabel.LEFT);
		lastTouchTimeLabel.setBounds(285, 195 + topOffset, 110, 15);
		add(lastTouchTimeLabel);

		JLabel lowLabel = new JLabel("low: ", JLabel.LEFT);
		lowLabel.setBounds(285, 210 + topOffset, 110, 15);
		add(lowLabel);

		JLabel highLabel = new JLabel("high: ", JLabel.LEFT);
		highLabel.setBounds(285, 225 + topOffset, 110, 15);
		add(highLabel);

		for (int i = 0; i < pagesNum; i++) {
			labels[i].setBounds(i < pagesNum / 2 ? 70 : 210, (i
					% (pagesNum / 2) + 2)
					* 15 + topOffset, 60, 15);
			labels[i].setForeground(Color.red);
			labels[i].setFont(new Font("Courier", 0, 10));
			add(labels[i]);
		}

		kernel.init(commands, config);

		setVisible(true);
	}

	public void paintPage(Page page) {
		virtualPageValueLabel.setText(Integer.toString(page.id));
		physicalPageValueLabel.setText(Integer.toString(page.physical));
		RValueLabel.setText(Integer.toString(page.R));
		MValueLabel.setText(Integer.toString(page.M));
		inMemTimeValueLabel.setText(Integer.toString(page.inMemTime));
		lastTouchTimeValueLabel.setText(Integer.toString(page.lastTouchTime));
		lowValueLabel.setText(Long.toString(page.low, Kernel.addressradix));
		highValueLabel.setText(Long.toString(page.high, Kernel.addressradix));
	}

	public void setStatus(String status) {
		statusValueLabel.setText(status);
	}

	public void addPhysicalPage(int physicalPage, int pageNum) {
		if (physicalPage >= 0 && physicalPage < pagesNum)
			labels[physicalPage].setText("page " + pageNum);
	}

	public void removePhysicalPage(int physicalPage) {
		if (physicalPage >= 0 && physicalPage < pagesNum)
			labels[physicalPage].setText(null);
	}

	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == runButton) {
				setStatus("RUN");
				runButton.setEnabled(false);
				stepButton.setEnabled(false);
				resetButton.setEnabled(false);
				kernel.run();
				setStatus("STOP");
				resetButton.setEnabled(true);
			} else if (e.getSource() == stepButton) {
				setStatus("STEP");
				kernel.step();
				if (kernel.runcycles == kernel.runs) {
					stepButton.setEnabled(false);
					runButton.setEnabled(false);
				}
				setStatus("STOP");
			} else if (e.getSource() == resetButton) {
				kernel.reset();
				runButton.setEnabled(true);
				stepButton.setEnabled(true);
			} else if (e.getSource() == exitButton) {
				System.exit(0);
			} else {
				for (int i = 0; i < pagesNum; i++)
					if (e.getSource().equals(buttons[i])) {
						kernel.getPage(i);
						return;
					}
			}
		}
	}

    /**
     * ActionListener for combobox which shows list of replacement algorithms
     **/

    class RACBActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            kernel.setReplacementAlgorithm ((ReplacementAlgorithm.Names) cb.getSelectedItem());
        }
    }
}
