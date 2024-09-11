import java.sql.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Sophie Wang
 * @since 05/22/2024
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min
    private static final int SIZE_TWO = 2;
    private static final int SIZE_TEN = 10;

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(SIZE_TWO, SIZE_TEN, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.heap = (T[]) new Comparable[heapSize];
        this.d = SIZE_TWO;
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1){
            throw new IllegalArgumentException();
        }
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return The number of elements currently stored in the heap.
     */
    @Override
    public int size() {
        return this.nelems;
    }

    /**
     * Removes and returns the root element from the heap.
     *
     * @return The root element of the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (this.size() == 0){
            throw new NoSuchElementException();
        }
        // Save the value from the root of the heap.
        T removeValue = heap[0];

        // Move the last item in the array into index 0.
        T replaceValue = heap[this.size() - 1];
        this.nelems--;
        if (this.size() > 0) {
            heap[0] = replaceValue;

            // Percolate down to restore heap property.
            trickleDown(0);
        }

        // Return the max value
        return removeValue;
    }

    /**
     * Adds an element to the heap. If the heap is full, it will be resized.
     *
     * @param item The element to be added to the heap.
     * @throws NullPointerException if the added item is null.
     */
    @Override
    public void add(T item) throws NullPointerException {
        if (item == null){
            throw new NullPointerException();
        }
        if (nelems == heap.length) {
            resize();
        }
        heap[nelems] = item;
        bubbleUp(nelems);
        nelems++;
    }


    /**
     * Clears all elements from the heap.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int i = 0; i < nelems; i++) {
            heap[i] = null;
        }
        // Reset the number of elements to 0
        nelems = 0;
    }

    /**
     * Retrieves, but does not remove, the root element of the heap.
     *
     * @return The root element of the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    @Override
    public T element() throws NoSuchElementException {
        if (this.size() == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    /**
     * Calculates the parent index for a given index in the heap.
     * This method is essential for navigating up the heap during the bubble-up process.
     *
     * @param index The index of the node in the heap whose parent index is to be calculated.
     * @return The index of the parent node.
     */
    private int parent(int index) {
        return (index - 1) / d;
    }

    /**
     * Restores the heap property by moving the element at the specified index up the heap
     * until the heap property is restored. This method is called after insertion of a new element.
     *
     * @param index The index at which the bubble up process starts, typically the index of the
     * newly added element.
     */
    private void bubbleUp(int index) {
        while (index > 0) {
            int parentIndex = parent(index);
            // Directly use the compare method to decide if a swap is needed
            if (compare(heap[index], heap[parentIndex]) > 0) {
                T temp = heap[index];
                heap[index] = heap[parentIndex];
                heap[parentIndex] = temp;
                index = parentIndex;  // Move up to the parent index
            } else {
                break;  // If no swap is needed, the heap property is satisfied
            }
        }
    }

    /**
     * Compares two elements of the heap based on the heap type.
     *
     * This method abstracts the comparison logic for the heap, allowing the
     * same code to manage both max-heaps and min-heaps.
     *
     * @param a The first element to compare.
     * @param b The second element to compare.
     * @return A positive integer if the first element is considered greater than the second
     * element, zero if they are equal, and a negative integer if the first element is considered
     * less than the second, all depending on the heap type.
     */
    private int compare(T a, T b){
            if (isMaxHeap) {
                return a.compareTo(b);
            } else {
                return b.compareTo(a);
            }
        }

    /**
     * Restores the heap property by moving the element at the specified index down the heap
     * until the heap property is restored. This method is called after the removal of the root
     * element.
     *
     * @param index The index at which the trickle down process starts, typically the root index
     * after swapping with the last element.
     */
    private void trickleDown(int index) {
        int current = index;
        while (true) {
            int childIndex = d * current + 1;
            int bestChildIdx = current;

            for (int i = 0; i < d && childIndex + i < nelems; i++) {
                if (compare(heap[childIndex + i], heap[bestChildIdx]) > 0) {
                    bestChildIdx = childIndex + i;
                }
            }

            if (bestChildIdx == current) {
                break;  // No child is better than the current node
            }

            T temp = heap[current];
            heap[current] = heap[bestChildIdx];
            heap[bestChildIdx] = temp;
            current = bestChildIdx;  // Continue at new position
        }
    }


    /**
     * Private method to double the size of the heap array when full.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newHeap = (T[]) new Comparable[heap.length * SIZE_TWO];
        System.arraycopy(heap, 0, newHeap, 0, heap.length);
        heap = newHeap;
    }
}
