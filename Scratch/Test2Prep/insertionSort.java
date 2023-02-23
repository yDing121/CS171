package Scratch.Test2Prep;

public class insertionSort {
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
        for (int i=0; i<a.length-1; i++){
            int j = i+1;
            int num = a[j];
            while (j > 0 && a[j-1] > num){
                a[j] = a[--j];
            }
            a[j] = num;
        }
    }

    public static void main(String[] args) {
        int[] arr = randomize(10, 10);
        printArr(arr);
        sort(arr);
        printArr(arr);
    }
}
