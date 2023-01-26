import java.util.Iterator;

public class StackArray<Item> implements Stack{
    /*
     * push
     * pop
     * peek
     * isEmpty
     * size
     */

    private int size;
    private Item[] items;


    // Constructor
    public StackArray(int maxSize){
        size = 0;
        items = (Item[]) (new Object[maxSize]);
    }

    public Item pop(){
        Item item = items[--size];
        items[size] = null;
        return item;
    }

    public void push(Item item){
        items[size++] = item;
    }

    public Item peek(){
        return items[size-1];
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    public String toString(){
        String s = "";
        for (Item item: items){
            s += item + " ";
        }
        return s;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>(){
            int nextPos = 0;

            @Override
            public boolean hasNext() {
                return nextPos < size;
            }

            @Override
            public Item next() {
                return items[nextPos++];
            }

        };
    }
}