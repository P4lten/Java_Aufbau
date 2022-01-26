package layout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoxPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public BoxPanel(int axis) {

		setLayout(new BoxLayout(this, axis));
		addButton("Nummer 1", axis, 1.0f, true);
		addButton("Nummer 2", axis, 0.75f, true);
		addButton("Nummer 3", axis, 0.25f, false);
		addButton("Nummer 4", axis, 0.0f, false);
	}

	private void addButton(String text, int axis, float align, boolean addGlue) {
		JButton btn = new JButton(text);
		add(btn);

		if (axis == BoxLayout.X_AXIS) {
			btn.setAlignmentY(align); // y-Position relativ zur X-Achse
										// ausrichten
			if (addGlue)
				// eine Glue-Komponente einfügen, die in horizontaler Richtung
				// breiter und schmäler wird
				add(Box.createHorizontalGlue());
			else
				// einen fixen hoizontalen Abstand einfügen
				add(Box.createHorizontalStrut(5));

		} else {
			btn.setAlignmentX(align); // x-Position relativ zur Y-Achse
										// ausrichten
			if (addGlue)
				// eine Glue-Komponente einfügen, die in vertikaler Richtung
				// höher und niedriger wird
				add(Box.createVerticalGlue());
			else
				// einen fixen vertikalen Abstand einfügen
				add(Box.createVerticalStrut(5));
		}

	}

}
