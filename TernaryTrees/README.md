**Term/Year:** Fall 2022

**Subject Code and Course Number**: CS120 Intro to OOP, Sections B & D

**University and Faculty:** American University of Armenia, CSE

**Instructor name**:  Monika Stepanyan

**Trees, Binary Trees, Ternary Trees**: 木




**AbstractTree**:   Added methods
**(a)** method ancestors(p) that returns an ArrayList containing the ancestors of position p or-
dered from the root to p.
**(b)** method path(p1, p2) that returns a path connecting two positions p1 and p2 as an iterable
collection of positions. You should use the ancestors() method in your implementation.



**TernaryTree**:  Created a generic interface TernaryTree that represents the concept of an ordered generic Tree where each node has at most three children. The children are going to be identified as the left, the middle and the right one. The interface should include methods for returning the positions of respective children, given the position of a parent as well as a method for returning an iterable of positions of the siblings of a position. An appropriate error must be thrown if the argument position is invalid.

**AbstractTernaryTree**:  Created a generic abstract class AbstractTernaryTree that is a generic TernaryTree and provides concrete implementations for some of the methods inherited from ancestor interfaces. These should include
**(a)** siblings
**(b)** numChildren
**(c)** children
**(d)** triorder: returns an iterable of positions of the tree in “triorder” traversal. Visits the nodes in Left-Parent-Middle-Parent-Right manner. Parent is always visited after visiting the left subtree. Parent might be optionally visited between the middle and the right nodes if both of them exist.
**(e)** triorderSubtree: private helper method that adds positions of the subtree rooted at position p to an interable container using a triorder traversal.
**(f)** positions: Returns an iterable collection of the positions of the tree using triorder traversal.



**LinkedTernaryTree**:  Created a generic class LinkedTernaryTree that extends the generic AbstractTernaryTree. The class should include all the functionality/attributes of the LinkedBinaryTree with appro-
priate modifications. Include a main method in this class and create a tree corresponding to Figure 1. The tree should be constructed by first constructing the left, middle and right subtrees of 1 as separate trees and then attaching them to the root node. Print the elements using breadth-first traversal.

**ArrayTernaryTree**:  Created a generic class ArrayTernaryTree that extends the generic AbstractTernaryTree and represents an array-based implementation of a ternary tree. Note that each position in this implementation will be associated with a particular index (computed
through level numbering function).
The class should have two instance variables– an ArrayList for storing the Positions and an int representing the size of the tree.
The class should have two constructors– one receiving a default capacity for the underlying list, and one initializing it to 1000. Both constructors should insert null elements into the list so that to fill the given capacity.
The methods of your class that add new elements into the tree are not allowed to add null values. These methods are also not allowed to add a node such that its index assigned by level numbering function is larger than the capacity of the container.
The class should include an inner static generic class Cell that implements generic position and represents a combination of an element and its associated index. This class should include the following methods:
**(a)** A two-argument constructor
**(b)** Getters for the instance variables (including the inherited methods)
**(c)** Setters for the instance variables
**(d)** getLeftIndex, getMiddleIndex, getRightIndex, getParentIndex that correspondingly return the index of the left/middle/right child or the parent position
The class ArrayTernaryTree should include all the methods included in LinkedTernaryTree with the exception of attach and remove.
Includes a main method in this class and create a tree corresponding to Figure 1. Print the elements using triorder traversal.
