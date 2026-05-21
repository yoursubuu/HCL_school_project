
public class Palindrom {

	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		System.out.print("Enter a number: ");
		String input = scanner.nextLine().trim();

		if (input.isEmpty()) {
			System.out.println("No input provided.");
			return;
		}

		boolean negative = input.startsWith("-");
		String digits = negative ? input.substring(1) : input;

		// Validate that the remaining characters are digits only
		if (!digits.matches("\\d+")) {
			System.out.println("Invalid input. Please enter an integer number (digits only). Example: 12321");
			return;
		}

		boolean isPal = isPalindrome(digits);

		if (negative) {
			// Common convention: negative numbers are not considered palindromes because of the '-' sign.
			System.out.printf("%s -> %s (absolute value %s)%n", input, (isPal ? "Palindrome" : "Not palindrome"), digits);
			System.out.println("Note: by convention negative numbers are usually NOT treated as palindromes because of the minus sign.");
		} else {
			System.out.printf("%s -> %s%n", input, (isPal ? "Palindrome" : "Not palindrome"));
		}
	}

	private static boolean isPalindrome(String s) {
		int i = 0, j = s.length() - 1;
		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) return false;
			i++; j--;
		}
		return true;
	}

}
