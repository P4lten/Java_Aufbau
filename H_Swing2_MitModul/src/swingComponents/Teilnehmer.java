package swingComponents;


public class Teilnehmer {
	private String zuname, vorname, plz, ort, strasse;
	private String unixKenntnisse, windowsKenntnisse;
	boolean programmierKenntnisse;
	static enum Geschlecht {
		MAENNLICH, WEIBLICH
	};
	Geschlecht geschlecht;
	String spezialKenntnisse;

	public String getZuname() {
		return zuname;
	}
	public void setZuname(String zuname) {
		this.zuname = zuname;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getUnixKenntnisse() {
		return unixKenntnisse;
	}
	public void setUnixKenntnisse(String unixKenntnisse) {
		this.unixKenntnisse = unixKenntnisse;
	}
	public String getWindowsKenntnisse() {
		return windowsKenntnisse;
	}
	public void setWindowsKenntnisse(String windowsKenntnisse) {
		this.windowsKenntnisse = windowsKenntnisse;
	}
	public boolean isProgrammierKenntnisse() {
		return programmierKenntnisse;
	}
	public void setProgrammierKenntnisse(boolean programmierKenntnisse) {
		this.programmierKenntnisse = programmierKenntnisse;
	}
	public Geschlecht getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}
	public String getSpezialKenntnisse() {
		return spezialKenntnisse;
	}
	public void setSpezialKenntnisse(String spezialKenntnisse) {
		this.spezialKenntnisse = spezialKenntnisse;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		sb.append(String.format("%s %s (%s)%n", vorname, zuname, geschlecht));
		sb.append(String.format("Adresse: %s, %s %s%n", strasse, plz, ort));
		sb.append("Windowskenntnisse: ");

		if (windowsKenntnisse != null && !windowsKenntnisse.isEmpty()) 
			sb.append(windowsKenntnisse);
		 else
			sb.append("keine, ");

		sb.append("\n");

		sb.append("Unixkenntnisse: ");
		if (unixKenntnisse != null && !unixKenntnisse.isEmpty()) 
			sb.append(unixKenntnisse);
		 else
			sb.append("keine, ");
		sb.append("\n");

		sb.append("Programmierkenntnisse: ");
		if (programmierKenntnisse) {
			sb.append("vorhanden");
		} else {
			sb.append("keine");
		}
		sb.append("\n");
		sb.append(String.format("Spezialkenntnisse: %s%n", spezialKenntnisse));
		return sb.toString();
	}

}
