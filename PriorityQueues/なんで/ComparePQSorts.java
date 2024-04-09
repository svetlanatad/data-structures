package なんで;
import java.util.Random;
import java.util.Comparator;

public class ComparePQSorts {

    public static void main(String[] args) {
        long startTime, endTime;


        int[] array = new int[20000];
        Random rand = new Random();
        for (int i = 0; i < 20000; i++) {
            array[i] = rand.nextInt(100001);
        }

        Comparator<Integer> comparator = Comparator.naturalOrder();
        startTime = System.nanoTime();
        HeapPriorityQueue<Integer, String> heap1 = new HeapPriorityQueue<>(comparator);
        for (int value : array) {
            heap1.insert(value, null);
        }
        endTime = System.nanoTime();
        long constructionTime = endTime - startTime;
        System.out.println("Time taken to construct first heap: " + constructionTime);
        startTime = System.nanoTime();
        while(!heap1.isEmpty()){
            heap1.removeMin();
        }
        endTime = System.nanoTime();
        long removalTime = endTime - startTime;
        System.out.println("Time taken to remove values of first heap: " + removalTime);

        //........

        startTime = System.nanoTime();
        HeapPriorityQueue<Integer, String> heapBottomUp = new HeapPriorityQueue<>(comparator);
        for (int value : array) {
            heapBottomUp.insert(value, null);
        }
        heapBottomUp.heapify();

        endTime = System.nanoTime();
        constructionTime = endTime - startTime;
        System.out.println("Time taken to construct bottom up heap: " + constructionTime);
        startTime = System.nanoTime();
        while(!heapBottomUp.isEmpty()){
            heapBottomUp.removeMin();
        }
        endTime = System.nanoTime();
        removalTime = endTime - startTime;
        System.out.println("Time taken to remove values of heap: " + removalTime);

        //........
        startTime = System.nanoTime();
        SortedPriorityQueue<Integer, String> heap2 = new SortedPriorityQueue<>(comparator);
        for (int value : array) {
            heap2.insert(value, null);
        }
        endTime = System.nanoTime();
        constructionTime = endTime - startTime;
        System.out.println("Time taken to construct heap: " + constructionTime);
        startTime = System.nanoTime();
        while(!heap2.isEmpty()){
            heap2.removeMin();
        }
        endTime = System.nanoTime();
        removalTime = endTime - startTime;
        System.out.println("Time taken to remove values of heap: " + removalTime);
        //.......
        startTime = System.nanoTime();
        UnsortedPriorityQueue<Integer, String> heap3 = new UnsortedPriorityQueue<>(comparator);
        for (int value : array) {
            heap3.insert(value, null);
        }
        endTime = System.nanoTime();
        constructionTime = endTime - startTime;
        System.out.println("Time taken to construct heap: " + constructionTime);
        startTime = System.nanoTime();
        while(!heap3.isEmpty()){
            heap3.removeMin();
        }
        endTime = System.nanoTime();
        removalTime = endTime - startTime;
        System.out.println("Time taken to remove values of first heap: " + removalTime);


        System.out.println("Bottom up HeapPriorityQueue was the fastest at construction, while SortedPriorityQueue took the most time.\n" +
                "\n" +
                "For removal, UnsortedPriorityQueue was the fastest, with HeapPriorityQueue's bottom-up construction taking a lot of time. SortedPriorityQueue and HeapPriorityQueue's top-down construction ranked in between\n" +
                "As you can see from the results\n" + "It should be valid to take into consideration which data structure would fit best according to specific application needs and also in general our defined methods work really slow");


    }

}
