package battleship.program;

import java.util.Random;

import battleship.repository.Cell;
import battleship.repository.Field;
import common.MessageBox;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;


public class EnemyKI {

	private Random random = new Random();
	private boolean enemyTurn;
	private Field playerField;
	private TextArea txtArea;
	private Button button;

	// werden jedes mal wieder auf ihren ursprünglichen wert gesetzt wenn ich die
	// methode aufrufe
	private int hit = 0;
	private int xHit = 0;
	private int yHit = 0;
	private int right = 1;
	private int down = 1;
	private int left = -1;
	private int up = -1;

	public boolean isEnemyTurn() {
		return enemyTurn;
	}

	public void setEnemyTurn(boolean enemyTurn) {
		this.enemyTurn = enemyTurn;
	}

	public EnemyKI(Field field, TextArea txtArea, Button button, boolean enemyTurn) {
		this.playerField = field;
		this.button = button;
		this.txtArea = txtArea;
		this.enemyTurn = enemyTurn;
	}
	// methode move mit switch case!!
	// je nach schwierigkeitsgard move methode
	// schwierigkeitsgrad merken und koordinaten vom letzten schuss merken!
	// ob getroffen wurde merken
	// erst mal 2 machen

	public void enemyMoveEasy() {
		while (enemyTurn) {// solange der Spieler trifft wird die schleife ausgeführt
			int x = random.nextInt(10);// koordiante wird random bestimmt
			int y = random.nextInt(10);// koordiante wird random bestimmt

			Cell cell = playerField.getCell(x, y);
			if (cell.wasShot)// überprüfen ob die Cell schon mal beschossen wurde
				continue;// wenn ja wieder von vorne anfangen

			enemyTurn = cell.shoot();// ist true wenn ein Schiff getroffen wurde

			if (playerField.ships == 0) {
				MessageBox.show("Spiel ende", "Verloren bitte auf Ok Button klicken ", AlertType.INFORMATION,
						ButtonType.OK);
				txtArea.appendText("VERLOREN\n");
				button.setDisable(false);
			}
		}
	}

	public void enemyMoveMedium() {

//		System.out.println("down: " + down);
//		System.out.println("up: " + up);
//		System.out.println("left: " + left);
//		System.out.println("right: " + right);
//		System.out.println("hit: " + hit);

		while (enemyTurn) {
			if (hit < 1) {
				int x = random.nextInt(10);
				int y = random.nextInt(10);
				System.out.println("\nStart while");
				System.out.println(x + ", " + y);

				// wenn Zelle schon beschossen wurde wieder von schleifenbeginn angfangen
				Cell cell = playerField.getCell(x, y);
				if (cell.wasShot)
					continue;

				enemyTurn = cell.shoot();// liefert true wenn ein Schiff getroffen wurde
				if (enemyTurn) {
					hit++; // hit iterieren damit man beim nächsten durchlauf in nächste schleife kommt
					xHit = x; // x stelle merken wo getroffen wurde
					yHit = y; // y stelle merken wo getroffen wurde
				} else {
					break; // wenn kein Schiff getroffen wurde aus schleife breaken
				}
			} else if (down > 0) {
				while (enemyTurn) {
					System.out.println("Down Schleife");
					System.out.println(xHit + ", " + yHit + " + " + down);

					// wenn es ein Valider punkt am Feld ist darf er die Cell auswählen
					if (playerField.isValidPoint(xHit, yHit + down)) {
						Cell cellHit = playerField.getCell(xHit, yHit + down);
						// prüfen ob schon geschossen wurde wenn false -> down auf 0 setzen und breaken
						// -> sonst endlosschleife
						if (cellHit.wasShot) {
							down = 0;
							break;
						}
						System.out.println("Down Schleife after cell.wasShot");

						enemyTurn = cellHit.shoot();// liefert true wenn ein Schiff getroffen wurde
						if (enemyTurn) {
							down++;// Wenn nach unten getroffen wurde dann weiter nach unten schießen
							hit++;// hit iterieren (optional)
						} else {
							down = 0; // Wenn nicht getroffen wurde down auf 0 setzen und aus schleife breaken
							break;
						}
						// wenn kein schiff ist down = 0 -> kann kein Teil des Schiffes mehr sein
						if (cellHit.ship == null) {
							down = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							// Wenn schiff versenkt dann wieder alles auf anfang und neues schiff suchen
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}

					} else { // wenn es kein Valider punkt war down auf 0 setzen und aus schleife breaken
						down = 0;
						break;
					}
				}
			} // ende von else if down
			else if (up < 0) { // funktioniert wie down schleife nur nach oben
				while (enemyTurn) {
					System.out.println("Up Schleife");
					System.out.println(xHit + ", " + yHit + " + " + up);
					// up
					if (playerField.isValidPoint(xHit, yHit + up)) {
						Cell cellHit = playerField.getCell(xHit, yHit + up);
						if (cellHit.wasShot) {
							up = 0;
							break;
						}

						enemyTurn = cellHit.shoot();
						if (enemyTurn) {
							up--;
							hit++;
						} else {
							up = 0;
							break;
						}
						if (cellHit.ship == null) {
							up = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}
					} else {
						up = 0;
						break;
					}
				}
			} // ende von else if up
			else if (left < 0) {// funktioniert wie down schleife nur nach links
				while (enemyTurn) {
					System.out.println("Left Schleife");
					System.out.println(xHit + ", " + yHit + " + " + left);
					// left
					if (playerField.isValidPoint(xHit + left, yHit)) {
						Cell cellHit = playerField.getCell(xHit + left, yHit);
						if (cellHit.wasShot) {
							left = 0;
							break;
						}
						enemyTurn = cellHit.shoot();
						if (enemyTurn) {
							left--;
							hit++;
						} else {
							left = 0;
							break;
						}
						if (cellHit.ship == null) {
							left = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}
					} else {
						left = 0;
						break;
					}
				}
			} // ende von else if left
			else if (right > 0) {// funktioniert wie down schleife nur nach rechts
				while (enemyTurn) {
					System.out.println("Right Schleife");
					System.out.println(xHit + ", " + yHit + " + " + right);
					// right
					if (playerField.isValidPoint(xHit + right, yHit)) {
						Cell cellHit = playerField.getCell(xHit + right, yHit);
						if (cellHit.wasShot) {
							right = 0;
							break;
						}
						enemyTurn = cellHit.shoot();
						if (enemyTurn) {
							right++;
							hit++;
						} else {
							right = 0;
							break;
						}
						if (cellHit.ship == null) {
							right = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}
					} else {
						right = 0;
						break;
					}
				}
			} // ende von else if left

			if (playerField.ships == 0) {
				txtArea.appendText("VERLOREN\n");
				MessageBox.show("Spiel ende", "Verloren bitte auf Ok Button klicken ", AlertType.INFORMATION,
						ButtonType.OK);
				button.setDisable(false);
			}
		} // ende von while schleife
	}

