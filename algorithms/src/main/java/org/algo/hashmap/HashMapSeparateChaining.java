package org.algo.hashmap;


// For now build a simple HashMap just for Strings
@SuppressWarnings({"rawtypes","unchecked"})
public class HashMapSeparateChaining<K, V> implements HashMapInterface<K, V>
{
    public static final int INNITIAL_BUCKET_SIZE = 16;
    public static final float LOAD_FACTOR = 0.75f;
    private int numBucketsUsed = 0;

    //NOTE: This is what we have to do for generics
    Node<K, V>[] buckets = (Node<K,V>[])new Node[INNITIAL_BUCKET_SIZE];

    public V get(Object key) {

        int bucketIndex = getBucketIndex(key);

        Node<K, V> node = buckets[bucketIndex];
        if(node == null) {
            return null; // Nothing in the bucket so key not present
        }

        // Go through all the node in the bucket and search for the key
        do {
            if(node.key.equals(key)) {
                return node.value;
            }

            node = node.next;
        } while(node.hasNext());



        return null; // Not found in any of the nodes in bucket
    }

    //Returns previous value
    public V put(K key, V value) {
        int bucketIndex = getBucketIndex(key);

        Node<K, V> node = buckets[bucketIndex];

        // If bucket is empty, insert node int obucket
        if (node == null) {
            buckets[bucketIndex] = new Node<K, V>(key, value, null);
            numBucketsUsed++; //If a new bucket is used, update number
            rehash();
            return null;
        }

        // Go through all the node in the bucket and search for the key
        // If key is found, then replace node with new value
        do {
            if(node.getKey().equals(key)) {
                V oldVal = node.getValue();
                node = new Node<K, V>(key, value, node.getNext());
                return oldVal;
            }

            node = node.next;
        } while(node.hasNext());

        // If we are here, it means that there is no element with key in bucket
        // We insert at the end of the last node in the bucket
        node.setNext(new Node<K, V>(key, value, null));
        rehash();
        return null;
    }

    public V remove(Object key) {
        int bucketIndex = getBucketIndex(key);

        Node<K, V> node = buckets[bucketIndex];

        // If bucket is empty, return null
        if (node == null) {
            return null;
        }

        // If the first node in the bucket has the value, then remove the node
        if(node.key.equals(key)) {
            V value = node.getValue();
            node = node.getNext();
            rehash();
            return value;
        }



        // Go through all the node in the bucket and search for the key
        // If key is found, then remove the node
        do {
            if(node.getNext().getKey().equals(key)) {
                V value = node.getValue();
                node.setNext(node.getNext());
                return value;
            }

            node = node.next;
        } while(node.hasNext());

        // If we are here, it means that there is no element with key in bucket
        // We return null since we didn't find the element
        return null;
    }

    private int getBucketIndex(Object key) {
        return key.hashCode() % buckets.length;
    }

    private void rehash() {
        if(numBucketsUsed/buckets.length > LOAD_FACTOR) {
            Node<K, V>[] originalBuckets = this.buckets; // Make temporary copy of current buckets

            //Create a new array of buckets with twice the size
            this.buckets = (Node<K,V>[])new Node[originalBuckets.length*2];
            numBucketsUsed = 0; //Reset value

            for(Node<K, V> n : originalBuckets) {
                put(n.key, n.value); // Insert item into new buckets
            }
        }


    }

    // Class for nodes of the linked-list in each bucket
    static class Node<K, V> {

        K key;
        V value;
        Node<K, V> next;
    
        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    
        public K getKey() {
            return key;
        }
    
        public V getValue() {
            return value;
        }
    
        public Node<K, V> getNext() {
            return next;
        }
    
        public boolean hasNext() {
            return next != null;
        }
    
        public void setNext(Node<K, V> next) {
            this.next = next;
        }
    }

}