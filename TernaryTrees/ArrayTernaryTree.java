
package sources;



public class ArrayTernaryTree<E> extends AbstractTernaryTree<E> {

    //each position in this implementation will be associated with a particular index (computed
    //through level numbering function).
    private ArrayList<Cell<E>> positions;
    private int size = 0;

    public ArrayTernaryTree() {
        this(1000);
    }

    public ArrayTernaryTree(int capacity) {
        positions = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            positions.add(null);
        }

    }

    /**
     * The class should include an inner static generic class Cell that implements generic position and represents a combination of an element and its associated index.
     * This class should include the following methods:
     * (a) A two-argument constructor
     * (b) Getters for the instance variables (including the inherited methods)
     * (c) Setters for the instance variables
     * (d) getLeftIndex, getMiddleIndex, getRightIndex, getParentIndex that correspondingly return the index of the left/middle/right child of the parent position
     */
    private static class Cell<E> implements Position<E> {
        private E element;
        private int index;

        public Cell(E e, int i) {
            this.element = e;
            this.index = i;
        }

        public E getElement() {
            return element;
        }

        public int getIndex() {
            return index;
        }

        public void setElement(E e) {
            this.element = e;
        }

        public void setIndex(int i) {
            this.index = i;
        }

        public int getLeftIndex() {
            return 3 * index + 1;
        }

        public int getMiddleIndex() {
            return 3 * index + 2 ;
        }

        public int getRightIndex() {
            return 3 * index + 3 ;
        }

        public int getParentIndex() {
            if (index == 0)
                return -1;
            return (index - 1) / 3;
        }


    }//----------------------------End of Cell Class--------------------------------------------//


    protected ArrayTernaryTree.Cell<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof ArrayTernaryTree.Cell<E>))
            throw new IllegalArgumentException("Not valid position type");
        ArrayTernaryTree.Cell<E> cell = (ArrayTernaryTree.Cell<E>) p;// safe cast
        if (cell.getIndex() == cell.getParentIndex())     // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return cell;
    }


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
        return positions.get(0);
    }

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        if (isRoot(p))
            return null;
        ArrayTernaryTree.Cell<E> cell = validate(p);
        return positions.get(cell.getParentIndex());
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    public Position<E> left(Position<E> p) throws IllegalArgumentException {

        ArrayTernaryTree.Cell<E> cell = validate(p);
        return positions.get(cell.getLeftIndex());
    }

    @Override
    public Position<E> middle(Position<E> p) throws IllegalArgumentException {

        ArrayTernaryTree.Cell<E> cell = validate(p);
        return positions.get(cell.getMiddleIndex());
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

        ArrayTernaryTree.Cell<E> cell = validate(p);
        return positions.get(cell.getRightIndex());
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
        positions.set(0, new Cell(e, 0));
        size = 1;
        return positions.get(0);

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

        ArrayTernaryTree.Cell<E> parent = validate(p);
        int leftindex = parent.getLeftIndex();
        if (positions.get(leftindex) != null)
            throw new IllegalArgumentException("p already has a left child");
        if (leftindex >= positions.size())
            throw new IllegalArgumentException("index issue");
        ArrayTernaryTree.Cell<E> child = new ArrayTernaryTree.Cell<>(e, leftindex);
        positions.set(leftindex, child);
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
        ArrayTernaryTree.Cell<E> parent = validate(p);
        int rightindex = parent.getRightIndex();
        if (positions.get(rightindex) != null)
            throw new IllegalArgumentException("p already has a right child");
        if (rightindex >= positions.size())
            throw new IllegalArgumentException("index issue");
        ArrayTernaryTree.Cell<E> child = new ArrayTernaryTree.Cell<>(e, rightindex);
        positions.set(rightindex, child);
        size++;
        return child;
    }

    public Position<E> addMiddle(Position<E> p, E e) throws IllegalArgumentException {
        ArrayTernaryTree.Cell<E> parent = validate(p);
        int middleindex = parent.getMiddleIndex();
        if (positions.get(middleindex) != null)
            throw new IllegalArgumentException("p already has a middle child");
        if (middleindex >= positions.size())
            throw new IllegalArgumentException("index issue");
        ArrayTernaryTree.Cell<E> child = new ArrayTernaryTree.Cell<>(e, middleindex);
        positions.set(middleindex, child);
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
        ArrayTernaryTree.Cell<E> cell = validate(p);
        E temp = cell.getElement();
        cell.setElement(e);
        return temp;
    }

    public static void main(String[] args) {
        ArrayTernaryTree<Integer> mainTree = new ArrayTernaryTree<>();

        Position<Integer> rootOfMainTree = mainTree.addRoot(1);
        Position<Integer> leftChildOfRoot = mainTree.addLeft(rootOfMainTree, 2);
        Position<Integer> leftChildOfleftChildOfRoot = mainTree.addLeft(leftChildOfRoot, 5);
        Position<Integer> middleChildOfleftChildOfleftChildOfRoot = mainTree.addMiddle(leftChildOfleftChildOfRoot, 9);
        Position<Integer> middleChildOfRoot = mainTree.addMiddle(rootOfMainTree, 3);
        Position<Integer> leftOfmiddleChildOfRoot = mainTree.addLeft(middleChildOfRoot, 6);
        Position<Integer> rightOfmiddleChildOfRoot = mainTree.addRight(middleChildOfRoot, 7);
        Position<Integer> middleChildOfrightOfmiddleChildOfRoot = mainTree.addMiddle(rightOfmiddleChildOfRoot, 10);
        Position<Integer> rightChildOfRoot = mainTree.addRight(rootOfMainTree, 4);
        Position<Integer> rightChildOfrightChildOfRoot = mainTree.addRight(rightChildOfRoot, 8);

        Iterable<Position<Integer>> result = mainTree.triorder();

        for (Position<Integer> position : result) {
            System.out.println(position.getElement());
        }


    }
}
