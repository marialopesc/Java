
// Testing for GenericQueue class
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GQTest {

    private GenericQueue<String> queue;
    private GenericQueue<Integer> queueInt;


    @BeforeEach
    void setUp() {
        // Initializing a fresh queue for each test
        queue = new GenericQueue<>();
    }

    @Test
    void constructorShouldInitializeEmptyQueue() {
        assertNotNull(queue);
        assertNull(queue.getHead());
        assertNull(queue.getTail());
        assertEquals(0, queue.getLength());
    }

    @Test
    void nodeConstructorShouldInitializeNodeCorrectly() {
        GenericList.Node<String> node = new GenericList.Node<>("Test", 1);
        assertNotNull(node);
        assertEquals("Test", node.data);
        assertEquals(1, node.code);
        assertNull(node.next);
    }

    @Test
    void enqueueWithDataAndCodeShouldAddToEmptyQueue() {
        queue.enqueue("Test", 1);
        assertNotNull(queue.getHead());
        assertEquals("Test", queue.getHead().data);
        assertEquals(1, queue.getHead().code);
        assertEquals(1, queue.getLength());
        assertEquals(queue.getHead(), queue.getTail()); // Check tail after enqueue
    }

    @Test
    void enqueueWithDataShouldAddWithDefaultCode() {
        queue.enqueue("Test");
        assertNotNull(queue.getHead());
        assertEquals("Test", queue.getHead().data);
        assertEquals(0, queue.getHead().code);
        assertEquals(1, queue.getLength());
    }

    @Test
    void dequeueFromEmptyQueueShouldThrowException() {
        assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    void dequeueShouldRetrieveInOrder() {
        queue.enqueue("Test1", 1);
        queue.enqueue("Test2", 2);
        assertEquals("Test1", queue.dequeue());
        assertEquals("Test2", queue.dequeue());
    }

    @Test
    void dequeueAfterLastItemShouldReturnNull() {
        queue.enqueue("Test1", 1);
        queue.enqueue("Test2", 2);
        queue.dequeue();
        queue.dequeue();
        assertNull(queue.dequeue());
    }

    @Test
    void addShouldAppendItemToQueueWithDefaultCode() {
        queue.add("Test");
        assertNotNull(queue.getHead());
        assertEquals("Test", queue.getHead().data);
        assertEquals(0, queue.getHead().code);
        assertEquals(1, queue.getLength());
    }

    @Test
    void checkTailAfterMultipleEnqueues() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.enqueue("Test3");
        assertEquals("Test3", queue.getTail().data);
    }

    @Test
    void lengthAfterMultipleOperations() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.dequeue();
        assertEquals(1, queue.getLength());
    }

    // Assuming you implement a descendingIterator in your GenericQueue
    @Test
    void descendingIteratorShouldRetrieveInReverseOrder() {
        queue.add("Test1");
        queue.add("Test2");
        queue.add("Test3");

        Iterator<String> descendingIterator = queue.descendingIterator();
        assertTrue(descendingIterator.hasNext());
        assertEquals("Test3", descendingIterator.next());
        assertEquals("Test2", descendingIterator.next());
        assertEquals("Test1", descendingIterator.next());
        assertFalse(descendingIterator.hasNext());
    }

    @Test
    void forEachShouldProcessAllItemsInOrder() {
        ArrayList<String> result = new ArrayList<>();
        queue.add("Test1");
        queue.add("Test2");
        queue.add("Test3");

        queue.forEach(result::add);

        assertEquals(3, result.size());
        assertEquals("Test1", result.get(0));
        assertEquals("Test2", result.get(1));
        assertEquals("Test3", result.get(2));
    }

    @Test
    public void testEnqueueAndDequeue() {
        queueInt.enqueue(1);
        queueInt.enqueue(2);
        assertEquals(Integer.valueOf(1), queueInt.dequeue());
        assertEquals(Integer.valueOf(2), queueInt.dequeue());
    }

    @Test
    public void testIterator() {
        queueInt.enqueue(1);
        queueInt.enqueue(2);
        Iterator<Integer> iterator = queueInt.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(2), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testForEachLoop() {
        queueInt.enqueue(1);
        queueInt.enqueue(2);
        int sum = 0;
        for (int num : queueInt) {
            sum += num;
        }
        assertEquals(3, sum);
    }

}
