public class SetRunner {
    public static void main(String[] args) {
        Set<Integer> s1 = new Set<Integer>();
        Set<Integer> s2 = new Set<Integer>();

        // for (int i=5; i> 0; i--){
        //     s1.add(i);
        // }

        // for (int i=10; i>0; i--){
        //     s2.add(i);
        // }

        int[] i1 = {10, 8, 7, 4, 2, -1};
        int[] i2 = {25, 17, 12, 10, 8, 5, 0, -1, -2};

        for (int i: i1){
            s1.add(i);
        }

        for (int i: i2){
            s2.add(i);
        }

        System.out.println("s1: " + s1);
        System.out.println("s2: " + s2);

        System.out.println(s1.union(s2));
        System.out.println(s1.intersect(s2));
        System.out.println(s1.difference(s2));
        // System.out.println(s1);
        
    }
}
