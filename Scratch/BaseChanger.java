package Scratch;
// import java.util.Scanner;
import java.util.Stack;
public class BaseChanger {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        int base = 5;
        int n = 1073;
        
        while (n > 0){
            s.push(n % base);
            n /= base;
        }

        while (!s.isEmpty()){
            System.out.print(s.pop());
        }
    }
}
