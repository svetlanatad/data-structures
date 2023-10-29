
public class PositionalQuickSort {

    //it used to work before i modified the iterator

    public static <E extends Comparable<E>> void quickSort(LinkedPositionalList<E> list) {
        int size = list.size();
        if (size <= 1) {
            return;
        }

        Position<E> pivot = list.last();

        LinkedPositionalList<E> less = new LinkedPositionalList<>();

        LinkedPositionalList<E> equal = new LinkedPositionalList<>();
        LinkedPositionalList<E> greater = new LinkedPositionalList<>();

        for (Position<E> position : list.positions()) {

            if (position.getElement().compareTo(pivot.getElement()) < 0) {

                less.addLast(position.getElement());

            } else if (position.getElement().compareTo(pivot.getElement()) == 0) {

                equal.addLast(position.getElement());
            } else {
                greater.addLast(position.getElement());
            }
        }

        quickSort(less);
        quickSort(greater);

        /**I'm not sure if I was supposed to use an iterator to get rid of list elements, since
         it would be more efficient, but
        I just found myself extremely confused on how I should remove List elements without
         modifying them incorrectly. ;-;
         **/
        while(!list.isEmpty()){
            list.remove(list.first());
        }
        for (Position<E> position : less.positions()) {
            list.addLast(position.getElement());
        }
        for (Position<E> position : equal.positions()) {
            list.addLast(position.getElement());
        }
        for (Position<E> position : greater.positions()) {
            list.addLast(position.getElement());
        }
    }

    public static void main(String[] args) {
        LinkedPositionalList<Integer> list = new LinkedPositionalList<>();
        list.addLast(12);
        list.addLast(4);
        list.addLast(7);
        list.addLast(420);
        list.addLast(8);
        list.addLast(98);
        list.addLast(69);

        System.out.println("Original list " + list);

        quickSort(list);

        System.out.println("Sorted list " + list);



    }
}
