package animals.filter;

public class HeavyCarnivoreFilter implements AnimalFilter {

	private AnimalFilter herbiFilter = new HerbivoreFilter();
	private AnimalFilter weightFilter = new WeightFilter(200);
	
	public HeavyCarnivoreFilter(int minWeight) {
		herbiFilter = new HerbivoreFilter();
		weightFilter = new WeightFilter(minWeight);
	}

	@Override
	public boolean isTrueFor(Animal a) {
		// True, wenn kein Herbivore UND wenn Gewicht passt
		return !herbiFilter.isTrueFor(a) && weightFilter.isTrueFor(a);
	}
	
}
