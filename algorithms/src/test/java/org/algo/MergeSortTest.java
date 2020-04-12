package org.algo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;

public class MergeSortTest 
{

    @Test
    public void testMergeSortEvenNumElements()
    {
        final Mergesort mergeSort = new Mergesort();

        final int[] arr1 = { 4, 3, 2, 1 };
        final int[] sortedArr1 = mergeSort.sort(arr1);
        final int[] expectedArr1 = { 1, 2, 3, 4 };
        assertTrue(Arrays.equals(sortedArr1, expectedArr1));
    }

    @Test
    public void testMergeSortOddNumElements()
    {
        final Mergesort mergeSort = new Mergesort();

        final int[] arr1 = { 5, 4, 3, 2, 1 };
        final int[] sortedArr1 = mergeSort.sort(arr1);
        final int[] expectedArr1 = { 1, 2, 3, 4, 5 };
        assertTrue(Arrays.equals(sortedArr1, expectedArr1));
    }
}
