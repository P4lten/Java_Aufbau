package bankverwaltung;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;




public class Bankkonten implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<Integer, Bankkonto> konto = new TreeMap<>();
	
	public Map<Integer, Bankkonto> getFahrzeuge() {
		return konto;
	}
	
	// ein Fahrzeug in der Map einfügen
		public void add(Bankkonto bk) {
			konto.put(bk.getKontonr(), bk);
		}

		public void showAll() {
			konto.values().forEach(System.out::println);
		}
}
