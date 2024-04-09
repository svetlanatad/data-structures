
package grr;
import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StringHolderHashMap<V> extends AbstractMap<StringHolder, V> {
    private static class HashMapEntry<StringHolder, V> implements Entry<StringHolder, V> {
        private StringHolder grr;
        private V value;
        public HashMapEntry(StringHolder key, V val) {
            grr = key;
            value = val;
        }

        public StringHolder getKey() {
            return grr;
        }

        public V getValue() {
            return value;
        }

        public void setKey(StringHolder key) {
            grr = key;
        }

        public void setValue(V v) {
            value = v;
        }


    }

    private ArrayList<HashMapEntry<StringHolder, V>>[] buckets;
    private HashFunction<StringHolder> hashFunction;
    private int capacity;
    public StringHolderHashMap(int capacity, HashFunction<StringHolder> hashFunction) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity !<= 0");
        }
        this.capacity = capacity;
        this.hashFunction = hashFunction;
        this.buckets = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }
    }
    public int totalNumberOfCollisions() {
        int count = 0;
        for (ArrayList<HashMapEntry<StringHolder, V>> bucket : buckets) {
            if (bucket.size() > 1) {
                count += bucket.size() - 1;
            }
        }
        return count;
    }

    public Iterable<Entry<StringHolder, V>> entrySet() {
        ArrayList<Entry<StringHolder, V>> buffer = new ArrayList<>();
        for (int h = 0; h < capacity; h++)
            if (buckets[h] != null)
                for (Entry<StringHolder, V> entry : buckets[h])
                    buffer.add(entry);
        return buffer;
    }

    public int size() {
        int count = 0;
        for (ArrayList<HashMapEntry<StringHolder, V>> bucket : buckets) {
            if (bucket != null) {
                count += bucket.size();
            }
        }
        return count;
    }
    public V get(StringHolder key) {
        int index = hashFunction.hash(key) % capacity;
        ArrayList<HashMapEntry<StringHolder, V>> bucket = buckets[index];
        for (HashMapEntry<StringHolder, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }
    public V put(StringHolder key, V value) {
        int index = hashFunction.hash(key) % capacity;
        ArrayList<HashMapEntry<StringHolder, V>> bucket = buckets[index];
        for (HashMapEntry<StringHolder, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        bucket.add(new HashMapEntry<>(key, value));
        return null;
    }
    public V remove(StringHolder key) {
        int index = hashFunction.hash(key) % capacity;
        ArrayList<HashMapEntry<StringHolder, V>> bucket = buckets[index];
        Iterator<HashMapEntry<StringHolder, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            HashMapEntry<StringHolder, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                iterator.remove();
                return entry.getValue();
            }
        }
        return null;
    }

    public int maxCollisions() {
        int maxCollisions = 0;
        for (ArrayList<HashMapEntry<StringHolder, V>> bucket : buckets) {
            int bucketCollisions = bucket.size() - 1;
            if (bucketCollisions > maxCollisions) {
                maxCollisions = bucketCollisions;
            }
        }
        return maxCollisions;
    }
    public int emptyBuckets() {
        int count = 0;
        for (ArrayList<HashMapEntry<StringHolder, V>> bucket : buckets) {
            if (bucket.isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public double averageNumberInEachBucket() {
        int nonEmptyBuckets = 0;
        int totalEntries = 0;

        for (ArrayList<HashMapEntry<StringHolder, V>> bucket : buckets) {
            if (!bucket.isEmpty()) {
                nonEmptyBuckets++;
                totalEntries += bucket.size();
            }
        }
        if(nonEmptyBuckets == 0){
            return 0;
        }else{
            return (double)totalEntries/nonEmptyBuckets;
        }
    }

        public static void main(String[] args) {
            try {
                BufferedReader text = new BufferedReader(new FileReader("/Users/lanatadevosyan/Desktop/hw5/grr/50K.txt"));
                String line;
                String[] words = new String[58000];
                int wordCount = 0;

                while ((line = text.readLine()) != null && wordCount < 58000) {
                    words[wordCount++] = line.trim();
                }

                for (int i = 1; i <= 10; i++) {
                    //i'd rather it put the warning on this comment xD
                    HashCode<StringHolder> hashCode;
                    CompressionFunction compressionFunction = switch (i % 5) {
                        case 1 -> {
                            hashCode = new DefaultHashCode<>();
                            yield new MADCompressionFunction(106921);
                        }
                        case 2 -> {
                            hashCode = new SummingHashCode();
                            yield new MADCompressionFunction(106921);
                        }
                        case 3 -> {
                            hashCode = new ProductHashCode();
                            yield new MADCompressionFunction(106921);
                        }
                        case 4 -> {
                            hashCode = new PolynomialHashCode(17);
                            yield new MADCompressionFunction(106921);
                        }
                        case 0 -> {
                            hashCode = new StringHashCode();
                            yield new MADCompressionFunction(106921);
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + i % 5);
                    };
                    HashFunction<StringHolder> hashFunction = new HashFunction<>(hashCode, compressionFunction);
                    StringHolderHashMap<String> map = new StringHolderHashMap<>(106921, hashFunction);
                    for (String word : words) {
                        map.put(new StringHolder(word), word);
                    }

                    System.out.println("Map " + i);
                    System.out.println("Total Collisions: " + map.totalNumberOfCollisions());
                    System.out.println("Max Collisions: " + map.maxCollisions());
                    System.out.println("Empty Buckets: " + map.emptyBuckets());
                    System.out.println("Average Entries: " + map.averageNumberInEachBucket());
                    System.out.println();

                }
            } catch (IOException e) {
                //why does getLocalizedMessage() not throw any warnings but the usual getMessage throws a warning....interesting...
                e.getLocalizedMessage();
            }
        }
    }

