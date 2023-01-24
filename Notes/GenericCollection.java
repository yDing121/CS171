public class GenericCollection<Item extends Comparable<Item>>{
    // ... extends Comparable<Item> to ensure that all items stored within
    // can be compared and therefore can be sorted

    Item item;
    Item[] items;
    String[] strings;

    public GenericCollection(Item item, Item anotherItem){
        this.item = item;

        strings = new String[5];
        items = (Item[]) new Comparable[5];
        
        System.out.println(item.compareTo(anotherItem));
    }
}