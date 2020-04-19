package org.algo.hashmap;

import static org.junit.Assert.*;

import org.junit.Test;

public class HashMapTest {

    @Test
    public void testHashMap() {

        HashMapInterface<CustomInt, String> hm = new HashMapSeparateChaining<>();

        // Test inserting into HashMap:

        // Will have the same HashCode

        //Keys:
        CustomInt k101 = new CustomInt(101);
        CustomInt k201 = new CustomInt(201);
        CustomInt k301 = new CustomInt(301);
        CustomInt k401 = new CustomInt(401);
        CustomInt k501 = new CustomInt(501);
        CustomInt k601 = new CustomInt(601);


        //Values:
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        String v5 = "v5";
        String v6 = "v6";
        String v7 = "v7";
        String v8 = "v8";

        // Test insert into map - output of put method returns null
        assertNull(hm.put(k101, v1));
        assertNull(hm.put(k201, v2));
        assertNull(hm.put(k301, v3));
        assertNull(hm.put(k401, v4));

        // Test get value
        assertEquals(hm.get(k101), v1);
        assertEquals(hm.get(k201), v2);
        assertEquals(hm.get(k301), v3);
        assertEquals(hm.get(k401), v4);
        assertNull(hm.get(k501)); // Element not present in map

        // Test update value
        assertEquals(hm.put(k101, v5), v1);
        assertEquals(hm.put(k201, v6), v2);
        assertEquals(hm.put(k301, v7), v3);
        assertEquals(hm.put(k401, v8), v4);

        // Test get value
        assertEquals(hm.get(k101), v5);
        assertEquals(hm.get(k201), v6);
        assertEquals(hm.get(k301), v7);
        assertEquals(hm.get(k401), v8);

        // Test delete value:
        assertNull(hm.put(k501, v3)); // Add a few more elements
        assertNull(hm.put(k601, v4));

        assertEquals(hm.remove(k401), v8); // Remove from the middle element in bucket
        assertEquals(hm.remove(k501), v3); // Remove 2nd to last element in bucket
        assertEquals(hm.remove(k601), v4); // Remove last element in bucket
        assertEquals(hm.remove(k101), v5); // Remove first element in bucket
        assertEquals(hm.remove(k201), v6);
        assertEquals(hm.remove(k301), v7); // Remove last


        // Verify that elements have been removed
        assertNull(hm.get(k101));
        assertNull(hm.get(k201));
        assertNull(hm.get(k301));
        assertNull(hm.get(k401));
        assertNull(hm.get(k501));
        assertNull(hm.get(k601));
    }

    /**
     * Test that hashmap gets automatically resized when it's over 0.75 full
     */
    @Test
    public void testMapResize() {

        HashMapSeparateChaining<CustomInt, String> hm = new HashMapSeparateChaining<>();

        // Add elements so hash map is 0.75 full
        for(int i=1; i<13; i++) {
            hm.put(new CustomInt(i), "abc");
        }

        assertEquals(hm.getNumBuckets(), 16); // Check that hashmap has not yet been resized

        hm.put(new CustomInt(13), "abc");

        assertEquals(hm.getNumBuckets(), 32); // Check that hashmap has not been resized

    }

}