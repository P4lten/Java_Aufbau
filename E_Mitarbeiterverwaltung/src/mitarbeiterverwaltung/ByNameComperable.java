package mitarbeiterverwaltung;

import java.util.Comparator;



public class ByNameComperable implements Comparator<Mitarbeiter> {

	@Override
	public int compare(Mitarbeiter o1, Mitarbeiter o2) {
			int ret = 0;
			// beide null
			if (o1 == null && o2 == null) {
				ret = 0;
			} else if (o2 == null) {
				ret = 1;
			} else if (o1 == null) {
				ret = -1;
			}
			// keines der beiden null
			else {
				ret = o1.getName().compareTo(o2.getName());				
			}	return ret;
	}

}
