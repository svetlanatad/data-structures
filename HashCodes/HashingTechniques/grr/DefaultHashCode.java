package grr;
public class DefaultHashCode<E> implements HashCode<E> {
    public int hashCode(E element) {
        return System.identityHashCode(element);
    }
}
