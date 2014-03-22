package interpret;

import javax.swing.SwingUtilities;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Model model = new Model();
				View view = new View(model);
				view.setVisible(true);
			}
		});
	}
}
