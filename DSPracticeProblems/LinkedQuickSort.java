public class LinkedQuickSort {

    //(a) Copy the Node class from the SinglyLinkedList into this one.
    private static class Node<E>{
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
        public E getElement(){return element;}
        public Node<E> getNext(){return next;}
        public void setNext(Node<E> n) {next = n;}
    }

    //(b) Implement a generic method, that given the first node of a singly-linked structure, prints its
    //contents.
    public static <E> void printStuff(Node<E> firstNode) {
        while (firstNode != null) {
            System.out.print(firstNode.getElement() + " ");
            firstNode = firstNode.getNext();
        }
        System.out.println();
    }


   //Implement a generic method that, given an array of generic values, creates a singly-linked structure
   // containing the values and returns the first node of the sequence.
    public static<E> Node<E> createSinglyLinkedListFromArray(E[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        Node<E> head = new Node<>(array[0], null);
        Node<E> currentNode = head;

        for (int i = 1; i < array.length; i++) {
            Node<E> temp = new Node<>(array[i], null);
            currentNode.setNext(temp);
            currentNode = temp;
        }

        return head;
    }
    //d) Implement a method that, given the first node of a singly-linked structure, sorts it using the quick-sort algorithm.
    // The pivot should always be the first element of the sequence.
    // The sequences smaller and greater should be constructed by relinking the existing nodes.
    // You are not allowed to create any new objects. In the end, the first node of the sorted sequence should be returned.

    //I had to look up how to compare generic types syntax, acknowledgement

    public static <E extends Comparable<? super E>> Node<E> quickSort(Node<E> firstNode) {
        if (firstNode == null || firstNode.getNext() == null) {
            return firstNode;
        }

        //Quicksort implementation according to the slides
        Node<E> smallerHead = null;

        Node<E> smallerTail = null;

        Node<E> pivot = firstNode;

        Node<E> greaterHead = null;

        Node<E> greaterTail = null;

        Node<E> current = firstNode.getNext();

        while (current != null) {

            Node<E> next = current.getNext();

            current.setNext(null);

            if (current.getElement().compareTo(firstNode.getElement()) < 0) {

                if (smallerHead == null) {

                    smallerHead = current;

                } else {

                    smallerTail.setNext(current);
                }

                smallerTail = current;

            } else if (current.getElement().compareTo(firstNode.getElement()) == 0) {

                pivot.setNext(current);

                pivot = current;

            } else {

                if (greaterHead == null) {

                    greaterHead = current;

                } else {

                    greaterTail.setNext(current);
                }

                greaterTail = current;
            }

            current = next;
        }

        smallerHead = quickSort(smallerHead);

        greaterHead = quickSort(greaterHead);

        if (smallerHead == null) {

            pivot.setNext(greaterHead);
            return firstNode;

        } else {

            smallerTail.setNext(firstNode);
            pivot.setNext(greaterHead);
            return smallerHead;
        }
    }




    //(e) Add a main method, and test all the methods above.
    public static void main(String[] args) {

        Node<Integer> node = new Node<>(1, new Node<>(2, null));

        printStuff(node);

        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Node<Integer> node0 = createSinglyLinkedListFromArray(array);

        printStuff(node0);

        Node<Integer> node1 = new Node<>(22, new Node<>(12, new Node<>(42, new Node<>(32, new Node<>(52, null)))));

        node1 = quickSort(node1);

        printStuff(node1);


    }
}
