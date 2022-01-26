package maps.hashMaps;


public class PersonalNr {
	private final int abteilung, nummer;

	public PersonalNr(int abteilung, int nr) {
		this.abteilung = abteilung;
		this.nummer = nr;
	}

	@Override
	public String toString() {
		return String.format("(%d-%d) (hashCode: %d)", abteilung, nummer, hashCode());
	}

	
	
	// für die Verwendung als Key in einer Hashmap -> hashCode und equals überschreiben
	@Override
	public int hashCode() {
		// XOR Verknüpfung zwischen abteilung und nummer
		return abteilung ^ nummer;
	}

	@Override
	public boolean equals(Object obj) {
		// wenn das andere Objekt auch eine PersonalNr ist
		if(obj instanceof PersonalNr) {
			PersonalNr pnr = (PersonalNr) obj;
			return this.abteilung == pnr.abteilung && this.nummer == pnr.nummer;			
		}else {
			return false;
		}
		
		
	}
}
