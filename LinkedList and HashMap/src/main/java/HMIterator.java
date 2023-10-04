/*
Student: Maria Bezerra
UIN: 676493398
netId: mbeze2@uic.edu
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


class KeyValueHolder<T> {
    String key;
    T value;

    public KeyValueHolder(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

}

class HMIterator<T> implements Iterator<T> {
    private ArrayList<GenericQueue<KeyValueHolder<T>>> map;
    private int currentIndex = 0;
    private Iterator<KeyValueHolder<T>> currentQueueIterator;

    public HMIterator(ArrayList<GenericQueue<KeyValueHolder<T>>> map) {
        this.map = map;
        if (!map.isEmpty()) {
            currentQueueIterator = map.get(currentIndex).iterator();
        }
    }

    @Override
    public boolean hasNext() {
        // Skip any empty queues
        while (currentIndex < map.size() && (currentQueueIterator == null || !currentQueueIterator.hasNext())) {
            currentIndex++;
            if (currentIndex < map.size()) {
                currentQueueIterator = map.get(currentIndex).iterator();
            } else {
                currentQueueIterator = null;
            }
        }

        return currentQueueIterator != null && currentQueueIterator.hasNext();
    }

    @Override
    public T next() {
        if (hasNext()) {
            return currentQueueIterator.next().getValue();
        } else {
            throw new NoSuchElementException();
        }
    }
}
