package grr;
import java.util.Arrays;
/**
 * Write a program that given an Integer array with strictly increasing numbers, constructs a LinkedBinaryTree such that the resulting tree satisfies the binary search tree relational property, and has the smallest possible height.
 * Your class should include methods with the following headings:
 * // f i l l s the given empty tree with the values from data according to the description given above .
 * public static <E> void constructTree(LinkedBinaryTree<E> tree , E[] data)
 * //recursive method for achieving the goal .
 * private static <E> void constructTree(LinkedBinaryTree<E> tree , Position<E> parent , ???, ???, ???, E[] data)
 * Test your program in the main method. Traverse the tree in inorder to ensure that the result is correct. Compare the resulting height with the expected one.
 */

public class SortedToBST {
    public static <E> void constructTree(LinkedBinaryTree<E> tree, E[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("GRRRR!!!!! >:(((((((((");
        }
        tree.root = constructTree(tree, null, true, true, data);
    }
    private static <E> LinkedBinaryTree.Node<E> constructTree(LinkedBinaryTree<E> tree, LinkedBinaryTree.Node<E> parent, boolean isLeft, boolean isRight, E[] data) {
        if (data.length == 0) {
            return null;
        }
        int midpoint = data.length / 2;
        E element = data[midpoint];
        LinkedBinaryTree.Node<E> node = tree.createNode(element, parent, null, null);
        if (parent != null) {
            if (isLeft){
                parent.setLeft(node);
            }else if(isRight){
                parent.setRight(node);
            }
        }else{
            tree.root = node;
        }
        constructTree(tree, node, true, false, Arrays.copyOfRange(data, 0, midpoint));
        constructTree(tree, node, false, true, Arrays.copyOfRange(data, midpoint + 1, data.length));
        return node;
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        constructTree(tree, data);
        System.out.println("Here's the inorder traversal!! ??--n8jx44jddb this is not a reference, take a guess what it can be! :)");
        inorder(tree, tree.root());
        System.out.println("The expected height is the minimal one, which is, log n where n is num of elements, so expected height is 3, now the actual height is: " + tree.height(tree.root()) + "comparing them will return 0, so they're equal to each other ");
    }

    public static <E> void inorder(LinkedBinaryTree<E> tree, Position<E> position) {
        if (position != null) {
            inorder(tree, tree.left(position));
            System.out.print(position.getElement() + " ");
            inorder(tree, tree.right(position));
        }
    }
}
