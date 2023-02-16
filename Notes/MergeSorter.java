public class MergeSorter<Item extends Comparable<Item>> {
    Item[] a;
    Item[] aux;

    private boolean less(Item v, Item w){
        return (v.compareTo(w) < 0);
    }

    public void sort(Item[] a){
        aux = (Item[]) (new Object[a.length]);
        mergeSort(0, a.length-1);
    }

    private void mergeSort(int lo, int hi){

    }

    public static void main(String[] args) {
        
    }
}
