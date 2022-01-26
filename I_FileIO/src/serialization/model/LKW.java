package serialization.model;

import java.time.LocalDate;
public class LKW extends Auto {
	private static final long serialVersionUID = 1;
	private int maximalGewicht;

	private double ladeflaeche;

	private int achsen;

	public LKW(String marke, double preis, LocalDate erzeugt, int leistung, String farbe, int maximalGewicht,
			double ladeflaeche, int achsen) {
		super(marke, preis, erzeugt, leistung, farbe);
		this.maximalGewicht = maximalGewicht;
		this.ladeflaeche = ladeflaeche;
		this.achsen = achsen;
	}

	public int getMaximalGewicht() {
		return maximalGewicht;
	}

	public void setMaximalGewicht(int maximalGewicht) {
		this.maximalGewicht = maximalGewicht;

	}

	public double getLadeflaeche() {
		return ladeflaeche;
	}

	public void setLadeflaeche(double ladeflaeche) {
		this.ladeflaeche = ladeflaeche;
	}

	public int getAchsen() {
		return achsen;
	}

	public void setAchsen(int achsen) {
		this.achsen = achsen;
	}

	@Override
	public String toString() {
		return super.toString() + String.format("%n\t%d Achsen; max. Gewicht: %d kg; Ladefläche: %.2f m2", achsen, 
		maximalGewicht, ladeflaeche);
	}
}
