// all variable names describe what they are or do
public class MusicLibrary {
	private GTerm gt;
	private int maxNumSongs;
	private int currentNumSongs;
	private int songsInList;
	private Song[] songs;

	public MusicLibrary(int maxNumSongs) {
		this.gt = new GTerm(500, 500);
		this.gt.addTextField("", 300);
		this.songsInList = 0;
		this.maxNumSongs = maxNumSongs;
		this.songs = new Song[maxNumSongs];
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
		this.gt.addButton("sort list alphabetically", this, "sortAlphabetically");
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
			this.songs = this.increaseSongArraySize(this.songs);
			this.maxNumSongs *= 2;
		}
		// enter input to array
		this.sortIntoArrays(newSong);
		// print array to list
		this.clearList();
		this.printSongArrayToList(this.songs);
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
		int i = findArrayIndex(targetTitle);
		// remove the selected song and move up everything after it in the array
		while (i < this.currentNumSongs - 1) {
			this.songs[i] = this.songs[i + 1];
			i++;
		}
		this.songs[i] = null;
		this.currentNumSongs--;
		// reprint songs to list
		this.clearList();
		this.printSongArrayToList(this.songs);
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
			if (this.songs[i].getTitle().toLowerCase().contains(targetTitle)) {
				String elementToAdd = (this.songs[i].getTitle() + "," + this.songs[i].getNumTimesPlayed());
				gt.addElementToList(0, elementToAdd);
				this.songsInList++;
			}
			i++;
		}
	}

	// play song button
	public void playSongButton() {
		String[] selected = gt.getSelectedElementFromList(0).split(",");
		String targetTitle = selected[0];
		playSong(targetTitle);
	}

	// play random song button
	public void playRandomSong() {
		// generate random number index then play song at generated index
		int randomIndex = (int) (Math.random() * ((this.currentNumSongs - 1) - 0 + 1) + 0);
		String targetTitle = this.songs[randomIndex].getTitle();
		playSong(targetTitle);
	}

	// play song from list
	public void playSong(String targetTitle) {
		// find array index of selected song
		int i = findArrayIndex(targetTitle);
		// increment times song was played
		this.songs[i].setNumTimesPlayed(songs[i].getNumTimesPlayed() + 1);
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
			Runtime.getRuntime().exec(command + " \"" + this.songs[i].getlocation() + "\"");
		} catch (Exception e) {
			this.gt.println("Unable to launch player");
		}
// ---------------------------------------------------------------
		// reprint songs to list with updated numer of times played
		this.clearList();
		this.printSongArrayToList(this.songs);
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
		boolean notOverWritten = true;
		// check if there is anything in array
		if (this.currentNumSongs == 0) {
			this.songs[0] = new Song(title, location);
			this.currentNumSongs++;
		} else {
			// check if song title exists and if yes, overwrite it
			i = 0;
			while (i < this.currentNumSongs) {
				if (title.compareToIgnoreCase(this.songs[i].getTitle()) == 0) {
					this.songs[i].setLocation(location);
					this.songs[i].setNumTimesPlayed(0);
					notOverWritten = false;
					i = this.currentNumSongs;
				}
				i++;
			}
			// if no song was overwritten then go through the array and put the song into
			// the correct spot on the array and push everything after that over
			if (notOverWritten) {
				i = 0;
				while (i <= this.currentNumSongs) {
					if (this.songs[i] == null) {
						this.songs[i] = new Song(title, location);
						this.songs[i].setNumTimesPlayed(timesPlayed);
					} else if (title.compareTo(songs[i].getTitle()) < 0) {
						tempTitle = this.songs[i].getTitle();
						tempLocation = this.songs[i].getlocation();
						tempTimesPlayed = this.songs[i].getNumTimesPlayed();
						this.songs[i].setTitle(title);
						this.songs[i].setLocation(location);
						this.songs[i].setNumTimesPlayed(timesPlayed);
						title = tempTitle;
						location = tempLocation;
						timesPlayed = tempTimesPlayed;
					}
					i++;
				}
				this.currentNumSongs++;
			}
		}
	}

// check if entry is invalid
	public boolean validateInput(String[] newSong) {
		String message = "";
		// check if the entry has the correct number of inputs and make sre the name is
		// not blank
		if (newSong.length != 2 || (newSong[0].strip()).length() == 0) {
			message += "new song input must be \n";
			message += "song name then song location separated by a comma\n";
			message += "name cannot be blank or contain a comma";
			gt.showMessageDialog(message);
			return true;
		}
		return false;
	}

	// double the size of the given Song array
	public Song[] increaseSongArraySize(Song[] smallArray) {
		Song[] bigArray = new Song[(smallArray.length * 2)];
		int i = 0;
		while (i < smallArray.length) {
			bigArray[i] = smallArray[i];
			i++;
		}
		return bigArray;
	}

	public void printSongArrayToList(Song[] songArray) {
		int i = 0;
		String elementToAdd;
		// go through song array and print the title and nuber of times played from each
		// song to the list
		while (i < songArray.length && songArray[i] != null) {
			elementToAdd = (songArray[i].getTitle() + "," + songArray[i].getNumTimesPlayed());
			gt.addElementToList(0, elementToAdd);
			this.songsInList++;
			i++;
		}
	}

	// clear list
	public void clearList() {
		int i = 0;
		// remove the first element fro the list until there are no elements left
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
			mostWatched[i] = this.songs[i].getTitle();
			timesWatched[i] = this.songs[i].getNumTimesPlayed();
			i++;
		}
		String tempMostWatched;
		int tempTimesWatched;
		int highestNumIndex;

		// sort local array according to most watched by swapping the correct item into
		// each index
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

// sort alphabetically list button
	public void sortAlphabetically() {
		this.clearList();
		this.printSongArrayToList(this.songs);
	}

// print given string array and int array to list
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

// find the index in the songs array of the selected title
	public int findArrayIndex(String targetTitle) {
		int arrayIndex = 0;
		// go through the array till the title matches the target and return the found
		// index
		while (arrayIndex < this.currentNumSongs && !this.songs[arrayIndex].getTitle().equalsIgnoreCase(targetTitle)) {
			arrayIndex += 1;
		}
		return arrayIndex;
	}

	public static void main(String[] args) {
		MusicLibrary iie07 = new MusicLibrary(1);
	}
}
