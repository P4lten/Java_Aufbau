package stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class LinkedListDemo {

	public static void main(String[] args) {
		stackDemo1();

		stackDemo2();
		
		queueDemo1();
		
		queueDemo2();
	}
	
	static void stackDemo1() {
		// Interface Deque für einen Stack verwenden
		Deque<String> myStack = new LinkedList<String>();
		
		// an einem Ende einfügen
		myStack.addLast("Erstes Element");
		
		for (int i = 2; i <= 5; i++) {
			myStack.addLast(i + ". Element");
		}
		
		// am selben Ende auslesen bzw. entfernen
		// abfragen
		String elem = myStack.peekLast();
		System.out.println("Oberstes Element: " + elem);
		
		// alle Elmente abholen -> Reihenfolge umkehren
		while (myStack.size() > 0) {
			elem = myStack.removeLast();
			System.out.println("Vom Stack entfernt: " + elem);
		}
				
		
		
	}
	
	static void stackDemo2() {
		System.out.println("Stack mit Stack-Klasse");
		// Klasse Stack verwenden
		Stack<String> myStack = new Stack<>();
		
		// an einem Ende einfügen
		myStack.push("Erstes Element");
		
		for (int i = 2; i <= 5; i++) {
			myStack.push(i + ". Element");
		}
		
		// am selben Ende auslesen bzw. entfernen
		// abfragen
		String elem = myStack.peek();
		System.out.println("Oberstes Element: " + elem);
		
		// alle Elmente abholen -> Reihenfolge umkehren
		while (myStack.size() > 0) {
			elem = myStack.pop();
			System.out.println("Vom Stack entfernt: " + elem);
		}
				
	}
	
	static void queueDemo1() {
		System.out.println("Warteschlange mit Interface Deque");
		// das Deque Interface als Queue verwenden
		Deque<String> tasks = new LinkedList<String>();
		// am hinteren Ende einfügen
		tasks.addLast("Aufgabe 1");
		for (int i = 2; i <= 5; i++) {
			tasks.addLast("Aufgabe " + i);
		}
		// am vorderen Ende auslesen und abholen
		String elem = tasks.peekFirst();
		System.out.println("Erstes Element: " + elem);
		
		while (tasks.size() > 0) {
			elem = tasks.removeFirst();
			System.out.println("Aus Queue abgeholt: " + elem);
		}
	}

	static void queueDemo2() {
		System.out.println("Warteschlange mit Interface Queue");
		// das Queue Interface als Queue verwenden
		Queue<String> tasks = new LinkedList<String>();
		// einfügen
		tasks.add("Aufgabe 1");
		for (int i = 2; i <= 5; i++) {
			tasks.add("Aufgabe " + i);
		}
		// auslesen und abholen
		String elem = tasks.peek();
		System.out.println("Erstes Element: " + elem);
		
		while (tasks.size() > 0) {
			elem = tasks.remove();
			System.out.println("Aus Queue abgeholt: " + elem);
		}
	}
}
