

import java.util.Iterator;
import java.util.NoSuchElementException;


public class GLLIterator<E> implements Iterator<E> {
    private GenericQueue.Node<E> current;

    public GLLIterator(GenericQueue.Node<E> head) {
        this.current = head;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public E next() {
        if (current == null) {
            throw new NoSuchElementException();
        }
        E data = current.data;
        current = current.next;
        return data;
    }
}
