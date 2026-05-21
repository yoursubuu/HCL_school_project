
public class Star {

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		System.out.print("How many lines in pyramid? ");
		String input = scanner.nextLine().trim();

		if (input.isEmpty()) {
			System.out.println("No input provided.");
			return;
		}

		int n;
		try {
			n = Integer.parseInt(input);
		} catch (NumberFormatException e) {
			System.out.println("Invalid number. Please enter a positive integer.");
			return;
		}

		if (n <= 0) {
			System.out.println("Please enter a positive integer greater than zero.");
			return;
		}

		// Print centered pyramid of stars
		for (int i = 1; i <= n; i++) {
			int spaces = n - i;
			for (int s = 0; s < spaces; s++) System.out.print(" ");
			int stars = 2 * i - 1;
			for (int k = 0; k < stars; k++) System.out.print("*");
			System.out.println();
		}
	}

}
