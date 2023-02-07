import java.util.Arrays;

public class FailFastCollection {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6};
        FailFastCollection f = new FailFastCollection(a);
        System.out.println(f);
    }

    int[] contents;
    public FailFastCollection(int[] array){
        contents = Arrays.copyOf(array, array.length);
    }

    public String toString() {
        return null;
    }
}
