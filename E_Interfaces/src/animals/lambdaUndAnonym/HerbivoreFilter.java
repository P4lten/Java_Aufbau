package animals.lambdaUndAnonym;

public class HerbivoreFilter implements AnimalFilter {

	@Override
	public boolean isTrueFor(Animal a) {
//		if (a.isHerbivore() == true)
//			return true;
//		else
//			return false;
		return a.isHerbivore();
	}
	
	

}
