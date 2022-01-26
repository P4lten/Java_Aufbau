module xmlSerializationDemo {
	// das Modul aus der JAXB Library verwenden 
	requires jakarta.xml.bind;
	
	// das Package für Reflection öffnen
	// Reflection-Zugriff auf unsere Fahrzeug-Klassen erlauben
	// sonst passiert ein java.lang.IllegalAccessError/java.lang.IllegalAccessException: 
	// z.B...  cannot access class xml.adapters.LocalDateAdapter (in module xmlSerializationDemo) because module xmlSerializationDemo does not export xml.adapters to module com.sun.xml.bind.core
	opens xml.model;
	opens xml.adapters;
}