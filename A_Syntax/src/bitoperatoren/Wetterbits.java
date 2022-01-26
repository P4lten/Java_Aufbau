package bitoperatoren;

import java.time.LocalDate;

public class Wetterbits {
	// Konstante für die einzelnen Wetter-Phaenomene
	public final static byte SONNE =1, WOLKEN = 2, REGEN = 4, SCHNEE = 8, WIND = 16, NEBEL = 32;

	private String stadt;
	private LocalDate datum;
	private byte wetterBits;
	
	public Wetterbits(String stadt, LocalDate datum, byte wetterBits) {
		super();
		this.stadt = stadt;
		this.datum = datum;
		this.wetterBits = wetterBits;
	}

	public String getStadt() {
		return stadt;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public byte getWetterBits() {
		return wetterBits;
	}
	
	public boolean enthaeltBits(byte testBits) {
	// wenn alle Bits aus testBits gesetzt sind -> true zurueckliefern
		if ((wetterBits & testBits) == testBits) {
			return true;
		} else {
			return false;
		}
	}
	
	public void bitsHinzu(byte bits) {
		//wetterBits = (byte) (wetterBits | bits);
		//wetterBits = wetterBits | bits;
		//die Bits in den eigenen wetterBits hinzufuegen
		wetterBits |= bits;
	}
	
	public void bitsEntfernen (byte bits) {
		// den Kehrwert der Bits berechnen und mit unseren Wetterbits verknüpfen &
		// wetterBits = (byte)(wetterBits & (~bits));
		// wetterBits = wetterBits & (~bits);
		wetterBits &= ~bits;
	}
	
	public String toString() {
		StringBuilder buffer = new StringBuilder(100);
		// 1. Zeile: Wo und wann
		buffer.append(String.format("Wetter in %s am %s: \n", stadt, datum));
		// 2. Zeile: die Zahl
		buffer.append(String.format("%3d (%8s)\n", wetterBits, Integer.toBinaryString(wetterBits)));

		return buffer.toString();
	}

}
