package model;

public class Game {
	private String date;
	private String away;
	
	public Game() {}
	
	public Game(String date, String away) {
		this.date=date;
		this.away=away;
	}

	public String getDate() {
		return date;
	}
	
	public String getAway() {
		return away;
	}

	public void setAway(String away) {
		this.away = away;
	}

	@Override
	public String toString() {
		return date + " 두산 베어스 VS "+away;
	}
}
