package battleship.program;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

import battleship.repository.Player;
import battleship.repository.PlayerRepository;
import battleship.repository.db.PlayerDbRepository;
import common.MessageBox;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class FinishWindowController {

	private PlayerRepository repository;

	@FXML
	private Button btnAllGames;

	@FXML
	private Button btnExit;

	@FXML
	private TableView<Player> tblPlayers;

	@FXML
	private TableColumn<Player, Integer> colRank;

	@FXML
	private TableColumn<Player, String> colPlayer;

	@FXML
	private TableColumn<Player, Integer> colTime;

	@FXML
	private TableColumn<Player, Integer> colPoints;

	@FXML
	private TableColumn<Player, LocalDate> colDate;

	@FXML
	private Label lblRank;

	@FXML
	private Label lblName;

	@FXML
	private Label lblTime;

	@FXML
	private Label lblPoints;

	@FXML
	private Label lblDate;

	private ListProperty<Player> players;

	private ObjectProperty<Player> selectedPlayer;

	private Player playerResult;

	public FinishWindowController() {
		players = new SimpleListProperty<>();
		selectedPlayer = new SimpleObjectProperty<>();

	}

	// die Property
	public Player getSelectedPlayer() {
		return selectedPlayer.get();
	}

	// setter
	public void setSelectedPlayer(Player selectedPlayer) {
		// löst die Bindings aus dem initialize aus!! Event
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

	public Player getPlayer() {
		return playerResult;
	}

	public void setPlayer(Player player) {
		this.playerResult = player;
	}

	@FXML
	private void initialize() {
		System.out.println("Player Table initialize");

		// handler für Änderunge des Selection im Listview
		tblPlayers.getSelectionModel().selectedItemProperty().addListener
		// in der ObjectProperty das Player-Objekt setzen
		((o, oldP, newP) -> {
			// in der Object-Property den selektierten Player setzten
			// damit wird ein Changed-Event von der Property ausgelöst
			selectedPlayer.set(newP);

		});

		lblRank.textProperty().bind(Bindings.selectString(selectedPlayer, "rank"));
		lblName.textProperty().bind(Bindings.selectString(selectedPlayer, "name"));
		lblTime.textProperty().bind(Bindings.selectString(selectedPlayer, "time"));
		lblPoints.textProperty().bind(Bindings.selectString(selectedPlayer, "points"));
		lblDate.textProperty().bind(Bindings.selectString(selectedPlayer, "date"));

		colRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
		colPlayer.setCellValueFactory(new PropertyValueFactory<>("name"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
		// statt Property callback selber schreiben
		// new callback -> update Value @Override
		// gibt string zur�ck und aus dem sch�nes format erstellen
		colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
		colPoints.setCellValueFactory(new PropertyValueFactory<>("points"));

		colDate.setCellFactory(this::createLocalDateCell);

		// die Items des TableView an unsere ListProperty binden
		tblPlayers.itemsProperty().bind(getPlayerList());

		colRank.setSortType(TableColumn.SortType.DESCENDING);
	}


	@FXML
	public void onCancel(ActionEvent event) {
		// das Fenster schließen (damit kehrt showAndWait zurück)
		Stage stage = (Stage) btnExit.getScene().getWindow();
		stage.close();
	}
	
	@FXML
	public void onClicked(MouseEvent me) throws IOException {
		System.out.println("mouse clicked");
		// wenn die Maus 2x gecklickt wurde
		if (me.getClickCount() == 2) {
			openGamesView();
		}
	}
	
	@FXML
	public void onKey(KeyEvent ke) throws IOException {
		System.out.printf("Key pressed: KeyCode=%s\n", ke.getCode());
		switch (ke.getCode()) {
		case ENTER -> openGamesView();
		default -> System.out.println("Keine Aktion zugeordnet");
		}
	}

	@FXML
	public void openGamesView() throws IOException {
		
		Player selecPlayer = getSelectedPlayer();
		System.out.println("Selectierter Spieler" + selecPlayer);
		GamesTableViewWindow dlg = new GamesTableViewWindow();
		Player entity = dlg.showModal();

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

	// TableCell-Factory-Methode für die Anzeige von einem LocalDate mit
	// Regionaleinstellungen
	private TableCell<Player, LocalDate> createLocalDateCell(TableColumn<Player, LocalDate> col) {
		return new TableCell<Player, LocalDate>() {
			@Override
			protected void updateItem(LocalDate value, boolean empty) {
				// Basisklasse aufrufen
				super.updateItem(value, empty);
				if (empty || value == null) {
					setText("");
				} else {
					setText(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(value));
				}
			}
		};
	}

}
