package org.algo.sorts;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;

public class QuickSortTest 
{

    @Test
    public void testQuickSortEvenNumElementsReverseOrder()
    {
        final Quicksort quickSort = new Quicksort();

        final int[] arr1 = { 4, 3, 2, 1 };
        quickSort.sort(arr1);
        final int[] expectedArr1 = { 1, 2, 3, 4 };
        assertTrue(Arrays.equals(arr1, expectedArr1));
    }

    @Test
    public void testQuickSortOddNumElementsReverseOrder()
    {
        final Quicksort quickSort = new Quicksort();

        final int[] arr1 = { 5, 4, 3, 2, 1 };
        quickSort.sort(arr1);
        final int[] expectedArr1 = { 1, 2, 3, 4, 5 };
        assertTrue(Arrays.equals(arr1, expectedArr1));
    }

    @Test
    public void testQuickSortOrdered()
    {
        final Quicksort quickSort = new Quicksort();

        final int[] arr1 = { 1, 2, 3, 4, 5 };
        quickSort.sort(arr1);
        final int[] expectedArr1 = { 1, 2, 3, 4, 5 };
        assertTrue(Arrays.equals(arr1, expectedArr1));
    }


    @Test
    public void testQuickSortJumbledArray()
    {
        final Quicksort quickSort = new Quicksort();

        final int[] arr1 = { 3, 8, 2, 2, 5, 1, 4, 4, 7, 1 };
        quickSort.sort(arr1);
        final int[] expectedArr1 = { 1, 1, 2, 2, 3, 4, 4, 5, 7, 8 };
        assertTrue(Arrays.equals(arr1, expectedArr1));
    }


}
