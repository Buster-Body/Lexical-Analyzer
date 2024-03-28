import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Lexical_Analyzer {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String tokenizer = "\\s+|(?<=[(),;=+*/-])|(?=[(),;=+*/-])";

		String keywords = "int|double|String";
		String operators = "[=()+*/,;-]";
		String identifier = "[_a-zA-Z][_a-zA-Z0-9]*";
		String int_Constant = "[0-9]+";
		String double_Constant = "[0-9]+[.][0-9]+";
		String literal = "[\"][^\"]*[\"]";

		System.out.println("Enter a text document file-path!");

		try (Scanner input = new Scanner(System.in);
				BufferedReader reader = new BufferedReader(new FileReader(input.nextLine().replace("\"", "")))) {

			String line;
			int lineNumber = 0;
			while ((line = reader.readLine()) != null) {

				lineNumber++;
				String[] tokens = line.split(tokenizer);

				for (String token : tokens) {

					if (token.matches(keywords)) {
						System.out.printf("Line:%2d %-16s%s\n", lineNumber, "KEYWORD:", token);
					} else if (token.matches(operators)) {
						System.out.printf("Line:%2d %-16s%s\n", lineNumber, "OPERATOR:", token);
					} else if (token.matches(identifier)) {
						System.out.printf("Line:%2d %-16s%s\n", lineNumber, "IDENTIFIER:", token);
					} else if (token.matches(int_Constant)) {
						System.out.printf("Line:%2d %-16s%s\n", lineNumber, "INT_CONSTANT:", token);
					} else if (token.matches(double_Constant)) {
						System.out.printf("Line:%2d %-16s%s\n", lineNumber, "DOUBLE_CONSTANT:", token);
					} else if (token.matches(literal)) {
						System.out.printf("Line:%2d %-16s%s\n", lineNumber, "LITERAL:", token);
					} else if (token.isEmpty()) {
						continue;
					} else {
						System.out.printf("Line:%2d %-16s%s <==\n", lineNumber, "ERROR", token);
					}
				}
			}
		}
	}
}
