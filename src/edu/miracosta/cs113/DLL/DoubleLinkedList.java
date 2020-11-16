package edu.miracosta.cs113.DLL;
import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E> {  // Data fields
	//---------------------------------------------------------------------------------------------------------------------------------------------------------

	    public static void main(String[] args) {
	        DoubleLinkedList<String> L = new DoubleLinkedList<String>();
	        L.addLast("One");
	        L.addLast("Two");
	        System.out.println(L.toString());
	        ListIterator it = L.listIterator(0);
	        it.next();
	        it.set("shit");
	        System.out.println(L.toString());
	        L.add(2, "more-shit1");
	        System.out.println(L.toString());
	        L.add(0, "more-shit2");
	        System.out.println(L.toString());
	        L.add(L.size, "more-shit3");
	        System.out.println(L.toString());
	        ListIterator it2 = L.listIterator(0);
	        it2.next();
	        it2.remove();
	        System.out.println(L.toString());
	        //ListIterator it3 = L.listIterator(L.size - 1);
	        //it3.next();
	        //it3.remove();
	        ListIterator it4 = L.listIterator(1);
	        it4.next();
	        it4.remove();
	        System.out.println(L.toString());
	        //L.clear();
	        /*
	        ListIterator it5 = L.listIterator(0);
	        it5.next();
	        it5.remove();
	        it5.next();
	        it5.remove();
	        it5.next();
	        it5.remove();
	         */
	        L.set(0, "Howdy");
	        System.out.println(L.toString());
	    }

	    //-------------------------------------------------------------------------------------------------------------------------------------------------------
	    private Node<E> head = null;   // points to the head of the list
	    private Node<E> tail = null;   //points to the tail of the list
	    private int size = 0;    // the number of items in the list

	    public void add(int index, E obj) { // Fill Here
	        if (index < 0 || index > size) {
	            throw new IndexOutOfBoundsException("Invalid index " + (index - 1));
	        } else {
	            if (index == 0) {//add before head
	                addFirst(obj);
	                return;
	            } else if (index == size) {//add after tail
	                addLast(obj);
	                return;
	            }
	            ListIterator<E> iter = listIterator(index - 1);//go to index - 1
	            iter.next();//jump 1
	            iter.add(obj);
	        }
	    }

	    public void addFirst(E obj) { // add at head
	        Node<E> newNode = new Node<E>(obj);
	        if (size == 0) {
	            head = tail = newNode;
	            size++;
	        } else {
	            newNode.next = head;
	            head.prev = newNode;
	            head = newNode;
	            size++;
	        }

	    }

	    public void addLast(E obj) { // add at tail
	        Node<E> newNode = new Node<E>(obj);
	        if (size == 0) {
	            head = tail = newNode;
	            size++;
	        } else {
	            newNode.prev = tail;
	            tail.next = newNode;
	            tail = newNode;
	            size++;
	        }
	    }

	    @Override
	    public boolean isEmpty() {
	        return size == 0;
	    }

	    @Override
	    public boolean contains(Object o) {
	        ListIterator<E> iter = listIterator(0);
	        while (iter.hasNext()) {
	            E temp = iter.next();
	            if (temp.equals(o)) {//string check
	                return true;
	            }
	        }
	        return false;
	    }

	    @Override
	    public int indexOf(Object o) {//first occurrence of o
	        int i = 0;
	        ListIterator<E> iter = listIterator(0);
	        while (iter.hasNext()) {
	            E temp = iter.next();
	            if (temp.equals(o)) {
	                return i;
	            }
	            i++;
	        }
	        return -1;
	    }

	    @Override
	    public int lastIndexOf(Object o) {//last occurrence of o
	        int i = 0;
	        int j = -1;
	        ListIterator<E> iter = listIterator(0);
	        while (iter.hasNext()) {
	            E temp = iter.next();
	            if (temp.equals(o)) {
	                j = i;
	            }
	            i++;
	        }
	        if (j != -1) {
	            return j;
	        }
	        return -1;
	    }

	    public E get(int index) {//done
	        if (size == 0 || index >= size) {
	            throw new IndexOutOfBoundsException("Invalid index " + index);
	        }
	        if (index == 0) {
	            return head.data;
	        }
	        if (index == size) {
	            return tail.data;
	        }
	        ListIterator<E> iter = listIterator(index);
	        return iter.next();

	    }

	    public E getFirst() {//done
	        if (size == 0) {
	            throw new NoSuchElementException();
	        }
	        return head.data;
	    }

	    public E getLast() {//done
	        if (size == 0) {
	            throw new NoSuchElementException();
	        }
	        return tail.data;
	    }

	    public int size() {//done
	        return size;
	    } // Fill Here

	    public E set(int index, E obj) {
	        if (size == 0 || index >= size || index < 0) {
	            throw new IndexOutOfBoundsException("Invalid index " + index);
	        }
	        E returnValue = null;
	        if (index == 0) {
	            head.data = obj;
	            return obj;
	        }
	        ListIterator<E> iter = listIterator(index);
	        if (iter.hasNext()) {
	            returnValue = iter.next();
	            iter.set(obj);
	        }
	        return returnValue;
	    }

	    public E remove(int index) {//in book
	        if (size == 0 || index >= size || index < 0) {
	            throw new IndexOutOfBoundsException("Invalid index " + index);
	        }
	        E returnValue = null;
	        ListIterator<E> iter = listIterator(index);
	        if (iter.hasNext()) {
	            returnValue = iter.next();
	            iter.remove();
	        } else {
	            throw new IndexOutOfBoundsException();
	        }
	        return returnValue;
	    }

	    @Override
	    public void clear() {//
	        if (size == 0) {
	            throw new NoSuchElementException();
	        }
	        while (size > 0) {
	            ListIterator<E> iter = listIterator(0);
	            if (iter.hasNext()) {
	                iter.next(); //for remove you must first call next() or previous() to set lastItemReturned
	                iter.remove();
	            }
	        }
	    }

	    public Iterator iterator() {//done
	        return new ListIter(0);
	    }

	    public ListIterator listIterator() {//done
	        return new ListIter(0);
	    }

	    public ListIterator listIterator(int index) {//done
	        return new ListIter(index);
	    }

	    public ListIterator listIterator(ListIterator iter) {//done
	        return new ListIter((ListIter) iter);
	    }

	    @Override
	    public String toString() {//done
	        String TO_STRING_EMPTY = "[]";
	        if (size == 0) {
	            String ss = TO_STRING_EMPTY;
	            return ss;
	        }
	        String s = "[";
	        ListIterator<E> iter = listIterator();//same as index = 0 in argument
	        int i = 0;
	        while (iter.hasNext()) {
	            s += iter.next();
	            if (i < size - 1) {
	                s += ", ";
	            } else {
	                s += "]";
	            }
	            i++;
	        }
	        return s;
	    }
	//----------------------------------------------------------------------------------------------------------------------

	    /**
	     * *************************************************************************************************
	     */
	    // Inner Classes
	    private static class Node<E> {

	        private E data;
	        private Node<E> next = null;
	        private Node<E> prev = null;

	        private Node(E dataItem) //constructor
	        {
	            data = dataItem;
	        }
	    }  // end class Node

	    /**
	     * ********************************************************************************************************
	     */
	    public class ListIter implements ListIterator<E> {

	        private Node<E> nextItem; // the current node

	        private Node<E> lastItemReturned;// the previous node - usually null unless next() or previous() just called

	        private int index = 0;   //

	        public ListIter(int i) // constructor for ListIter class
	        {
	            if (i < 0 || i > size) {
	                throw new IndexOutOfBoundsException("Invalid index " + i);
	            }
	            lastItemReturned = null; //only set with next() or previous()

	            if (i == size) // Special case of last item
	            {//index = size. Must run previous() to get the tail. The cursor is behind tail. previous() jumps over the tail and returns its value.
	                index = size;
	                nextItem = null;
	            } else // start at the beginning
	            {//index = 0 is the head - like in an array. The cursor is before the head. With next, the head is returned - i.e. index 0.
	                nextItem = head; //index = 0 is the head. The tail is size - 1 = index. At size, the list is over and nextItem is null;
	                for (index = 0; index < i; index++) {
	                    nextItem = nextItem.next;
	                }
	            }// end else
	        }  // end constructor

	        public ListIter(ListIter other) {//copy constructor
	            nextItem = other.nextItem;
	            index = other.index;
	        }

	        @Override
	        public boolean hasNext() {//done
	            if (size == 0 || head == null) {
	                return false;
	            }
	            return nextItem != null;
	        } // Fill Here

	        @Override
	        public boolean hasPrevious() {//done
	            if (size == 0 || head == null) {
	                return false;
	            }
	            return (nextItem == null && size != 0) || nextItem.prev != null;//book code
	        } // Fill Here

	        @Override
	        public int previousIndex() {
	            if (index <= 0) {//rule
	                return -1;
	            }
	            return index - 1;
	        } // Fill Here

	        @Override
	        public int nextIndex() {
	            if (index + 1 >= size) {//rule
	                return size;
	            }
	            return index + 1;
	        } // Fill here

	        @Override
	        public void set(E o) {
	            if (size == 0) {
	                throw new NoSuchElementException();
	            }
	            if (index < 0 || index >= size) {
	                throw new NoSuchElementException();
	            }
	            if (lastItemReturned == null) {//set also needs a call to next() or previous() first.
	                throw new IllegalStateException("IllegalStateException expected without prior call to next or previous.");
	            }
	            if (index == 0) {
	                head.data = o;
	            } else if (index == size) {
	                tail.data = o;
	            } else {
	                lastItemReturned.data = o;
	            }
	        }

	        /**
	         * Removes from the list the last element that was returned by next() or
	         * previous() (optional operation*)
	         */
	        public void remove() {
	            if (size == 0) {
	                throw new NoSuchElementException();
	            }
	            if (lastItemReturned == null) {//must call next() or previous() to set lastItemReturned
	                throw new IllegalStateException("IllegalStateException expected without prior call to next or previous.");
	            }
	            Node<E> temp = lastItemReturned;//temp is deleted
	            if (temp == null) {
	                return;
	            }
	            if (head == null) {//empty List
	                return;
	            } else if (temp == head) {//at the head of the list
	                head = temp.next;
	            } else if (temp == tail) {//at the tail of the list
	                temp.prev.next = temp.next;
	            } else { //in the middle of the list
	                temp.next.prev = temp.prev;//bypassing temp for both references - next and prev
	                temp.prev.next = temp.next;
	            }
	            size--;
	            index--;
	            lastItemReturned = null;
	        }      // not implemented

	        /**
	         * Returns the next element in the list and advances the cursor
	         * position.
	         */
	        public E next() {//done
	            if (size == 0 || index == size) {
	                throw new NoSuchElementException();
	            }
	            if (!hasNext()) {
	                throw new NoSuchElementException();
	            }
	            if (index < 0 || index > size) {
	                throw new IndexOutOfBoundsException("Invalid index " + index);
	            }

	            lastItemReturned = nextItem;
	            nextItem = nextItem.next;
	            index++;
	            return lastItemReturned.data;
	        }

	        @Override
	        public E previous() {//done
	            if (size == 0 || index == 0) {
	                throw new NoSuchElementException();
	            }
	            if (!hasPrevious()) {
	                throw new NoSuchElementException();
	            }
	            if (index < 0 || index > size) {
	                throw new IndexOutOfBoundsException("Invalid index " + index);
	            }

	            if (nextItem == null) {
	                nextItem = tail;
	            } else {
	                nextItem = nextItem.prev;
	            }
	            lastItemReturned = nextItem;
	            index--;
	            return lastItemReturned.data;
	        }

	        /**
	         * Inserts the specified element into the list (optional operation).
	         */
	        @Override
	        public void add(E obj) {//done
	            if (obj == null) {
	                return;
	            }
	            if (head == null) {//empty
	                head = new Node<E>(obj);
	                tail = head;
	            } else if (nextItem == head) {//at the head of the list
	                Node<E> newNode = new Node<E>(obj);
	                newNode.next = nextItem;
	                nextItem.prev = newNode;
	                head = newNode;
	            } else if (nextItem == null) {//at the tail of the list
	                Node<E> newNode = new Node<E>(obj);
	                tail.next = newNode;
	                newNode.prev = tail;
	                tail = newNode;
	            } else { //in the middle of the list
	                Node<E> newNode = new Node<E>(obj);
	                newNode.prev = nextItem.prev;
	                nextItem.prev.next = newNode;
	                newNode.next = nextItem;
	                nextItem.prev = newNode;
	            }
	            size++;
	            index++;
	            lastItemReturned = null;
	        }
	    }// end of inner class ListIter
	    /**
	     * **********************************************************************************************************************************************
	     */
	}// end of class DoubleLinkedList
