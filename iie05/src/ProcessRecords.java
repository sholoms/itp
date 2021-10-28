import java.awt.Color;

public class ProcessRecords {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		// alternate names: records/inputs
		// used this name because is the most descriptive
		// alternate data types: byte/short/long
		// used int because is big enough for most people
		int numOfRecords = Integer.parseInt(gt.getInputString("Enter maximum number of records"));

		/*
		 * alternative names: "num", "x" or "randomwholenum" I used this name because it
		 * tells exactly what the variable is and since it is a random number than that
		 * is the best and shortest valid description i could think of alternative data
		 * types are "byte", "short" and "long" I used int because the it gives a big
		 * enough range to cover most random numbers i will choose but that big that it
		 * is just wasting space
		 */
		int[] wholenum = new int[numOfRecords];
		/*
		 * alternative names: "decimalnum" or "ransomdecnum" used this name because it
		 * is short and descriptive alternate data types: "double" used float because it
		 * is accurate enough for almost all random choices
		 */
		double[] decnum = new double[numOfRecords];
		// used this name because it is short and descriptive
		// no alternate data types that use true and false
		boolean[] bool = new boolean[numOfRecords];
		// used this name because it was short and descriptive
		// alternate data type: "string"
		// used char because it is a single letter so no reason
		// to waste space by creating a string
		char[] letter = new char[numOfRecords];
		// alternative names "letters"
		// being as i am just putting a word or bunch of letters called it word didn't
		// use "letters because to similiar to letter"
		String[] word = new String[numOfRecords];

		// alternate names: recordCounter
		// used this name because is short and descriptive
		// alternate data types: byte/short/long
		// used int because is big enough for most people
		int counter = 0;

		while (numOfRecords > counter) {
			// used this variable name because it describes what the variable is
			// could have use rawsString but even after it is split it is still just the a
			// bunch of
			// strings so used given strings instead
			String givenString = gt.getInputString("Enter an int, float, bool, char and word seperated by commas");

			if (givenString == null) {
				numOfRecords = counter;
			} else {

				// used this name because it is simple and descriptive
				String[] input = givenString.split(",");

				wholenum[counter] = Integer.parseInt(input[0]);
				decnum[counter] = Float.parseFloat(input[1]);
				while (wholenum[counter] < 0) {
					wholenum[counter] = Integer.parseInt(gt.getInputString("int must be positive"));
				}
				while (decnum[counter] < 0) {
					decnum[counter] = Float.parseFloat(gt.getInputString("float must be positive"));
				}
				bool[counter] = Boolean.parseBoolean(input[2]);
				letter[counter] = input[3].charAt(0);
				word[counter] = input[4];
				counter++;
			}
		}

		// used double because it needs to be large enough to add a number of integers
		// together and may be too big for float
		// used double not long because when i divide it for the average i want a more
		// accurate result
		// used this name because it is descriptive
		double wholenumTotal = 0.0;

		// used float because it needs to be large enough to add a number of floats
		// together and may require a double
		// used this name because it is descriptive
		counter = 0;
		double decnumTotal = 0.0;
		while (numOfRecords > counter) {
			wholenumTotal += wholenum[counter];
			decnumTotal += decnum[counter];
			counter++;
		}
		// used double because one of the inputs is a double so had to make it a double
		// used this name because it is descriptive
		double wholenumAverage = wholenumTotal / numOfRecords;
		// used double because one of the inputs is a double so had to make it a double
		// used this name because it is descriptive
		double decnumAverage = decnumTotal / numOfRecords;
		counter = 0;

		while (numOfRecords > counter) {
			if (wholenum[counter] < wholenumAverage) {
				gt.setFontColor(Color.RED);
			} else if (wholenum[counter] > wholenumAverage) {
				gt.setFontColor(Color.GREEN);
			} else {
				gt.setFontColor(Color.BLUE);
			}
			gt.println("whole number " + counter + " is " + wholenum[counter]);

			if (decnum[counter] < decnumAverage) {
				gt.setFontColor(Color.RED);
			} else if (decnum[counter] > decnumAverage) {
				gt.setFontColor(Color.green);
			} else {
				gt.setFontColor(Color.BLUE);
			}
			gt.println("decimal number " + counter + " is " + decnum[counter]);
			counter++;
		}
	}
}