public interface Stack<Item> extends Iterable<Item>{
    boolean isEmpty();

    int size();

    void push(Item item);

    Item pop();

    Item peek();
}
