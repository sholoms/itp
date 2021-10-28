
public class Song {
	private String title;
	private String location;
	private int numTimesPlayed;
	
	public Song(String title, String location) {
		this.title = title;
		this.location = location;
		this.numTimesPlayed = 0;
	}
	public String getTitle() {
		return title;
	}


	public int getNumTimesPlayed() {
		return this.numTimesPlayed;
	}


	public String getlocation() {
		return location;
	}
}
