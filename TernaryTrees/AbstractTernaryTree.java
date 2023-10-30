package sources;




public abstract class AbstractTernaryTree<E> extends AbstractTree<E> implements TernaryTree<E> {
    /**
     * (a) siblings
     * (b) numChildren
     * (c) children
     * (d) triorder: returns an iterable of positions of the tree in “triorder” traversal. Visits the nodes in Left-Parent-Middle-Parent-Right manner.
     * Parent is always visited after visiting the left subtree. Parent might be optionally visited between the middle and the right nodes if both of them exist.
     * (e) triorderSubtree: private helper method that adds positions of the subtree rooted at position p to an iterable container using a triorder traversal.
     * (f) positions: Returns an iterable collection of the positions of the tree using triorder traversal.
     */


    /**
     * Returns Iterable of Positions of p's siblings (or null if no sibling exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the sibling (or null if no sibling exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */

    public Iterable<Position<E>> siblings(Position<E> p) {
        if (parent(p) == null) return null;                  // p must be the root
        java.util.List<Position<E>> snapshot = new java.util.ArrayList<>(3);// max capacity of 2
        if(left(p) != null && middle(p) != null && right(p) != null){
            snapshot.add(left(p));
            snapshot.add(middle(p));
            snapshot.add(right(p));
        }
        if (right(p) == null && left(p) != null && middle(p) != null ){
            snapshot.add(left(p));
            snapshot.add(middle(p));
        }
        if (left(p) == null && right(p) != null && middle(p) != null){
            snapshot.add(middle(p));
            snapshot.add(right(p));
        }
        if (middle(p) == null && left(p) != null && right(p) != null ){
            snapshot.add(left(p));
            snapshot.add(right(p));
        }
        if (middle(p) == null && left(p) == null && right(p) != null || left(p) == null && middle(p) == null && right(p) != null){
            snapshot.add(right(p));
        }
        if (middle(p) == null && right(p) == null && left(p) != null || right(p) == null && middle(p) == null && left(p) != null){
            snapshot.add(left(p));
        }
        if (right(p) == null && left(p) == null && middle(p) != null || left(p) == null && right(p) == null && middle(p) != null){
            snapshot.add(middle(p));
        }

        return snapshot;
    }

    /**
     * Returns the number of children of Position p.
     *
     * @param p    A valid Position within the tree
     * @return number of children of Position p
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public int numChildren(Position<E> p) {
        int count=0;
        if (left(p) != null)
            count++;
        if (right(p) != null)
            count++;
        if (middle(p) != null)
            count++;
        return count;
    }

    /**
     * Returns an iterable collection of the Positions representing p's children.
     *
     * @param p    A valid Position within the tree
     * @return iterable collection of the Positions of p's children
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        java.util.List<Position<E>> snapshot = new java.util.ArrayList<>(2);    // max capacity of 2
        if (left(p) != null)
            snapshot.add(left(p));
        if (middle(p) != null)
            snapshot.add(middle(p));
        if (right(p) != null)
            snapshot.add(right(p));

        return snapshot;
    }

    /**
     * Adds positions of the subtree rooted at Position p to the given
     * snapshot using an inorder traversal
     * @param p       Position serving as the root of a subtree
     * @param snapshot  a list to which results are appended
     */
    //TODO CHECK
    private void triorderSubtree(Position<E> p, ArrayList<Position<E>> snapshot) {
        if (left(p) != null)
            triorderSubtree(left(p), snapshot);

        snapshot.add(p);
        if(middle(p) != null){
            triorderSubtree(middle(p), snapshot);
            if(right(p) != null)
                snapshot.add(p);
        }

        if (right(p) != null)
            triorderSubtree(right(p), snapshot);

    }

    /**
     * Returns an iterable collection of positions of the tree, reported in triorder.
     * @return iterable collection of the tree's positions reported in triorder
     */
    public Iterable<Position<E>> triorder() {
        ArrayList<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty())
            triorderSubtree(root(), snapshot);   // fill the snapshot recursively
        return snapshot;
    }

    /**
     * Returns an iterable collection of the positions of the tree using inorder traversal
     * @return iterable collection of the tree's positions using inorder traversal
     */
    @Override
    public Iterable<Position<E>> positions() {
        return triorder();
    }


}
