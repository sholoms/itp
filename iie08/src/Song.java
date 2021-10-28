
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
	public void setNumTimesPlayed(int numTimesPlayed) {
		this.numTimesPlayed = numTimesPlayed;
	}
	public void setTitle(String title) {
		this.title = title;		
	}
	public void setLocation(String location) {
		this.location = location;
	}
}
