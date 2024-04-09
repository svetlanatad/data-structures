American University of Armenia, CSE CS121 Data Structures B
Fall 2023
Homework Assignment 4
Due Date: Sunday, November 19 by 23:59 electronically on Moodle
Solve the programming tasks using Java, following good coding practices and required format.
1. (DHeapPriorityQueue | 35 points) Create a class DHeapPriorityQueue that represents a concrete implementation of the AbstractPriorityQueue which uses a d-ary heap as the underlying container. D-ary heap is a generalization of a binary heap, where nodes have d children instead of two.
The class should have two instance variables– d representing the possible number of children (should be greater than or equal to 2) and an ArrayList of entries called heap.
The class should implement all the necessary methods of the parent class as well as include the following functionality:
• A helper method for checking of the current position has an “i-th” child.
• A helper method for returning the index of the possible “i-th” child.
• A helper method that given the index of a position, returns the index of the parent position.
• A helper method for exchanging positions of two elements in the heap.
• A helper upheap(i) that moves the entry at index i higher, if necessary, to restore the heap property.
• A helper downheap(i) that moves the entry at index i lower, if necessary, to restore the heap property.
• A helper heapify() that performs a bottom-up construction of the heap.
• A constructor that takes two arguments– d value and Comparator and initializes the instance
variables accordingly.
• A constructor that takes one argument– d.
• A constructor that takes no arguments and initializes d to be 2.
• A constructor that takes three arguments– d, an ArrayList of keys and an ArrayList of values (both of same size), and efficiently constructs the priority queue.
Create a main method in your class. Create an array of size 1000000 and fill it with integer values in the range [0, 1000]. Create four DHeapPriorityQueue of integer keys and values (should be based on the generated array). Let d for each one be 2, 3, 4 and 10 respectively. Also create an object of type HeapPriorityQueue. Measure the time spend on constructing each queue (in milliseconds) and report each one separately. Now empty all the queues, and again, separately measure and report the time spend on emptying each one.
Run the program several times and discuss the obtained results.
2. (ComparePQSorts | 15 points) Create a class ComparePQSorts that is going to consist of a single main method. The method should first create an array of size 20000 and fill it with integer val- ues in the range [0, 100000]. Then it should create four PriorityQueues– two HeapProrityQueues, one SortedPriorityQueue and one UnsortedPriorityQueue. One of the heap priority queues should be constructed bottom up.
You should measure and report the time (in nanoseconds) taken by each phase of the priority queue sort using each of the four objects. Run the program several times and discuss the obtained results. For each phase, rank the objects according to their time efficiency.
1
3. (MaxPriorityQueue | 5 points) Create a generic interface MaxPriorityQueue that represents the idea of a priority queue where the higest priority is given to the key with the maximum value. The methods included should be:
• size()
• isEmpty()
• insert(.....): inserts a key-value pair and does not return anything
• max(): returns the value that corresponds to the maximum key
• removeMax(): removes and returns the value that corresponds to the maximum key
4. (ConcreteMaxPriorityQueue | 20 points) Create a generic class ConcreteMaxPriorityQueue that efficiently implements MaxPriorityQueue with integer keys using a single object of type PriorityQueue.
5. (UnsortedQueueMap | 25 points) Write a generic class UnsortedQueueMap that extends the AbstractMap class using a queue as the underlying data structure.
Your class should support all of the following functionality:
(a) a no-arg constructor that creates an empty map;
(b) size method (note that isEmpty is inherited from AbstractMap);
(c) the fundamental methods get, put, and remove from the Map ADT. Note that you are not allowed to use additional data structures or recursion here.
(d) the entrySet method from the Map ADT.
You may add utility method(s) to your class. Specify and justify the running times of all the methods.
