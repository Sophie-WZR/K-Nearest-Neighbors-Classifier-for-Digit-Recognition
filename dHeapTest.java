import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class dHeapTest {
    private dHeap<Integer> maxHeap;
    private dHeap<Integer> minHeap;
    private dHeap<String> maxstrHeap;
    private dHeap<Double> mindoubleHeap;

    @BeforeEach
    public void setUp(){
        maxHeap = new dHeap<>();
        minHeap = new dHeap<>(2, 10, false);
        maxstrHeap = new dHeap<>(100);
        mindoubleHeap = new dHeap<>(4, 9, false);
    }

    @Test
    public void testDheap(){
        assertThrows(IllegalArgumentException.class, () -> {
            new dHeap<Integer>(0, 10, true);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new dHeap<Integer>(-1, 10, true);
        });
        assertDoesNotThrow(() -> {
            new dHeap<Integer>(2, 10, true);
        });
    }

    @Test
    public void testSize() {
        assertEquals(0, maxHeap.size());
        assertThrows(NoSuchElementException.class, () ->{
            maxHeap.remove();
        });
        assertEquals(0, maxHeap.size());
        maxstrHeap.add("hi");
        maxstrHeap.add("dsc");
        maxstrHeap.add("good");
        assertEquals(3, maxstrHeap.size());
        maxstrHeap.remove();
        assertEquals(2, maxstrHeap.size());
        maxstrHeap.clear();
        assertEquals(0, maxstrHeap.size());
        assertThrows(NoSuchElementException.class, () ->{
            maxstrHeap.remove();
        });
        minHeap.add(10);
        minHeap.add(10);
        assertEquals(2, minHeap.size());
        for (int i = 0; i < 100; i++) {
            mindoubleHeap.add(9.76);
        }
        assertEquals(100, mindoubleHeap.size());
        mindoubleHeap.remove();
        assertEquals(99, mindoubleHeap.size());
        int initialSize = maxHeap.size();
        assertThrows(NullPointerException.class, () -> maxHeap.add(null));
        assertEquals(initialSize, maxHeap.size());
    }

    @Test
    public void testAdd() {
        assertEquals(0, maxHeap.size());
        maxHeap.add(25);
        assertEquals(1, maxHeap.size());
        assertEquals(Integer.valueOf(25), maxHeap.element());

        maxHeap.add(35);
        maxHeap.add(15);
        assertEquals(Integer.valueOf(35), maxHeap.element());
        assertEquals(3, maxHeap.size());

        maxHeap.add(45);
        assertEquals(Integer.valueOf(45), maxHeap.element());

        assertEquals(0, minHeap.size());
        minHeap.add(22);
        assertEquals(1, minHeap.size());
        assertEquals(Integer.valueOf(22), minHeap.element());

        minHeap.add(18);
        minHeap.add(27);
        assertEquals(Integer.valueOf(18), minHeap.element());
        assertEquals(3, minHeap.size());

        minHeap.add(10);
        assertEquals(Integer.valueOf(10), minHeap.element());
        minHeap.clear();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);
        assertEquals(5, minHeap.element());

        maxHeap.clear();
        maxHeap.add(10);
        maxHeap.add(5);
        maxHeap.add(15);
        assertEquals(15, maxHeap.element());
        maxHeap.clear();
        for (int i = 0; i < 50; i++) {
            maxHeap.add(i);
        }
        for (int i = 49; i >= 0; i--) {
            assertEquals(i, maxHeap.remove());
        }

        for (int i = 0; i < 120; i++) {
            maxstrHeap.add("wow");
        }
        assertEquals(120, maxstrHeap.size());
        assertEquals("wow", maxstrHeap.element());
        maxstrHeap.clear();
        maxstrHeap.add("banana");
        maxstrHeap.add("apple");
        maxstrHeap.add("cherry");
        assertEquals("cherry", maxstrHeap.remove());
        assertThrows(NullPointerException.class, () -> maxHeap.add(null));

        int expectedSize = 0;
        for (int i = 0; i < 10; i++) {
            mindoubleHeap.add(i+2.345);
            expectedSize++;
            assertEquals(expectedSize, mindoubleHeap.size());
        }
    }

    @Test
    public void testRemove() {
        assertThrows(NoSuchElementException.class, () -> minHeap.remove());
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        assertEquals(30, maxHeap.remove());
        assertEquals(20, maxHeap.remove());
        assertEquals(10, maxHeap.remove());
        assertThrows(NoSuchElementException.class, maxHeap::remove);

        minHeap.add(10);
        minHeap.add(5);
        minHeap.add(15);
        assertEquals(5, minHeap.remove());
        assertEquals(10, minHeap.remove());
        assertEquals(15, minHeap.remove());
        assertThrows(NoSuchElementException.class, minHeap::remove);

        for (int i = 1; i <= 10; i++) {
            maxstrHeap.add("ok");
            maxstrHeap.add("fine");
        }
        maxstrHeap.remove();  // Remove max, which is 10
        assertEquals("ok", maxstrHeap.element());

        double[] elements = {4.2, 1.4, 3.567, 2.88, 16.0, 9.12, 10.6};
        for (double el : elements) {
            mindoubleHeap.add(el);
        }
        while (mindoubleHeap.size() > 0) {
            mindoubleHeap.remove();
        }
        assertEquals(0, mindoubleHeap.size());
        assertThrows(NoSuchElementException.class, maxHeap::remove);

        minHeap.clear();
        minHeap.add(10);
        minHeap.add(5);
        minHeap.remove();
        minHeap.add(3);
        assertEquals(3, minHeap.element());
    }

    @Test
    public void testClear() {
        assertEquals(0, minHeap.size());
        minHeap.clear();
        assertEquals(0, minHeap.size());
        maxHeap.add(30);
        maxHeap.add(40);
        maxHeap.add(50);
        assertFalse(maxHeap.size() == 0);
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        assertFalse(maxHeap.size() == 0);
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        for (int i = 0; i < 5; i++) {
            maxHeap.add(i);
        }
        maxHeap.clear();
        assertEquals(0, maxHeap.size());
        maxHeap.add(99);
        assertEquals(Integer.valueOf(99), maxHeap.remove());
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(3);
        assertFalse(minHeap.size() == 0);
        maxHeap.clear();
        maxHeap.add(1);
        maxHeap.add(2);
        minHeap.add(1);
        minHeap.add(2);
        maxHeap.clear();
        minHeap.clear();
        maxHeap.add(100);
        minHeap.add(100);
        assertEquals(Integer.valueOf(100), maxHeap.element());
        assertEquals(Integer.valueOf(100), minHeap.element());
        assertEquals(1, minHeap.size());
        maxstrHeap.add("apple");
        maxstrHeap.add("banana");
        mindoubleHeap.add(1.1);
        mindoubleHeap.add(2.2);
        maxstrHeap.clear();
        mindoubleHeap.clear();
        assertEquals(0, maxstrHeap.size());
        assertEquals(0, mindoubleHeap.size());
    }

    @Test
    public void testElement() {
        assertThrows(NoSuchElementException.class, () -> maxHeap.element());
        maxHeap.add(100);
        maxHeap.add(200);
        assertEquals(200, maxHeap.element());
        assertEquals(2, maxHeap.size());
        maxHeap.clear();
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        assertEquals(Integer.valueOf(30), maxHeap.element());
        assertEquals(Integer.valueOf(30), maxHeap.element());
        assertThrows(NoSuchElementException.class, () -> minHeap.element());
        minHeap.add(300);
        minHeap.add(100);
        assertEquals(100, minHeap.element());
        assertEquals(2, minHeap.size());
        maxstrHeap.add("o");
        maxstrHeap.add("p");
        maxstrHeap.add("q");
        assertEquals("q", maxstrHeap.element());

        assertThrows(NoSuchElementException.class, () -> mindoubleHeap.element());
        mindoubleHeap.add(10.1);
        mindoubleHeap.add(20.2);
        mindoubleHeap.add(5.05);
        assertEquals(Double.valueOf(5.05), mindoubleHeap.element());
        assertEquals(Double.valueOf(5.05), mindoubleHeap.element());
        mindoubleHeap.clear();
        mindoubleHeap.add(15.15);
        mindoubleHeap.add(10.10);
        mindoubleHeap.add(30.30);
        mindoubleHeap.add(25.25);
        assertEquals(Double.valueOf(10.10), mindoubleHeap.element());
        mindoubleHeap.remove();
        assertEquals(Double.valueOf(15.15), mindoubleHeap.element());
        mindoubleHeap.clear();
        mindoubleHeap.add(40.40);
        assertEquals(Double.valueOf(40.40), mindoubleHeap.element());
        mindoubleHeap.clear();
        mindoubleHeap.add(-0.001);
        mindoubleHeap.add(0.0);
        mindoubleHeap.add(0.001);
        assertEquals(Double.valueOf(-0.001), mindoubleHeap.element());
        mindoubleHeap.clear();
        mindoubleHeap.add(1.0001);
        mindoubleHeap.add(1.0002);
        mindoubleHeap.add(1.0003);
        assertEquals(Double.valueOf(1.0001), mindoubleHeap.element());
    }
}
