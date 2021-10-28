
public class Player {
	// can only use string
	// stores the name of the player
	private String name;
	// used int because it is standard for whole numbers
	// stores how many games the player has played
	private int gamesPlayed;
	// stores how many goals the player has scored
	private int goalsScored;
	
	public Player(String[] newPlayer) {
		this.name = newPlayer[0];
		this.gamesPlayed = Integer.parseInt(newPlayer[1]);
		this.goalsScored = Integer.parseInt(newPlayer[2]);
	}

	public String getname() {
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public void setGamesPlayed(int newGamesPlayed) {
		this.gamesPlayed = newGamesPlayed;
	}

	public void setGoalsScored(int newGoalsScored) {
		this.goalsScored = newGoalsScored;
	}
	public String toString() {
		String message = (name + ", " + gamesPlayed + ", " + goalsScored);
		return message;
	}
}

