import java.util.Scanner;

import javax.annotation.processing.SupportedSourceVersion;
class BrokenTypewriter{
    static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // String theString = getInputFromUser();
        // retypeBadely(theString);
        System.out.println('Z' > 'N');
    }

    // Get input string from user
    public static String getInputFromUser(){
        System.out.println("Please input your string:\t");
        String theString = scanner.nextLine();
        return theString;
    }

    public static String retypeBadely(String string){
        // Replace 'A' and 'a' with '4'
        string = (string.replace('A', '4')).replace('a', '4');

        // Replace 'O' and 'o' with '0'
        string = (string.replace('O', '0')).replace('o', '0');

        // Add '.' after third letter if between 'N' and 'Z'
        for (int i=string.length() - 1; i>=0; i--){
            if (string.charAt(i) > 'N' && string.charAt(i) < 'Z'){
                string = string.substring(0, i) + "." + string.substring(i);
            }
        }

        System.out.println(string);
        return string;
    }
}