package org.algo.hashmap;

public interface HashMapInterface<K, V> {

    public V get(Object key);

    public V put(K key, V value);

    public V remove(Object key);



}