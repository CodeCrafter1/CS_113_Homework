package edu.miracosta.cs113.HashTable;

import java.util.*;

public class HashTableChain<K, V> implements KWHashMap<K, V> {

    private LinkedList<Entry<K, V>>[] table;
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;

    private static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;

        /**
         * Creates a new key-value pair.
         *
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Retrieves the key.
         *
         * @returnThe key
         */
        public K getKey() {
            return key;
        }

        /**
         * Retrieves the value.
         *
         * @returnThe value
         */
        public V getValue() {
            return value;
        }

        /**
         * Sets the value.
         *
         * @param val The new value
         * @return The old value
         */
        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        public EntrySet() {

        }

        @Override
        public int size() {
            return numKeys;
        }

        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new SetIterator();
        }
    }

    private class SetIterator implements Iterator<Map.Entry<K, V>> {

        private Map.Entry<K, V> nextItem;//current item
        private Map.Entry<K, V> lastItemReturned;
        private int index;

        public SetIterator() {
            index = 0;
            lastItemReturned = null;

        }

        public void next() {

        }

        public void hasNext() {

        }

        public void remove() {

        }
    }

    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    public int size() {
        return table.length;
    }

    public boolean isEmpty() {
        return numKeys == 0;
    }

    public V remove(Object obj) {
        int index = obj.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; //key is not in the table
        }
        Entry<K, V> e = null, f = null;
        //Search the list at table[index] to find the key.
        for (int j = 0; j < table[index].size(); j++) {
            e = table[index].get(j);
            if (e.equals(obj)) {
                f = table[index].remove(j);
                break;
            }
        }
        return f.value;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HashTableChain h = new HashTableChain<String, Integer>();
    }

    /**
     * Method get for class HashtableChain.
     *
     * @param key The key being sought
     * @returnThe value associated with this key if found; otherwise, null
     */
    //@Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; //key is not in the table
        }
        //Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.key.equals(key)) {
                return nextItem.value;
            }
        }
        //assert: key is not in the tab1e.
        return null;
    }

    //@Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            //Createanewlinkedlistattable
            table[index] = new LinkedList<Entry<K, V>>();
        }
        //Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {
            //If the search is successful, replace the old value.
            if (nextItem.key.equals(key)) {
                //Replace value for this key.
                V oldVal = nextItem.value;
                nextItem.setValue(value);
                return oldVal;
            }
        }
        //assert: key is not in the table, add new item.
        table[index].addFirst(new Entry<K, V>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)) {
            rehash();
        }
        return null;
    }

    //
    private void rehash() {
        //Save a reference to old Table.
        LinkedList<Entry<K, V>>[] oldTable = table;
        //Double capacity of this table.
        table = new LinkedList[2 * CAPACITY + 1];
        //Reinsert all items  in oldTable into expanded table
        numKeys = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                for (int j = 0; j < oldTable[i].size(); j++) {
                    Entry<K, V> e = oldTable[i].get(j);
                    if (e != null) {
                        put(e.key, e.value);
                    }
                }
            }
        }
    }

}
