public class Pyramid {
	// called unit because it is the unit the pyramid is built out of
	public Pyramid(char unit) {
	    int hight = 5;
	    // print pyramid
	    for (int i = 0; i < hight; i++)
	    {
	        // move the pyramid be to the right
	        for (int k = 0; k < hight - i - 1; k++)
	        {
	            System.out.print(" ");

	        }
	        //print the rows
	        for (int j = 0; j < i + 1; j++)
	        {
	            System.out.print("" + unit);
	        }
	        System.out.print("\n");
	    }
	}
	
	public static void main(String[] args) {
			Pyramid iie07 = new Pyramid('a');		
	}
}
