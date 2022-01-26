package animals.streamAPI;

/**
 * Klasse für ein (Zoo-)Tier mit mehreren Eigenschaften wie Name, Lateinischer
 * Name oder Gewicht
 * 
 * @author Michaela
 *
 */
public class Animal implements Comparable<Animal>{
	private String name, latinName;

	private int weight;

	private boolean herbivore;

	public Animal(String name, String lateinischerName, int gewicht, boolean pflanzenfresser) {
		this.name = name;
		this.latinName = lateinischerName;
		this.weight = gewicht;
		this.herbivore = pflanzenfresser;
	}

	public String getName() {
		return name;
	}

	public String getLatinName() {
		return latinName;
	}

	public int getWeight() {
		return weight;
	}

	public boolean isHerbivore() {
		return herbivore;
	}

	@Override
	public String toString() {
		return String.format("%s, %s - %d kg - Vegetarier: %s",
				name, latinName, weight, herbivore ? "ja" : "nein");
	}

	@Override
	public int compareTo(Animal o) {
		if(o == null) {
			return 1;
		}
		// nach Name sortieren
		return this.name.compareTo(o.name);
	}


}
