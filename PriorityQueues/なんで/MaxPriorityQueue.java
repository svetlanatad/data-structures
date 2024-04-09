package なんで;

public interface MaxPriorityQueue<K,V>{
     int size();
     boolean isEmpty();
     void insert(K key, V value);
     V max();
     V removeMax();
}
