public interface Stack<Item> extends Iterable<Item> {
    
    boolean isEmpty();           // returns true if nothing in the stack, false otherwise
    int size();                  // returns the number of elements in the stack
    void push(Item item);        // pushes the given item onto the top of the stack
    Item peek();                 // returns the top element of the stack without removing it
    Item pop();                  // pops the top-most item from the stack, returning it
}
