public class LinkedMatrix<E> {

    //(a) Create a generic static inner class representing a node with capabilities to store an element and be
    // linked to a node below/to the right. The class should have accessors and mutators for all the fields.
    private static class Node<E>{
        private E element;
        private LinkedMatrix.Node<E> below;

        private LinkedMatrix.Node<E> next;
        public Node(E e, LinkedMatrix.Node<E> n, LinkedMatrix.Node<E> b){
            element = e;
            next = n;
            below = b;
        }
        public E getElement(){return element;}
        public LinkedMatrix.Node<E> getNext(){return next;}
        public LinkedMatrix.Node<E> getBelow(){return below;}

        public void setNext(LinkedMatrix.Node<E> n) {next = n;}
        public void setBelow(LinkedMatrix.Node<E> b) {below = b;}

    }
    // (b) Add a generic method that, given the upper-left node of such a structure, prints all elements in column-major order.
    public void printColumnMajor(Node<E> upperLeft) {
        //In our loops, current would be the 'first' element to start printing the below elements.
        Node<E> current = upperLeft;

        while (current != null) {

            Node<E> columnStart = current;

            while (current != null) {

                System.out.println(current.getElement());

                current = current.getBelow();
            }

            if (columnStart.getNext() != null) {

                current = columnStart.getNext();
            }
        }
    }

    // (c) Add a generic method that, given a two-dimensional array of elements, creates a linked structure corresponding to it and returns the upper-left node.

    public Node<E> createFromArray(E[][] array) {

        int rows = array.length;

        int cols = array[0].length;

        if (cols == 0) {
            return null;
        }

        Node<E>[][] nodes = new Node[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                nodes[i][j] = new Node<>(array[i][j], null, null);

                //For the first node, and for the first iteration, it skips linking nodes together, so that during the
                //iteration where j > 0, it assigns so that on row i at the moment the nodes would be linked together from
                //right to left, hence, nodes[i][j-1].next - > nodes [i][j]

                if (j > 0) {
                    nodes[i][j - 1].next = nodes[i][j];
                }
                //in the case where i > 0, on jth column, the nodes would be linked together from up to bottom, hence,
                //nodes [i-1][j].below -> nodes[i][j]
                if (i > 0) {
                    nodes[i - 1][j].below = nodes[i][j];
                }
            }
        }

        return nodes[0][0];
    }

// (d)Add a main method to test the two methods.

    public static void main(String[] args) {
        LinkedMatrix<Integer> matrix = new LinkedMatrix<>();

        Node<Integer> upperLeft = new LinkedMatrix.Node<>(1, null, null);
        Node<Integer> n1 = new LinkedMatrix.Node<>(2, null, null);
        Node<Integer> n2 = new LinkedMatrix.Node<>(22, null, null);
        Node<Integer> n3 = new LinkedMatrix.Node<>(222, null, null);

        /** structure is the following
        upperLeft -> n1
        |            |
        v            v
        n2    ->     n3
         */

        upperLeft.setNext(n1);
        upperLeft.setBelow(n2);
        n1.setBelow(n3);
        n2.setNext(n3);


        matrix.printColumnMajor(upperLeft);


        Integer[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Node<Integer> upperLeftFromArray = matrix.createFromArray(array);


        matrix.printColumnMajor(upperLeftFromArray);
    }



}
