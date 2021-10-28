import java.awt.Color;

public class iie01 {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		gt.setBackgroundColor(10, 200, 180);
		gt.setFontColor(Color.CYAN);
		gt.setXY(175, 15);
		gt.setFontSize(25);
		String name = gt.getInputString("Enter your name");
		gt.println("Hello " + name);
		gt.setFontColor(255, 0, 0);
		gt.setFontSize(15);
		gt.setXY(80, 50);
		gt.println("My name is Sholom Serebryanski. ");
		gt.println("I am almost 20 years old, I live in melbourne and");
		gt.println("I am currently studying the bachelors of IT.");
		gt.println("My hobbies include sports, reading and PC gaming.");
		gt.setXY(147, 150);
		gt.addImageIcon(gt.getFilePath());

	}
}