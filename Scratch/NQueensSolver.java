package Scratch;
import java.util.Stack;

// DO NOT ADD, CHANGE, OR REMOVE CODE ANYWHERE EXCEPT THOSE PLACES SAYING "ADD CODE HERE"

public class NQueensSolver {
    
    ///////////////////
    // INNER CLASSES //
    ///////////////////
    
    // The private "Choice" inner class provides an abstraction for the "choices" made
    // in NQueensSolver, a choice is a column in which a queen is to be placed in a given row.
    // The order in which these choices are made begins with the top-most row and proceeds to the bottom
    
    public static class Choice {
        private int col;
        
        private Choice(int columnChosen) {       // note, the column chosen should be numbered 0-7
            this.col = columnChosen;
        }
        
        public static Choice firstChoice() {     // returns what choice should be tested first
            return new Choice(0);
        }
        
        public Choice nextChoice() {       
            col++;      // returns what we should try next if "this" choice doesn't work out
            return this;
        }
        
        public boolean unconsideredChoicesExist() {   // true if and only if there are unconsidered choices for this row
            return this.col < 8;
        }
        
        protected Choice clone() {               // makes a copy of "this" choice 
            return new Choice(Integer.valueOf(this.col));          // note, the copy should not include any references 
        }                                        // to the original Choice object or any of its parts
        
        public String toString() {               // show the choice (e.g., if choice was column 3
            String str = "";
            for (int i=0; i<this.col; i++){
                str += "* ";
            }           // the string should be "* * * Q * * * *" with 
            str += "Q ";
            for (int i=this.col+1; i<8; i++){
                str += "* ";
            }
            return str + "\n";
        }                                        // the system line separator at the end
        
    }
    
    // the inner class StackOfChoices should keep a stack of Choice objects, 
    // but have the two additional methods described below
    
    public static class StackOfChoices extends Stack<Choice> {
        
        public boolean mightHaveSolutionWith(Choice choice) {   // return false only if using this choice
            for (int i=0; i<this.size(); i++){
                // Vertical check
                if ((this.get(i).col)==(choice.col)){
                    return false;
                }

                // y=-x check
                if (this.size() - i == choice.col - this.get(i).col){
                    return false;
                }

                // y=x check
                if (this.size() - i == this.get(i).col - choice.col){
                    return false;
                }

                // Index check
                if (choice.col >= 8){
                    return false;
                }
            }
            return true;                          // with the others in "this" stack of choices
        }                                                       // creates a problem
        
        public String toString() {    
            String str = "";                          // creates a string, that when printed
            for (Choice i : this){
                str += i;
            }
            return str;
            ///// ADD CODE HERE //////                          // draws the board with the current choices made
        }
    }
    
    ////////////////////////
    // INSTANCE VARIABLES //
    ////////////////////////
    
    private static int n = 0;  
    private StackOfChoices stackOfChoicesMade;
    
    //////////////////
    // CONSTRUCTORS //
    //////////////////
    
    public NQueensSolver(int size) {
        NQueensSolver.n = size;
	    stackOfChoicesMade = new StackOfChoices();
    }
    
    //////////////////////
    // INSTANCE METHODS //
    //////////////////////
    
    public boolean solutionFound() {
        return stackOfChoicesMade.size() >= 8;
        ///// ADD CODE HERE //////                             // true if the existing stackOfChoices represents
    }                                                          // a solution
    
    public int solve() throws InterruptedException {
        
        int numSolutionsFound = 0;
        Choice choice = Choice.firstChoice();   

        while (true) {
            
            if (stackOfChoicesMade.mightHaveSolutionWith(choice)) {
                stackOfChoicesMade.push(choice);
                // System.out.println("----------\nCurrent choices:\n" + stackOfChoicesMade);
                choice = Choice.firstChoice();                
            } 
            else if (choice.unconsideredChoicesExist()) {
                choice = choice.nextChoice();
            }
            else {                                    
                if (stackOfChoicesMade.isEmpty() || !stackOfChoicesMade.get(0).unconsideredChoicesExist())   // Here, you intend to pop to backtrack if you can.
                    break;                          // If you can't (because the stack is empty), you are done! 
                    
                choice = stackOfChoicesMade.pop().nextChoice();    // If the pop is safe, pick up where you 
                                                                   // left off with your last choice.
                                                                   // (i.e., choose the next choice after that one)
            }   
            
            if (solutionFound()) {    
                reactToSolutionFound();
                numSolutionsFound++;
                choice = stackOfChoicesMade.pop();    // normally we check to make sure -- but it's safe to pop.. why? 
                choice = choice.nextChoice();         // keep going to try to find other solutions 
            }            
        }

        return numSolutionsFound;
    }
    
    public void reactToSolutionFound() {
        System.out.println(stackOfChoicesMade);
        ///// ADD CODE HERE //////                    // print the solution
    }
    
    ///////////////////////////////////
    // TESTING VIA THE main() METHOD //
    ///////////////////////////////////
    
    public static void main(String[] args) throws InterruptedException {
        NQueensSolver nQueensSolver = new NQueensSolver(8);
        int numSolutions = nQueensSolver.solve();
        System.out.println(numSolutions + " solutions were found!");
    }

}
