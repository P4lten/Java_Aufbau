package animals.filter;

//Dieses Interface verwenden wir für die Tier-Filterung
// Implementerende Klassen können in ihrer isTrueFor-Implementierung
// festlegen, ob das übergenebe Animal-Objekt im Ergebnis enthalten
// sein soll oder nicht
public interface AnimalFilter {
	boolean isTrueFor(Animal a);
	
}
