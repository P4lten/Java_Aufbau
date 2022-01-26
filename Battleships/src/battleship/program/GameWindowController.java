package battleship.program;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import battleship.repository.BattleshipsException;
import battleship.repository.Cell;
import battleship.repository.Field;
import battleship.repository.Player;
import battleship.repository.PlayerRepository;
import battleship.repository.Ship;
import battleship.repository.db.PlayerDbRepository;
import common.MessageBox;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.converter.NumberStringConverter;

public class GameWindowController extends Parent {

	@FXML
	private GridPane gpAll;

	@FXML
	private Field enemyField;

	@FXML
	private Field playerField;

	@FXML
	private TextArea txtArea;

	@FXML
	private Label lblPoints;

	@FXML
	private Label lblName;

	@FXML
	private Label lblDate;

	@FXML
	private Label lblTime;

	@FXML
	private Button btnNext;

	@FXML
	private Button btnSurrender;

	@FXML
	private FinishWindowController tblPlayers;

	private PlayerRepository repository;

	// wenn runnig false ist platziet man noch seine schiffe =true ist man fertig
	// damit
	private boolean running = false;

	private int shipsToPlace = 10;

	private int shipLength = 5;

	private boolean enemyTurn = false;

	private Random random = new Random();

	private IntegerProperty count = new SimpleIntegerProperty();

	private Timer myTimer;

	long start = System.currentTimeMillis();

	private ListProperty<Player> players;

	private ObjectProperty<Player> selectedPlayer;

	private EnemyKI enemy;

	public GameWindowController() {
		players = new SimpleListProperty<>();
		selectedPlayer = new SimpleObjectProperty<>();

	}

	// die Property
	public Player getSelectedPlayer() {
		return selectedPlayer.get();
	}

	// setter
	public void setSelectedPlayer(Player selectedPlayer) {
		// lässt die Bindings aus dem initialize aus!! Event
		this.selectedPlayer.set(selectedPlayer);
	}

	public ListProperty<Player> getPlayerList() {
		return players;
	}

	public ObjectProperty<Player> selectedPlayerProperty() {
		return selectedPlayer;
	}

	public void setConnection(String dbUrl, String userName, String password) {
		repository = new PlayerDbRepository(dbUrl, userName, password);
		reload();
	}

	// Im GameWindow wird der Spieler geupdated

	@FXML
	private void initialize() {
		System.out.println("Game Window initialize");

		// jprie: wird bei setSelectedPlayer ausgelÃ¶st!
		lblPoints.textProperty().bind(Bindings.selectString(selectedPlayer, "points"));
		lblName.textProperty().bind(Bindings.selectString(selectedPlayer, "name"));
		lblDate.textProperty().bind(Bindings.selectString(selectedPlayer, "date"));
		lblTime.textProperty().bindBidirectional(count, new NumberStringConverter());

		// für eigenes Datumsformat

		new StringBinding() {
			{
				bind(Bindings.select(selectedPlayer, "date"));
			}

			@Override
			protected String computeValue() {

				DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
				if (selectedPlayer.get() != null) {
					return selectedPlayer.get().getDate().format(fmt);
				} else {
					return "";
				}

			}
		};

		txtArea.setEditable(false);
//		btnNext.setDisable(true);

		makePlayerField();
		makeEnemyField();

		gpAll.add(enemyField, 0, 3); // spielfeld gegner hinzufügen
		gpAll.add(playerField, 1, 3);// spielfeld spieler hinzufügen

		txtArea.setFont(Font.font(15));
		txtArea.appendText(
				"Spielstart:\nBitte platzieren Sie Ihre Schiffe auf dem rechten Spielfeld \nMit der linken Maustaste horizontal und der rechten vertikal\n");

	}

	private void onTimerBreak() {
//		System.out.printf("TimerBreak in Thread %s, Uhrzeit: %s\n", Thread.currentThread().getName(), LocalDateTime.now().toString());
		// der Thread in dem der Callback ausgefÃ¼hrt wurd, ist NICHT der Hauptthread
		// des Fensters (FX application)
		// daher ist hier kein Zugriff auf die Controls erlaubt
		// -> die Zugriffe auf den Hauptthread des Fensters (Fx application thread)
		// "umleiten"
		Platform.runLater(() -> {
//			System.out.printf("Platform.runLater in Thread %s\n", Thread.currentThread().getName());
			DateTimeFormatter fmt = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
			lblTime.setText(fmt.format(LocalTime.now()));
		});
	}

	@FXML
	public void onCancel(ActionEvent event) throws BattleshipsException {
		// das Fenster schließen (damit kehrt showAndWait zurück)
		if (isPlayerInList()) {
			reload();
			repository.deletePlayer(getSelectedPlayer());
			reload();
		} else {
			repository.deletePlayerInGames(getSelectedPlayer());
		}
		Stage stage = (Stage) btnSurrender.getScene().getWindow();
		stage.close();
	}

