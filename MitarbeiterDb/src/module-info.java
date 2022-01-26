module mitarbeiter {
requires jakarta.xml.bind;
	
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	 
	// für JAXB öffnen
	opens mitarbeiter.repository;
	opens mitarbeiter.repository.db;
	
	// JDBC API
	requires java.sql;
	
	// für Java FX
	opens mitarbeiter.program;
	
}