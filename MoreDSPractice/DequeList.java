import java.util.Iterator;

public class DequeList<E> implements List<E> {
    private Deque<E> deque;

    public DequeList(Deque<E> deque) {
        this.deque = deque;
    }

    public int size() {
        return deque.size();
    }
    //T(n) is constant since it just has one return statement

    public boolean isEmpty() {
        return deque.isEmpty();
    }
    //T(n) is constant since it just has one return statement

    public E get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Invalid index :(");
        }

        if (i == 0) {
            return deque.first();
            //best case    T(n) is constant since it just has one return statement
        } else if (i == size() - 1) {
            //best case T(n) is constant since it just has one return statement
            return deque.last();
        } else {
            E element;
            for (int k = 0; k < i; k++) {
                //worst case T(n) is linear (specifically 2n as there are two basic operations) since 'i' might be the last index, and it traverses until that
                element = deque.removeFirst();
                deque.addLast(element);
            }
            element = deque.first();
            return element;
        }
    }

    @Override
    public E set(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Invalid index :(");
        }

        if (i == 0) {
            //best case T(n) is constant since it just has one return statement and a few basic operations
            E replacedElement = deque.first();
            deque.removeFirst();
            deque.addFirst(e);
            return replacedElement;
        } else {
            E replacedElement;
            if (i == size() - 1) {
                //another best case
                replacedElement = deque.last();
                deque.removeLast();
                deque.addLast(e);
            } else {

                for (int k = 0; k < i; k++) {
                    //worst case T(n) is linear since 'i' might be the one previous from last index, and it traverses until that

                    replacedElement = deque.removeFirst();
                    deque.addLast(replacedElement);
                }
                replacedElement = deque.first();
                deque.removeFirst();
                deque.addFirst(e);
            }
            return replacedElement;
        }
    }


    @Override
    public void add(int i, E e) throws IndexOutOfBoundsException {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("Invalid index :(");
        }

        if (i == 0) {
            //best case T(n) is constant since it just adds element in front of dequeue
            deque.addFirst(e);
        } else if (i == size()) {
            //best case T(n) is constant since it just adds element in rear of dequeue
            deque.addLast(e);
        } else {
            for (int k = 0; k < i; k++) {
                deque.addLast(deque.removeFirst());
            }
            deque.addLast(e);

            for (int k = 0; k < i - 1; k++) {
                deque.addLast(deque.removeFirst());
            }

            //worst case T(n) is linear since 'i' might be the one previous from last index, and it traverses until that

        }
    }

    public E remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Invalid index :(");
        }

        //O(1)
        if (i == 0) {
            return deque.removeFirst();
        } else if (i == size() - 1) {
            //O(1)
            return deque.removeLast();
        } else {
            //O(n)
            for (int k = 0; k < i; k++) {
                deque.addLast(deque.removeFirst());
            }
            E removedElement = deque.removeFirst();
            deque.addLast(deque.removeFirst());
            return removedElement;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
