**Term/Year:** Fall 2022

**Subject Code and Course Number**: CS121 Data Structures, Section B

**University and Faculty:** American University of Armenia, CSE

**Instructor name**  Monika Stepanyan

**Data Structures and Sorting Algorithms Practice** :)

**DequeList**: Creates a class DequeList that efficiently implements List using a Deque as the underlying container. The constructor of this class should receive an argument of generic Deque type. The methods should not consume any extra memory. Specify and justify the execution times of each of the List methods in this implementation.

**Alphabet**: Given an array list of unique lower case Latin letters sorted in decreasing order, adds the letters that are missing to get the full alphabet. Similarly, write a method that does the same thing for a linked positional list. Both methods should use no additional space. Specifies and justify the running times of the two methods.

**PositionalQuickSort**: Creates a class PositionalQuickSort that has a static generic method for sorting a LinkedPositionalList using the Quick-Sort algorithm (pivot should be the last element). Make sure you use for-each loops whenever possible. Add a main method, and test your program.

**ArrayList**:  Modifies the ArrayList class, so that the underlying array shrinks in half when the number of elements falls bellow the 14 of the capacity.
In addition, modify the ArrayIterator class so that to support “fail-fast” behavior.
All of the methods of ArrayIterator should throw ConcurrentModificationException when needed.

**LinkedStack**: Makes the class LinkedStack efficiently Iterable. Specify and justify the execution times of each of the methods in this implementation. Add a method that prints the contents of the calling stack using an iterator. 

**LinkedPositionalList**:  Changes the behavior of the PositionIterator so that one full iteration (traversal) consists of going from start till end then back to start of the list. That is, for a list with the following values:
The full iteration would look like:
1,4,5,7,0 1,4,5,7,0,0,7,5,4,1