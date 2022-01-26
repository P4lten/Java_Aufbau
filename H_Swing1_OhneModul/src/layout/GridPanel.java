package layout;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public GridPanel() {
		// Grid-Layout mit 2 Zeilen x 3 Spalten erzeugen,
		// wenn Zeilen == 0 und Spalten == n, werden Zeilen zu n Spalten hinzugefügt
		// wenn Zeilen == n und Spalten == 0, werden n Zeilen hinzugefügt
		setLayout(new GridLayout(2, 3, 20, 20));
		String gridButtons[] = { "(0,0)", "(0,1)", "(0,2)", "(1,0)", "(1,1)", "(1,2)" };
		for (int i = 0; i < gridButtons.length; i++)
			add(new JButton(gridButtons[i]));
	}
}
