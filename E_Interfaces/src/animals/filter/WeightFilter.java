package animals.filter;

public class WeightFilter implements AnimalFilter {
	private int minWeight;
	
	public WeightFilter(int minWeight) {
		this.minWeight = minWeight;
				
	}
	
	
	@Override
	public boolean isTrueFor(Animal a) {
		
		return a.getWeight() >= minWeight;
	}

}
