package Scratch;

import java.util.Scanner;
import java.util.Stack;

public class DelimiterMatch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input line that needs evaluation");
        String line = scanner.nextLine();
        scanner.close();

        if (Evaluate(line)){
            System.out.println("Correct delimiters!");
        }
        else{
            System.out.println("Error in delimiters!");
        }
    }

    public static boolean Evaluate(String str) {
        Stack<String> stack = new Stack<String>();
        Scanner scanner = new Scanner(str);

        String cur = "";

        while (scanner.hasNext()) {
            cur = scanner.next();

            switch (cur) {
                case "(":
                case "[":
                case "{":
                    stack.push(cur);
                    break;
                case ")":
                    if (stack.isEmpty() || !stack.pop().equals("("))
                        return false;
                    break;
                case "]":
                    if (stack.isEmpty() || !stack.pop().equals("["))
                        return false;
                    break;
                case "}":
                    if (stack.isEmpty() || !stack.pop().equals("{"))
                        return false;
                    break;
            }
        }
        scanner.close();

        return stack.isEmpty();
    }
}
