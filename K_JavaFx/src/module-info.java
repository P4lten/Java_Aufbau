module javaFxDemos {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	
	
	// damit JavaFx auf unsere Application und View Klassen mit Reflection zugreifen darf
	// sonst kommen fehler wie: cannot access class intro.IntroMain (in module javaFxDemos) because module javaFxDemos does not export intro to module javafx.graphics
	opens intro;
	opens calculatorFX;
	opens introFxml;
	opens menus;
	opens layout;
}