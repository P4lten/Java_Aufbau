package battleship.repository;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class Cell extends Rectangle {
	public int x, y;
	public Ship ship = null;
	public boolean wasShot = false;

	private Field field;

	// Constructor von Cell
	public Cell(int x, int y, Field field) {
		super(30, 30);
		this.x = x;
		this.y = y;
		this.field = field;
		setFill(Color.LIGHTGRAY);
		setStroke(Color.BLACK);
	}

	public boolean shoot() {
		wasShot = true;
		setFill(Color.BLUE);
		if (ship != null) {// wenn schiff getroffen wurde rot makieren und leben--
			ship.hit();
			setFill(Color.RED);
			if (!ship.isAlive()) {
				field.ships--;
			}
			// methode ist true wenn ein schiff getroffen wurde
			return true;
		}
		// sonst false
		return false;
	}

	
	
}
