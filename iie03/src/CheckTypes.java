
public class CheckTypes {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		// used this variable name because it describes what the variable is
		// could have use rawsString but even after it is split it is still just the a
		// bunch of
		// strings so used given strings instead
		String givenString1 = gt.getInputString("Enter an int, float, bool, char and word seperated by ");
		String givenString2 = gt.getInputString("Enter an int, float, bool, char and word seperated by ");
		// used this name because it is the simplest discriptive name i came up with
		String[] input1 = givenString1.split(",");
		String[] input2 = givenString2.split(",");
		/*
		 * alternative names: "num", "x" or "randomwholenum" I used this name because it
		 * tells exactly what the variable is and since it is a random number than that
		 * is the best and shortest valid description i could think of alternative data
		 * types are "byte", "short" and "long" I used int because the it gives a big
		 * enough range to cover most random numbers i will choose but that big that it
		 * is just wasting space
		 */
		int wholenum1 = Integer.parseInt(input1[0]);
		int wholenum2 = Integer.parseInt(input2[0]);
		/*
		 * alternative names: "decimalnum" or "ransomdecnum" i used this name beacause
		 * it is the shortest discriptive name i came up with alternate data types:
		 * "double" I used float because it is accurate enough for any of my random
		 * choices
		 */
		float decnum1 = Float.parseFloat(input1[1]);
		float decnum2 = Float.parseFloat(input2[1]);
		// I used this name because it is the shortest descriptive name i came up with
		// no alternate data types that use true and false
		boolean bool1 = Boolean.parseBoolean(input1[2]);
		boolean bool2 = Boolean.parseBoolean(input2[2]);
		// i used this name beacause it was the shortest descriptive i came up with
		// alternate data type: "string"
		// i used char beacuase it is a single letter so no reason
		// to waste space by creating a string
		char letter1 = input1[3].charAt(0);
		char letter2 = input2[3].charAt(0);
		// alternative names "letters"
		// being as i am just putting a word or bunch of letters called it word didn't
		// use "letters because to similiar to letter"
		String word1 = input1[4];
		String word2 = input2[4];

		// for all the if statements i could have structured them in any order but i put
		// equal last because it is the least likely and greater than and less than have
		// the
		// same chances so no reason to specifically do one before the other

		if (wholenum1 > wholenum2) {
			gt.println("wholenum1 is greater then wholenum2");
		} else if (wholenum1 < wholenum2) {
			gt.println("wholenum1 is less then wholenum2");
		} else {
			gt.println("wholenum1 is equal to wholenum2");
		}

		if (decnum1 > decnum2) {
			gt.println("decnum1 is greater then decnum2");
		} else if (decnum1 < decnum2) {
			gt.println("decnum1 is less then decnum2");
		} else {
			gt.println("decnum1 is equal to decnum2");
		}

		if (bool1 == bool2) {
			gt.println("bool1 is equal to bool2");
		} else {
			gt.println("bool 1 does not equal bool2");
		}

		if (letter1 > letter2) {
			gt.println("letter1 is greater then letter2");
		} else if (letter1 < letter2) {
			gt.println("letter1 is less then letter2");
		} else {
			gt.println("letter1 is equal to letter2");
		}

		if (word1.compareTo(word2) > 0) {
			gt.println("word1 is greater than word2");
		} else if (word1.compareTo(word2) < 0) {
			gt.println("word1 is less than word 2");
		} else {
			gt.println("word1 is equal to word2");
		}
	}
}