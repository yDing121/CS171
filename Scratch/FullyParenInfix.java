package Scratch;

import java.util.Stack;
import java.util.Scanner;

public class FullyParenInfix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input line that needs evaluation");
        String line = scanner.nextLine();
        scanner.close();

        System.out.println(evaluate(line));
    }

    public static double evaluate(String str) {
        Stack<Double> numStack = new Stack<Double>();
        Stack<String> opStack = new Stack<String>();
        Double d1, d2;

        Scanner scanner = new Scanner(str);

        while (scanner.hasNext()) {
            // Next is number
            if (scanner.hasNextDouble()) {
                numStack.push(scanner.nextDouble());
                continue;
            }

            // Next is operator
            String cur = scanner.next();
            if ("+-*/^".contains(cur)) {
                opStack.push(cur);
            }
            // Next is right parenthesis
            else if (cur.equals(")")) {
                d2 = numStack.pop();
                d1 = numStack.pop();

                switch (opStack.pop()) {
                    case "+":
                        numStack.push(d1 + d2);
                        break;
                    case "-":
                        numStack.push(d1 - d2);
                        break;
                    case "*":
                        numStack.push(d1 * d2);
                        break;
                    case "/":
                        numStack.push(d1 / d2);
                        break;
                    case "^":
                        numStack.push(Math.pow(d1, d2));
                        break;
                }
            }
        }
        return numStack.pop();
    }
}
