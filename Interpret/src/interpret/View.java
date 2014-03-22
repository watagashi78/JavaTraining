package interpret;

import interpret.Model.RawData;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class View extends Frame implements ModelListener, ActionListener {
	private final Controller controller = new Controller();

	private static List objectList = new List();
	private List arrayList = new List();
	private List fieldList = new List();
	private List methodList = new List();
	private JTable fieldTable = new JTable(1, 2);
	private JTable methodTable = new JTable(8, 2);
	private JTextArea results = new JTextArea();
	private static ObjectHolder holder = new ObjectHolder();
	private static HashMap<String, Model> targetModelMap = new HashMap<String, Model>();
	private Model targetModel;
	private Field[] targetFields;
	private Method[] targetMethods;
	private static ViewConstructor viewConst;
	private RawData[] selectedMethodRaw;
	private RawData[] selectedFieldRaw;
	private DefaultTableModel selectedMethodTableStringModel;
	private DefaultTableModel selectedFieldTableStringModel;
	private String targetObjectString;
	private Method targetMethod;

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

	public void modelChanged(ModelEvent e) {
		Model model = controller.getModel();
		fieldList = model.getAllStringFields();
		methodList = model.getAttStringMethods();
	}

	public void initComponents() {
		setTitle("Interpret");
		setSize(800, 700);
		setResizable(false);
		setLocation(200, 200);
		setLayout(null);
		redirectConsole(results);

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
		Label l1 = new Label(" Object");
		objectList.addItemListener(new TargetObjectChangedListener());
		objectPane.add("North", l1);
		objectPane.add("Center", objectList);
		objectPane.setBounds(10, 55, 150, 285);
		add(objectPane);

		// Array Pane
		JPanel arrayPane = new JPanel();
		arrayPane.setLayout(new BorderLayout());
		Label l4 = new Label(" Array");
		arrayList.addItemListener(new TargetArrayChangedListener());
		arrayPane.add("North", l4);
		arrayPane.add("Center", arrayList);
		arrayPane.setBounds(10, 350, 150, 340);
		add(arrayPane);

		// Field List Panel
		Panel fieldlist = new Panel(new BorderLayout());
		Label l2 = new Label(" Fields");
		fieldList.addItemListener(new TargetFieldChangedListener());
		fieldlist.add("North", l2);
		fieldlist.add("Center", fieldList);
		fieldlist.setBounds(170, 55, 620, 130);
		add(fieldlist);

		// Field Set Panel
		Panel fieldset = new Panel();
		fieldset.setLayout(new BoxLayout(fieldset, BoxLayout.Y_AXIS));
		ScrollPane spane1 = new ScrollPane();
		Label l3 = new Label("                 Type                                      Value");
		spane1.add(fieldTable);
		fieldset.add(l3);
		fieldset.add(spane1);
		fieldset.setBounds(510, 185, 280, 41);
		add(fieldset);

		// Method List Panel
		Panel methodlist = new Panel(new BorderLayout());
		Label l5 = new Label(" Methods");
		methodlist.add("North", l5);
		methodlist.add("Center", methodList);
		methodList.addItemListener(new TargetMethodChangedListener());
		methodlist.setBounds(170, 220, 330, 225);
		add(methodlist);

		// Method Set Panel
		Panel methodset = new Panel(new BorderLayout());
		ScrollPane spane2 = new ScrollPane();
		Button execMethod = new Button("Execute Method");
		execMethod.addActionListener(this);
		spane2.add(methodTable);
		methodset.add("Center", spane2);
		methodset.add("South", execMethod);
		methodset.setBounds(510, 243, 280, 200);
		add(methodset);

		// Result Panel
		JScrollPane result = new JScrollPane(results, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		results.setEditable(false);
		results.append(" \n \n \n \n \n \n");
		result.setBounds(170, 455, 620, 235);
		add(result);
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
		case "Execute Method":
			pushExecuteButton();
			break;
		default:
			break;
		}
	}

	private void pushObjectButton() {
		String value = JOptionPane.showInputDialog(this, "Please Input Class: ", "java.awt.Frame");
		if (value == null) {
			return;
		} else {
			viewConst = new ViewConstructor(View.this, new Model(value));
		}
	}

	private void pushExecuteButton() {
		boolean isCorrect = true;
		String wrongParam = "";
		for (int i = 0; i < selectedMethodRaw.length; i++) {
			if (selectedMethodRaw[i].getRawObject().equals("")) {
				isCorrect = false;
				wrongParam += (wrongParam.isEmpty()) ? i : ", " + i;
			}
		}
		if (isCorrect) {
			try {
				Object retObject = Utilities.invokeMethod(targetMethod, targetModel.getObject(), selectedMethodRaw);
				if (retObject == null) {
					System.out.println("メソッドが正しく実行されました. (Void)");
				} else {
					System.out.println("メソッドの戻り値: " + retObject);
					System.out.println("戻り値をオブジェクトリストに自動的に追加します");
					addObjectList(retObject);
				}
			} catch (Throwable e1) {
				System.out.println(e1.toString());
			}
		} else
			System.out.println("メソッドの引数の値が不正です. Param = " + wrongParam);
	}

	class TargetObjectChangedListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (!objectList.getSelectedItem().equals(targetObjectString)) {
				updateTargetObject(objectList.getSelectedItem());
			}
		}
	}

	class TargetArrayChangedListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {

		}
	}

	class TargetFieldChangedListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			selectedFieldRaw = targetModel.getRawData(fieldList.getSelectedItem());
			selectedFieldTableStringModel = targetModel.getStringTableModel(fieldList.getSelectedItem());
			selectedFieldTableStringModel.addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					String changeData = selectedFieldTableStringModel.getValueAt(e.getLastRow(), e.getColumn())
							.toString();
					Object retObj = null;
					Class<?> cls = selectedFieldRaw[e.getLastRow()].getRawClass();
					try {
						retObj = Utilities.parse(cls, changeData);
						selectedFieldRaw[e.getLastRow()].setValue(retObj);
						System.out.println("フィールドに値がセットされました. Type = " + cls.getSimpleName() + ", Value = "
								+ changeData);
					} catch (RuntimeException err) {
						System.out.println("フィールドの値が不正です. 元の値をセットします.");
						selectedFieldTableStringModel.setValueAt(selectedFieldRaw[e.getLastRow()].getRawObject(),
								e.getLastRow(), e.getColumn());
					} finally {
						;
					}
				}
			});
			fieldTable.setModel(selectedFieldTableStringModel);
		}
	}

	class TargetMethodChangedListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			targetMethod = targetMethods[methodList.getSelectedIndex()];
			selectedMethodRaw = targetModel.getRawData(methodList.getSelectedItem());
			selectedMethodTableStringModel = targetModel.getStringTableModel(methodList.getSelectedItem());
			selectedMethodTableStringModel.addTableModelListener(new TableModelListener() {
				@Override
				public void tableChanged(TableModelEvent e) {
					String changeData = selectedMethodTableStringModel.getValueAt(e.getLastRow(), e.getColumn())
							.toString();
					Object retObj = null;
					Class<?> cls = selectedMethodRaw[e.getLastRow()].getRawClass();
					try {
						retObj = holder.getObject(changeData);
						if (retObj != null && retObj.getClass().equals(cls)) {
							retObj = holder.getObject(changeData);
						} else {
							retObj = Utilities.parse(cls, changeData);
						}
						selectedMethodRaw[e.getLastRow()].setValue(retObj);
						System.out.println("メソッドの引数に値がセットされました. Param = " + e.getLastRow() + ", Type = "
								+ cls.getSimpleName() + ", Value = " + changeData);
					} catch (RuntimeException err) {
						System.out.println("メソッドの引数の値が不正です. Param = " + e.getLastRow() + ", Type = "
								+ cls.getSimpleName());
					} finally {
						;
					}
				}
			});
			methodTable.setModel(selectedMethodTableStringModel);
		}
	}

	public ObjectHolder getHolder() {
		return holder;
	}

	public void setHolder(Object obj) {
		holder.addObject(obj);
	}

	private static void redirectConsole(final JTextArea ta) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream() {
			@Override
			public synchronized void flush() throws IOException {
				ta.setText(toString());
			}
		};

		PrintStream out = new PrintStream(bytes, true);
		System.setErr(out);
		System.setOut(out);
	}

	@SuppressWarnings("deprecation")
	public void updateTargetObject(String key) {
		targetObjectString = key;
		targetModel = targetModelMap.get(key);
		targetFields = targetModel.getFields();
		targetMethods = targetModel.getMethods();
		fieldList.clear();
		methodList.clear();
		fieldTable.setModel(new DefaultTableModel());
		methodTable.setModel(new DefaultTableModel());
		for (Field f : targetFields) {
			fieldList.add(Utilities.strip(f.toString(), "java"));
		}
		for (Method m : targetMethods) {
			methodList.add(Utilities.strip(m.toString(), "java"));
		}
	}

	public static void addObjectList() {
		if (viewConst.getNewObject() != null) {
			String key = holder.addObject(viewConst.getNewObject());
			objectList.add(key);
			targetModelMap.put(key, new Model(viewConst.getNewObject()));
		}
	}

	public void addObjectList(Object obj) {
		String key = holder.addObject(obj);
		objectList.add(key);
		targetModelMap.put(key, new Model(obj));
	}

}
