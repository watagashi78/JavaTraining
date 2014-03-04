package interpret;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

public class View extends Frame implements ModelListener, ActionListener, ItemListener {
	private final Controller controller = new Controller();

	private JTree objectTree = new JTree(new String[0]);
	private List fieldList = new List();
	private List methodList = new List();
	private JTable fieldTable = new JTable(1, 2);
	private JTable methodTable = new JTable(8, 2);
	private TextArea results = new TextArea();
	private GridBagLayout gbl = new GridBagLayout();

	// Main Method
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Model model = new Model();
				View view = new View(model);
				view.setVisible(true);
			}
		});
	}

	public View(Model model) {
		initComponents();
		setModel(model);
	}

	public void setModel(Model model) {
		Model oldModel = controller.getModel();
		if (oldModel != null) {
			oldModel.removeModelListener(this);
		}
		controller.setModel(model);
		model.addModelListener(this);
	}

	public void modelChanged(ModelEvent e){
		Model model = controller.getModel();
		fieldList = model.getAllStringFields();
		methodList = model.getAttStringMethods();
	}

	public void initComponents() {
		setTitle("Interpret");
		setSize(850, 600);
		setResizable(false);
		setLocation(200, 200);
		setLayout(gbl);

		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		// [File]
		Menu menuFile = new Menu("File");
		menuFile.addActionListener(this);
		menuBar.add(menuFile);

		// [File] - [New] - [Object]
		Menu menuNew = new Menu("New... ");
		MenuItem newObject = new MenuItem("Object");
		menuNew.add(newObject);
		menuNew.addActionListener(this);
		menuFile.add(menuNew);

		// [File] - [Exit]
		MenuItem menuExit = new MenuItem("Exit");
		menuFile.add(menuExit);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				System.exit(0);
			}
		});

		// Object Pane
		JPanel objectPane = new JPanel();
		objectPane.setLayout(new BorderLayout());
		Label l1 = new Label("Object");
		objectPane.add("North", l1);
		objectPane.add("Center", objectTree);
		addComponent(objectPane, 0, 0, 1, 3);

		// Field List Panel
		Panel fieldlist = new Panel(new BorderLayout());
		Label l2 = new Label("Fields");
		fieldlist.add("North", l2);
		fieldlist.add("Center", fieldList);
		fieldList.addItemListener(this);
		addComponent(fieldlist, 1, 0, 4, 1);

		// Field Set Panel
		Panel fieldset = new Panel(new BorderLayout());
		ScrollPane spane1 = new ScrollPane();
		Label l3 = new Label(" ");
		Button changeValue = new Button("Change Field Value");
		changeValue.addActionListener(this);
		spane1.add(fieldTable);
		fieldset.add("North", l3);
		fieldset.add("Center", spane1);
		fieldset.add("South", changeValue);
		addComponent(fieldset, 5, 0, 1, 1);

		// Method List Panel
		Panel methodlist = new Panel(new BorderLayout());
		Label l5 = new Label("Methods");
		methodlist.add("North", l5);
		methodlist.add("Center", methodList);
		methodList.addItemListener(this);
		addComponent(methodlist, 1, 1, 4, 1);

		// Method Set Panel
		Panel methodset = new Panel(new BorderLayout());
		ScrollPane spane2 = new ScrollPane();
		Button execMethod = new Button("Execute Method");
		execMethod.addActionListener(this);
		Label l6 = new Label(" ");
		spane2.add(methodTable);
		methodset.add("North", l6);
		methodset.add("Center", spane2);
		methodset.add("South", execMethod);
		addComponent(methodset, 5, 1, 1, 1);

		// Result Panel
		Panel result = new Panel(new BorderLayout());
		Label l8 = new Label("Results");
		results.setEditable(false);
		result.add("North", l8);
		result.add("Center", results);
		addComponent(result, 1, 2, 5, 1);
	}

	private void addComponent(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = 0.1d;
		gbc.weighty = 0.1d;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbl.setConstraints(c, gbc);
		add(c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Object":
			pushObjectButton();
			break;
		case "Exit":
			System.exit(0);
			break;
		default:
			break;
		}
	}

	private void pushObjectButton() {
		String value = JOptionPane.showInputDialog(this,  "Please Input Class: ", "java.lang.String");
		if (value == null) {
			return;
		} else {
			new ViewConstructor(View.this, new Model(value));
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {

	}
}
