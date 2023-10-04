
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class GenericList<T> implements Iterable<T> {

    protected Node<T> head;
    protected int length;

    public static class Node<T> {
        T data;
        int code;
        Node<T> next;

        public Node(T val, int code) {
            this.data = val;
            this.code = code;
            this.next = null;
        }
    }

    public GenericList() {
        this.head = null;
        this.length = 0;
    }

    public GenericList(T value, int code) {
        this.head = new Node<T>(value, code);
        this.length = 1;
    }

    public void print() {
        Node<T> temp = head;

        if (temp == null) {
            System.out.println("Empty List");
            return;
        }

        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public abstract void add(T data);

    public abstract T delete();

    public ArrayList<T> dumpList() {
        ArrayList<T> dumpArray = new ArrayList<T>();
        Node<T> current = head;

        while (current != null) {
            dumpArray.add(current.data);
            current = current.next;
        }

        return dumpArray;
    }

    public T get(int index) {
        if (index < 0 || index >= length) {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public T set(int index, T element) {
        if (index < 0 || index >= length) {
            return null;
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T prevElement = current.data;
        current.data = element;

        return prevElement;
    }

    public int getLength() {
        return length;
    }

    protected void setLength(int length) {
        this.length = length;
    }

    public Node<T> getHead() {
        return head;
    }

    protected void setHead(Node<T> head) {
        this.head = head;
    }

    @Override
    public Iterator<T> iterator() {
        return new GLLIterator<>(head);
    }

    public Iterator<T> descendingIterator() {
        return new ReverseGLLIterator<>(this); // Pass the GenericList itself as the argument
    }



}
