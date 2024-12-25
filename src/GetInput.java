import java.util.Scanner;

public class GetInput {
    public String GetStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int GetNumberInput() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new InvalidInputException("Invalid input. Please enter a valid number.");
        }
    }
}