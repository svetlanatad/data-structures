package なんで;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Write a generic class UnsortedQueueMap that extends the AbstractMap class using a queue as the underlying data structure.
 * Your class should support all of the following functionality:
 * (a) a no-arg constructor that creates an empty map;
 * (b) size method (note that isEmpty is inherited from AbstractMap);
 * (c) the fundamental methods get, put, and remove from the Map ADT. Note that you are not allowed to use additional data structures or recursion here.
 * (d) the entrySet method from the Map ADT.
 * You may add utility method(s) to your class. Specify and justify the running times of all the methods.
 * @param <K> key
 * @param <V> value
 */

public class UnsortedQueueMap<K, V> extends AbstractMap<K, V> {




    private ArrayQueue<Entry<K, V>> queue;

    //works
    public UnsortedQueueMap() {
        queue = new ArrayQueue<>();
    }

    @Override
    public int size() {
        return queue.size();
    }

    //works
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("cannot be null.");
        }

        for (Entry<K, V> entry : entrySet()) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public V remove(K key) {
    EntryIterator entryIterator = new EntryIterator(this.queue);
    return entryIterator.remove(key);
}

    //works

    public Iterable<V> values(){
        return snapshot(0);

     }
    private Iterable<V> snapshot(int startIndex) {
        ArrayList<V> buffer = new ArrayList<>();
        ArrayList<Entry<K, V>> queueCopy = new ArrayList<>();


        while(!queue.isEmpty()) {
            Entry<K, V> entry = queue.dequeue();
            queueCopy.add(entry);
        }
        for (Entry<K, V> entry : queueCopy) {
            queue.enqueue(entry);
        }


        int i = startIndex;
        while (i < queueCopy.size())
            buffer.add(queueCopy.get(i++).getValue());
        return buffer;
    }

    //also works
    private class EntryIterator implements Iterator<Entry<K, V>> {
        private int j = 0;
        private Queue<Entry<K, V>> entryQueue;

        public EntryIterator(Queue<Entry<K, V>> entryQueue) {
            this.entryQueue = entryQueue;
        }

        public boolean hasNext() {
            return j < entryQueue.size();
        }

        public Entry<K, V> next() {
            if (j == entryQueue.size()) {
                throw new NoSuchElementException("No further entries");
            }
            Entry<K, V> entry = entryQueue.first();
            j++;
            entryQueue.enqueue(entryQueue.dequeue());
            return entry;
        }
        //works
        public V remove(K key) {
            if (key == null) {
                return null;
            }

            Queue<Entry<K, V>> tempQueue = new ArrayQueue<>();
            boolean removed = false;

            while (!entryQueue.isEmpty()) {
                Entry<K, V> entry = entryQueue.dequeue();
                if (entry.getKey().equals(key)) {
                    removed = true;
                } else {
                    tempQueue.enqueue(entry);
                }
            }

            while (!tempQueue.isEmpty()) {
                entryQueue.enqueue(tempQueue.dequeue());
            }

            if (!removed) {
                while (!tempQueue.isEmpty()) {
                    entryQueue.enqueue(tempQueue.dequeue());
                }
            }
            if (removed && !tempQueue.isEmpty()) {
                return tempQueue.first().getValue();
            }

            return null;
        }


    }


    //----------- end of nested EntryIterator class -----------

    //---------------- nested EntryIterable class ----------------
    private class EntryIterable implements Iterable<Entry<K,V>> {
        public Iterator<Entry<K,V>> iterator() { return new UnsortedQueueMap.EntryIterator(queue); }
    } //----------- end of nested EntryIterable class -----------

    /**
     * Returns an iterable collection of all key-value entries of the map.
     *
     * @return iterable collection of the map's entries
     */
    @Override
    public Iterable<Entry<K,V>> entrySet() { return new UnsortedQueueMap.EntryIterable(); }

    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("UnsortedQueueMap does not allow null keys.");
        }

        for (Entry<K, V> entry : entrySet()) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                queue.enqueue(new MapEntry<>(key, value));
                return oldValue;
            }
        }

        queue.enqueue(new MapEntry<>(key, value));
        return null;
    }





    void print(){
        while(!this.queue.isEmpty()){
            System.out.println(this.queue.dequeue());
        }
    }



//here's sample code to help with checking process :)
//    public static void main(String[] args){
//        UnsortedQueueMap<Integer, Integer> qu = new UnsortedQueueMap<>();
//
//        qu.put(5,6);
//        qu.put(7,8);
//        qu.put(9,8);
//        qu.put(11,8);
//        qu.put(13,14);
//        //qu.remove(5);
//        //qu.print();
//        //System.out.println(qu.entrySet());
//        //System.out.println(qu.values());
//        for (Entry<Integer, Integer> entry : qu.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }
//        System.out.println("aaaaaaaaaaaaaaa");
//
//       qu.remove(9);
//        qu.print();
//    }
//



}
