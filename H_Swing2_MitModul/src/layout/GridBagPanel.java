package layout;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.WEST;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridBagPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public GridBagPanel() {
		setLayout(new GridBagLayout());

		// Zeile 1, Spalte 1
		add(new JButton("Zelle 1"), createCell(0, 0, 1, 1,0.5));
		// Zeile 1, Spalte 2
		add(new JButton("Zelle 2"), createCell(1, 0, 1, 2, 5));
		// Zeile 1, Spalte 3
		add(new JButton("Zelle 3"), createCell(2, 0, 1, 1));
		// Zeile 2, Spalte 1
		add(new JButton("Zelle 4"), createCell(0, 1, 1, 1));
		// Zeile 2, Spalte 3
		add(new JButton("Zelle 5"), createCell(2, 1, 1, 2, 1.5));
		// Zeile 3, Spalte 1
		add(new JButton("Zelle 6"), createCell(0, 2, 2, 1));

	}

	private GridBagConstraints createCell(int gridx, int gridy, int gridwidth,
			int gridheight) {
		return createCell(gridx, gridy, gridwidth, gridheight, 0);
	}

	private GridBagConstraints createCell(int gridx, int gridy, int gridwidth,
			int gridheight, double weightX) {
		return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightX, 1, WEST,
				BOTH, new Insets(2, 2, 2, 2), 0, 0);
	}

}
