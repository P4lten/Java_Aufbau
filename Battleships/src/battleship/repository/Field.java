package battleship.repository;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Field extends Parent {
	private VBox rows = new VBox();
	private boolean enemy = false;
	public int ships = 10;

	// Konstruktor mit boolean ob enemy oder player field & maushandler
	public Field(boolean enemy, EventHandler<? super MouseEvent> handler) {
		this.enemy = enemy;
		for (int i = 0; i < 10; i++) { 
			HBox row = new HBox();
			for (int y = 0; y < 10; y++) { // Cells erstellen mit mousehandler
				Cell c = new Cell(y, i, this);
				c.setOnMouseClicked(handler);
				row.getChildren().add(c);
			}

			rows.getChildren().add(row);
		}

		getChildren().add(rows);
	}

	// jprie: Algorithmus kommentieren!
	public boolean placeShip(Ship ship, int x, int y) {
		if (canPlaceShip(ship, x, y)) {
			int length = ship.type;
			if (ship.vertical) {// vertikal setzen
				for (int i = y; i < y + length; i++) {
					Cell cell = getCell(x, i);
					cell.ship = ship;
					if (!enemy) {
						cell.setFill(Color.WHITE);
						cell.setStroke(Color.ORANGE);
					}
				}
			} else {// horizontal setzen
				for (int i = x; i < x + length; i++) {
					Cell cell = getCell(i, y);
					cell.ship = ship;
					if (!enemy) {
						cell.setFill(Color.WHITE);
						cell.setStroke(Color.ORANGE);
					}
				}
			}

			return true;
		}
		return false;
	}

	public Cell getCell(int x, int y) { // um eine Zelle auszuwählen
		Cell cell = (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);

		return cell;

	}

	// jprie: Algorithmus kommentieren!
	private Cell[] getNeighbors(int x, int y) {
		Point2D[] points = new Point2D[] { 
				new Point2D(x - 1, y), 
				new Point2D(x + 1, y), 
				new Point2D(x, y - 1),
				new Point2D(x, y + 1) 
				};

		List<Cell> neighbors = new ArrayList<Cell>();

		for (Point2D p : points) {
			if (isValidPoint(p)) {
				neighbors.add(getCell((int) p.getX(), (int) p.getY()));
			}
		}
		return neighbors.toArray(new Cell[0]);
	}

	// jprie: Algorithmus kommentieren!
	private boolean canPlaceShip(Ship ship, int x, int y) {
		int length = ship.type;

		if (ship.vertical) {
			for (int i = y; i < y + length; i++) {
				if (!isValidPoint(x, i))
					return false;

				Cell cell = getCell(x, i);
				if (cell.ship != null)
					return false;

				for (Cell neighbor : getNeighbors(x, i)) {
					if (!isValidPoint(x, i))
						return false;

					if (neighbor.ship != null)
						return false;
				}
			}
		} else {
			for (int i = x; i < x + length; i++) {
				if (!isValidPoint(i, y))
					return false;

				Cell cell = getCell(i, y);
				if (cell.ship != null)
					return false;

				for (Cell neighbor : getNeighbors(i, y)) {
					if (!isValidPoint(i, y))
						return false;

					if (neighbor.ship != null)
						return false;
				}
			}
		}

		return true;
	}

	private boolean isValidPoint(Point2D point) {
		if (isValidPoint(point.getX(), point.getY())) {
			return true;
		}

		return false;

	}

	public boolean isValidPoint(double x, double y) {
		if (x >= 0 && x < 10 && y >= 0 && y < 10) {
			return true;
		}

		return false;
	}
}
