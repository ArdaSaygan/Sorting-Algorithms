

/**
 * Sorting
 */
public class Sorting {


public static int[] Insertion(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
        for (int j = i; 0<j; j--) {
            if (arr[j] < arr[j-1]){
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
            }
            else {break;}
        }
    }
    return arr;
}

public static int[] Selection(int[] arr){
    for (int i = 0; i < arr.length - 1; i++) {
        int k = i; 
        for (int j = i; j < arr.length; j++) {
            if (arr[j] < arr[k]){
                k = j;
            }
        }
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }
    return arr;
}

public static void Quick(int[] arr, int start, int end){ //end should be the last elements index
    if (start >= end) {
        return;
    }

    int p = end;
    int i = start;
    for (int j = start; j < end; j++) {
        if (arr[p] > arr[j]){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
        }
    }

    int temp = arr[i];
    arr[i] = arr[p];
    arr[p] = temp;
   
    Quick(arr, start, i-1);
    Quick(arr, i+1, end);
}
    
}