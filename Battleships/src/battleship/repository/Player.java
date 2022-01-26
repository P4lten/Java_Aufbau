package battleship.repository;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlType;



	@XmlType(name= "player", propOrder = {"rank", "name", "date",
			"points", "time" })
	
	public class Player {
	

	private int rank;
	private String name;
	private LocalDate date = LocalDate.now();
	private int points;
	private long time = System.currentTimeMillis();
	private String difficulty;

	/**
	 * @param rank der Rang
	 * @param name der Name
	 * @param date das Datum
	 * @param points die Punkteanzahl
	 * @param time die Zeit (millisekunden)
	 */
	public Player(String name, int rank, int points, long time, LocalDate date) {
		super();
		this.rank = rank;
		this.name = name;
		date = LocalDate.now();
		this.points = points;
		time = System.currentTimeMillis();
		this.difficulty = "";
	}

	public Player() {
		
	}
	
	//erstellt einen neuen Spieler nur mit Namen
	public Player(String name) {
		this.name = name;
		
	}

	public int getRank() {
		return rank;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rang = ");
		builder.append(getRank());
		builder.append("\nName = ");
		builder.append(getName());
		builder.append("\nDatum = ");
		builder.append(getDate());
		builder.append("\nPunkte = ");
		builder.append(getPoints());
		builder.append("\nZeit = ");
		builder.append(getTime());
		builder.append("\nEnde\n");
		return builder.toString();
	}
}
