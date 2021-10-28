import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Assignment3StatSheet {
	// used these names because it describes what the variable is storing when the
	// program is in use
	// variables are declared over here because they will be used throughout the
	// class
	// used gterm array instead of 2 separate gterms in order to make it easier to
	// align them with the number of players in each list which allows the reduction
	// of repeated code
	private GTerm[] gt;
	private Player[] players;
	// used int because is standard for non decimal numbers
	private int maxNumPlayers;
	private int[] playersInList;
	private int currentNumPlayers;

	public Assignment3StatSheet() {
		this.gt = new GTerm[2];
		this.gt[0] = new GTerm(400, 600);
		this.gt[1] = new GTerm(400, 450);
		this.currentNumPlayers = 0;
		this.maxNumPlayers = 1;
		playersInList = new int[2];
		playersInList[0] = 0;
		playersInList[1] = 0;
		this.players = new Player[this.maxNumPlayers];
		this.gt[0].setFontSize(20);
		this.gt[0].setXY(100, 15);
		this.gt[0].println("Stats Sheet");
		this.gt[0].setFontSize(12);
		this.gt[0].setXY(25, 50);
		this.gt[0].addTextField("", 347);
		this.gt[0].println("");
		this.gt[0].addButton("add Player", this, "addPlayerButton");
		this.gt[0].addButton("remove player", this, "removeSelectedPlayer");
		this.gt[0].addButton("find player", this, "findPlayerMain");
		this.gt[0].println("");
		this.gt[0].addButton("load", this, "loadFromFile");
		this.gt[0].addButton("save", this, "saveToFile");
		this.gt[0].println("");
		this.gt[0].addButton("update", this, "updateMain");
		this.gt[0].println("");
		this.gt[0].println("Player Name,   Games Played,   Goals Scored");
		this.gt[0].addList(347, 400, null, null);
		this.gt[1].addButton("find player to edit", this, "findPlayerSub");
		this.gt[1].println("");
		this.gt[1].addTextField("", 300);
		this.gt[1].println("");
		this.gt[1].addButton("edit player name", this, "editSelectPlayerName");
		this.gt[1].addButton("edit number of games", this, "editSelectGamesPlayed");
		this.gt[1].println("");
		this.gt[1].addButton("edit number of goals", this, "editSelectGoalsScored");
		this.gt[1].println("");
		this.gt[1].println("Player Name,   Games Played,   Goals Scored");
		this.gt[1].addList(300, 300, null, null);
	}

// add player button
	public void addPlayerButton() {
		this.addPlayer(this.gt[0].getTextFromEntry(0));
		// clear entry box
		this.gt[0].setTextInEntry(0, "");
	}

	// used this name because it adds a player to the list.
	public void addPlayer(String playerToAdd) {
		String[] newPlayer = playerToAdd.split(",");
		boolean noError = true;
		// validate input
		if (this.validateNewPlayerInput(newPlayer) && this.playerNotExist(newPlayer[0], 0)) {
			// increase size of array if necessary
			// checks if adding a player will go over the current limit in the arrays
			if (this.currentNumPlayers == (this.maxNumPlayers - 1)) {
				this.players = this.increasePlayerArraySize(this.players);
				this.maxNumPlayers *= 2;
			}
			// enter input to arrays and make sure the inputs are of a valid data type
			try {
				this.players[this.currentNumPlayers] = new Player(newPlayer);
			} catch (Exception e) {
				// in case the error is in the goalsScored input make sure that gamesPlayed is
				// null
				noError = false;
			}
			// makes sure there was no errors whilst trying to add the player to the arrays
			if (noError) {
				this.currentNumPlayers++;
				// sort the arrays so that they are in alphabetical order
				this.sortArraysAlphabetcally();
				// print array to list
				this.clearList(0);
				this.printToList(0, this.players);
			} else {
				// used else instead of putting this in the catch because i needed to use an if
				// else statement
				this.gt[0].showMessageDialog("invalid input");
			}
		}
	}

	// refresh button on main window
	// called it updateMain because it updates the main page to include changes to
	// info
	// that were put in using the sub window
	public void updateMain() {
		this.clearList(0);
		this.printToList(0, this.players);
	}

	// main window find player button
	// could findPlayerMainWindow/findPlayerMainButton but this describes what it
	// does while differentiating where necessary therefore having a longer name was
	// not required
	public void findPlayerMain() {
		this.findPlayers(0);
	}

	// sub window find player button
	public void findPlayerSub() {
		this.findPlayers(1);
	}

	// find players that contains input string
	// used findplayers not findPlayer because it finds all the players containing a
	// given string
	// called gtIndex as apposed to just i or index to show it reffers only to the
	// gterm index and not to the index of things like playerNames or something else
	public void findPlayers(int gtIndex) {
		// find players containing target title and then print them to list
		// used targetString name because it is the the string that is being searched
		// for
		String targetString = this.gt[gtIndex].getTextFromEntry(0);
		// called elementToAdd because the only thing that this is used for is to
		// store the different components that need to be added to the list together in
		// order to add them as a single element
		targetString = targetString.toLowerCase();
		targetString = targetString.strip();
		this.clearList(gtIndex);
		// called i as it is an index that is being gone through using the loop
		int i = 0;
		// go through the array and print all players that contain the targetString to
		// the list
		while (i < this.currentNumPlayers) {
			if (this.players[i].getname().toLowerCase().contains(targetString)) {
				this.gt[gtIndex].addElementToList(0, this.players[i].toString());
				this.playersInList[gtIndex]++;
			}
			i++;
		}
		// prints an error message if the targetString is not found in the array
		if (this.playersInList[gtIndex] == 0) {
			// called error message because it is the message shown in the case of an error
			// occurring
			String errorMessage = "";
			errorMessage += targetString;
			errorMessage += " not found";
			this.gt[gtIndex].showMessageDialog(errorMessage);
		}
	}

	// remove player button
	// called removeSelectedPlayer because it removes the player that is selected
	public void removeSelectedPlayer() {
		// called selected because it is the element that is selected and so too for the
		// selected in the other methods
		String selected = this.gt[0].getSelectedElementFromList(0);
		// make sure an element is selected
		if (this.validateElementSelected(0, selected)) {
			// find the index of the player in the array in the array
			int i = findArrayIndex(selected);
			// move over everything in the array after the removed player, overwriting the
			// song to remove it
			while (i < this.currentNumPlayers - 1) {
				this.players[i] = players[i + 1];
				i++;
			}
			// set the last player to null in order to remove a player and edit the total
			// number of players
			this.players[i] = null;
			this.currentNumPlayers--;
			// reprint players to list
			this.clearList(0);
			this.printToList(0, this.players);
		}
	}

	// edit player name button
	// called editSelectPlayerName because it edits the name of a selected player
	public void editSelectPlayerName() {
		// see above
		String selected = this.gt[1].getSelectedElementFromList(0);
		// called userInput because it stores the input that the user put in as opposed
		// to something hard coded
		String userInput = this.gt[1].getTextFromEntry(0);
		// make sure an element is selected and input is not blank and a player with
		// that name doesn't already exist
		if (this.validateElementSelected(1, selected) && this.validateInputExists(1, userInput)
				&& playerNotExist(userInput, 1)) {
			// find the player in the array
			int i = findArrayIndex(selected);
			// replace the player name of the selected index with the new number
			this.players[i].setName(this.gt[1].getTextFromEntry(0));
			// make sure the arrays are still in order alphabetical
			this.sortArraysAlphabetcally();
			// reprint the main list with the updated info
			this.clearList(1);
			this.printToList(1, this.players);
		}
	}

	// edit games played button
	// called editSelectGamesPlayed because it edits the games played of the
	// selected player
	public void editSelectGamesPlayed() {
		// see above
		String selected = this.gt[1].getSelectedElementFromList(0);
		String userInput = this.gt[1].getTextFromEntry(0);
		// make sure an element is selected and input is not blank
		if (this.validateElementSelected(1, selected) && this.validateInputExists(1, userInput)) {
			// find the player in the array
			int i = findArrayIndex(selected);
			// replace the games played of the selected index with the new number
			// and make sure the input is an integer
			try {
				this.players[i].setGamesPlayed(Integer.parseInt(this.gt[1].getTextFromEntry(0)));
			} catch (Exception e) {
				this.gt[1].showMessageDialog("invalid input");
			}
			// reprint the main list with the updated info
			this.clearList(1);
			this.printToList(1, this.players);
		}
	}

	// edit goals scored button
	// called editSelectGoalsScored because it edits the goals scored of the
	// selected player
	public void editSelectGoalsScored() {
		String selected = this.gt[1].getSelectedElementFromList(0);
		String userInput = this.gt[1].getTextFromEntry(0);
		// make sure an element is selected and input isnt blank
		if (this.validateElementSelected(1, selected) && this.validateInputExists(1, userInput)) {
			// find the player in the array
			int i = findArrayIndex(selected);
			// replace the goals scored of the selected index with the new number
			try {
				this.players[i].setGoalsScored(Integer.parseInt(userInput));
			} catch (Exception e) {
				this.gt[1].showMessageDialog("invalid input");
			}
			// reprint the main list with the updated info
			this.clearList(1);
			this.printToList(1, this.players);
		}

	}

// called validateInput because it makes sure that there is an input
	// called gtIndex because it is the index of the gt window
	// called user input because it is the input of the user
	public boolean validateInputExists(int gtIndex, String userInput) {
		// called InputExists because it holds the true or false value of weather there
		// is a non blank input
		boolean inputExists = true;
		// makes sure an element is selected
		if (userInput.strip().length() == 0) {
			String errorMessage = "";
			errorMessage += "invalid input";
			this.gt[gtIndex].showMessageDialog(errorMessage);
			inputExists = false;
		}
		return inputExists;
	}

	// check if button was used correctly
	// called validateElementSelected because it validates that there was
	// an element selected
	public boolean validateElementSelected(int gtIndex, String selectedElement) {
		boolean valid = true;
		// makes sure an element is selected
		if (selectedElement == null) {
			String errorMessage = "";
			errorMessage += "invalid usage of button";
			this.gt[gtIndex].showMessageDialog(errorMessage);
			valid = false;
		}
		return valid;
	}

	// find the array index of the given player
	// called find array index because finds the index in an array of a given
	// selected element from a list
	// called selected because it is the element that is selected from the list
	public int findArrayIndex(String selected) {
		// called arrayIndex because it stores the array index of the input
		int arrayIndex = 0;
		// called selectedSplit because it is the selected element after it has been
		// split using the commas
		String[] selectedSplit = selected.split(",");
		// called targetTitle because it is the title that we are looking for in the
		// array
		String targetTitle = selectedSplit[0];
		// go through the array till the title matches the target and return the found
		// index
		while (arrayIndex < this.currentNumPlayers
				&& !this.players[arrayIndex].getname().equalsIgnoreCase(targetTitle)) {
			arrayIndex += 1;
		}
		return arrayIndex;
	}

	// double the size of the given Player array
	// called increasePlayerArraySize because it increases Integer arrays only
	public Player[] increasePlayerArraySize(Player[] smallArray) {
		Player[] bigArray = new Player[(smallArray.length * 2)];
		int i = 0;
		while (i < smallArray.length) {
			bigArray[i] = smallArray[i];
			i++;
		}
		return bigArray;
	}

	// check if entry is valid
	// called validateNewPlayerInput because it makes sure that the new player that
	// is added is added in an a valid method
	public boolean validateNewPlayerInput(String[] newPlayer) {
		boolean inputValid = true;
		if (newPlayer.length != 3 || (newPlayer[0].strip()).length() == 0) {
			String errorMessage = "";
			errorMessage += "new player input must be \n";
			errorMessage += "player name then then games played then goals scored each separated by a comma\n";
			errorMessage += "name cannot be blank or contain a comma";
			this.gt[0].showMessageDialog(errorMessage);
			inputValid = false;
		}
		return inputValid;
	}

	// sort arrays alphabetically
	// called sortArraysAlphabetcally because it sorts the array in alphabetical
	// order
	public void sortArraysAlphabetcally() {
		// called firstAlphabeticallyIndex because it stores the index of whatever comes
		// first alphabetically
		int firstAlphabeticallyIndex;
		// called temp.. because they temporarily store the corresponding value to
		// enable the index's to be swapped
		Player tempPlayer;
		int j;
		int i;
		// go through the array and put the player into the correct spot on the array
		// and and make sure each index is correct
		i = 0;
		while (i < this.currentNumPlayers) {
			j = i;
			firstAlphabeticallyIndex = i;
			// go through the remaining array and find the first entry alphabetically and
			// save its index
			while (j < this.currentNumPlayers) {
				if (this.players[firstAlphabeticallyIndex].getname().compareTo(this.players[j].getname()) > 0) {
					firstAlphabeticallyIndex = j;
				}
				j++;
			}
			// if the current index is not the first alphabetically swap the first
			// alphabetically with the index we are currently checking
			if (firstAlphabeticallyIndex != i) {
				tempPlayer = this.players[i];
				this.players[i] = this.players[firstAlphabeticallyIndex];
				this.players[firstAlphabeticallyIndex] = tempPlayer;
			}
			i++;
		}
	}

	// clear list
	// called clearList because it clears the list in the chosen window
	public void clearList(int gtIndex) {
		// as long as there is at least 1 element in the list remove the first element
		while (this.playersInList[gtIndex] != 0) {
			gt[gtIndex].removeElementFromList(0, 0);
			this.playersInList[gtIndex]--;
		}
	}

	// could have used playerNameArray and goalsScorredArray etc but this way the
	// code is easier to use elsewhere
	// called print to list because it prints the given arrays to a list
	public void printToList(int gtIndex, Player[] playersToPrint) {
		int i = 0;
		// adds the players to the list
		while (i < playersToPrint.length && playersToPrint[i] != null) {
			gt[gtIndex].addElementToList(0, playersToPrint[i].toString());
			this.playersInList[gtIndex]++;
			i++;
		}
	}

	// called playerNotExist because if the player does not exist it returns true if
	// he does exist it returns false
	public boolean playerNotExist(String newPlayerName, int gtIndex) {
		// called playerNotExist because it stores a boolean of false if the player
		// exists and true if the player does not exist
		boolean playerNotExist = true;
		int i = 0;
		while (i < this.currentNumPlayers) {
			if (newPlayerName.compareToIgnoreCase(this.players[i].getname()) == 0) {
				String errorMessage = "a player with that name already exists";
				this.gt[gtIndex].showMessageDialog(errorMessage);
				playerNotExist = false;
			}
			i++;
		}
		return playerNotExist;
	}

	public void loadFromFile() {
		try {
			BufferedReader dataFile = new BufferedReader(new FileReader(this.gt[0].getFilePath()));
			String lineFromFile = dataFile.readLine();
			while (lineFromFile != null) {
				this.addPlayer(lineFromFile);
				lineFromFile = dataFile.readLine();
			}
			dataFile.close();
		} catch (Exception e) {
			this.gt[0].showMessageDialog("Error loading from file");
		}
	}

	public void saveToFile() {
		try {
			BufferedWriter dataFile = new BufferedWriter(new FileWriter(this.gt[0].setFilePath()));
			int i = 0;
			while (i < this.currentNumPlayers) {
				dataFile.write("" + this.players[i] + "\n");
				i++;
			}
			dataFile.close();
		} catch (

		Exception e) {
			this.gt[0].showMessageDialog("Error saving file");
		}
	}

// The main method must only perform the included operation.
// Do not add any other code to the main method.
	public static void main(String[] args) {
		Assignment3StatSheet a2obj = new Assignment3StatSheet();
	}
}