	public void onCancel() throws BattleshipsException {
		// das Fenster schließen (damit kehrt showAndWait zurück)
		if (isPlayerInList()) {
			repository.deletePlayer(getSelectedPlayer());
		} else {
			repository.deletePlayerInGames(getSelectedPlayer());
		}
		reload();
		Stage stage = (Stage) btnSurrender.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void gameEnded(ActionEvent event) {
		try {
			System.out.println("btnNext clicked...");

			// listener für die TextArea damit die scrollbar mit dem Text mitgeht
			txtArea.textProperty().addListener(new ChangeListener<Object>() {
				@Override
				public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
					txtArea.setScrollTop(Double.MAX_VALUE);
				}
			});

			// um Timer zu beenden
			myTimer.cancel();

			// zeit wie lange der Spieler für das spiel braucht in sekunden
			// / 1000 um von milisekunden auf sekunden zu kommen
			// /60 um minuten anzuzeigen
			// format
			long time = (System.currentTimeMillis() - start) / 1000 /* / 60 */;

			// Punkte Name und Datum in selectet Player setzen
			getSelectedPlayer().setPoints(Integer.parseInt(lblPoints.getText()));
			getSelectedPlayer().setName(lblName.getText());
			getSelectedPlayer().setDate(LocalDate.parse(lblDate.getText()));

			// wenn spieler schon in Highscore-Liste ist, updaten und die Ränge
			// alktualisieren falls nötig
			if (isPlayerInList()) {
				getSelectedPlayer().setTime(time);
				repository.updatePlayer(getSelectedPlayer());
				reload();
				sortPlayerRanks();
			} else {// sonst in Games nach Zeit hinzufügen und die Zeit aktualisieren
				repository.updatePlayerInGames(getSelectedPlayer());
				repository.updatePlayerGamesTime(time, getSelectedPlayer());
			}

			// um zu überprüfen ob selber player mehr Punkte bei diesem versuch hat
			Player oldPlayer = repository.selectByName(getSelectedPlayer().getName());
			if (getSelectedPlayer().getName().equals(oldPlayer.getName())
					&& getSelectedPlayer().getPoints() > (oldPlayer.getPoints())) {
				// alten Bestwert in Games kopieren
				repository.insertPlayerInGames(oldPlayer);
				// neuen bestwert in Highscore-Liste updaten
				getSelectedPlayer().setTime(time);
				repository.updatePlayer(getSelectedPlayer());
				reload();
				sortPlayerRanks();
			}

			FinishWindow dlg = new FinishWindow();
			Player entity = dlg.showModal();

			Stage stage = (Stage) btnNext.getScene().getWindow();
			stage.close();
		} catch (

		Exception e) {
			e.printStackTrace();
			MessageBox.show("Erfassen", "Fehler beim Erfassen: " + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}
	}

	private void sortPlayerRanks() throws BattleshipsException {
		// spieler liste nach punkten absteigend und nach zeit aufsteigend sortieren
		players.sort(Comparator.comparingInt(Player::getPoints).reversed().thenComparing(Player::getTime));
		for (int i = 0; i < players.size(); i++) {
			// die RÃ¤nge der Spieler updaten mit Statements
			repository.updatePlayerRank(i + 1, players.get(i).getName());
		}

	}

	@FXML
	public void reload() {
		try {
			List<Player> tmplist = repository.selectAllFromPlayers();
			System.out.printf("%d Entities geladen\n", tmplist.size());
			// neu der List-Property eine neue Collection verpassen
			this.players.set(FXCollections.observableArrayList(tmplist));
			System.out.printf("%d Entities geladen\n", players.size());
		} catch (Exception e) {
			e.printStackTrace();
			MessageBox.show("Reload", "Fehler beim Laden: " + e.getMessage(), AlertType.ERROR, ButtonType.OK);
		}
	}

	private void makeEnemyField() {

		enemyField = new Field(true, event -> {
			if (!running)
				return;
			// der Rest des Codes wird nicht mehr ausgeführt wenn running nicht false ist

			Cell cell = (Cell) event.getSource();
			if (cell.wasShot)// Rest des Codes wird nicht ausgeführt wenn wasShot true ist
				return;

			enemyTurn = !cell.shoot();
			enemy.setEnemyTurn(enemyTurn);

			// Punkte zählern + 50 bei treffer - 10 bei schuss ins wasser
			if (cell.wasShot && cell.ship != null) {
				getSelectedPlayer().setPoints(getSelectedPlayer().getPoints() + 50);
//				System.out.println(getSelectedPlayer().getPoints());
				lblPoints.textProperty().bind(Bindings.selectString(selectedPlayer, "points"));
				txtArea.appendText("Treffer!" + "\n");
				if (!cell.ship.isAlive()) {// wenn schiff gesunken ist ausgeben
					txtArea.appendText("Schiff versenkt" + "\n");
				}
			} else {// wenn nicht getroffen Punkte - 10
				getSelectedPlayer().setPoints(getSelectedPlayer().getPoints() - 10);
				lblPoints.textProperty().bind(Bindings.selectString(selectedPlayer, "points"));
//				System.out.println(getSelectedPlayer().getPoints());
				txtArea.appendText("Daneben!" + "\n");
			}

//			Spielende wenn die Anzahl der gegnerischen Schiffe auf null fällt
			if (enemyField.ships == 0) {
				txtArea.appendText("GEWONNEN!!!\n");
				MessageBox.show("Spiel ende", "Gewonnen bitte auf Ok Button klicken ", AlertType.CONFIRMATION,
						ButtonType.OK);
				btnNext.setDisable(false);
			}

			if (enemyTurn) {
				switch (getSelectedPlayer().getDifficulty()) {
				case "Leicht":
					enemy.enemyMoveEasy();
					break;
				case "Mittel":
					enemy.enemyMoveMedium();
					break;
				case "Schwer":
					enemy.enemyMoveHard();
					break;
				default:
					break;
				}
			}
		});

	}

	private void placeEnemyShips() {
		// plaziere gegnerische Schiffe
		int numberOfShips = 10;
		int shipLength = 5;

		while (numberOfShips > 0) {
			// mit random zufällige x und y koordinaten bestimmen
			int x = random.nextInt(10);
			int y = random.nextInt(10);

			// place ships aufrufen und mit Math.random() einen zufälligen boolischen wert
			// bestimmen ob schiff vertikal oder horizontal plaziert wird
			if (enemyField.placeShip(new Ship(shipLength, Math.random() < 0.5), x, y)) {
				numberOfShips--;
				if (numberOfShips >= 8 && numberOfShips < 10) { // 2 schiffe platzieren mit length 4
					shipLength = 4;
				} else if (numberOfShips < 8 && numberOfShips > 6) {// 3 schiffe platzieren mit length 3
					shipLength = 3;
				} else if (numberOfShips < 5 && numberOfShips > 0) {// 4 schiffe platzieren mit length 2
					shipLength = 2;
				}
			}
		}
		running = true; // gegner hat seine Schiffe platziert
	}

	private void makePlayerField() {
		// jprie: Algorithmus kommentieren!
		playerField = new Field(false, event -> {

			if (running)
				return;

			Cell cell = (Cell) event.getSource();
			// Schiffe mit maus platzieren links = vertikal rechts = horizontal
			if (playerField.placeShip(new Ship(shipLength, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {
				--shipsToPlace;
				txtArea.appendText("Schiffe zu Platzieren " + shipsToPlace + "\n");
				if (shipsToPlace >= 8 && shipsToPlace < 10) {
					shipLength = 4;
				} else if (shipsToPlace < 8 && shipsToPlace > 6) {
					shipLength = 3;
				} else if (shipsToPlace < 5 && shipsToPlace > 0) {
					shipLength = 2;
				}
			}else {
				MessageBox.show("ERROR", "Bitte Schiffe richtig Platzieren ", AlertType.ERROR,
						ButtonType.OK);
				txtArea.appendText("Schiffe richtig plazieren\n");
			}
			if (shipsToPlace == 0) { // wenn alle Plaziert sind beginnt das Spiel
				txtArea.appendText("Spielstart!" + "\nBeginnen Sie mit dem Beschuss des linken Feldes!\n");
				placeEnemyShips();
			}

		});

	}

	private boolean isPlayerInList() {// Hilfsmethode um zu überprüfen ob aktueller Spieler schon in liste ist
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getTime() == getSelectedPlayer().getTime()) {
				return true;
			}
		}
		return false;
	}

	public void startGame() {
		enemy = new EnemyKI(playerField, txtArea, btnNext, enemyTurn);

		System.out.println("enemy Ready");
		// Timer erzeugen
		myTimer = new Timer("Uhrzeit-Timer");
		// ... und starten
		myTimer.schedule(
				// die Aktion die jeweils ausgeführt werden soll
				new TimerTask() {

					@Override
					public void run() {
						onTimerBreak();
					}
				}, 0, // wann soll es zum ersten Mal ausgeführt werden
						// in welchem Intervall
				1000);
		// den Timer beenden, wenn das Fenster geschlossen wird
		// (wir verwenden Platform.runLater damit das Window vollständig erzeugt und
		// initialisiert ist)
		Platform.runLater(() -> {
			btnNext.getScene().getWindow().addEventFilter(javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST, we -> {
				System.out.println("Fenster wird geschlossen -> Timer wird Beendet");
				myTimer.cancel();
				try {
					onCancel();
				} catch (BattleshipsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		});

	}

}
