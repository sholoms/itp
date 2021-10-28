import java.io.*;

public class CsvReader {
	GTerm gt;
	// called TTT because it stores a tic tac toe game results
	TicTacToe[] TTT;
	// stores the current num of TTTs in the array
	int currentNumTTTs;
	// stores the number elements in the list
	int eInList;

	CsvReader() {
		gt = new GTerm(600, 400);
		TTT = new TicTacToe[15];
		eInList = 0;
		// button to save the file
		this.gt.addButton("save", this, "saveFile");
		this.gt.addButton("edit line", this, "changeLine");
		this.gt.addButton("remove line", this, "removeLine");
		this.gt.println("");
		this.gt.addList(300, 300, null, null);
		readDataFile();
		printToList();
	}

	public void readDataFile() {
		try {
			BufferedReader dataFile = new BufferedReader(new FileReader("tic-tac-toe.data"));
			String lineFromFile = dataFile.readLine();
			int i = 0;
			this.gt.setFontSize(16);
			while (lineFromFile != null && i < 15) {
				this.TTT[i] = new TicTacToe(lineFromFile);
				lineFromFile = dataFile.readLine();
				i++;
				this.currentNumTTTs++;
			}
			dataFile.close();
		} catch (

		Exception e) {
			this.gt.showMessageDialog("Error");
		}
	}

// saves the file
	public void saveFile() {
		try {
			BufferedWriter dataFile = new BufferedWriter(new FileWriter(this.gt.setFilePath()));
			int i = 0;
			this.gt.setFontSize(16);
			while (i < this.currentNumTTTs) {
				dataFile.write("" + this.TTT[i] + "\n");
				i++;
			}
			dataFile.close();
		} catch (

		Exception e) {
			this.gt.showMessageDialog("Error");
		}
	}

// edits a line in the file
	public void changeLine() {
		if (this.gt.getSelectedElementFromList(0) != null) {
			String message = "";
			message += "replace line with squares from left to right and top to bottom \n";
			message += "put an X, O, or a B depending on the player that went in the corresponding square (b for blank) \n";
			message += " afterwards write positive or negative for 'x' victory \n";
			message += " all inputs should be seperated ba a comma";
			String newTTT = this.gt.getInputString(message);
			if (newTTT != null) {
				int index = gt.getSelectedElementIndexFromList(0);
				this.TTT[index] = new TicTacToe(newTTT);
				clearList();
				printToList();
			}
		}
	}

	public void printToList() {
		int i = 0;
		while (i < this.currentNumTTTs) {
			this.gt.addElementToList(0, ("" + this.TTT[i]));
			i++;
			this.eInList++;
		}
	}

	public void clearList() {
		// remove the first element from the list until there are no elements left
		while (this.eInList > 0) {
			gt.removeElementFromList(0, 0);
			this.eInList--;
		}
	}

	public void removeLine() {
		// remove the selected line and move up everything after it in the array
		if (this.gt.getSelectedElementFromList(0) != null) {
			int i = gt.getSelectedElementIndexFromList(0);

			while (i < this.currentNumTTTs - 1) {
				this.TTT[i] = this.TTT[i + 1];
				i++;
			}
			this.TTT[i] = null;
			this.currentNumTTTs--;
			// reprint songs to list
			this.clearList();
			this.printToList();
//		this.gt.print("" + currentNumTTTs);
		}
	}

	public static void main(String[] args) {
		CsvReader iie10 = new CsvReader();
	}
}
