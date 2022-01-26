package genericPackage;

/* Schreibe eine Klasse für einen generischen Stack mit folgender
 * Funktionalität:
 * 
 * Erzeugung des Stapels unter Angabe der Kapazität push:
 *  ein Element wird zuoberst auf den Stapel gestellt. Wenn die Kapazität erschöpft ist, soll eine
 * Exception geworfen werden. 
 * pop: das oberste Element wird zurückgeliefert (und
 * vom Stapel entfernt). Wenn kein oberstes Element existiert, soll eine
 * Exception geworfen werden peek: das oberste Element wird zurückgeliefert (und
 * am Stapel belassen). 
 * size: die Anzahl der Elemente am Stapel wird zurückgeliefert
 */

/**
 * generische Stack-Klasse
 * 
 * @author Paltenberg
 *
 * @param <E> der Typ der Elemente an Stack
 */

// so würde eine Deklatration mit Bound aussehen
// public class GenericStack<E extends Comparable <E>>

public class GenericStack<E> {
	// Deklaratio eines generischen Arrays ist möglich, aber nicht die Erzeugung
	// private E[] genElements;
	// Bei generischer Klasse mit Array von elementen, muss man trotzdem ein
	// Object-Array verwenden
	private Object[] elements;

	// index fuer das jeweils oberste Element
	// -1 bedeutet kein Element am Stack
	// (Anzahl Elemente -1) bedeutet "der Stack ist voll"
	private int topIndex = -1;

	public GenericStack(int count) {
		// Objekt Array in der passenden Größe erzeugen
		elements = new Object[count];
		// wäre schön, geht aber nicht
		// genElements = new E [count];

	}

	/**
	 * Ein Element zuoberst auf den Stapel legen
	 * 
	 * @param element
	 */
	public void push(E element) {
		// wenn der Stapel voll ist Exception
		if (topIndex == elements.length - 1) {
			throw new IllegalStateException("Der Stack ist voll");
		}
		// und den topIndex inkrementieren und das Element im Array ablegen
		elements[++topIndex] = element;
	}

	/**
	 * das oberste Element zurückliefer und vom Stapel entfernen. Wirft eine
	 * Exception wenn der Stack leer ist
	 * 
	 * @return
	 */
	public E pop() {
		if (topIndex == -1) {
			throw new IllegalStateException("Der Stack ist leer");

		}
		// ein Element holen, dabei müssen wir einen sogenannten "unchecked cast" machen
		// dh. das JRE (und damit wir) haben keine Möglichkeit zu prüfen, ob das Element
		// vom Typ "E" ist
		@SuppressWarnings("unchecked")
		E elem = (E) elements[topIndex];
		// den Platz im Array löschen und den topIndex dekrementieren
		elements[topIndex--] = null;
		// das Element zurückliefern
		return elem;

	}

	/**
	 * das oberste Element zurückliefer (oder null, wenn der Stack leer ist)
	 * 
	 * @return
	 */
	public E peek() {
		if (topIndex == -1) {
			return null;
		} else {
			@SuppressWarnings("unchecked")
			E elem = (E) elements[topIndex];

			return elem;
		}
	}

	/**
	 * Anzahl der Elemente am Stack
	 * 
	 * @return
	 */
	public int size() {
		return topIndex + 1;
	}

	void dumpElements() {
		System.out.printf("The stack contains %d Elements\n", size());
		// für alle Array-Positionen, die mit einem Element belegt sind
		for (int i = 0; i < elements.length; i++) {
			E elem = (E) elements[i];
			System.out.printf("\tTyp=%s: %s\n", elem.getClass(), elem.toString());
		}
	}

	void compareElements() {
		System.out.println("Comparing Elements...");
		for (int i = 0; i < elements.length; i++) {
			@SuppressWarnings("unchecked")
			E elem1 = (E) elements[i];
			@SuppressWarnings("unchecked")
			E elem2 = (E) elements[i + 1];
			// die Methode compareTo kann ich nicht aufrufen, wenn
			// das typargument mit <E extends Comparable <E>> eingeschränkt wird
//			int ret = elem1.compareTo(elem2);
//			System.out.printf("Vergleich %s und %s => %d\n", elem1, elem2, ret);
		}

	}
}