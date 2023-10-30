package sources;

public class LinkedTernaryTree<E> extends AbstractTernaryTree<E> {

    //---------------- nested Node class ----------------

    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;          // an element stored at this node
        private LinkedTernaryTree.Node<E> parent;     // a reference to the parent node (if any)
        private LinkedTernaryTree.Node<E> left;       // a reference to the left child (if any)
        private LinkedTernaryTree.Node<E> right;      // a reference to the right child (if any)

        private LinkedTernaryTree.Node<E> middle;

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param above      reference to a parent node
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, LinkedTernaryTree.Node<E> above, LinkedTernaryTree.Node<E> leftChild, LinkedTernaryTree.Node<E> rightChild, LinkedTernaryTree.Node<E> middleChild) {
            element = e;
            parent = above;
            left = leftChild;
            right = rightChild;
            middle = middleChild;
        }

        // accessor methods
        public E getElement() {
            return element;
        }

        public LinkedTernaryTree.Node<E> getParent() {
            return parent;
        }

        public LinkedTernaryTree.Node<E> getLeft() {
            return left;
        }

        public LinkedTernaryTree.Node<E> getRight() {
            return right;
        }

        public LinkedTernaryTree.Node<E> getMiddle() {
            return middle;
        }


        // update methods
        public void setElement(E e) {
            element = e;
        }

        public void setParent(LinkedTernaryTree.Node<E> parentNode) {
            parent = parentNode;
        }

        public void setLeft(LinkedTernaryTree.Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(LinkedTernaryTree.Node<E> rightChild) {
            right = rightChild;
        }

        public void setMiddle(LinkedTernaryTree.Node<E> middleChild) {
            middle = middleChild;
        }
    } //----------- end of nested Node class -----------

    /**
     * Factory function to create a new node storing element e.
     */
    protected LinkedTernaryTree.Node<E> createNode(E e, LinkedTernaryTree.Node<E> parent,
                                                   LinkedTernaryTree.Node<E> left, LinkedTernaryTree.Node<E> right, LinkedTernaryTree.Node<E> middle) {
        return new LinkedTernaryTree.Node<>(e, parent, left, right, middle);
    }

    // LinkedBinaryTree instance variables
    /**
     * The root of the ternary tree
     */
    protected LinkedTernaryTree.Node<E> root = null;     // root of the tree

    /**
     * The number of nodes in the ternary tree
     */
    private int size = 0;              // number of nodes in the tree

    // constructor

    /**
     * Constructs an empty binary tree.
     */
    public LinkedTernaryTree() {
    }      // constructs an empty ternary tree

    // nonpublic utility

    /**
     * Verifies that a Position belongs to the appropriate class, and is
     * not one that has been previously removed. Note that our current
     * implementation does not actually verify that the position belongs
     * to this particular list instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected LinkedTernaryTree.Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof LinkedTernaryTree.Node))
            throw new IllegalArgumentException("Not valid position type");
        LinkedTernaryTree.Node<E> node = (LinkedTernaryTree.Node<E>) p;       // safe cast
        if (node.getParent() == node)     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        return node.getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> middle(Position<E> p) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        return node.getMiddle();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        return node.getRight();
    }

    // update methods supported by this class

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null, null);
        size = 1;
        return root;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        LinkedTernaryTree.Node<E> child = createNode(e, parent, null, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        LinkedTernaryTree.Node<E> child = createNode(e, parent, null, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    public Position<E> addMiddle(Position<E> p, E e)
            throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> parent = validate(p);
        if (parent.getMiddle() != null)
            throw new IllegalArgumentException("p already has a middle child");
        LinkedTernaryTree.Node<E> child = createNode(e, parent, null, null, null);
        parent.setMiddle(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1, t2, and t3, respectively, as the left, middle, and right subtrees of the
     * leaf Position p. As a side effect, t1 and t2 and t3 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the middle child of p
     * @param t3 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */

    //something is wrong here
    public void attach(Position<E> p, LinkedTernaryTree<E> t1,
                       LinkedTernaryTree<E> t2, LinkedTernaryTree<E> t3) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size() + t3.size();
        if (!t1.isEmpty()) {                  // attach t1 as left subtree of node
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {                  // attach t2 as middle subtree of node
            t2.root.setParent(node);
            node.setMiddle(t2.root);
            t2.root = null;
            t2.size = 0;
        }
        if (!t3.isEmpty()) {                  // attach t3 as right subtree of node
            t3.root.setParent(node);
            node.setRight(t3.root);
            t3.root = null;
            t3.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        LinkedTernaryTree.Node<E> node = validate(p);
        if (numChildren(p) == 3 || numChildren(p) == 2)
            throw new IllegalArgumentException("p has 3 or 2 children");

        if (node.getLeft() != null) {
            LinkedTernaryTree.Node<E> child = node.getLeft();
            if (child != null)
                child.setParent(node.getParent());
            if (node == root)
                root = child;
        } else {
            LinkedTernaryTree.Node<E> child = node.getLeft();
            LinkedTernaryTree.Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else if (node == parent.getRight())
                parent.setRight(child);
            else
                parent.setMiddle(child);
        }
        if (node.getMiddle() != null) {
            LinkedTernaryTree.Node<E> child = node.getMiddle();
            if (child != null)
                child.setParent(node.getParent());
            if (node == root)
                root = child;
        } else {
            LinkedTernaryTree.Node<E> child = node.getMiddle();
            LinkedTernaryTree.Node<E> parent = node.getParent();
            if (node == parent.getMiddle())
                parent.setMiddle(child);
            else if (node == parent.getRight())
                parent.setRight(child);
            else
                parent.setLeft(child);
        }
        if (node.getRight() != null) {
            LinkedTernaryTree.Node<E> child = node.getRight();
            if (child != null)
                child.setParent(node.getParent());
            if (node == root)
                root = child;
        } else {
            LinkedTernaryTree.Node<E> child = node.getRight();
            LinkedTernaryTree.Node<E> parent = node.getParent();
            if (node == parent.getMiddle())
                parent.setMiddle(child);
            else if (node == parent.getRight())
                parent.setRight(child);
            else
                parent.setLeft(child);
        }

        size--;
        E temp = node.getElement();
        node.setElement(null);                // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setMiddle(null);
        node.setParent(node);                 // our convention for defunct node
        return temp;
    }

    public Iterable<Position<E>> breadthfirst( ) {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) {
            Queue<Position<E>> cringe = new LinkedQueue<>();
            cringe.enqueue(root()); // start with the root
            while (!cringe.isEmpty()) {
                Position<E> p = cringe.dequeue(); // remove from front of the queue
                snapshot.add(0, p);
                for (Position<E> c : children(p))
                    cringe.enqueue(c);
            }
        }
        int size = snapshot.size();
        for (int i = 0; i < size / 2; i++) {
            int j = size - i - 1;
            Position<E> temp = snapshot.get(i);
            snapshot.set(i, snapshot.get(j));
            snapshot.set(j, temp);
        }

        return snapshot;
    }

    public static void main(String[] args) {
        LinkedTernaryTree<Integer> mainTree = new LinkedTernaryTree<>();

        LinkedTernaryTree<Integer> leftTree = new LinkedTernaryTree<>();
        LinkedTernaryTree<Integer> middleTree = new LinkedTernaryTree<>();
        LinkedTernaryTree<Integer> rightTree = new LinkedTernaryTree<>();

        Position<Integer> rootOfMainTree = mainTree.addRoot(1);
        Position<Integer> rootOfLeftTree = leftTree.addRoot(2);
        Position<Integer> leftChild = leftTree.addLeft(rootOfLeftTree, 5);
        Position<Integer> middleChildOfLeftChild = leftTree.addMiddle(leftChild, 9);

        Position<Integer> rootOfMiddleTree = middleTree.addRoot(3);
        Position<Integer> leftChildOfMiddleTree = middleTree.addLeft(rootOfMiddleTree, 6);
        Position<Integer> rightChildOfMiddleTree = middleTree.addRight(rootOfMiddleTree, 7);
        Position<Integer> middleChildOfRightChildOfMiddleTree = middleTree.addMiddle(rightChildOfMiddleTree, 10);

        Position<Integer> rootOfRightTree = rightTree.addRoot(4);
        Position<Integer> rightChildOfRightTree = rightTree.addRight(rootOfRightTree, 8);
        mainTree.attach(rootOfMainTree, leftTree, middleTree, rightTree );

        Iterable<Position<Integer>> result = mainTree.breadthfirst();

        for (Position<Integer> position : result) {
            System.out.println(position.getElement());
        }

        mainTree.remove(leftChildOfMiddleTree);

        Iterable<Position<Integer>> result2 = mainTree.breadthfirst();
        for (Position<Integer> position : result2) {
            System.out.println(position.getElement());
        }

    }
}
