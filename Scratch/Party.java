package Scratch;
import java.util.Stack;

// A celebrity at a party is someone that is known by everyone, but knows nobody himself or herself.
// Task: Given a 2d matrix of acquaintances, find the celebrity.

public class Party {
    
    int[][] acquaintances;
    int len;
    
    public Party(int[][] acquaintances) {
        // it's best if we make a defensive copy of the array we are given...
        int numPeople = this.len = acquaintances.length;
        this.acquaintances = new int[numPeople][numPeople];
        for (int i = 0; i < numPeople; i++) {
            for (int j = 0; j < numPeople; j++) {
                this.acquaintances[i][j] = acquaintances[i][j];
            }
        }
    }

    private boolean knows(int a, int b) {
        return ((acquaintances[a][b] == 1) ? true : false);
    }
    
    public int findCelebrity() {
        
        // TODO: Insert code here
        System.out.println(len);
        Stack<Integer> c = new Stack<Integer>();
        int i = 0, j = 0;

        while (i < len){
            
        }
        return 0;
    }

    public static void main(String[] args) {
        
        // the structure of the array below is that 
        // if acquaintances[a][b] == 1, then "a knows b"
        // if acquaintances[a][b] == 0, then "a does not know b"
        
        int[][] acquaintances = {{0,1,1,1},
                                 {1,0,1,0},
                                 {0,0,0,0},
                                 {1,0,1,0}};
        
        Party party = new Party(acquaintances);
        int celebrity = party.findCelebrity();
        
        if (celebrity == -1)
            System.out.println("No Celebrity");
        else 
            System.out.println("Celebrity ID = " + celebrity);
    }
}