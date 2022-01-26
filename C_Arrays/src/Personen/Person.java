package Personen;

public class Person {

		private static int zaehler = 0;
		private int nr, alter;
		private String name;


		public Person(String name, int alter) {
			this.nr = ++zaehler;
			this.name = name;
			this.alter = alter;
		}

		
		public int getAlter() {
			return alter;
		}

		public void setAlter(int alter) {
			this.alter = alter;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getNr() {
			return nr;
		}

		
		public String toString() {
			return String.format("Person {nr=%3s, alter=%2s, name=%-10s}", nr, alter, name);
		}

		
		
		
	

}
