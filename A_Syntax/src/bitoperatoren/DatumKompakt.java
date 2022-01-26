package bitoperatoren;

public class DatumKompakt {
	
	
final static int MONAT_SHIFT = 5, JAHR_SHIFT = 9;
	// 5 bitsfür den Tag, 4 Bit für das Monat, 7 Bit für das Jahr
	private short datumBits;
	
	public DatumKompakt(int tag, int monat, int jahr) {
		// Jahre von 0 bis 99 abbilden
		jahr %= 100;
		datumBits = (short)(jahr << JAHR_SHIFT | monat << MONAT_SHIFT | tag);
	}
	
	
	
	public int getTag() {
		// die untersten 5 Bits für den Tag verwenden
		return datumBits & 0b1_1111;
	}
	
	public int getMonat() {
		//die Bits 5 Stellen nach rechts schieben und davon die untersten 4 Bit für das
		// Monat verwenden
		return datumBits >>> MONAT_SHIFT & 0b1111;
	}
	
	public int getJahr() {
		//die Bits 9 Stellen nach rechts schieben und davon die untersten 7 Bit für das
		// Jahr verwenden
		return datumBits >>> JAHR_SHIFT & 0b111_1111;
	}
	public String toString() {
		return String.format("%02d.%02d.%02d (%6d - %32s)", getTag(), getMonat(), getJahr(),
				datumBits, Integer.toBinaryString(datumBits));
	}
}
