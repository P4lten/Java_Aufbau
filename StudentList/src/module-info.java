module studentList {
	// XMLBinding
	requires jakarta.xml.bind;
	
	// JAvafx
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	
	// JDBC API
	requires java.sql;
	
	// für JAXB öffnen
	opens students.repository;
	opens students.repository.xml;
	
	// Für Java FX
	opens students.program;
	
	
}