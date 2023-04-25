package Scratch;
import java.util.Random;

public class thing{
    public static void main(String[] args) {
        int c = 0;
        Random random = new Random();

        for (int i=0; i<72727; i++){
            for (int j=0; j<1000000; j++){
                if (random.nextFloat() < 0.5){
                    c ++;
                }
                else{
                    c--;
                }
            }
        }
        System.out.println("Count differential: " + c);
    }
}