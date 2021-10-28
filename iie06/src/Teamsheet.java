public class Teamsheet {
	private GTerm gt;

	public Teamsheet() {
		this.gt = new GTerm(600, 400);
		// Insert code to add a "text field" to gt
		this.gt.addTextField("", 150);
		// Insert code to add a button/buttons to gt
		this.gt.addButton("add to squad", this, "addToSquad");
		gt.println("");
		gt.println("     squad              team");
		this.gt.addList(125, 300, this, "takeFromList1");
		this.gt.addList(125, 300, this, "takeFromList2");
		gt.println("");
		this.gt.addButton("move to team", this, "moveToTeam");
		this.gt.addButton("move off team", this, "moveOffTeam");
		gt.println("");

	}

// Write method to be called/invoked when the button is selected
	public void addToSquad() {
		// used this name because it is descriptive
		String player = this.gt.getTextFromEntry(0);
		gt.addElementToList(0, player);
	}

// Write method to be called/invoked when a list item is selected
	public void takeFromList1() {
// used this name because it is descriptive
		String selected = gt.getSelectedElementFromList(0);
		gt.setTextInEntry(0, selected);
	}

	public void takeFromList2() {
		String selected = gt.getSelectedElementFromList(1);
		gt.setTextInEntry(0, selected);
	}

	public void moveToTeam() {
		String selected = gt.getSelectedElementFromList(0);
		// called it Eindex as apose to just index because there is also the list index
		// being dealt with over here and wanted to specify element index not list index
		int Eindex = gt.getSelectedElementIndexFromList(0);
		gt.addElementToList(1, selected);
		gt.removeElementFromList(0, Eindex);
	}

	public void moveOffTeam() {
		String selected = gt.getSelectedElementFromList(1);
		int Eindex = gt.getSelectedElementIndexFromList(1);
		gt.addElementToList(0, selected);
		gt.removeElementFromList(1, Eindex);
	}

	public static void main(String[] args) {
		Teamsheet iie06 = new Teamsheet();
	}
}
