package src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
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

public class SwingPropertyDialog extends JDialog {
	private SwingClock2 clock;
	private ClockSettings2 currentSettings;
	private ClockSettings2 newSettings;
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
	private final JComboBox fontSelector;
	private final JSpinner fontSizeSelector = new JSpinner(new SpinnerNumberModel(1, 1, MAX_FONT_SIZE, 1));
	private final JComboBox fontColorSelector = new JComboBox(COLORS);
	private final JComboBox backGroundColorSelector = new JComboBox(COLORS);
	private final JButton cancel = new JButton("Cancel");
    private final JButton ok = new JButton("OK");

	public SwingPropertyDialog(SwingClock2 clock) {
		super(clock);
		this.clock = clock;
		this.currentSettings = clock.getClockSettings();
		this.newSettings = currentSettings.clone();
		fontSelector = new JComboBox(currentSettings.getAvailableFonts());
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
        addComponent(mainPanel, gbl, new JLabel("Font"), 0, 0);
        addComponent(mainPanel, gbl, fontSelector, 1, 0);
        addComponent(mainPanel, gbl, new JLabel("Font Size"), 0, 1);
        addComponent(mainPanel, gbl, fontSizeSelector, 1, 1);
        addComponent(mainPanel, gbl, new JLabel("Font Color"), 0, 2);
        addComponent(mainPanel, gbl, fontColorSelector, 1, 2);
        addComponent(mainPanel, gbl, new JLabel("Background Color"), 0, 3);
        addComponent(mainPanel, gbl, backGroundColorSelector, 1, 3);
        addComponent(mainPanel, gbl, cancel, 0, 4);
        addComponent(mainPanel, gbl, ok, 1, 4);
        pack();
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
	    final Dimension screenSize = toolkit.getScreenSize();
	    final int x = (screenSize.width - this.getWidth()) / 2;
	    final int y = (screenSize.height - this.getHeight()) / 2;
	    this.setLocation(x, y);
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

	private void addComponent(JPanel panel, GridBagLayout gbl, Component component, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = x == 0 ? GridBagConstraints.EAST : GridBagConstraints.WEST;
		gbl.setConstraints(component, gbc);
		panel.add(component);
	}

	class ColorListCellRenderer implements ListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JPanel panel = new JPanel();
			Color color = (Color) value;
			panel.setBackground(color);
			panel.add(new JLabel(" "));
			backGroundColorSelector.setBackground(color);
			return panel;
		}
	}

	class FontColorListCellRenderer implements ListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JPanel panel = new JPanel();
			Color color = (Color) value;
			JLabel label = new JLabel("Font Color");
			label.setForeground(color);
			label.setBackground(Color.WHITE);
			panel.add(label);
			return panel;
		}
	}
}
