/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Realization of a stack as an adaptation of a SinglyLinkedList.
 * All operations are performed in constant time.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 * @see SinglyLinkedList
 */
public class LinkedStack<E> implements Stack<E> {

  /**
   * The primary storage for elements of the stack
   */
  private SinglyLinkedList<E> list = new SinglyLinkedList<>();   // an empty list


  /**
   * Constructs an initially empty stack.
   */
  public LinkedStack() {
  }                   // new stack relies on the initially empty list

  /**
   * Returns the number of elements in the stack.
   *
   * @return number of elements in the stack
   */

  //O(1) becase returen
  @Override
  public int size() {
    return list.size();
  }

  /**
   * Tests whether the stack is empty.
   *
   * @return true if the stack is empty, false otherwise
   */

  //O(1) becase returen

  @Override
  public boolean isEmpty() {
    return list.isEmpty();
  }

  /**
   * Inserts an element at the top of the stack.
   *
   * @param element the element to be inserted
   */
  //O(1) becase addfirst

  @Override
  public void push(E element) {
    list.addFirst(element);
  }

  /**
   * Returns, but does not remove, the element at the top of the stack.
   *
   * @return top element in the stack (or null if empty)
   */

  //O(1) because return
  @Override
  public E top() {
    return list.first();
  }

  /**
   * Removes and returns the top element from the stack.
   *
   * @return element removed (or null if empty)
   */
  //O(1) because return

  @Override
  public E pop() {
    return list.removeFirst();
  }




  /**
   * Produces a string representation of the contents of the stack.
   * (ordered from top to bottom)
   * <p>
   * This exists for debugging purposes only.
   *
   * @return textual representation of the stack
   */

  //  //O(1) because return
  public Iterator<E> iterator() throws CloneNotSupportedException {
    return new LinkedStackIterator();     // create a new instance of the inner class
  }

  //o(n) becausd has to print elemtns
  public void printStack() throws CloneNotSupportedException {
    if(isEmpty()){
      throw new IllegalStateException();
    }

    Iterator<E> iterator = iterator();
    while (iterator.hasNext()) {
      E element = iterator.next();
      System.out.print(element + " ");
    }
    System.out.println();
  }

  private class LinkedStackIterator implements Iterator<E> {

    private SinglyLinkedList<E> listClone = list.clone();


      private E current = list.first();

    private LinkedStackIterator() throws CloneNotSupportedException {
    }
//o(1) beacsue reutrn
    public boolean hasNext() {
      return !listClone.isEmpty();
    }
//o(1) becasue return
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      E element = listClone.removeFirst();
      current = element;
      return element;

    }
    //both reomves are o(n) because this is inefficient implementation
    public void remove(){
      int count = 0;

      while(!hasNext()){
        count++;
      }
      remove(count);

    }
    public void remove(int index) {
      if (index < 0 || index >= size()) {
        throw new IndexOutOfBoundsException("out of bounds");
      }

      if (index == 0) {
        list.removeFirst();
      } else {
        SinglyLinkedList<E> tempList = new SinglyLinkedList<>();
        int count = 0;

        while (!list.isEmpty()) {
          if (count == index - 1) {
            list.removeFirst();
            break;
          }
          tempList.addLast(list.removeFirst());
          count++;
        }


        while (!tempList.isEmpty()) {
          list.addLast(tempList.removeFirst());
        }
      }
    }


  }




}
