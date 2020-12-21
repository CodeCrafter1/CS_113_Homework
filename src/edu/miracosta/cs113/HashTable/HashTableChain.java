package edu.miracosta.cs113.HashTable;

import java.util.*;

/**
 *
 * @author
 */
public class HashTableChain<K, V> implements Map<K, V> {

    private LinkedList<Entry<K, V>>[] table; //Make an array of lists with Key values
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;  //Check how many number of keys we have

    private static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;

        /**
         * Creates a new key-value pair.
         *
         * @param key The key
         * @param value The value
         */
        public Entry(K key, V value) {  //constructor one entry in a list ..in an array of lists
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

        @Override
        public String toString() {
            return key.toString() + "=" + value.toString();
        }

    }

    public Set<Map.Entry<K, V>> entrySet() {  
        return new EntrySet();
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> { //Make a set of KVs

        /**
         * Return the size of the set.
         */
        @Override
        public int size() {
            return numKeys;
        }

        /**
         * Return an iterator over the set.
         */
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
        	Iterator<Map.Entry<K, V>> i = new HashTableChain.SetIterator(); //Use to go through the set of data access directly
            return i;
        }
        
    }

    private class SetIterator implements Iterator<Map.Entry<K, V>> {

        int i = 0;
        Iterator<Entry<K, V>> lIterator = null;

        @Override
        public boolean hasNext() {  //Is the next item a null
            if (lIterator != null) {
                if (lIterator.hasNext()) {
                    return true;
                } else {
                    lIterator = null;
                    i++;
                }
            }
            while (i < table.length
                    && table[i] == null) {
                i++;
            }
            if (i == table.length) {
                return false;
            }
            lIterator = table[i].iterator();  //Keeping track of where we are on the table
            return lIterator.hasNext();      //Returns whether or not we have a next item
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return lIterator.next();
        }

        @Override
        public void remove() {
            lIterator.remove();
            if (table[i].size() == 0) {
                table[i] = null;
            }
            numKeys--;
        }
    }

    public HashTableChain() { //default constructor
        table = new LinkedList[CAPACITY];
    }

    public int size() {
        return numKeys;
    }

    public boolean isEmpty() {
        return numKeys == 0;  //Number of keys in all lists
    }

    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        int index = key.hashCode() % table.length;  //Calculate the index of the table where the key is located
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; //key is not in the table
        }
        Entry<K, V> e = null, f = null;
        //Search the list at table[index] to find the key.
        for (int j = 0; j < table[index].size(); j++) {  //Go through the list at this index to find a match
            e = table[index].get(j);
            if (e != null) {
                if (e.key.equals(key)) {              //Check key of the input with key of list and removes it if its there.
                    f = table[index].remove(j);
                    numKeys--;
                    break;
                }
            }
        }

        if (f == null) {
            return null;
        }

        return f.value;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    }

    /**
     * Method get for class HashtableChain.
     *
     * @param key The key being sought
     * @returnThe value associated with this key if found; otherwise, null
     */
    //@Override
    public V get(Object key) {
        int index = key.hashCode() % table.length; //Look for Obj and return the value asso  with key
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null; //key is not in the table
        }
        //Search the list at table[index] to find the key.
        for (Entry<K, V> nextItem : table[index]) {      //For loop for above
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
        if (numKeys > (LOAD_THRESHOLD * table.length)) {  //Doubles size to of the table if true when we "put"
            rehash();
        }
        return null;
    }

    // returns boolean if table has the searched for key
    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    Entry<K, V> e = table[i].get(j);
                    if (e != null) {
                        if (e.key.equals(key)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // returns boolean if table has the searched for value
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    Entry<K, V> e = table[i].get(j);
                    if (e != null) {
                        if (e.value.equals(value)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // throws UnsupportedOperationException
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw new UnsupportedOperationException();
    }

    // empties the table
    @Override
    public void clear() {
        if (table == null) {
            return;
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].size() > 0) {
                    for (int j = 0; j < table[i].size(); j++) {
                        table[i].removeFirst();
                        numKeys--;
                    }
                }
                if (table[i].size() == 0) {
                    table[i] = null;
                }
            }
        }
        numKeys = 0;
    }

    // returns a view of the keys in set view
    @Override
    public Set<K> keySet() {
        Set<K> coll = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    Entry<K, V> e = table[i].get(j);
                    if (e != null) {
                        coll.add(e.key);
                    }
                }
            }
        }
        return coll;
    }

    // throws UnsupportedOperationException
    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException();
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

    @Override
    public int hashCode() {  //Hash code for the table
        int result = 1;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (int j = 0; j < table[i].size(); j++) {
                    Entry<K, V> e = table[i].get(j);
                    if (e != null) {
                        result += e.key.hashCode();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {  //Checks every entry
        if (obj == this) {
            return true;
        }
        try {
            Map<K, V> objct = (Map<K, V>) obj;  //Difficult**************
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    for (int j = 0; j < table[i].size(); j++) {
                        Entry<K, V> e = table[i].get(j);
                        if (e != null) {
                            V val = objct.get(e.key);
                            if (!e.value.equals(val)) {
                                return false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}
