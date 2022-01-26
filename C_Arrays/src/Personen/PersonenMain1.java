package Personen;

public class PersonenMain1 {

	public static void main(String[] args) {
		
		int zahl1, zahl2, zahl3;
		int [] zahlen = new int [3];
		int iTmp = zahlen[0];
		
		Person p1, p2, p3, p4, p5;
		
		
		//ein Array von 5 Personen-Referenzen erzeugen
		// jedes Element wird mit null initialisiert
		Person[] gruppe = new Person[5];
		//pTmp hat den wert null
		Person pTmp = gruppe[0];
		
		gruppe[0] = new Person ("Max",10);
		gruppe[1] = new Person ("Moriz",8);
		gruppe[2] = new Person ("Susi",9);
		
		for (int i = 0; i < gruppe.length; i++) {
			Person tmpPerson = gruppe[i];
			// wenn an dieser Stelle ein objekt steht 
			if(tmpPerson != null) {
				System.out.printf("Am Index %d steht %s\n",i, tmpPerson.toString() );
			}else {
				System.out.printf("Am Index %d steht kein Objekt\n",i );
			}
		}
		
		//Fehlerhafter Zugriff, ohne Überprüfung auf null
		for (int i = 0; i < gruppe.length; i++) {
			Person tmpPerson = gruppe[i];
				System.out.printf("Am Index %d steht %s\n",i, tmpPerson.toString() );
			}
		}

	}


