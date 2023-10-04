
import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericQueue<T> extends GenericList<T> {
    private Node<T> tail;
    private int size;

    public GenericQueue() {
        super();
        this.tail = null;
        this.size = 0;
    }

    public GenericQueue(T value, int code) {
        super(value, code);
        this.tail = getHead();
        this.size = 0;
    }

    // Accessor to get the tail of the queue
    public Node<T> getTail() {
        return this.tail;
    }

    public int size() {
        return size;
    }

    public void enqueue(T data, int code) {
        add(data, code);
    }

    public void enqueue(T data) {
        add(data);
    }

    public T dequeue() {
        return delete();
    }

    @Override
    public void add(T data) {
        add(data, 0);
    }

    public void add(T data, int code) {
        Node<T> newNode = new Node<T>(data, code);

        if (getHead() == null) {
            setHead(newNode);
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        setLength(getLength() + 1);
        size++;
    }

    public boolean isEmpty() {
        return getHead() == null;
    }

    @Override
    public T delete() {
        if (getHead() == null) {  // If the list is empty
            return null;
        }

        if (getHead() == tail) {  // If there's only one element
            T value = tail.data;
            setHead(null);
            tail = null;
            setLength(0);
            return value;
        }

        Node<T> current = getHead();
        while (current.next != tail) {
            current = current.next;
        }

        T value = tail.data;
        tail = current;
        tail.next = null;
        setLength(getLength() - 1);

        return value;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = getHead();

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }


}
