
package grr;
import java.util.Iterator;

/**
 * Write a class SortedMapList that implements List using a single efficient instance of a SortedMap as un underlying container.
 * Specify and justify the running times of all the methods in your implementation.
 */
public class SortedMapList<E> implements List<E> {
    private AVLMap<Integer, E> sortedMap;
    public SortedMapList() {
        sortedMap = new AVLMap<>();
    }
    public int size() {
        return sortedMap.size();
    }
    public boolean isEmpty() {
        return sortedMap.isEmpty();
    }
    public E get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("yes");
        }
        Entry<Integer, E> entry = sortedMap.ceilingEntry(i);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("get sshed");
        }
        E oldValue = sortedMap.get(i);
        sortedMap.put(i, e);
        return oldValue;
    }

    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("i !< 0 || i !> size");
        }
        for (int j = size(); j > i; j--) {
            sortedMap.put(j, sortedMap.remove(j - 1));
        }
        sortedMap.put(i, e);
    }

    public E remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("out of bounds index: " + i);
        }
        E removedValue = sortedMap.remove(i);
        for (int j = i + 1; j < size(); j++) {
            sortedMap.put(j - 1, sortedMap.remove(j));
        }
        return removedValue;
    }

    public Iterator<E> iterator() {
        return new Iterator<>() {
            private Iterator<Entry<Integer, E>> entryIterator = sortedMap.entrySet().iterator();
            public boolean hasNext() {
                return entryIterator.hasNext();
            }
            public E next() {
                return entryIterator.next().getValue();
            }
        };
    }

    //Here's a main method to help you with testing! :)
    /**
     *
     *  public static void main(String[] args) {
     *         SortedMapList<Integer> sortedList = new SortedMapList<>();
     *         sortedList.add(0, 0);
     *         sortedList.add(1, 1);
     *         sortedList.add(2, 2);
     *         System.out.println("original list: ");
     *         print(sortedList);
     *         sortedList.set(1, 11);
     *         System.out.println("modified list: ");
     *         print(sortedList);
     *         Integer removedElement = sortedList.remove(0);
     *         System.out.println("list after removing element at index 0:");
     *         print(sortedList);
     *         System.out.println("Removed Element: " + removedElement);
     *     }
     *
     *     private static void print(SortedMapList<Integer> list) {
     *         for (int i = 0; i < list.size(); i++) {
     *             System.out.println("Index " + i + ": " + list.get(i));
     *         }
     *         // iterator testing
     *         for(Integer el : list){
     *             System.out.println("Element: " + el);
     *         }
     *     }
     */

    /**
     * Justification for all of the implemented methods
     * size(): O(1)
     * The size is just the avl sorted map's number of elements which is constant operation
     * isEmpty(): O(1)
     * this is just size == 0 from the avl's size which is constant operation
     * get(int i): O(log n)
     * The ceilingEntry method of the AVLMap is used, which performs a binary search in the AVL tree to find the needed entry. Since binary search is always O(logn) because 2^? = n => ? = logn
     * set(int i, E e): O(log n)
     * Similar to get, the put method of the AVLMap is used to update the value associated with the specified index. This operation also involves a binary search so with the same logic it's O(log n)
     * add(int i, E e): O(n)
     * To add an element, a loop is used to shift elements with keys greater than or equal to the current index. In the worst case, this involves shifting all elements to the right, soo it will be O(n).
     * remove(int i): O(n)
     * Removing an element at a specific index i also requires shifting elements to fill the gap, and in the same way, the worst case, all elements will be shifted to the left, so it will be O(n).
     * iterator(): O(1) for initialization, O(n) for iterating over the entire list
     * The iterator is initialized in constant operation so it's a constant time. But to iterate you need to traverse  all entries in the AVL tree, which will be O(n).
     */


}
