package Assignment2;

import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;


public class LatinSquares {
    
    ///////////////////
    // INNER CLASSES //
    ///////////////////
    
    public static class Choice {
        private int numChosen;
        
        private Choice(int numChosen) {
            //// ADD CODE HERE ////
            this.numChosen = numChosen;
        }
        
        public static Choice firstChoice() {
            //// ADD CODE HERE ////
            return new Choice(0);
        }
        
        public Choice nextChoice() {
            //// ADD CODE HERE ////
            this.numChosen++;
            return this;
        }
        
        public boolean unconsideredChoicesExist() {
            //// ADD CODE HERE ////
            return this.numChosen < n;
        }
        
        protected Choice clone() {
            //// ADD CODE HERE ////
            return new Choice(Integer.valueOf(this.numChosen));
        }
        
        public String toString() {
            //// ADD CODE HERE ////
            return this.numChosen + " ";
        }
    }
    
    public static class StackOfChoices implements Iterable<Choice> {    
        
        private Choice[][] choices;
        private int cur; //point at next insert location, same concept as size()
        //// ADD ADDITIONAL INSTANCE VARIABLES HERE ////
        
        public StackOfChoices(int n) {
            //// ADD CODE HERE ////
            choices = new Choice[n][n];

        }
        
        public boolean isEmpty() {
            //// ADD CODE HERE ////
            // for (int i=0; i<n; i++){
            //     for (int j=0; j<n; j++){
            //         if (!(choices[i][j] == null)){
            //             return false;
            //         }
            //     }
            // }
            // return true;
            return cur == 0;
        }
        
        public int size() {
            //// ADD CODE HERE ////
            // int c = 0;
            // for (int i=0; i<n; i++){
            //     for (int j=0; j<n; j++){
            //         if (!(choices[i][j] == null)){
            //             c++;
            //         }
            //     }
            // }
            // return c;
            return cur;
        }
        
        public void push(Choice choice) {
            //// ADD CODE HERE ////
            int c = 0;
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    if (c >= cur){
                        choices[i][j] = choice;
                        cur++;
                        return;
                    }
                    c++;
                }
            }
        }
        
        public Choice peek() {
            //// ADD CODE HERE ////
            // if (cur == 0 || cur >= n*n) return null;
            return choices[(cur-1)/n][(cur-1)%n];
        }
        
        public Choice pop() {
            //// ADD CODE HERE ////
            Choice ret = peek();
            choices[(cur-1)/n][(cur-1)%n] = null;
            cur--;
            return ret;
        }
        
        public Iterator<Choice> iterator() {
            //// ADD CODE HERE ////
            return new Iterator<Choice>(){
                int idx = 0;

                @Override
                public boolean hasNext() {
                    // TODO Auto-generated method stub
                    return idx + 1 < n*n;
                }

                @Override
                public Choice next() {
                    // TODO Auto-generated method stub
                    Choice ret = choices[idx/n][idx%n];
                    idx++;
                    return ret;
                }
            };
        }
        
        public boolean mightHaveSolutionWith(Choice choice) {
            //// ADD CODE HERE ////
            int r = cur/n;
            int c = cur%n;

            // Boundary check
            if (choice.numChosen >= n){
                return false;
            }

            // Row check
            for (int i=0; i<c; i++){
                if (choices[r][i].numChosen == choice.numChosen){
                    return false;
                }
            }

            // Col checck
            for (int i=0; i<r; i++){
                if (choices[i][c].numChosen == choice.numChosen){
                    return false;
                }
            }

            return true;
        }
        
        
        public String toString() {
            //// ADD CODE HERE ////
            String str = "";
            for (Choice[] r : choices){
                for (Choice c : r){
                    if (Objects.isNull(c)){
                        str += ". ";
                    }
                    else{
                        str += c;
                    }
                }
                str += "\n";
            }
            return str;
        }
    }
    
    ////////////////////////
    // INSTANCE VARIABLES //
    ////////////////////////
    
    private static int n = 0;  
    private StackOfChoices stackOfChoicesMade;
    private Stack<String> stackOfSolutions;
    
    public LatinSquares(int size) {
        LatinSquares.n = size;
        stackOfChoicesMade = new StackOfChoices(n);
        stackOfSolutions = new Stack<String>();
    }
    
    public boolean solutionFound() {
        //// ADD CODE HERE //// 
        return stackOfChoicesMade.cur >= n*n;
    }
    
    public void solve() throws InterruptedException {
        
        Choice choice = Choice.firstChoice();   

        while (true) {

            if (stackOfChoicesMade.mightHaveSolutionWith(choice)) {
                stackOfChoicesMade.push(choice);
                // System.out.println("------\nCurrent situation:\n" + stackOfChoicesMade);
                choice = Choice.firstChoice();                
            } 
            else if (choice.unconsideredChoicesExist()) {
                choice = choice.nextChoice();
            }
            else {                                    
                if (stackOfChoicesMade.isEmpty())   // Here, you intend to pop to backtrack if you can.
                    break;                          // If you can't (because the stack is empty), you are done! 
                    
                choice = stackOfChoicesMade.pop().nextChoice();    // If the pop is safe, pick up where you 
                                                                   // left off with your last choice.
                                                                   // (i.e., choose the next choice after that one)
            }   
            
            if (solutionFound()) {    
                reactToSolutionFound();
                choice = stackOfChoicesMade.pop();    // normally we check to make sure -- but it's safe to pop.. why? 
                choice = choice.nextChoice();         // keep going to try to find other solutions 
            }            
        }
    }
    
    public void reactToSolutionFound() {
        //// ADD CODE HERE ////
        System.out.println(String.format("Solution number %d:", stackOfSolutions.size()));
        // System.out.println(stackOfChoicesMade);
        stackOfSolutions.push(stackOfChoicesMade.toString().substring(
            0, stackOfChoicesMade.toString().length()-1)
            );
    }
    
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Enter n: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        
        LatinSquares latinSquares = new LatinSquares(n);
        latinSquares.solve();
        
        System.out.println("\nSolutions:\n");
        for (String solution : latinSquares.stackOfSolutions) {
            System.out.println(solution + "\n------");
        }
        System.out.println(latinSquares.stackOfSolutions.size() + " solutions found!\n");
        
    }

}
