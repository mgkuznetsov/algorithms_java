package org.algo.sorts;

import java.util.Arrays;

public class Mergesort 
{
    public int[] sort( int[] arr )
    {
        if(arr.length <= 1) {
            return arr;
        }

        int halfLength = arr.length/2;

        // Recourse on the right and left sub-arrays
        int[] left = sort(Arrays.copyOfRange(arr, 0, halfLength));
        int[] right = sort(Arrays.copyOfRange(arr, halfLength, arr.length));

        return merge(left, right); // Merge the arrays
    }

    public int[] merge(int[] left, int[] right) {
        int[] combined = new int[left.length + right.length];

        //Iterate through the arrays
        int lPointer = 0; // Index of left array
        int rPointer = 0; // Index of right array
        int cPointer = 0; // Index of combination array

        //Compare both arrays.
        while(lPointer < left.length && rPointer < right.length) {
            if(left[lPointer] <= right[rPointer]) {
                combined[cPointer] = left[lPointer];
                cPointer++;
                lPointer++;
            } else {
                combined[cPointer] = right[rPointer];
                cPointer++;
                rPointer++;
            }
        }

        //Combine the leftovers
        while(lPointer < left.length) {
            combined[cPointer] = left[lPointer];
            cPointer++;
            lPointer++;
        }

        while(rPointer < right.length) {
            combined[cPointer] = right[rPointer];
            cPointer++;
            rPointer++;
        }

        return combined;
    }
}