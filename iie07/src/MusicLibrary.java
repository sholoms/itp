// all variable names describe what they are 
public class MusicLibrary {
	private GTerm gt;
	private int maxNumSongs;
	private String[] songTitles;
	private String[] songLocations;
	private int currentNumSongs;
	private int songsInList;
	private int[] numTimesPlayed;

	public MusicLibrary(int maxNumSongs) {
		this.gt = new GTerm(500, 500);
		this.gt.addTextField("", 300);
		this.songsInList = 0;
		this.maxNumSongs = maxNumSongs;
		this.songTitles = new String[this.maxNumSongs];
		this.songLocations = new String[this.maxNumSongs];
		this.numTimesPlayed = new int[this.maxNumSongs];
		this.currentNumSongs = 0;
		this.gt.println("");
		this.gt.addButton("add song", this, "addSong");
		this.gt.addButton("find song", this, "findSong");
		this.gt.println("");
		this.gt.addButton("remove song", this, "removeSong");
		this.gt.addButton("play selected song", this, "playSongButton");
		this.gt.addButton("play random song", this, "playRandomSong");
		this.gt.println("");
		this.gt.addButton("sort list by most played", this, "sortMostPlayed");
		this.gt.println("");
		this.gt.addList(225, 300, null, null);
	}

	// add song button
	public void addSong() {
		String[] newSong = gt.getTextFromEntry(0).split(",");
		// validate input
		if (this.validateInput(newSong)) {
			return;
		}
		// increase size of array
		if (this.currentNumSongs == (this.maxNumSongs - 1)) {
			this.songTitles = this.increaseStringArraySize(this.songTitles);
			this.songLocations = this.increaseStringArraySize(this.songLocations);
			this.numTimesPlayed = this.increaseIntArraySize(this.numTimesPlayed);
			this.maxNumSongs *= 2;
		}
		// enter input to arrays
		this.sortIntoArrays(newSong);
		// print array to list
		this.clearList();
		this.printToList(this.songTitles, this.numTimesPlayed);
		// clear entry box
		this.gt.setTextInEntry(0, "");
	}

	// remove song button
	public void removeSong() {

		String[] selected = gt.getSelectedElementFromList(0).split(",");
		String targetTitle = selected[0];
		// make sure an element is selected
		if (targetTitle == null) {
			return;
		}
		// find the song in the array
		int i = 0;
		while (i < this.currentNumSongs && !this.songTitles[i].equalsIgnoreCase(targetTitle)) {
			i += 1;
		}
		// remove the selected song and move up everything after it in the array
		while (i < this.currentNumSongs - 1) {
			this.songTitles[i] = this.songTitles[i + 1];
			this.songLocations[i] = this.songLocations[i + 1];
			i++;
		}
		this.songTitles[i] = null;
		this.songLocations[i] = null;
		this.currentNumSongs--;
		// reprint songs to list
		this.clearList();
		this.printToList(this.songTitles, this.numTimesPlayed);
	}

	// find song button
	public void findSong() {
		// find songs containing target title and then print them to list
		String targetTitle = gt.getTextFromEntry(0);
		targetTitle = targetTitle.toLowerCase();
		targetTitle = targetTitle.strip();
		this.clearList();
		int i = 0;
		while (i < this.currentNumSongs) {
			if (this.songTitles[i].toLowerCase().contains(targetTitle)) {
				String elementToAdd = (this.songTitles[i] + "," + this.numTimesPlayed[i]);
				gt.addElementToList(0, elementToAdd);
				this.songsInList++;
			}
			i++;
		}
	}

	public void playSongButton() {
		String[] selected = gt.getSelectedElementFromList(0).split(",");
		String targetTitle = selected[0];
		playSong(targetTitle);
	}

	public void playRandomSong() {
		// generate random number index
		int randomIndex = (int) (Math.random() * ((this.currentNumSongs - 1) - 0 + 1) + 0);
		String targetTitle = this.songTitles[randomIndex];
		playSong(targetTitle);
	}

	// play song from list
	public void playSong(String targetTitle) {
		// find selected song
		int i = 0;
		while (i < this.currentNumSongs && !this.songTitles[i].equalsIgnoreCase(targetTitle)) {
			i += 1;
		}
		// increment times song was played
		this.numTimesPlayed[i]++;
// ---------------------------------------------------------------
// For ITP, you do not need to understand how the code segment below works.
		String os = System.getProperty("os.name").toLowerCase();
		String command;
		if (os.contains("windows"))
			command = "explorer ";
		else if (os.contains("linux"))
			command = "xdg-open ";
		else
			command = "open ";
		try {
			Runtime.getRuntime().exec(command + " \"" + songLocations[i] + "\"");
		} catch (Exception e) {
			this.gt.println("Unable to launch player");
		}
// ---------------------------------------------------------------
		this.clearList();
		this.printToList(this.songTitles, this.numTimesPlayed);
	}

