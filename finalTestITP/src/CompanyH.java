public class CompanyH {
	GTerm gtMain;
	GTerm gtSub;

	public CompanyH() {
		this.gtMain = new GTerm(700, 600);
		this.gtMain.setXY(50, 15);
		this.gtMain.setFontSize(30);
		this.gtMain.println("Company H accomodations");
		this.gtMain.println("");
		this.gtMain.setFontSize(18);
		this.gtMain.addButton("Show more details", this, "showSelectedAccomodation");
		this.gtMain.println("");	
		this.gtMain.println("Search using the name of the desired accomadation range.");
		this.gtMain.addButton("Search", this, "searchList");
		this.gtMain.addTextArea("", 300, 33);
		this.gtMain.println("");
		this.gtMain.println("   Range           Accomodation level");
		this.gtMain.addList(600, 300, null, null);
		this.gtMain.addElementToList(0, "Diamond range: Luxury");
		this.gtMain.addElementToList(0, "Bed and Roof range: Budget");
		this.gtMain.addElementToList(0, "Green Starts range: Eco-friendly");
		this.gtSub = new GTerm (600, 430);
		this.gtSub.setFontSize(18);
		this.gtSub.setXY(25, 20);
		this.gtSub.println("Range: ");
		this.gtSub.addList(500, 60, null, null);
		this.gtSub.println("");	
		this.gtSub.println("Location: ");
		this.gtSub.addList(500, 60, null, null);
		this.gtSub.println("");	
		this.gtSub.println("Price: ");
		this.gtSub.addList(500, 60, null, null);
		this.gtSub.println("");
		this.gtSub.println("");	
		this.gtSub.print("Name:");
		this.gtSub.addTextArea("", 180, 33);
		this.gtSub.print("   Number:");
		this.gtSub.addTextArea("", 180, 33);
		this.gtSub.println("");
		this.gtSub.println("");
		this.gtSub.print("    ");
		this.gtSub.addButton("Create a booking", this, "createBooking");
	}

	public void searchList() {

	}

	public void showSelectedAccomodation() {

	}
	
	public void createBooking() {
		
	}

	public static void main(String[] args) {
		CompanyH finalTest = new CompanyH();
	}
}
