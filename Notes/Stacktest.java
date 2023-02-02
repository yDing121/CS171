import java.util.Stack;
import java.util.Scanner;

public class Stacktest {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        Scanner scanner = new Scanner(System.in);


        int n=50;
        while (n>0){
            s.push(n%2);
            n/=2;
        }

        while (!s.isEmpty()){
            System.out.println(s.pop());
        }

        Stack<String> s2 = new Stack<String>();
        for (String str: args){
            s2.push(str);
        }

        while (!s2.isEmpty()){
            System.out.println(s2.pop());
        }
    }
}
