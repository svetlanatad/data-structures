package なんで;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class ConcreteMaxPriorityQueue<K, V> implements MaxPriorityQueue<K, V> {
    private PriorityQueue<K, V> maxPriorityQueue;

    public ConcreteMaxPriorityQueue(Comparator<K> comparator) {
        this.maxPriorityQueue = new HeapPriorityQueue<>(comparator);
    }

    @Override
    public int size() {
        return maxPriorityQueue.size();
    }

    @Override
    public boolean isEmpty() {
        return maxPriorityQueue.isEmpty();
    }

    @Override
    public void insert(K key, V value) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        maxPriorityQueue.insert(key, value);
    }

    @Override
    public V max() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return maxPriorityQueue.min().getValue();
    }

    @Override
    public V removeMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        return maxPriorityQueue.removeMin().getValue();
    }
    //here's a sample code to help with the checking process :)
//    public static void main(String[] args){
//        ConcreteMaxPriorityQueue<Integer, Integer> qu = new ConcreteMaxPriorityQueue<>(new DefaultComparator<>());
//        qu.insert(7,7);
//        qu.insert(3,2);
//        qu.removeMax();
//        for(int i = 0;  i < qu.size(); i++){
//            System.out.println("Entry: " +  qu.max());
//        }
//    }
}
