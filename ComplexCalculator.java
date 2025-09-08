import java.util.Scanner;
import java.lang.Math;

public class ComplexCalculator {

    private static final int MAX_CAPACITY = 64;
    private double[] numbers;
    private int count;

    public ComplexCalculator() {
        this.numbers = new double[MAX_CAPACITY];
        this.count = 0;
    }

    public boolean addNumber(double number) {
        if (count < MAX_CAPACITY) {
            numbers[count] = number;
            count++;
            return true;
        } else {
            System.out.println("Error: Calculator capacity reached. Cannot add more numbers.");
            return false;
        }
    }

    public void clear() {
        this.count = 0;
        System.out.println("Calculator cleared. The array is now empty.");
    }

    public void printNumbers() {
        if (count == 0) {
            System.out.println("The array is empty.");
            return;
        }
        System.out.println("Current numbers in the array:");
        for (int i = 0; i < count; i++) {
            System.out.printf("[%d]: %.2f%n", i, numbers[i]);
        }
    }

    public double add() {
        if (count == 0) {
            System.out.println("No numbers to add. Returning 0.");
            return 0;
        }
        double sum = 0;
        for (int i = 0; i < count; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public double subtract() {
        if (count == 0) {
            System.out.println("No numbers to subtract. Returning 0.");
            return 0;
        }
        double result = numbers[0];
        for (int i = 1; i < count; i++) {
            result -= numbers[i];
        }
        return result;
    }

    public double multiply() {
        if (count == 0) {
            System.out.println("No numbers to multiply. Returning 1.");
            return 1;
        }
        double product = 1;
        for (int i = 0; i < count; i++) {
            product *= numbers[i];
        }
        return product;
    }

    public double divide() {
        if (count == 0) {
            System.out.println("No numbers to divide. Returning 0.");
            return 0;
        }
        double result = numbers[0];
        for (int i = 1; i < count; i++) {
            if (numbers[i] == 0) {
                System.out.println("Error: Division by zero.");
                return Double.NaN;
            }
            result /= numbers[i];
        }
        return result;
    }

    public void squareRoot() {
        if (count == 0) {
            System.out.println("No numbers to operate on.");
            return;
        }
        System.out.println("Calculating square root for each number:");
        for (int i = 0; i < count; i++) {
            if (numbers[i] < 0) {
                System.out.printf("Skipping index [%d]: Cannot take square root of a negative number (%.2f).%n", i, numbers[i]);
            } else {
                numbers[i] = Math.sqrt(numbers[i]);
            }
        }
        printNumbers();
    }

    public void power(double exponent) {
        if (count == 0) {
            System.out.println("No numbers to operate on.");
            return;
        }
        System.out.printf("Raising each number to the power of %.2f:%n", exponent);
        for (int i = 0; i < count; i++) {
            numbers[i] = Math.pow(numbers[i], exponent);
        }
        printNumbers();
    }

    public void logarithm() {
        if (count == 0) {
            System.out.println("No numbers to operate on.");
            return;
        }
        System.out.println("Calculating natural logarithm for each number:");
        for (int i = 0; i < count; i++) {
            if (numbers[i] <= 0) {
                System.out.printf("Skipping index [%d]: Cannot take log of a non-positive number (%.2f).%n", i, numbers[i]);
            } else {
                numbers[i] = Math.log(numbers[i]);
            }
        }
        printNumbers();
    }

    public void sine() {
        if (count == 0) {
            System.out.println("No numbers to operate on.");
            return;
        }
        System.out.println("Calculating sine for each number:");
        for (int i = 0; i < count; i++) {
            numbers[i] = Math.sin(numbers[i]);
        }
        printNumbers();
    }

    public void cosine() {
        if (count == 0) {
            System.out.println("No numbers to operate on.");
            return;
        }
        System.out.println("Calculating cosine for each number:");
        for (int i = 0; i < count; i++) {
            numbers[i] = Math.cos(numbers[i]);
        }
        printNumbers();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ComplexCalculator calculator = new ComplexCalculator();
        boolean running = true;

        System.out.println("Welcome to the Complex Calculator!");
        System.out.printf("Capacity: %d numbers%n", MAX_CAPACITY);

        while (running) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add a number");
            System.out.println("2. Perform basic operations (add, subtract, etc.) on all numbers");
            System.out.println("3. Perform complex operations (sqrt, pow, sin, etc.) on each number");
            System.out.println("4. Print all numbers");
            System.out.println("5. Clear all numbers");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter numbers separated by spaces: ");
                        String inputLine = scanner.nextLine();
                        String[] numberStrings = inputLine.trim().split("\\s+");
                        for (String s : numberStrings) {
                            if (!s.isEmpty()) {
                                try {
                                    double num = Double.parseDouble(s);
                                    calculator.addNumber(num);
                                } catch (NumberFormatException e) {
                                    System.out.printf("Skipping invalid input: '%s'%n", s);
                                }
                            }
                        }
                        break;
                    case 2:
                        System.out.println("\n--- Basic Operations ---");
                        System.out.println("1. Add all numbers");
                        System.out.println("2. Subtract all numbers");
                        System.out.println("3. Multiply all numbers");
                        System.out.println("4. Divide all numbers");
                        System.out.print("Enter basic operation choice: ");
                        int opChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (opChoice) {
                            case 1:
                                double sum = calculator.add();
                                System.out.printf("Sum: %.2f%n", sum);
                                break;
                            case 2:
                                double subResult = calculator.subtract();
                                System.out.printf("Result of subtraction: %.2f%n", subResult);
                                break;
                            case 3:
                                double prod = calculator.multiply();
                                System.out.printf("Product: %.2f%n", prod);
                                break;
                            case 4:
                                double divResult = calculator.divide();
                                if (!Double.isNaN(divResult)) {
                                    System.out.printf("Result of division: %.2f%n", divResult);
                                }
                                break;
                            default:
                                System.out.println("Invalid basic operation choice.");
                        }
                        break;
                    case 3:
                        System.out.println("\n--- Complex Operations ---");
                        System.out.println("1. Square Root (on each number)");
                        System.out.println("2. Power (on each number)");
                        System.out.println("3. Natural Logarithm (on each number)");
                        System.out.println("4. Sine (on each number)");
                        System.out.println("5. Cosine (on each number)");
                        System.out.print("Enter complex operation choice: ");
                        int complexOpChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (complexOpChoice) {
                            case 1:
                                calculator.squareRoot();
                                break;
                            case 2:
                                System.out.print("Enter the exponent: ");
                                if (scanner.hasNextDouble()) {
                                    double exponent = scanner.nextDouble();
                                    scanner.nextLine();
                                    calculator.power(exponent);
                                } else {
                                    System.out.println("Invalid input. Please enter a number.");
                                    scanner.nextLine();
                                }
                                break;
                            case 3:
                                calculator.logarithm();
                                break;
                            case 4:
                                calculator.sine();
                                break;
                            case 5:
                                calculator.cosine();
                                break;
                            default:
                                System.out.println("Invalid complex operation choice.");
                        }
                        break;
                    case 4:
                        calculator.printNumbers();
                        break;
                    case 5:
                        calculator.clear();
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting calculator. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again with valid input.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}
