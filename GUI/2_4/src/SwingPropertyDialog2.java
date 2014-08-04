package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ListCellRenderer;
import javax.swing.SpinnerNumberModel;

public class SwingPropertyDialog2 extends JDialog {
	private SwingClock4 clock;
	private ClockSettings4 currentSettings;
	private ClockSettings4 newSettings;
	private static final int MAX_FONT_SIZE = 1000;
	private static final Color[] COLORS = {
			Color.WHITE,
			Color.LIGHT_GRAY,
			Color.GRAY,
			Color.DARK_GRAY,
			Color.BLACK,
			Color.BLUE,
			Color.CYAN,
			Color.GREEN,
			Color.YELLOW,
			Color.ORANGE,
			Color.PINK,
			Color.MAGENTA,
			Color.RED,
	};

	private final GridBagLayout gbl = new GridBagLayout();
	private final JPanel mainPanel = new JPanel();
	private final JComboBox<String> fontSelector;
	private final JSpinner fontSizeSelector = new JSpinner(new SpinnerNumberModel(1, 1, MAX_FONT_SIZE, 1));
	private final JComboBox<Color> fontColorSelector = new JComboBox<Color>(COLORS);
	private final JComboBox<Color> backGroundColorSelector = new JComboBox<Color>(COLORS);
	private final JButton cancel = new JButton("Cancel");
	private final JButton ok = new JButton("        OK        ");

	public SwingPropertyDialog2(SwingClock4 clock) {
		super(clock);
		this.clock = clock;
		this.currentSettings = clock.getClockSettings();
		this.newSettings = currentSettings.clone();
		fontSelector = new JComboBox<String>(currentSettings.getAvailableFonts());
		fontColorSelector.setRenderer(new FontColorListCellRenderer());
		backGroundColorSelector.setRenderer(new ColorListCellRenderer());

		fontSelector.setSelectedItem(currentSettings.getFont_name());
		fontSizeSelector.setValue(currentSettings.getFont_size());
		fontColorSelector.setSelectedItem(currentSettings.getFont_color());
		backGroundColorSelector.setSelectedItem(currentSettings.getBackGround_color());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setupListener();
		setupComponent();
	}

	private void setupComponent() {
		setTitle("Settings");
		setResizable(false);
		getContentPane().add(BorderLayout.CENTER, mainPanel);
		mainPanel.setLayout(gbl);
		JLabel font = new JLabel("フォント");
		JLabel font_size = new JLabel("フォントサイズ");
		JLabel font_color = new JLabel("文字色");
		JLabel bg_color = new JLabel("背景色");
		font.setHorizontalAlignment(JLabel.RIGHT);
		font_size.setHorizontalAlignment(JLabel.RIGHT);
		font_color.setHorizontalAlignment(JLabel.RIGHT);
		bg_color.setHorizontalAlignment(JLabel.RIGHT);
		addComponent(mainPanel, gbl, font, 0, 0, 1);
		addComponent(mainPanel, gbl, fontSelector, 1, 0, 2);
		addComponent(mainPanel, gbl, font_size, 0, 1, 1);
		addComponent(mainPanel, gbl, fontSizeSelector, 1, 1, 2);
		addComponent(mainPanel, gbl, font_color, 0, 2, 1);
		addComponent(mainPanel, gbl, fontColorSelector, 1, 2, 2);
		addComponent(mainPanel, gbl, bg_color, 0, 3, 1);
		addComponent(mainPanel, gbl, backGroundColorSelector, 1, 3, 2);
		addComponent(mainPanel, gbl, cancel, 2, 4, 1);
		addComponent(mainPanel, gbl, ok, 1, 4, 1);
		pack();
		this.setLocation(clock.getWindowX(), clock.getWindowY());
	}

	private void setupListener() {
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clock.setClockSettings(currentSettings);
				dispose();
			}
		});
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newSettings.setFont((String) fontSelector.getSelectedItem());
				newSettings.setFont_size(Integer.parseInt(fontSizeSelector.getValue().toString()));
				newSettings.setFont_color((Color) fontColorSelector.getSelectedItem());
				newSettings.setBackGround_color((Color) backGroundColorSelector.getSelectedItem());
				clock.setClockSettings(newSettings);
				dispose();
			}
		});
	}

	private void addComponent(JPanel panel, GridBagLayout gbl, Component component, int x, int y, int w) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = x == 0 ? GridBagConstraints.EAST: GridBagConstraints.WEST;
		gbl.setConstraints(component, gbc);
		panel.add(component);
	}

	class ColorListCellRenderer implements ListCellRenderer<Color> {
		@Override
		public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Color Chip Settings
			final JPanel colorChip = new JPanel();
			colorChip.setBackground(value);
			colorChip.setSize(10, 10);
			colorChip.setPreferredSize(colorChip.getSize());

			JPanel panel = new JPanel();
			JLabel label = new JLabel("Background Color");
			label.setForeground(value);
			label.setBackground(value);
			panel.setBackground(value);
			panel.add(colorChip);
			panel.add(label);
			return panel;
		}
	}

	class FontColorListCellRenderer implements ListCellRenderer<Color> {
		@Override
		public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// Color Chip Settings
			final JPanel colorChip = new JPanel();
			colorChip.setBackground(value);
			colorChip.setSize(10, 10);
			colorChip.setPreferredSize(colorChip.getSize());

			JPanel panel = new JPanel();
			JLabel label = new JLabel("Font Color");
			label.setForeground(value);
			panel.add(colorChip);
			panel.add(label);
			return panel;
		}
	}
}
