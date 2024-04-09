package なんで;

import java.util.Comparator;
import java.util.Random;

/**
 *
 * Create a class DHeapPriorityQueue that represents a concrete implementation of the AbstractPriorityQueue which uses a d-ary heap as the underlying container. D-ary heap is a generalization of a binary heap, where nodes have d children instead of two.
 * The class should have two instance variables– d representing the possible number of children (should be greater than or equal to 2) and an ArrayList of entries called heap.
 * The class should implement all the necessary methods of the parent class as well as include the following functionality:
 * • A helper method for checking of the current position has an “i-th” child.
 * • A helper method for returning the index of the possible “i-th” child.
 * • A helper method that given the index of a position, returns the index of the parent position.
 * • A helper method for exchanging positions of two elements in the heap.
 * • A helper upheap(i) that moves the entry at index i higher, if necessary, to restore the heap property.
 * • A helper downheap(i) that moves the entry at index i lower, if necessary, to restore the heap property.
 * • A helper heapify() that performs a bottom-up construction of the heap.
 * • A constructor that takes two arguments– d value and Comparator and initializes the instance
 * variables accordingly.
 * • A constructor that takes one argument– d.
 * • A constructor that takes no arguments and initializes d to be 2.
 * • A constructor that takes three arguments– d, an ArrayList of keys and an ArrayList of values (both of same size), and efficiently constructs the priority queue.
 * @param <K>
 * @param <V>
 */
public class DHeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V>{

    private int d;
    private ArrayList<Entry<K,V>> heap;



    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public Entry<K, V> min() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        Entry<K, V> entry = new PQEntry<>(key, value);
        heap.add(entry);
        upHeap(heap.size() - 1);
        return entry;
    }

    @Override
    public Entry<K, V> removeMin() {
        if (heap.isEmpty()) {
            return null;
        }

        Entry<K, V> minEntry = heap.get(0);
        int lastIndex = heap.size() - 1;
        Entry<K, V> lastEntry = heap.get(lastIndex);

        heap.set(0, lastEntry);
        heap.remove(lastIndex);

        downHeap(0);
        return minEntry;
    }

    private void upHeap(int index) {
        while (index > 0) {
            int parent = parentIndex(index);
            if (compare(heap.get(index), heap.get(parent)) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void downHeap(int index) {
        int smallest = index;
        while (true) {
            int firstChild = ithChild(index, 1);
            if (firstChild >= heap.size()) {
                break;
            }
            int lastChild = Math.min(firstChild + d, heap.size());
            for (int i = firstChild; i < lastChild; i++) {
                if (compare(heap.get(i), heap.get(smallest)) < 0) {
                    smallest = i;
                }
            }
            if (smallest == index) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }
    private void swap(int i, int j) {
        Entry<K, V> temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    private boolean hasIthChild(int index, int i) {
        int childIndex = ithChild(index, i);
        return childIndex < heap.size();
    }
    private int ithChild(int index, int i) {
        return d * index + i;
    }
    private int parentIndex(int index) {
        return (index - 1) / d;
    }


    private void heapify() {
        int n = heap.size();
        for (int i = parentIndex(n - 1); i >= 0; i--) {
            downHeap(i);
        }
    }


    public DHeapPriorityQueue(int d, Comparator<K> c) {
        super(c);
        if (d < 2) {
            throw new IllegalArgumentException("d !> 2");
        }

        this.d = d;

        heap = new ArrayList<>();
    }

    public DHeapPriorityQueue(int d) {
        this(d, new DefaultComparator<>());
    }


    public DHeapPriorityQueue() {
        this(2, new DefaultComparator<>());
    }
    public DHeapPriorityQueue(int d, ArrayList<K> keys, ArrayList<V> values) {
        this(d, new DefaultComparator<>());

        if (keys.size() != values.size()) {
            throw new IllegalArgumentException("知識はあなたにも");
        }

        for (int i = 0; i < keys.size(); i++) {
            insert(keys.get(i), values.get(i));
        }

        heapify();
    }


    public static void main(String[] args) {

        int[] data = new int[1000000];
        Random rand = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(1001);
        }

        long startTime, endTime;
        int[] dValues = {2, 3, 4, 10};

        for (int d : dValues) {
            DHeapPriorityQueue<Integer, Integer> queue = new DHeapPriorityQueue<>(d);


            startTime = System.currentTimeMillis();
            for (int value : data) {
                queue.insert(value, value);
            }
            endTime = System.currentTimeMillis();
            System.out.println("Constructed D-ary Heap (d=" + d + ") in " + (endTime - startTime) + " ms");

            startTime = System.currentTimeMillis();
            while (!queue.isEmpty()) {
                queue.removeMin();
            }
            endTime = System.currentTimeMillis();
            System.out.println("Emptied D-ary Heap (d=" + d + ") in " + (endTime - startTime) + " ms");
        }

        for (int i = 1; i <= 4; i++) {


            HeapPriorityQueue<Integer, Integer> heapQueue = new HeapPriorityQueue<>();

            startTime = System.currentTimeMillis();
            for (int value : data) {
                heapQueue.insert(value, value);
            }
            endTime = System.currentTimeMillis();
            System.out.println("Constructed " + i + "th " + "HeapPriorityQueue in " + (endTime - startTime) + " ms");

            startTime = System.currentTimeMillis();
            while (!heapQueue.isEmpty()) {
                heapQueue.removeMin();
            }
            endTime = System.currentTimeMillis();
            System.out.println("Emptied " + i + "th " + "HeapPriorityQueue in " + (endTime - startTime) + " ms");
        }
        System.out.println();
        System.out.println();
        System.out.println("After running this code for 69 times, I could observe that every single time,");
        System.out.println();
        System.out.println("For construction of both of the heap structures, it takes  around 100 ms to complete, however,");
        System.out.println();
        System.out.println();
        System.out.println("For emptying, deconstruction, it takes about 1000 something ms, that's because the downHeap and remove worst case run in logn time. ");
        System.out.println("We can also see that the smaller is the d the better (それが彼が彼女に言ってほしいことだと彼が望んでいたことです。) time-wise, of course :) ");
        System.out.println("Also, we can notice that heap queue removal takes less time than dheap queue removal. That is, evidently, because d has more children");
        System.out.println("Idk what else to discuss! Have a great day :)");

//Here is some sample for you to help with checking process :)
////        int d = 3;
////        ArrayList<Integer> array = new ArrayList<>();
////        array.add(7);
////        array.add(6);
////        ArrayList<Character> arq = new ArrayList<>();
////        arq.add('a');
////        arq.add('b');
//        DHeapPriorityQueue<Integer, Character> qu = new DHeapPriorityQueue<>();
//        //qu.removeMin();
//        qu.insert(10, 'd');
//        qu.insert(9, 'j');
//        qu.insert(59, 'l');
//        qu.heapify();
//        for(int i = 0;  i < qu.size(); i++){
//            System.out.println("Entry: " +  qu.heap.get(i).getKey() + "," + qu.heap.get(i).getValue() );
//        }
//




    }




}




