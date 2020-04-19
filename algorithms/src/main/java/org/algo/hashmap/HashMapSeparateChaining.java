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

    public int getNumBuckets() {
        return buckets.length;
    }

    public V get(Object key) {

        int bucketIndex = getBucketIndex(key);

        Node<K, V> node = buckets[bucketIndex];
        if(node == null) {
            return null; // Nothing in the bucket so key not present
        }

        // Go through all the node in the bucket and search for the key
        while(node != null) {
            if(node.key.equals(key)) {
                return node.value;
            }

            node = node.next;
        }

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
            rehash(true);
            return null;
        }

        // Go through all the node in the bucket and search for the key
        // If key is found, then replace node with new value
        Node<K, V> prevNode = null; //Pointer to previous node
        while(node != null) {
            if(node.getKey().equals(key)) {
                V oldVal = node.getValue();
                node.setValue(value);
                return oldVal;
            }

            // Move to next node
            prevNode = node;
            node = node.getNext();
            
        }

        // If we are here, it means that there is no element with key in bucket
        // We insert at the end of the last node in the bucket
        // prevNode is last node
        prevNode.setNext(new Node<K, V>(key, value, null));
        return null;
    }

    public V remove(Object key) {
        int bucketIndex = getBucketIndex(key);

        Node<K, V> node = buckets[bucketIndex];

        // Go through all the node in the bucket and search for the key
        // If key is found, then remove the node
        Node<K, V> prevNode = null; //Pointer to previous node

        while(node != null) {
            if(node.getKey().equals(key)) {
                V value = node.getValue();
                
                if(!node.hasNext()) {
                    if(prevNode == null) {
                        buckets[bucketIndex] = null;
                        numBucketsUsed = 0;
                        rehash(false); // If it's the only node in the bucket, then we should resize bucket
                    } else {
                        prevNode.setNext(null); // If it's the last node
                    }
                } else {
                    if(prevNode == null) {
                        buckets[bucketIndex] = node.getNext(); // It's the first node
                    } else {
                       prevNode.setNext(node.getNext()); // Node is in the middle 
                    }
                }

                return value;
            }

            // Move to the next node
            prevNode = node;
            node = node.next;
        }

        // If we are here, it means that there is no element with key in bucket
        // We return null since we didn't find the element
        return null;
    }

    private int getBucketIndex(Object key) {
        return key.hashCode() % buckets.length;
    }

    // increaseSize = true when we're adding to the map
    // increaseSize = false when we're removing from the map
    private void rehash(boolean increaseSize) {
        float bucketLoad = (float)numBucketsUsed/buckets.length;
        Node<K, V>[] originalBuckets = buckets;
        if(numBucketsUsed > 0 && (bucketLoad > LOAD_FACTOR) && increaseSize) {
            this.buckets = (Node<K,V>[])new Node[originalBuckets.length*2]; //Create a new array of buckets with twice the size
        } else if (numBucketsUsed > 0 && bucketLoad < LOAD_FACTOR && !increaseSize && this.buckets.length > INNITIAL_BUCKET_SIZE) {
            this.buckets = (Node<K,V>[])new Node[originalBuckets.length/2]; //Create a new array of buckets with twice the size
        } else {
            return; //No need to rehash
        }
            
        // Rehash
        numBucketsUsed = 0; //Reset value

        for(Node<K, V> n : originalBuckets) {
            // Insert item into new buckets
            while(n!=null) {
                put(n.key, n.value); 
                n = n.next;
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

        public void setValue(V value) {
            this.value = value;
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