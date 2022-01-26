package animals.lambdaUndAnonym;

public class AnimalHelper {

	// Instanzmethode deren Signatur bis auf den Namen zu AnimalFilter.isTrueFor
	// passt
	public boolean isHeavyAnimal(Animal a) {
		return a.getWeight() >= 500;
	}

	// weitere Instanzmethode, deren Signatur bis auf den Namen zur AnimalFilter.isTrueFor passt
	public boolean isLightAnimal(Animal a) {
		return a.getWeight() < 500;
	}
	
	
}
