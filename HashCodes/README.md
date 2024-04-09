American University of Armenia, CSE CS121 Data Structures B
Fall 2023
Homework Assignment 5
Due Date: Tuesday, Dec 5, by 23:59 electronically on Moodle
Solve the programming tasks using Java, following good coding practices and required format.
1. (SortedToBST | 20 points) Write a program that given an Integer array with strictly increasing numbers, constructs a LinkedBinaryTree such that the resulting tree satisfies the binary search tree relational property, and has the smallest possible height.
Your class should include methods with the following headings:
// f i l l s the given empty tree with the values from data according to the description given above .
public static <E> void constructTree(LinkedBinaryTree<E> tree , E[] data)
//recursive method for achieving the goal .
private static <E> void constructTree(LinkedBinaryTree<E> tree , Position<E> parent , ???, ???, ???, E[] data)
Test your program in the main method. Traverse the tree in inorder to ensure that the result is correct. Compare the resulting height with the expected one.
2. (SortedMapList | 25 points) Write a class SortedMapList that implements List using a single efficient instance of a SortedMap as un underlying container.
Specify and justify the running times of all the methods in your implementation.
3. (55 points) This task investigates the implementation of several hashing approaches for Strings to be used for a hash map.
First, create a generic interface HashCode with a single method hashCode, that will be responsible for taking in an object and returning a hash code value.
Next, create a class StringHolder designed to store a single instance variable of type String. Add a one-argument constructor and a getter.
Next, create five concrete implementations of HashCode;
(a) Generic DefaultHashCode where hashCode returns the default hash value for an object using
the System.identityHashCode()
(b) SummingHashCode where hashCode sums up the ascii values of the argument StringHolder’s
string’s characters and returns it as the hash value.
(c) ProductHashCode where hashCode multiplies the ascii values of the argument StringHolder’s string’s characters and returns the product as the hash value
(d) PolynomialHashCode where the constructor of the class accepts a positive constant argument c that is going to be used when computing the hash value. The hashCode method should use Horner’s rule to compute the hash code based on the ascii values of the characters of the argument StringHolder’s string.
(e) StringHashCode where hashCode returns the hash value of the argument StringHolder’s string.
Create a class CompressionFunction that is going to be responsible for mapping the hash code to the range [0, N − 1]. It should have the following
(a) An instance variable N representing the range. (b) One-argument constructor and a getter.
(c) The method value that takes as an argument an integer value and maps it to the range [0, N-1].
1
Create a class MADCompressionFunction that is a compression function and uses the MAD ap- proach. It should have the following
(a) Three instance variables a, b and p.
(b) One-argument constructor that takes N as argument and calls the super constructor. It also
initializes p to the prime number that comes after N and randomly initializes a and b.
(c) Overridden method value that uses the MAD compression.
Create a class HashFunction that represents a combination of a hash code and compression func- tion. It should have two instance variables of the appropriate types and a two-argument constructor.
Finally, create class StringHolderHashMap which extends AbstractMap with StringHolder Keys and represents a Hash Map with separate chaining. The underlying container should be an array of ArrayLists. In addition, it should have two instance variables hashFunction and capacity where capacity represents the size of the underlying array.
In addition to overriding the necessary inherited methods, your class should support all of the following functionality:
(a) Two-argument constructor that takes as an input the length of the underlying container and a HashFunction
(b) Method totalNumberOfCollisions that calculates the total number of collisions along all the buckets. You can define the number of collisions as the number of elements in each bucket that exceeds one. That is, if you have three elements in a bucket, then the number of collisions is 2 (calculated as 3 − 1).
(c) Method maxCollisions that calculates the maximum number of collisions along all the buck- ets.
(d) Method emptyBuckets that calculates the number of empty buckets.
(e) Method averageNumberInEachBucket that calculates the average number of entries in all
non-empty buckets.
Create a main method for testing your implementation inside the StringHolderHashMap. Create 10 maps of size 106921. Each one of these should use one of the defined hash codes and one of the two available compression functions. Use the .txt file attached to the assignment (containing around 58K English words) to fill those maps. For each map, print out the total and maximum number of collisions, maximum number of collisions, number of empty buckets and average number of entries in non-empty buckets.
