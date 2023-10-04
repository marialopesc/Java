
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HMTest {
    private MyHashMap<Integer> hashMap;

    @BeforeAll
    public void setUp() {
        hashMap = new MyHashMap<>("testKey", 42);
    }

    @Test
    public void testPutAndGet() {
        hashMap.put("key1", 100);
        assertEquals(100, (int) hashMap.get("key1"));
    }

    @Test
    public void testContains() {
        hashMap.put("key1", 100);
        assertTrue(hashMap.contains("key1"));
        assertFalse(hashMap.contains("key2"));
    }

    @Test
    public void testSize() {
        hashMap.put("key1", 100);
        hashMap.put("key2", 200);
        assertEquals(2, hashMap.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(hashMap.isEmpty());
        hashMap.put("key1", 100);
        assertFalse(hashMap.isEmpty());
    }

    @Test
    public void testReplace() {
        hashMap.put("key1", 100);
        assertEquals(100, (int) hashMap.replace("key1", 200));
        assertEquals(200, (int) hashMap.get("key1"));
    }

    @Test
    public void testIteratorNextWithNoElements() {
        Iterator<Integer> iterator = hashMap.iterator();
        assertFalse(iterator.hasNext());

        try {
            iterator.next();
            fail("Expected NoSuchElementException was not thrown");
        } catch (NoSuchElementException e) {
            // NoSuchElementException was thrown, which is expected
        }
    }


    @Test
    public void testIterator() {
        hashMap.put("key1", 100);
        hashMap.put("key2", 200);
        Iterator<Integer> iterator = hashMap.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(100, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(200, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testDescendingIterator() {
        hashMap.put("key1", 100);
        hashMap.put("key2", 200);
        Iterator<Integer> iterator = HashMap.descendingIterator();
        assertTrue(iterator.hasNext());
        assertEquals(200, (int) iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(100, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }
}
