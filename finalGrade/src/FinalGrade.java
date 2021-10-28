public class FinalGrade {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		// used int because it is standard and being a small program the
		// amount of space saved by using something smaller makes no difference
		int mark = Integer.parseInt(gt.getInputString("enter grade"));
		// could have started with less than 50 and gone up but I am being
		// optimistic that the higher grades are more likely so did those first
		if (mark > 79) {
			gt.println("final grade: HD");
		} else if (mark > 69) {
			gt.println("final grade: DI");
		} else if (mark > 59) {
			gt.println("final grade: CR");
		} else if (mark > 49) {
			gt.println("final grade: PA");
		} else {
			gt.println("final grade: NN");
		}
	}
}
