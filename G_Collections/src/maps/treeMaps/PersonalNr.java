package maps.treeMaps;


// für Verwendung in TreeMap -> Comparable implementieren
public class PersonalNr implements Comparable<PersonalNr> {
	private final int abteilung, nummer;

	public PersonalNr(int abteilung, int nr) {
		this.abteilung = abteilung;
		this.nummer = nr;
	}

	@Override
	public String toString() {
		return String.format("(%d-%d)", abteilung, nummer);
	}

	
	public int getAbteilung() {
		return abteilung;
	}
	
	public int getNummer() {
		return nummer;
	}
	
	@Override
	public int compareTo(PersonalNr o) {
		if(o==null) {
			return 1;
		}
		int ret = Integer.compare(this.abteilung, o.abteilung);
		if (ret == 0) {
			ret = Integer.compare(this.nummer, o.nummer);
		}
		return ret;
	}

	
}
