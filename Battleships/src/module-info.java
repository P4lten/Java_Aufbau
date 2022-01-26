module battleships {
	requires jakarta.xml.bind;
	
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	requires java.sql;
	requires java.sql.rowset;
	
	opens battleship.repository;
	opens battleship.repository.db;
	opens battleship.program;
	
	
}