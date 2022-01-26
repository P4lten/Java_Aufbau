package layout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

public class SpringPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public SpringPanel() {
		String[] labels = { "Name: ", "Fax: ", "Email: ", "Address: " };
		final int cols = 2, rows = labels.length;
		JTextField txt;
		JLabel lbl;
		SpringLayout layout;
		// Komponenten erzeugen
		setLayout(layout = new SpringLayout());
		for (int i = 0; i < rows; i++) {
			add(lbl = new JLabel(labels[i], JLabel.TRAILING));
			add(txt = new JTextField(10));
			lbl.setLabelFor(txt);
		}
		
		// Align all cells in each column and make them the same width.
		int initialX = 6, initialY = 6, xPad = 6, yPad = 6;

		Spring x = Spring.constant(initialX);
		for (int c = 0; c < cols; c++) {
			Spring width = Spring.constant(0);
			for (int r = 0; r < rows; r++) {
				width = Spring.max(width, layout.getConstraints(
						getComponent(r * cols + c)).getWidth());
			}
			for (int r = 0; r < rows; r++) {
				SpringLayout.Constraints constraints = layout
						.getConstraints(getComponent(r * cols + c));
				constraints.setX(x);
				constraints.setWidth(width);
			}
			x = Spring.sum(x, Spring.sum(width, Spring.constant(xPad)));
		}

		// Align all cells in each row and make them the same height.
		Spring y = Spring.constant(initialY);
		for (int r = 0; r < rows; r++) {
			Spring height = Spring.constant(20);
			for (int c = 0; c < cols; c++) {
				SpringLayout.Constraints constraints = layout
						.getConstraints(getComponent(r * cols + c));
				constraints.setY(y);
				constraints.setHeight(height);
			}
			y = Spring.sum(y, Spring.sum(height, Spring.constant(yPad)));
		}

		// Set the parent's size.
		SpringLayout.Constraints pCons = layout.getConstraints(this);
		pCons.setConstraint(SpringLayout.SOUTH, y);
		pCons.setConstraint(SpringLayout.EAST, x);

	}

}
