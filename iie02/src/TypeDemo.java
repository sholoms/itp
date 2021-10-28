
public class TypeDemo {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		/*
		 * alternative names: "num", "x" or "randomwholenum" I used this name because it
		 * tells exactly what the variable is and since it is a random number than that
		 * is the best and shortest valid description i could think of alternative data
		 * types are "byte", "short" and "long" I used int because the it gives a big
		 * enough range to cover most random numbers i will choose but that big that it
		 * is just wasting space
		 */
		int wholenum = Integer.parseInt(gt.getInputString("Enter a random whole number"));
		/*
		 * alternative names: "decimalnum" or "ransomdecnum" i used this name beacause
		 * it is the shortest discriptive name i came up with alternate data types:
		 * "double" I used float because it is accurate enough for any of my random
		 * choices
		 */
		float decnum = Float.parseFloat(gt.getInputString("Enter random number with a decimal"));
		// I used this name because it is the shortest descriptive name i came up with
		// no alternate data types that use true and false
		boolean bool = true;
		// i used this name beacause it was the shortest descriptive i came yp with
		// alternate data type: "string"
		// i used char beacuase it is a single letter so no reason
		// to waste space by creating a string
		char letter = 's';
		gt.println("whole number: " + wholenum);
		gt.println("decimal number: " + decnum);
		gt.println("boolean: " + bool);
		gt.println("letter: " + letter);

		wholenum++;
		decnum++;
		bool = false;
		letter = 'c';
		String newvars = "new whole number: " + wholenum;
		newvars += "\nnew decimal number: " + decnum;
		newvars += "\nnew boolean: " + bool;
		newvars += "\nnew letter: " + letter;
		gt.showMessageDialog(newvars);
	}
}