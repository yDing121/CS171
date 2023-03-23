package Scratch.Test3Prep;

public class Quicksort {
    public static int[] randomize(int n, int max){
        int[] ret = new int[n];
        for (int i=0; i<n; i++){
            ret[i] = (int)(max*Math.random());
        }
        return ret;
    }

    public static void printArr(int[] a){
        for (int i: a){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // public void sort(int[] arr){
    //     sort(arr, 0, a.length - 1);
    // }

    public static void sort(int[] arr, int lo, int hi){
        if (lo < hi){
            int mid = mpartition(arr, lo, hi);
            sort(arr, lo, mid);
            sort(arr, mid+1, hi);
        }
    }

    private static void swap(int[] arr, int a, int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    public static int mpartition(int[] arr, int lo, int hi){
        int left = lo-1, right = hi+1;
        int pivot = arr[lo];

        while (true) {
            while (arr[++left] < pivot){
                if (left == hi) break;
            }
            
            while (arr[--right] > pivot){
                if (right == lo) break;
            }
            
            if (left >= right) break;

            swap(arr, left, right);
        }

        swap(arr, lo, right);

        return right;
    }

    public static int partition(int[] arr, int low, int high)
    {
        int pivot = arr[low];
        int i = low - 1, j = high + 1;
 
        while (true) {
            // Find leftmost element greater
            // than or equal to pivot
            do {
                i++;
            } while (arr[i] < pivot);
 
            // Find rightmost element smaller
            // than or equal to pivot
            do {
                j--;
            } while (arr[j] > pivot);
 
            // If two pointers met.
            if (i >= j)
                return j;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            // swap(arr[i], arr[j]);
        }
    }

    public static void main(String[] args) {
        int[] arr = randomize(10, 10);
        printArr(arr);

        for (int i=0; i<100; i++){
            System.out.println("----");
            arr = randomize(10, 10);
            printArr(arr);

            sort(arr, 0, arr.length-1);
            printArr(arr);
        }
    }
}
