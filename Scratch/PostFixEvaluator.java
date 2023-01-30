package Scratch;

import java.util.Scanner;
import java.util.Stack;

public class PostFixEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input your postfix expression (items separated with spaces)");
        String theLine = scanner.nextLine();
        scanner.close();

        System.out.println(evaluate(theLine));
    }

    public static Double evaluate(String str) {
        Scanner scanner = new Scanner(str);
        Stack<Double> stack = new Stack<Double>();
        String cur = "";

        while (scanner.hasNext()) {
            // If current item is not an operator
            if (scanner.hasNextDouble()) {
                stack.push(scanner.nextDouble());
            } else {
                cur = scanner.next();
                Double d2, d1;

                // If current item is an operator
                switch (cur) {
                    case "+":
                        stack.push(stack.pop() + stack.pop());
                        break;
                    case "-":
                        d2 = stack.pop();
                        d1 = stack.pop();
                        stack.push(d1 - d2);
                        break;
                    case "*":
                        stack.push(stack.pop() * stack.pop());
                        break;
                    case "/":
                        d2 = stack.pop();
                        d1 = stack.pop();
                        stack.push(d1 / d2);
                        break;
                    case "^":
                        d2 = stack.pop();
                        d1 = stack.pop();
                        stack.push(Math.pow(d1, d2));
                        break;
                }
            }

        }

        return stack.pop();
    }
}
