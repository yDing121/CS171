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
        // System.out.println(len);
        int n1, n2;
        Stack<Integer> c = new Stack<Integer>();
        for (int i=0; i<len; i++){
            c.push(i);
        }

        // Evaluate
        while (!c.isEmpty()){
            n1 = c.pop();
            if (!c.isEmpty()){
                n2 = c.pop();
            }
            else{
                c.push(n1);
                break;
            }

            if (knows(n1, n2) && knows(n2, n1)){
                continue;
            }
            else if (knows(n1, n2)){
                c.push(n2);
            }
            else if (knows(n2, n1)){
                c.push(n1);
            }
        }
        
        // Get top element to check if not empty, no celebrity if empty
        int n = !c.isEmpty()
        ? c.pop()
        : -1;

        // Check if the person actually is a celebrity
        for (int i=0; i<len; i++){
            if (i==n) continue;
            if (this.acquaintances[i][n] != 1){
                return -1;
            }
        }
        return n;
    }

    public static void main(String[] args) {

        // the structure of the array below is that
        // if acquaintances[a][b] == 1, then "a knows b"
        // if acquaintances[a][b] == 0, then "a does not know b"

        int[][] acquaintances = { 
                { 0, 1, 1, 1 },
                { 1, 0, 1, 0 },
                { 0, 0, 0, 0 },
                { 1, 0, 1, 0 } };

        Party party = new Party(acquaintances);
        int celebrity = party.findCelebrity();

        if (celebrity == -1)
            System.out.println("No Celebrity");
        else
            System.out.println("Celebrity ID = " + celebrity);
    }
}