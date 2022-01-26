package animals.sort;

import java.util.Comparator;

public class ByHerbivorComperator implements Comparator<Animal>{

	@Override
	public int compare(Animal o1, Animal o2) {
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
			ret = Boolean.compare(o1.isHerbivore(), o2.isHerbivore());
			// wenn beide Pflanzenfresser oder beide sind Fleischfresser sind
			if (ret == 0) {
				// => jetzt noch nach lat. Name vergleichen
				ret = o1.getLatinName().compareTo(o2.getLatinName());
			}
			
		}
		return ret;
		
	}
	
	
	

}
