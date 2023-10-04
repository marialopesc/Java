
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyHashMap<T> implements Iterable<T> {
    private ArrayList<GenericQueue<KeyValueHolder<T>>> map;

    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);
        put(key, value);
    }

    public void put(String key, T value) {
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;

        if (map.get(hashValue) == null) {
            GenericQueue<KeyValueHolder<T>> queue = new GenericQueue<>(new KeyValueHolder<T>(key, value), hashCode);
            map.add(hashValue, queue);
        } else {
            map.get(hashValue).add(new KeyValueHolder<T>(key, value), hashCode);
        }
    }

    public boolean contains(String key) {
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;

        if (map.get(hashValue) == null) {
            return false;
        }

        for (KeyValueHolder<T> item : map.get(hashValue)) {
            if (item.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    public T get(String key) {
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;

        if (map.get(hashValue) == null) {
            return null;
        }

        for (KeyValueHolder<T> item : map.get(hashValue)) {
            if (item.key.equals(key)) {
                return item.value;
            }
        }
        return null;
    }

    public int size() {
        int count = 0;
        for (GenericQueue<KeyValueHolder<T>> queue : map) {
            if (queue != null) {
                count += queue.size();
            }
        }
        return count;
    }

    public boolean isEmpty() {
        for (GenericQueue<KeyValueHolder<T>> queue : map) {
            if (queue != null && !queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public T replace(String key, T value) {
        int hashCode = key.hashCode();
        int hashValue = hashCode & 9;

        if (map.get(hashValue) != null) {
            Iterator<KeyValueHolder<T>> iterator = map.get(hashValue).iterator();
            while (iterator.hasNext()) {
                KeyValueHolder<T> entry = iterator.next();
                if (entry.key.equals(key)) {
                    T oldValue = entry.value;
                    entry.value = value;
                    return oldValue;
                }
            }
        }

        return null; // Key not found
    }


    @Override
    public Iterator<T> iterator() {
        return new HMIterator();
    }

    private class HMIterator implements Iterator<T> {
        private int currentQueueIndex;
        private Iterator<KeyValueHolder<T>> currentQueueIterator;

        public HMIterator() {
            this.currentQueueIndex = 0;
            if (!map.isEmpty()) {
                this.currentQueueIterator = map.get(0).iterator();
            }
        }

        @Override
        public boolean hasNext() {
            while (currentQueueIterator != null && !currentQueueIterator.hasNext()) {
                currentQueueIndex++;
                if (currentQueueIndex >= map.size()) {
                    currentQueueIterator = null;
                } else {
                    currentQueueIterator = map.get(currentQueueIndex).iterator();
                }
            }
            return currentQueueIterator != null && currentQueueIterator.hasNext();
        }

        @Override
        public T next() {
            if (hasNext()) {
                return currentQueueIterator.next().value;
            } else {
                throw new NoSuchElementException();
            }
        }
    }

    public static class KeyValueHolder<T> {
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



}