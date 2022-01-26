package animals.sort;

import java.util.Comparator;

public class ByWeightComperator implements Comparator<Animal> {

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
//			ret = Integer.compare(o1.getWeight(), o2.getWeight());
			//  oder
			ret = o1.getWeight() - o2.getWeight();
		}
		System.out.printf("Vergleiche nach Gewicht %s mit %s %d\n", o1, o2, ret);
		return ret;
	}

}
