import java.util.Scanner;

class BrokenTypewriter {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String theString = getInputFromUser();
        retypeBadely(theString);
        // System.out.println('Z' > 'N');
    }

    // Get input string from user
    public static String getInputFromUser() {
        System.out.println("Please input your string:\t");
        String theString = scanner.nextLine();
        return theString;
    }

    public static String retypeBadely(String string) {
        // Add '.' after every third letter between 'N' and 'Z'
        int counter = 0;
        // for (int i=string.length()-1; i>=0; i--){
        // if (string.toLowerCase().charAt(i) > 'n' && string.toLowerCase().charAt(i) <
        // 'z'){
        // counter++;
        // }
        // if (counter > 2){
        // counter = 0;
        // string = string.substring(0,i+1) + "." + string.substring(i+1);
        // }
        // }
        int i = 0;
        while (i < string.length()) {
            if (string.toLowerCase().charAt(i) > 'n' && string.toLowerCase().charAt(i) < 'z') {
                // System.out.println(string.charAt(i) + " detected");
                counter++;
            }

            if (counter > 2) {
                counter = 0;
                string = string.substring(0, i+1) + "." + string.substring(i+1);
            }
            i++;
        }

        // Replace 'A' and 'a' with '4'
        string = (string.replace('A', '4')).replace('a', '4');

        // Replace 'O' and 'o' with '0'
        string = (string.replace('O', '0')).replace('o', '0');

        System.out.println(string);
        return string;
    }
}