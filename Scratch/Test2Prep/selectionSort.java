package Scratch.Test2Prep;

public class selectionSort {
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

    public static void sort(int[] a){
        for (int i=0; i<a.length; i++){
            int min = a[i], midx = i;
            for (int j=i; j<a.length; j++){
                if (a[j] < min){
                    min = a[j];
                    midx = j;
                }
            }
            int tmp = a[i];
            a[i] = a[midx];
            a[midx] = tmp;
            // printArr(a);
            // System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = randomize(10, 10);
        printArr(arr);
        sort(arr);
        printArr(arr);
    }
}