	public void enemyMoveHard() {

//		System.out.println("down: " + down);
//		System.out.println("up: " + up);
//		System.out.println("left: " + left);
//		System.out.println("right: " + right);
//		System.out.println("hit: " + hit);

		while (enemyTurn) {
			if (hit < 1) {
				int x = random.nextInt(10);
				int y = random.nextInt(10);
				System.out.println("\nStart while");
				System.out.println(x + ", " + y);

				// wenn Zelle schon beschossen wurde wieder von schleifenbeginn angfangen
				Cell cell = playerField.getCell(x, y);
				if (cell.wasShot)
					continue;

				enemyTurn = cell.shoot();// liefert true wenn ein Schiff getroffen wurde
				if (enemyTurn) {
					hit++; // hit iterieren damit man beim nächsten durchlauf in nächste schleife kommt
					xHit = x; // x stelle merken wo getroffen wurde
					yHit = y; // y stelle merken wo getroffen wurde
				} else {
					break; // wenn kein Schiff getroffen wurde aus schleife breaken
				}
			} else if (down > 0) {
				while (enemyTurn) {
					System.out.println("Down Schleife");
					System.out.println(xHit + ", " + yHit + " + " + down);

					// wenn es ein Valider punkt am Feld ist darf er die Cell auswählen
					if (playerField.isValidPoint(xHit, yHit + down)) {
						Cell cellHit = playerField.getCell(xHit, yHit + down);
						// prüfen ob schon geschossen wurde wenn false -> down auf 0 setzen und breaken
						// -> sonst endlosschleife
						if (cellHit.wasShot) {
							down = 0;
							break;
						}
						System.out.println("Down Schleife after cell.wasShot");

						enemyTurn = cellHit.shoot();// liefert true wenn ein Schiff getroffen wurde
						if (enemyTurn) {
							// wenn schiff getroffen wurde Cell links davon uberprüfen ob gültig ist
							if (playerField.isValidPoint(xHit + left, yHit)) {
								// wenn ja auf true setzen weil zwei schiffe nicht nebeneinander plaziert werden
								// dürfen
								playerField.getCell(xHit + left, yHit).wasShot = true;
							}
							// wenn schiff getroffen wurde Cell rechts davon uberprüfen ob gültig ist
							if (playerField.isValidPoint(xHit + right, yHit)) {
								// wenn ja auf true setzen weil zwei schiffe nicht nebeneinander plaziert werden
								// dürfen
								playerField.getCell(xHit + right, yHit).wasShot = true;
							}
							// wenn schiff getroffen wurde Cell links davon und darunter uberprüfen ob
							// gültig ist
							if (playerField.isValidPoint(xHit + left, yHit + down)) {
								// wenn ja auf true setzen weil zwei schiffe nicht nebeneinander plaziert werden
								// dürfen
								playerField.getCell(xHit + left, yHit).wasShot = true;
							}
							// wenn schiff getroffen wurde Cell links davon und darüber uberprüfen ob gültig
							// ist
							if (playerField.isValidPoint(xHit + right, yHit + down)) {
								// wenn ja auf true setzen weil zwei schiffe nicht nebeneinander plaziert werden
								// dürfen
								playerField.getCell(xHit + right, yHit).wasShot = true;
							}
							down++;// Wenn nach unten getroffen wurde dann weiter nach unten schießen
							hit++;// hit iterieren (optional)
						} else {
							down = 0; // Wenn nicht getroffen wurde down auf 0 setzen und aus schleife breaken
							break;
						}
						// wenn kein schiff ist down = 0 -> kann kein Teil des Schiffes mehr sein
						if (cellHit.ship == null) {
							down = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							// Wenn schiff versenkt dann wieder alles auf anfang und neues schiff suchen
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}

					} else { // wenn es kein Valider punkt war down auf 0 setzen und aus schleife breaken
						down = 0;
						break;
					}
				}
			} // ende von else if down
			else if (up < 0) { // funktioniert wie down schleife nur nach oben
				while (enemyTurn) {
					System.out.println("Up Schleife");
					System.out.println(xHit + ", " + yHit + " + " + up);
					// up
					if (playerField.isValidPoint(xHit, yHit + up)) {
						Cell cellHit = playerField.getCell(xHit, yHit + up);
						if (cellHit.wasShot) {
							up = 0;
							break;
						}

						enemyTurn = cellHit.shoot();
						if (enemyTurn) {
							if (playerField.isValidPoint(xHit + left, yHit)) {
								playerField.getCell(xHit + left, yHit).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + right, yHit)) {
								playerField.getCell(xHit + right, yHit).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + left, yHit + up)) {
								playerField.getCell(xHit + left, yHit).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + right, yHit + up)) {
								playerField.getCell(xHit + right, yHit).wasShot = true;
							}
							up--;
							hit++;
						} else {
							up = 0;
							break;
						}
						if (cellHit.ship == null) {
							up = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}
					} else {
						up = 0;
						break;
					}
				}
			} // ende von else if up
			else if (left < 0) {// funktioniert wie down schleife nur nach links
				while (enemyTurn) {
					System.out.println("Left Schleife");
					System.out.println(xHit + ", " + yHit + " + " + left);
					// left
					if (playerField.isValidPoint(xHit + left, yHit)) {
						Cell cellHit = playerField.getCell(xHit + left, yHit);
						if (cellHit.wasShot) {
							left = 0;
							break;
						}
						enemyTurn = cellHit.shoot();
						if (enemyTurn) {
							up = -1;
							down = 1;
							if (playerField.isValidPoint(xHit, yHit + down)) {
								playerField.getCell(xHit, yHit + down).wasShot = true;
							}
							if (playerField.isValidPoint(xHit, yHit + up)) {
								playerField.getCell(xHit, yHit + up).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + left, yHit + down)) {
								playerField.getCell(xHit + left, yHit + down).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + left, yHit + up)) {
								playerField.getCell(xHit + left, yHit + up).wasShot = true;
							}
							up = 0;
							down = 0;
							left--;
							hit++;
						} else {
							left = 0;
							break;
						}
						if (cellHit.ship == null) {
							left = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}
					} else {
						left = 0;
						break;
					}
				}
			} // ende von else if left
			else if (right > 0) {// funktioniert wie down schleife nur nach rechts
				while (enemyTurn) {
					System.out.println("Right Schleife");
					System.out.println(xHit + ", " + yHit + " + " + right);
					// right
					if (playerField.isValidPoint(xHit + right, yHit)) {
						Cell cellHit = playerField.getCell(xHit + right, yHit);
						if (cellHit.wasShot) {
							right = 0;
							break;
						}
						enemyTurn = cellHit.shoot();
						if (enemyTurn) {
							up = -1;
							down = 1;
							if (playerField.isValidPoint(xHit, yHit + down)) {
								playerField.getCell(xHit, yHit + down).wasShot = true;
							}
							if (playerField.isValidPoint(xHit, yHit + up)) {
								playerField.getCell(xHit, yHit + up).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + right, yHit + down)) {
								playerField.getCell(xHit + right, yHit + down).wasShot = true;
							}
							if (playerField.isValidPoint(xHit + right, yHit + up)) {
								playerField.getCell(xHit + right, yHit + up).wasShot = true;
							}
							up = 0;
							down = 0;
							right++;
							hit++;
						} else {
							right = 0;
							break;
						}
						if (cellHit.ship == null) {
							right = 0;
							break;
						} else if (!cellHit.ship.isAlive()) {
							hit = 0;
							down = 1;
							up = -1;
							left = -1;
							right = 1;
							break;
						}
					} else {
						right = 0;
						break;
					}
				}
			} // ende von else if left

			if (playerField.ships == 0) {
				txtArea.appendText("VERLOREN\n");
				MessageBox.show("Spiel ende", "Verloren bitte auf Ok Button klicken ", AlertType.INFORMATION,
						ButtonType.OK);
				button.setDisable(false);
			}
		} // ende von while schleife
	}

}
