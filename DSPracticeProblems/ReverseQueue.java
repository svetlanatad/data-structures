public class ReverseQueue {
    //Write a single generic method that both removes every second element and reverses the contents of a queue.

    public static <E> void removeAndReverse(ArrayQueue<E> queue) {
//It reverses the queues using stacks, since enqueue only adds elements at the back of the queue, hence the need to use another data structure
        int size = queue.size();
        ArrayQueue<E> tempQueue = new ArrayQueue<>(size);
        Stack<E> stack = new ArrayStack<>();

        for (int i = 0; i < size; i++) {
            E element = queue.dequeue();
            if (i % 2 == 0) {
                stack.push(element);
            }
        }

        while (!stack.isEmpty()) {
            tempQueue.enqueue(stack.pop());
        }

        while (!tempQueue.isEmpty()) {
            queue.enqueue(tempQueue.dequeue());
        }

    }

    //Add a main method to test it on an ArrayQueue object.


    public static void main(String[] args) {

        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for (int i = 1; i <= 11; i++) {

            queue.enqueue(i);
        }
        removeAndReverse(queue);

        while (!queue.isEmpty()) {

            System.out.print(queue.dequeue() + " ");
        }

    }
}


