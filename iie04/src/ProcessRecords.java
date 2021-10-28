
public class ProcessRecords {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		// alternate names: records/inputs
		// used this name because is the most descriptive
		// alternate data types: byte/short/long
		// used int because is big enough for most people
		int numOfRecords = Integer.parseInt(gt.getInputString("Enter maximum number of records"));

		// alternate names: recordCounter
		// used this name because is short and descriptive
		// alternate data types: byte/short/long
		// used int because is big enough for most people
		int counter = 0;
		/*
		 * alternative names: "num", "x" or "randomwholenum" I used this name because it
		 * tells exactly what the variable is and since it is a random number than that
		 * is the best and shortest valid description i could think of alternative data
		 * types are "byte", "short" and "long" I used int because the it gives a big
		 * enough range to cover most random numbers i will choose but that big that it
		 * is just wasting space
		 */
		int wholenum1 = 0;
		int wholenum2 = 0;
		/*
		 * alternative names: "decimalnum" or "ransomdecnum" used this name because it
		 * is short and descriptive alternate data types: "double" used float because it
		 * is accurate enough for almost all random choices
		 */
		float decnum1 = 0.0f;
		float decnum2 = 0.0f;
		// used this name because it is short and descriptive
		// no alternate data types that use true and false
		boolean bool1 = true;
		boolean bool2 = true;
		// used this name because it was short and descriptive
		// alternate data type: "string"
		// used char because it is a single letter so no reason
		// to waste space by creating a string
		char letter1 = 'a';
		char letter2 = 'a';
		// alternative names "letters"
		// being as i am just putting a word or bunch of letters called it word didn't
		// use "letters because to similiar to letter"
		String word1 = "";
		String word2 = "";

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

				wholenum1 = Integer.parseInt(input[0]);
				decnum1 = Float.parseFloat(input[1]);
				bool1 = Boolean.parseBoolean(input[2]);
				letter1 = input[3].charAt(0);
				word1 = input[4];

				//
				while (wholenum1 < 0) {
					wholenum1 = Integer.parseInt(gt.getInputString("int must be positive"));
				}
				while (decnum1 < 0) {
					decnum1 = Float.parseFloat(gt.getInputString("float must be positive"));
				}
				// used the if statement to make sure it only compare values in the case that
				// there is more that one value
				if (counter > 0) {

					// for all the if statements i could have structured them in any order but i put
					// equal last because it is the least likely and greater than and less than have
					// the
					// same chances so no reason to specifically do one before the other

					if (wholenum1 > wholenum2) {
						gt.println(wholenum1 + " is greater then " + wholenum2);
					} else if (wholenum1 < wholenum2) {
						gt.println(wholenum1 + " is less then " + wholenum2);
					} else {
						gt.println(wholenum1 + " is equal to " + wholenum2);
					}

					if (decnum1 > decnum2) {
						gt.println(decnum1 + " is greater then " + decnum2);
					} else if (decnum1 < decnum2) {
						gt.println(decnum1 + " is less then " + decnum2);
					} else {
						gt.println(decnum1 + " is equal to " + decnum2);
					}

					if (bool1 == bool2) {
						gt.println(bool1 + " is equal 2 " + bool2);
					} else {
						gt.println(bool1 + " is not equal to " + bool2);
					}

					if (letter1 > letter2) {
						gt.println(letter1 + " is greater then " + letter2);
					} else if (letter1 < letter2) {
						gt.println(letter1 + " is less then " + letter2);
					} else {
						gt.println(letter1 + " is equal to " + letter2);
					}

					if (word1.compareTo(word2) > 0) {
						gt.println(word1 + " is greater then " + word2);
					} else if (word1.compareTo(word2) < 0) {
						gt.println(word1 + " is less than " + word2);
					} else {
						gt.println(word1 + " is equal to " + word2);
					}
				}
				wholenum2 = wholenum1;
				decnum2 = decnum1;
				bool2 = bool1;
				letter2 = letter1;
				word2 = word1;
				counter++;
			}
		}
	}
}