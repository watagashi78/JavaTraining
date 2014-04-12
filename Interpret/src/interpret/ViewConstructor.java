package interpret;

import interpret.Model.RawData;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ViewConstructor extends Dialog implements ActionListener, ItemListener {
	private Model model;
	private View view;
	private List constList = new List();
	private Constructor<?> selectConstructor;
	private DefaultTableModel selectedStringModel;
	private RawData[] selectedRawData;
	private JTable tbl = new JTable(5, 2);
	private Button decide = new Button("Create Constructor");
	private GridBagLayout gbl = new GridBagLayout();
	private Object newObject;
	private Method[] methods;
	private Method main;
	private ObjectHolder holder;

	public ViewConstructor(View view, Model model) {
		super(view);
		this.view = view;
		this.model = model;
		this.holder = view.getHolder();
		addList(model.getConstructors());
		setup();
		setVisible(true);
	}

	public void setup() {
		setLayout(gbl);
		setTitle("Select Constructor");
		setSize(400, 500);
		setLocation(500, 500);
		setResizable(false);

		addComponent(constList, 0, 0, 1, 6);
		addComponent(tbl, 0, 7, 1, 4);
		addComponent(decide, 0, 12, 1, 1);

		constList.addItemListener(this);
		decide.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				close.getWindow().dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create Constructor")) {
			boolean isCorrect = true;
			String wrongParam = "";
			for (int i = 0; i < selectedRawData.length; i++) {
				if (selectedRawData[i].getRawObject().equals("")) {
					isCorrect = false;
					wrongParam += (wrongParam.isEmpty()) ? i : ", " + i;
				}
			}
			if (isCorrect) {
				try {
					newObject = Utilities.createNewObject(selectConstructor, selectedRawData);
					view.addObjectList(newObject);
					if(checkMainMethod(newObject)) {
						Utilities.invokeMainMethod(main, newObject);
					}
				System.out.println("オブジェクトが生成されました : " + selectConstructor.getName() + "(" + Arrays.toString(selectConstructor.getParameterTypes()) +")");
				this.dispose();
				} catch (NumberFormatException e1) {
					System.out.println(e1);
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			} else System.out.println("不正な値です. Param = " + wrongParam);
		}
	}

	private void addList(Constructor<?>[] consts) {
		try {
			for (Constructor<?> c : consts) {
				constList.add(Utilities.strip(c.toString(), "java"));
			}
		} catch (NullPointerException e) {
			;
		}
	}

	private boolean checkMainMethod(Object obj) {
		HashSet<Method> methodSet = new HashSet<Method>();
		methodSet.addAll(Arrays.asList(obj.getClass().getMethods()));
		methodSet.addAll(Arrays.asList(obj.getClass().getDeclaredMethods()));
		methods = new Method[methodSet.size()];
		methodSet.toArray(methods);
		for(Method m : methods) {
			if(m.getName() == "main") {
				main = m;
				return true;
			}
		}
		return false;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		selectConstructor = model.getConstructor(constList.getSelectedItem());
		selectedRawData = model.getRawData(constList.getSelectedItem());
		selectedStringModel = model.getStringTableModel(constList.getSelectedItem());
		selectedStringModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				String changeData = selectedStringModel.getValueAt(e.getLastRow(), e.getColumn()).toString();
				Object retObj = null;
				Class<?> cls = selectedRawData[e.getLastRow()].getRawClass();
				try {
					if (cls.isArray()) {
						retObj = holder.getObject(changeData);
						if (retObj != null) {
							;
						} else {
							retObj = Utilities.parse(cls, changeData);
						}
					} else {
						retObj = holder.getObject(changeData);
						if (retObj != null) {
							;
						} else {
							retObj = Utilities.parse(cls, changeData);
						}
					}
					selectedRawData[e.getLastRow()].setValue(retObj);
					System.out.println("正常に値がセットされました. Param = " + e.getLastRow() + ", Type = " + cls.getSimpleName() + ", Value = " + changeData);
				} catch  (RuntimeException err){
					err.printStackTrace();
					System.out.println("不正な値です. Param = " + e.getLastRow() + ", Type = " + cls.getSimpleName());
				} finally {
					;
				}
			}
		});
		tbl.setModel(selectedStringModel);
	}

	public Object getNewObject() {
		return newObject;
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
}
