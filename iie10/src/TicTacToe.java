
public class TicTacToe {
	private char topLeftSquare;
	private char topMiddleSquare;
	private char topRightSquare;
	private char middleLeftSquare;
	private char middleMiddleSquare;
	private char middleRightSquare;
	private char bottomLeftSquare;
	private char bottomMiddleSquare;
	private char bottomRightSquare;
	private String xWin;

	// called line from file because it is the record being entered into
	// the object
	TicTacToe(String record) {
		String[] splitLine = record.split(",");
		topLeftSquare = splitLine[0].charAt(0);
		topMiddleSquare = splitLine[1].charAt(0);
		topRightSquare = splitLine[2].charAt(0);
		middleLeftSquare = splitLine[3].charAt(0);
		middleMiddleSquare = splitLine[4].charAt(0);
		middleRightSquare = splitLine[5].charAt(0);
		bottomLeftSquare = splitLine[6].charAt(0);
		bottomMiddleSquare = splitLine[7].charAt(0);
		bottomRightSquare = splitLine[8].charAt(0);
		xWin = splitLine[9];
	}

	public String toString() {
		String message = "";
		message += topLeftSquare;
		message += ", ";
		message += topMiddleSquare;
		message += ", ";
		message += topRightSquare;
		message += ", ";
		message += middleLeftSquare;
		message += ", ";
		message += middleMiddleSquare;
		message += ", ";
		message += middleRightSquare;
		message += ", ";
		message += bottomLeftSquare;
		message += ", ";
		message += bottomMiddleSquare;
		message += ", ";
		message += bottomRightSquare;
		message += ", ";
		message += xWin;
		return message;
	}
}
