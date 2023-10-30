package sources;


public interface TernaryTree<E> extends Tree<E> {

    /**
     * Create a generic interface TernaryTree that represents the concept of an ordered generic Tree where
     * each node has at most three children. The children are going to be identified as the left,
     * the middle and the right one. The interface should include methods for returning the positions of respective
     * children, given the position of a parent as well as a method for returning an iterable of positions of the
     * siblings of a position. An appropriate error must be thrown if the argument position is invalid.
     */

    //root
    //parent throws exception
    //iterable position children
    //numChildren throws exception
    //isInternal throws exception
    //isExternal throws exception
    //isRoot throws exception
    //size
    //isEmpty
    //iterator
    // iterable positions
    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */


    Position<E> left(Position<E> p) throws IllegalArgumentException;
    /**
     * Returns the Position of p's middle child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */


    Position<E> middle(Position<E> p) throws IllegalArgumentException;
    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */

    Position<E> right(Position<E> p) throws IllegalArgumentException;

    /**
     * Returns an Iterable of Positions of p's siblings (or null if no sibling exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the sibling (or null if no sibling exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */

    Iterable<Position<E>> siblings(Position<E> p) throws IllegalArgumentException;













}
