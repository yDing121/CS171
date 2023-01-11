import java.util.Scanner;
public class PrimeTable {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Please input rows and columns separated by a space:");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        // Generate integer array starting from 2
        int[][] intArr = new int[rows][columns];
        for (int i=0; i<rows; i++){
            for (int j=0; j<columns; j++){
                intArr[i][j] = i*columns + j + 2;
            }
        }

        // Print new table
        printTableOfPrimes(intArr);
    }

    // Check if n is prime
    public static boolean isPrime(int n){
        for (int i=2; i<n; i++){
            if (n%i == 0) return false;
        }
        return true;
    }

    // Boolean array of prime status
    public static boolean[][] whichArePrime(int[][] arr){
        boolean[][] boolArr = new boolean[arr.length][arr[0].length];
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                if (isPrime(arr[i][j])){
                    boolArr[i][j] = true;
                }
            }
        }
        return boolArr;
    }

    // Print table with an asterisk after primes
    static public void printTableOfPrimes(int[][] arr){
        boolean[][] boolArr = whichArePrime(arr);

        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                System.out.print(arr[i][j]);

                if (boolArr[i][j]){
                    System.out.print("*");
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
