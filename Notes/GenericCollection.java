public class GenericCollection<Item>{
    Item item;
    Item[] items;
    String[] strings;

    public GenericCollection(Item item){
        this.item = item;

        strings = new String[5];
        items = (Item[]) new Object[5];
        
    }
}