package org.algo.hashmap;

/**
 * This class is to test out the HashMap by providing a really
 * simple hashCode algorithm.
 */
public class CustomInt {

    private int intValue = 0;

    public CustomInt(int val) {
        this.intValue = val;
    }

    public int getValue() {
        return intValue;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof CustomInt && ((CustomInt)other).getValue() == this.intValue;
    }

    @Override
    public int hashCode() {
        return intValue%100;
    }


}