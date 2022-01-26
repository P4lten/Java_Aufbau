// unser Programm ist modulariesiert, dh es verwendet das modularisierte JDK
// => wir müssen alle Module, die wir verwenden wollen, registrieren
module swingDemos {
	// das Modul in dem Swing (und AWT) enthalten ist
	requires java.desktop;
	
}