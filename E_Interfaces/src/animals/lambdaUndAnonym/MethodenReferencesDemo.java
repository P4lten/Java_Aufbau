package animals.lambdaUndAnonym;

public class MethodenReferencesDemo {

	public static void main(String[] args) {
		AnimalList animals = new AnimalList();

		// statt Lambda-Expression, die unsere static Methode aufruft
		animals.showAnimals(a -> /* MethodenReferencesDemo. */istFleischfresser(a));
		// kann ersetzt werden durch eine Methodenreferenz
		animals.showAnimals(MethodenReferencesDemo::istFleischfresser);

		System.out.println("Schwere Tiere (2x)");
		// Varianzte 2: Instanzmethode in Klasse
		AnimalHelper helperObject = new AnimalHelper();
		// Lambada-Expression die Instanzmethode aufruft
		animals.showAnimals(a -> helperObject.isHeavyAnimal(a));
		// kann ersetzt werden durch eine Methodenreferenz
		animals.showAnimals(helperObject::isHeavyAnimal);

		System.out.println("Leichte Tiere: ");
		animals.showAnimals(helperObject::isLightAnimal);

		System.out.println("Vegetarier (2x)");
		// Vairante 3: Instanzmethode in Klasse, mit arbiträrem Objekt
		// Lambda-Expression, die mit dem Argument als akt. Objekt eine Methode aufruft
		animals.showAnimals(a -> a.isHerbivore());
		// kann ersetzt werden durch eine Methodenreferenz
		animals.showAnimals(Animal::isHerbivore);

	}

	// Methode deren Signatur bis auf den Namen zu AnimalFilter.isTrueFor
	static boolean istFleischfresser(Animal animal) {
		return !animal.isHerbivore();

	}

}
