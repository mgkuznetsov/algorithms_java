package org.algo.sorts;

public class Quicksort {

    public void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    public void sort(int[] arr, int startIndex, int endIndex) {

        // Base case
        if(endIndex-startIndex==0 || startIndex > endIndex) {
            return;
        }

        //Choose pivot and pre-process:
        placePivot(arr, startIndex, endIndex);
        int pivotIndex = startIndex;
        int pivotValue = arr[pivotIndex];

        //Partition:
        //j points to the first element that is > pivot

        for(int j=startIndex; j<=endIndex; j++) {
            if(arr[j] < pivotValue) {
                // Swap so that all elements before and including j are > pivot
                // Element after pivotIndex will now be less than pivot
                swap(arr, pivotIndex+1, j); 

                // Swap pivot and element after pivot.
                // Now all elements before pivot are < pivot
                swap(arr,pivotIndex+1,pivotIndex); 
                pivotIndex++;
            }
        }

        //Recourse:
        sort(arr, startIndex, pivotIndex-1); // Sort everything less than pivot
        sort(arr, pivotIndex+1, endIndex);
    }

    public void placePivot(int[] arr, int startIndex, int endIndex) {
        int pivotIndex = getPivotIndex(startIndex, endIndex);
        swap(arr, startIndex, pivotIndex); //Swap pivot with the first element of array
    }

    public int getPivotIndex(int startIndex, int endIndex) {
        //return startIndex; //For now return the first element of the array
        return startIndex + (int) (Math.random() * (endIndex - startIndex + 1));
    }

    public void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

}