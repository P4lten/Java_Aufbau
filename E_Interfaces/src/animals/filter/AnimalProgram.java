package animals.filter;


public class AnimalProgram  {

	public static void main(String[] args) {
		
		AnimalList animals = new AnimalList();
		animals.showAll();
		// Instanz von einer Klasse, die AnimalFilter implementiert
		HerbivoreFilter herbiFilter = new HerbivoreFilter();
		// kann implizit in AnimalFilter umgewandelt werden
		AnimalFilter filter1 = herbiFilter;
		System.out.println("Pflanzenfresser-Filter: ");		
		animals.showAnimals(filter1);
		
		AnimalFilter filter2 = new WeightFilter(500);
		System.out.println("Gewicht-Filter (500kg):");
		animals.showAnimals(filter2);
		
		System.out.println("Gewicht-Filter (90kg)");
		animals.showAnimals(new WeightFilter(200));
		
		animals.showAnimals(filter1,filter2);
		
		System.out.println("Schwere Fleischfresser: ");
		animals.showAnimals(new HeavyCarnivoreFilter(200));
		
	}

}


//