import java.util.Scanner;
import java.util.Stack;

public class PostFixEvaluator {
    public static void main(String[] args) {
        System.out.println("Enter a postfix expression:\t");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        scanner.close();
        System.out.println(evaluate(inputString));
    }

    private static double evaluate(String expression) {
        Stack<Double> operands = new Stack<Double>();
        Scanner scanner = new Scanner(expression);

        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                operands.push(scanner.nextDouble());
            } else {
                String operator = scanner.next();
                double operand2 = operands.pop();
                double operand1 = operands.pop();

                switch (operator) {
                    case "+":
                        operands.push(operand1 + operand2);
                        break;
                    case "-":
                        operands.push(operand1 - operand2);
                        break;
                    case "*":
                        operands.push(operand1 * operand2);
                        break;
                    case "/":
                        operands.push(operand1 / operand2);
                        break;
                    case "^":
                        operands.push(Math.pow(operand1, operand2));
                        break;
                }
            }
        }

        scanner.close();
        return operands.pop();
    }

    // Do this when bored
    private static double shunting(String expression){
        return 0;
    }

}
