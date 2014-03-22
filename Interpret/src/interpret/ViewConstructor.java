package interpret;

import interpret.Model.RawData;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
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
import java.util.Arrays;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ViewConstructor extends Dialog implements ActionListener, ItemListener {
	private Model model;
	private List constList = new List();
	private Constructor<?> selectConstructor;
	private DefaultTableModel selectedStringModel;
	private RawData[] selectedRawData;
	private JTable tbl = new JTable(5, 2);
	private Button decide = new Button("Create Constructor");
	private GridBagLayout gbl = new GridBagLayout();
	private Object newObject;

	public ViewConstructor(Frame parent, Model model) {
		super(parent);
		this.model = model;
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
				System.out.println("Create Constructor : " + selectConstructor.getName() + "(" + Arrays.toString(selectConstructor.getParameterTypes()) +")");
				try {
					newObject = Utilities.createNewObject(selectConstructor, selectedRawData);
					View.addObjectList();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
				this.dispose();
			} else System.out.println("不正な値です. Param = " + wrongParam);
		}
	}

	private void addList(Constructor<?>[] consts) {
		for (Constructor<?> c : consts) {
			constList.add(Utilities.strip(c.toString(), "java"));
		}
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
					retObj = Utilities.parse(cls, changeData);
					selectedRawData[e.getLastRow()].setValue(retObj);
					System.out.println("正常に値がセットされました. Param = " + e.getLastRow() + ", Type = " + cls.getSimpleName() + ", Value = " + changeData);
				} catch  (RuntimeException err){
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
