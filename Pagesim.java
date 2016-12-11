import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

class Pagesim {
	public static void main(String[] args) {
		// read in physical frame numbers
		int n = readArg(args);
		System.out.println("Number of page frames set to " + n + ".");
		
		// set up for main loop
		Scanner in = new Scanner(System.in);
		String line; // input from user
		RefString rs = null;

		
		// begin main loop:
		while (true) {
			System.out.println();
			System.out.println("Please choose from the following options:");
			System.out.println("0 - Exit");
			System.out.println("1 - Read reference string");
			System.out.println("2 - Generate reference string");
			System.out.println("3 - Display current reference string");
			System.out.println("4 - Simulate FIFO");
			System.out.println("5 - Simulate OPT");
			System.out.println("6 - Simulate LRU");
			System.out.println("7 - Simulate LFU");
			System.out.println();
			
			// read input
			line = in.next();
			in.nextLine();
			switch (line) {
				case "0":
				// exit
				System.out.println("Goodbye.");
				System.exit(0);
				break;
				case "1":
				// read reference string
				rs = readRefString(in);
				if (rs != null) {
					System.out.println("Valid reference string saved.");
				} else {
					System.out.println("Invalid reference string. Please try again.");
				}
				break;
				case "2":
				// generate reference string
				System.out.println("How long do you want the reference string to be?");
				int stringSize = getStringSize(in);
				break;
				case "3":
				// print reference string
				if (rs != null) {
					rs.print();
				} else {
					System.out.println("Error: no reference string entered.");
				}
				break;
				case "4":
				break;
				case "5":
				break;
				case "6":
				break;
				case "7":
				break;
				default:
				break;
			} // end switch
		} // end while (true)
	} // end main
	
	private static int readArg(String[] args) {
		// check for correct number of arguments
		if (args.length < 1) {
			System.out.println("Error: need to specify exactly 1 argument for number of physical frames.");
			System.exit(-1);
		}
		if (args.length > 1) {
			System.out.println("Warning: too many arguments. Every argument after the 1st will be ignored.");
		}
		// n will be our # of physical page frames
		int n = -1;
		
		// try to parse int; catch exceptions
		try {
			n = Integer.parseInt(args[0]);
		} catch(NumberFormatException e) {
			System.out.println("Error: argument must be an integer.");
			System.exit(-1);
		}
		
		// check if n is between 1 and 8
		if (n < 1 || n > 8) {
			System.out.println("Error: must be between 1 and 8 physical frames.");
			System.exit(-1);
		}
		
		// everything worked out OK, return n!
		return n;
	}
	
	static RefString readRefString(Scanner in) {
		System.out.println("Enter a series of numbers: ");
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		// create RefString
		RefString rs = null;

		do {
			// read in a line
			String line = in.nextLine();
			// create a scanner to operate on that line
			Scanner lineScanner = new Scanner(line);
			// extract the ints
			int temp;
			while (lineScanner.hasNextInt()) {
				temp = lineScanner.nextInt();
				// ensure that the numbers entered are between 0 and 9:
				if (temp < 0 || temp > 9) {
					System.out.println("Warning: numbers must be between 0 and 9; \"" + temp + "\" ignored.");
				} else {
					al.add(temp);
				}
			}
			// make sure at least 1 valid int entered:
			if (al.size() < 1) {
				System.out.println("Error: you must enter at least 1 valid integer between 0 and 9.");
			} 
		} while (al.size() < 1);
		rs = new RefString(al);
		return rs;
	}
	
	static int getStringSize(Scanner in) {
		//read in a line; parse an int
		int stringSize = 0;
		while (stringSize < 1) {
			try {
				stringSize = in.nextInt();
			}
			catch (InputMismatchException e) {
				System.out.println("You must enter an integer.");
			}
			in.nextLine();
			if (stringSize < 1) {
				System.out.println("You must enter a positive integer.");
			}
		}
		// if int is out of bounds, give error
		return stringSize;
	}
}