	// sort arrays as it adds new songs
	public void sortIntoArrays(String[] newSong) {
		int i = 0;
		String tempTitle;
		String tempLocation;
		String title = newSong[0];
		String location = newSong[1];
		int timesPlayed = 0;
		int tempTimesPlayed;
		// check if there is anything in array
		if (this.currentNumSongs == 0) {
			this.songTitles[this.currentNumSongs] = title;
			this.songLocations[this.currentNumSongs] = location;
			this.numTimesPlayed[this.currentNumSongs] = timesPlayed;
		} else {
			// check if song title exists and overwrite it
			i = 0;
			while (i < this.currentNumSongs) {
				if (title.compareToIgnoreCase(this.songTitles[i]) == 0) {
					this.songTitles[i] = title;
					this.songLocations[i] = location;
					this.numTimesPlayed[i] = timesPlayed;
					return;
				}
				i++;
			}
			// go through the array and put the song into the correct spot on the array and
			// push everything after that over
			i = 0;
			while (i <= this.currentNumSongs) {
				if (this.songTitles[i] == null) {
					this.songTitles[i] = title;
					this.songLocations[i] = location;
					this.numTimesPlayed[i] = timesPlayed;
				} else if (title.compareTo(songTitles[i]) < 0) {
					tempTitle = this.songTitles[i];
					tempLocation = this.songLocations[i];
					tempTimesPlayed = this.numTimesPlayed[i];
					this.songTitles[i] = title;
					this.songLocations[i] = location;
					this.numTimesPlayed[i] = timesPlayed;
					title = tempTitle;
					location = tempLocation;
					timesPlayed = tempTimesPlayed;
				}
				i++;
			}
		}
		this.currentNumSongs++;
	}

// check if entry is invalid
	public boolean validateInput(String[] newSong) {
		String message = "";
		if (newSong.length != 2 || (newSong[0].strip()).length() == 0) {
			message += "new song input must be \n";
			message += "song name then song location separated by a comma\n";
			message += "name cannot be blank or contain a comma";
			gt.showMessageDialog(message);
			return true;
		}
		return false;
	}

// double the size of the given String array
	public String[] increaseStringArraySize(String[] smallArray) {
		String[] bigArray = new String[(smallArray.length * 2)];
		int i = 0;
		while (i < smallArray.length) {
			bigArray[i] = smallArray[i];
			i++;
		}
		return bigArray;
	}

	// double the size of the given int array
	public int[] increaseIntArraySize(int[] smallArray) {
		int[] bigArray = new int[(smallArray.length * 2)];
		int i = 0;
		while (i < smallArray.length) {
			bigArray[i] = smallArray[i];
			i++;
		}
		return bigArray;
	}

	public void printToList(String[] Stringarray, int[] intArray) {
		int i = 0;
		String elementToAdd;
		while (i < Stringarray.length && Stringarray[i] != null) {
			elementToAdd = (Stringarray[i] + "," + intArray[i]);
			gt.addElementToList(0, elementToAdd);
			this.songsInList++;
			i++;
		}
	}

	// clear list
	public void clearList() {
		int i = 0;
		while (i < this.songsInList) {
			gt.removeElementFromList(0, 0);
			this.songsInList--;
		}
	}

// sort and print in order of most played
	public void sortMostPlayed() {
		this.clearList();
		int i;
		int j;
		// copy arrays to local arrays
		String[] mostWatched = new String[this.currentNumSongs];
		int[] timesWatched = new int[this.currentNumSongs];
		i = 0;
		while (i < this.currentNumSongs) {
			mostWatched[i] = this.songTitles[i];
			timesWatched[i] = this.numTimesPlayed[i];
			i++;
		}
		String tempMostWatched;
		int tempTimesWatched;
		int highestNumIndex;

		// sort local array according to most watched
		i = 0;
		while (i < this.currentNumSongs) {
			highestNumIndex = i;
			j = i;
			while (j < this.currentNumSongs) {
				if (timesWatched[highestNumIndex] < timesWatched[j]) {
					highestNumIndex = j;
				}
				j++;
				tempTimesWatched = timesWatched[i];
				tempMostWatched = mostWatched[i];
				timesWatched[i] = timesWatched[highestNumIndex];
				mostWatched[i] = mostWatched[highestNumIndex];
				timesWatched[highestNumIndex] = tempTimesWatched;
				mostWatched[highestNumIndex] = tempMostWatched;
			}
			i++;

		}
		this.printToList(mostWatched, timesWatched);
	}

	public static void main(String[] args) {
		MusicLibrary iie07 = new MusicLibrary(1);
	}
}
