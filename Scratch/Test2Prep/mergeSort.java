package Scratch.Test2Prep;

public class mergeSort {
    static int[] aux;
    static int[] arr;

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

    public static void sort(int lo, int hi){
        if (hi <= lo) return;
        int mid = (hi + lo)/2;
        sort(lo, mid);
        sort(mid+1,hi);
        merge(lo, mid, hi);
    }

    public static void merge(int lo, int mid, int hi){
        for (int i=lo; i<=hi; i++){
            aux[i] = arr[i];
        }

        int left = lo;
        int right = mid + 1;
        for (int i=lo; i<=hi; i++){
            if (left > mid){
                arr[i] = aux[right++];
            }
            else if (right > hi){
                arr[i] = aux[left++];
            }
            else if (aux[left] < aux[right]){
                arr[i] = aux[left++];
            }
            else{
                arr[i] = aux[right++];
            }
        }
    }

    public static void main(String[] args) {
        arr = randomize(10, 10);
        aux = new int[10];
        printArr(arr);
        sort(0, arr.length - 1);
        printArr(arr);
    }
}
