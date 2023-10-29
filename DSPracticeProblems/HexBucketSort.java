
public class HexBucketSort {

    public class ArrayStack<E> implements Stack<E>{
        public static final int CAPACITY = 1000;
        private E[] data;
        private int t = -1;
        public ArrayStack(){ this(CAPACITY);}
        public ArrayStack(int capacity){

            data = (E[])new Object[capacity];
        }
        public int size() {return (t+1);}
        public boolean isEmpty(){ return (t == -1);}
        public void push(E e) throws IllegalStateException {

            if(size() == data.length) throw new IllegalStateException("Stack full");

            data[++t] = e;
        }
        public E top(){

            if(isEmpty()) return null;

            return data[t];
        }
        public E pop(){

            if(isEmpty()) return null;

            E answer = data[t];

            data[t] = null;

            t--;

            return answer;

        }

    }


    public static void bucketSort(char[] arr) {

        HexBucketSort hexbucket = new HexBucketSort();

        ArrayStack<Character>[] buckets = new ArrayStack[16];

        for (int i = 0; i < 16; i++) {

            buckets[i] = hexbucket.new ArrayStack<>();
        }

        for (char element : arr) {

            int bucketIndex;

            if (element >= '0' && element <= '9') {
                //If the character is a digit from 0 to 9, it calculates the bucket index by subtracting
                // the ASCII value of '0' from the ASCII value of the character.
                //Therefore, it  converts the character '0' to '9' into integer values from 0 to 9, which correspond to the bucket indices
                bucketIndex = element - '0';
            } else {
                //it calculates the bucket index by subtracting the ASCII value of 'A' and then adding 10, since
                //hexadecimal digits 'A' to 'F' represent values from 10 to 15, which correspond to bucket indices 10 to 15, so it has to add 10
                bucketIndex = element - 'A' + 10;
            }
            buckets[bucketIndex].push(element);
        }

        int index = 0;

        for (Stack<Character> bucket : buckets) {

            while (!bucket.isEmpty()) {
                arr[index++] = bucket.pop();
            }
        }
    }

    public static void main(String[] args) {
        char[] arr = {'D', 'E', 'A', 'F', 'B', 'C', '6', '9', '4', '2', '0', '1', '3', '5', '8', '7'};

        bucketSort(arr);

        System.out.println(new String(arr));
    }

}