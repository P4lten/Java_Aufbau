package mitarbeiterverwaltung2;

import java.util.Comparator;

public class ByClassAndDate	implements Comparator<Mitarbeiter> {

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
		o1.getClass().getName().compareTo(o2.getClass().getName());
		if (ret == 0)
				ret = o1.getEintrittsdatum().compareTo(o2.getEintrittsdatum());				
		}	
		return ret;
}

	

}
