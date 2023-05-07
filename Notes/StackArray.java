/* StackArray<Item> (array-based, generic)
 * 
 * Methods
 * =======
 * boolean isEmpty()         : returns true if nothing in the stack, false otherwise
 * int size()                : returns the number of items in the stack
 * void push(Item item)      : pushes an item onto the stack
 * Item peek()               : returns the top element of the stack without removing it
 * Item pop()                : pops the top element off the stack and returns it
 * Iterator<Item> iterator() : returns a top-to-bottom "fail-fast" iterator 
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class StackArray<Item> implements Iterable<Item>, Stack<Item>{  
                                                            
    private Item[] items;
    private int size;
    private int modCount;  // for "fail fast" iterator
    
    public StackArray() {
        // we would like to do the following, but generic array creation is
        // not allowed in Java, sniff..  :o(
        //items = new Item[];  

        // so we settle for something like this and put up with the 
        // warning that results...
        items = (Item[]) (new Object[1]); 

        // the following are superfluous, of course..
        size = 0; 
        modCount = 0;
    }
    
    public boolean isEmpty() {
        return (size == 0);
    }
    
    public int size() {  // <-- this is a cheap, but useful method to add
        return size;
    }
    
    private void resize(int capacity) {
        
        // it's ugly - but remember we can't create generic arrays in Java
        Item[] newArray = (Item[]) (new Object[capacity]);  
        
        // now we copy items over to the new, appropriately-sized array..
        for (int i = 0; i < size; i++) {   
            newArray[i] = items[i];
        }
        
        // and update items to reference the new array...
        items = newArray;
    }
    
    public void push(Item item) {
        // when there is no room in the array, build a bigger array!
        if (size == items.length) {       
            resize( 2 * items.length );  // why double the size?
                                         // read about "resizing arrays" 
        }
        
        items[size++] = item;
        modCount++;  // a structural modification happened (an element was added)
    }

    public Item peek() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return items[size-1];
        }
    }
    
    public Item pop() {
        // rather than throw an exception when there is nothing to pop, 
        // let's return null and let the client decide what to do...
        if (this.isEmpty()) {  
            return null;       
        }
       
        Item item = items[--size];
       
       // we'll want to prevent "loitering" (where garbage collection can't 
       // reclaim the memory of items[size], but we know it will never be 
       // accessed again), so...
       items[size] = null;   
       
       // when the array is "too sparse" and we are tying up memory
       // that we probably won't use, we switch to a smaller array..
       if (size != 0 && size == items.length/4) {     // why 1/4 the size?
           resize(items.length/2);                    // read about "thrashing" 
       }
       
       modCount++;  // a structural modification happened (an element was removed)
       return item;
    }
    
    public Iterator<Item> iterator() {
        // remember anonymous inner classes?  we use one below...
        return new Iterator<Item>() {
            private int nextPos = 0;  // start at bottom and go up
            private int savedModCount = modCount;  // needed to make this iterator "fail fast"
           
            public boolean hasNext() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return nextPos < size;
            }

            public Item next() {
                if (modCount != savedModCount) throw new ConcurrentModificationException();
                return items[nextPos++];
            }
        };
    }

    public String toString() {
        String s = "";
        for (Item item : this) {
            s += item.toString() + " ";
        }
        return s;
    }
}

