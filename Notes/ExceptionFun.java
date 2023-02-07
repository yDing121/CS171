import java.util.Scanner;

public class ExceptionFun {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter two ints and I will (integer) divide them");
        String userInput = scanner.nextLine();
        scanner.close();
        processInput1(userInput);
    }

    public static void processInput1(String userInput){
        Scanner scanner = new Scanner(userInput);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        scanner.close();
        try{
            int q = doDivision3(x,y);
            System.out.println("Their quotient is " + q);
        }
        catch (BadQuotientException e){
            System.out.println("Stop dude");
        }
        finally {
            System.out.println("done!");
        }
        // scanner.close();
    }

    public static int doDivision1(int a, int b) {
        return a/b;
    }
    
    public static int doDivision2(int a, int b) {
        try {
            return a/b;
        } catch (ArithmeticException e) {
            return -1;
        }
    }

    public static class BadQuotientException extends Exception{
        int numerator;
        int denomimator;

        public BadQuotientException(int num, int den){
            this.numerator = num;
            this.denomimator = den;
        }

        public String getMessage() {
            return "bruh";
        }
    }

    public static int doDivision3(int a, int b) throws BadQuotientException{
        if (b==0)throw new BadQuotientException(a, b);
        return (a/b);
    }
}
