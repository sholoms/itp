// uses this class because that is what it says in the specs
public class ThatsYourstory {
	public static void main(String[] args) {
		// used this size gterm window because this size fit well with the design
		GTerm gt = new GTerm(1100, 800);

		// used these colours because the contrast was nice
		gt.setBackgroundColor(30, 250, 200);
		gt.setFontColor(225, 15, 175);

		// no alternate data types
		// alternate names: name
		// used kidName because it is more descriptive and easier to remember whos name
		String kidName = gt.getInputString("Enter boys name");
		// no alternate data types
		// alternate names: age
		// used kidAge because it is more descriptive and easier to remember whos age
		int kidAge = Integer.parseInt(gt.getInputString("Enter boys age between 3 and 10"));

		while (kidAge > 10 || kidAge < 3) {
			kidAge = Integer.parseInt(gt.getInputString("boys age must be between 3 and 10"));
		}
		// no alternate data types
		// alternate names: kidsFood
		// just used supper because being as there is only one "food" therefore nothing
		// to get confused with and it is descriptive
		String food = gt.getInputString("Enter pizza, hotdogs or fish and chips");
		// no alternate data types
		// alternate names foodShop
		// used shop because it is short and being as there is only one shop it is
		// descriptive
		String shop = "";

		// no reason to have any specific order so did this one just
		// to be consistent with the order that the options were given in
		// compared the first char as oppose to the whole string because it is a less
		// demanding comparison
		// included all this in the if else statements because only want one of these
		// options to happen based on user input
		if (food.charAt(0) == 'p' || food.charAt(0) == 'P') {
			shop = "pizza shop";
		} else if (food.charAt(0) == 'h' || food.charAt(0) == 'H') {
			shop = "butcher";
		} else {
			shop = "fish shop";
		}
		// title
		// all font sizes, placements and colours because they looked nice
		gt.setXY(400, 25);
		gt.setFontSize(25);
		gt.println("Is It Supper Time Yet");
		// first question
		gt.setFontSize(18);
		gt.setXY(25, 150);
		gt.println("There once was a boy named " + kidName + " who was " + kidAge + " years old.");

		gt.println(
				"One day when " + kidName + " was picked up from school, \nhe asked his mother is it supper time yet?");
		// first pic
		gt.setXY(700, 100);
		gt.addImageIcon("leavingSchool.jpg");
		// second question
		gt.setXY(375, 325);
		gt.println("No " + kidName + " his mother responded,\n first we have to order our food from the " + shop);
		// second pic
		gt.setXY(75, 250);
		// couldnt think of any reason to have any specific order so did this one just
		// to be consistent with the order that the options were given in
		// compared the first char as oppose to the whole string because it is a less
		// demanding comparison
		// included all this in the if else statements because i only want one of these
		// options to happen based on user input
		if (shop.charAt(0) == 'p') {
			gt.addImageIcon("pizzaShop.jpg");
		} else if (shop.charAt(0) == 'b') {
			gt.addImageIcon("butcher.jpg");
		} else {
			gt.addImageIcon("fishShop.png");
		}
		// third question
		gt.setXY(25, 475);
		gt.println("After they ordered their " + food + " from the shop, " + kidName + "\n asked is it supper yet?");
		gt.println("No answered his mother, first we have to pay for the food");
		// third pic
		gt.setXY(700, 400);
		gt.addImageIcon("cashRegister.jpg");
		// fourth question
		gt.setXY(375, 650);
		gt.println("Once they had paid " + kidName + " asked his mother is it supper time yet?");
		gt.println("Yes answered his mother, it is time to eat our delicious " + food);
		// fourth pic
		gt.setXY(75, 555);
		gt.addImageIcon("supperTime.jpg");
		/*
		 * picture sources 
		 * Pick up from school; https://www.freepik.com/premium-vector/teacher-mother-boy-school-
		 * class_6326923.htm 
		 * Pizza shop: https://www.clipartmax.com/middle/m2H7i8Z5m2A0d3A0_pizza-fast-food-italian-
		 * cuisine-illustration-pizza-shop-cartoon/ 
		 * Butcher: https://www.colourbox.com/vector/cartoon-chef-cook-character-and-meat-shop-
		 * vector-35894778 
		 * Fish shop: https://www.facebook.com/batterfishandchipsshop/
		 * Supper time: https://www.dreamstime.com/illustration/cartoon-family-eating.html Cash
		 * register: https://www.cleanpng.com/png-cashier-cash-register-clip-art-cashier-830928/
		 * 
		 */

	}
}