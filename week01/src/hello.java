public class hello {
	private GTerm gt;

	public hello() {
		this.gt = new GTerm(600, 400);
		gt.addTextArea("", 500, 30);
		gt.addButton("hi", this, "button");
		gt.print("Hello ");
		gt.println("World!");
		gt.showHelp();
	}

	public void button() {
		String message = gt.getTextFromEntry(0);
		this.gt.println(message);

	}

	public static void main(String[] args) {
		hello test = new hello();
	}

}
