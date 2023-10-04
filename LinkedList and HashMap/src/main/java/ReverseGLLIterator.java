
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;


public class ReverseGLLIterator<E> implements Iterator<E> {
    private GenericList<E> list; // Change the data type to GenericList
    private GenericList.Node<E> current;
    private GenericList.Node<E> previous;

    public ReverseGLLIterator(GenericQueue<E> queue) {
        this.queue = queue;
        this.current = queue.getTail();
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

        // Traverse to find the node right before the current node
        previous = null;
        GenericQueue.Node<E> tmp = queue.getHead();
        while (tmp != current) {
            previous = tmp;
            tmp = tmp.next;
        }
        current = previous;

        return data;
    }
}
