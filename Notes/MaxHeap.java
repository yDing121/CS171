public class MaxHeap<Item extends Comparable<Item>> {
    private Item[] items;
    private int size;

    public MaxHeap(int capacity){
        items = (Item[]) (new Comparable[capacity+1]);
        size = 0;
    }

    private boolean less(int v, int w){
        return (items[v].compareTo(items[w]) < 0);
    }

    private void exch(int i, int j){
        Item tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    public Item removeMaxItem(){
        Item maxItem = items[1];
        items[1] = items[size];
        items[size] = null;
        size--;

        sink(1);
        return maxItem;
    }

    private void rsink(int i){
        if (2*i <= size){
            int j = 2*i; // left child
            if ((j < size) && less(j, j+1)) j++; // make j right child if right child is larger
            // if (j<size) because you have to access j+1
            if (less(i, j)){
                exch(i, j);
                rsink(j);
            }
        }
    }

    private void sink(int k){
        while (2*k <= size){
            int j = 2*k;
            if ((j < size) && less(j, j+1)) j++; // make j right child if right child is larger

            if (less(k, j)) exch(k,j);
            else break;
            k = j;
        }
    }

    public void insert(Item item){
        items[++size] = item;
        swim(size);
    }

    private void swim(int i){
        while (i > 0 && less(i/2, i)){
            exch(i/2, i);
            i /= 2;
        }
    }

    public String toString(){
        String s = "";
        for (int i = 1; i <= size; i++){
            s += items[i] + " ";
        }
        return s;
    }



    public static void main(String[] args) {
        
    }
}
