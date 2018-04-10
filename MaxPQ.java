/**
 * P1 Priority Queue
 * @author Brian Tang, bjtang2@wisc.edu
 * Due Feb 5
 * Files used: EmptyQueueException.java, PriorityQueueADT.java, MaxPQ.java, TestMaxPQ.java, results.txt
 * 
 * Possible bugs: Console log reader may have an issue if results.txt does not exist. size() method is
 * variable, meaning if an insert or remove is made to the queue, the value of size() also updates.
 */

/**
 * 
 * @author Brian Tang
 * 
 * Priority Queue class, implemented as a max array-based heap.
 * 
 * @param <E> is the item type of the priority queue.
 */
public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E>
{
    private E[] items;
    private static final int INITIAL_SIZE = 10;

    /**
     * Constructor initializes items array to size 10. All values inside are still null.
     */
    public MaxPQ()
    {
        this.items = (E[]) new Comparable[INITIAL_SIZE];
    }

    /**
     * 
     * @param childIndex is the initial index we are calculating the parent of.
     * @return the calculated parent index.
     */
    private int getParent(int childIndex) {
    	return (childIndex - 1) / 2;
    }
    
    /**
     * 
     * @param parentIndex is the initial index we are calculating the left child of.
     * @return the calculated child index.
     */
    private int getLeftChild(int parentIndex) {
    	return (parentIndex * 2) + 1;
    }
    
    /**
     * 
     * @param parentIndex is the initial index we are calculating the right child of.
     * @return the calculated child index.
     */
    private int getRightChild(int parentIndex) {
    	return (parentIndex * 2) + 2;
    }
        
    /**
     * Checks if the priority queue has any
     * elements and returns true if no elements,
     * false otherwise.
     *
     * @return true if no elements in queue, false otherwise.
     */
    public boolean isEmpty() {
    	if(size() == 0)
    		return true;
    	return false;
    }
    
    /**
     * Swaps index1 and index2's items with each other.
     */
    private void swap(int index1, int index2) {
    	E temp = items[index1];
    	items[index1] = items[index2];
    	items[index2] = temp;
    }

    /**
     * The insert helper heapifies up the heap, swapping when
     * necessary to maintain the order rules of the heap.
     * 
     * @param index is the starting index to sort up from.
     */
    private void insertHelper(int index) {
    	if(items[index].compareTo(items[getParent(index)]) > 0) {
    		swap(index, getParent(index));
    		insertHelper(getParent(index));
    	}
    	return;
    }
    
    /**
     * Adds a data item to the priority queue.
     * Reorders all the other data items in the
     * queue accordingly.
     *
     * If the size if equal to the capacity of the
     * priority queue, double the capacity and then
     * add the new item.
     *
     * @param item the item to add
     * @throws IllegalArgumentException if item is null
     */
     public void insert(E item) {
    	 if(item == null) throw new IllegalArgumentException("IllegalArgumentException: Item being added is \"null\".");
    	 if(size() >= items.length) {
    		 Comparable[] temp = new Comparable[items.length*2];
    		 E[] newItems = (E[]) temp;
    		 for(int i = 0; i < items.length; i++)
    			 newItems[i] = items[i];
    		 items = newItems;
    	 }
    	 int size = size();
    	 items[size] = item;
    	 insertHelper(size);
     }

    /**
     * Returns the highest priority item in the priority queue.
     *
     * MinPriorityQueue => it will return the smallest valued element.
     * MaxPriorityQueue => it will return the largest valued element.
     *
     * @return the highest priority item in the priority queue.
     * @throws EmptyQueueException if priority queue is empty.
     */
    public E getMax() throws EmptyQueueException {
    	if(isEmpty())
    		throw new EmptyQueueException("EmptyQueueException: The queue is empty. Something must be inserted first before calling getMax().");
    	return items[0];
    }

    /**
     * The remove helper sorts down the heap by taking the index
     * and swapping with the largest child node until a leaf node
     * is reached, in which case the algorithm terminates.
     * 
     * @param index is the index to sort up from.
     */
    private void removeHelper(int index) {
    	int maxIndex; // needed for comparison between both child nodes to determine which to swap.
    	if (getRightChild(index) >= size()) {
            if (getLeftChild(index) >= size())
            	return; // terminates if both child indices are larger than array size.
            else
            	maxIndex = getLeftChild(index);
    	}
    	else {
            if (items[getLeftChild(index)].compareTo(items[getRightChild(index)]) > 0)
                maxIndex = getLeftChild(index);
            else
                maxIndex = getRightChild(index);
    	}
    	if (items[maxIndex].compareTo(items[index]) > 0) {
    		swap(index, maxIndex);
            removeHelper(maxIndex);
    	}    	
    }
    
    /**
     * Returns and removes the highest priority item in the priority queue.
     * Reorders all the other data items in the
     * queue accordingly.
     *
     * MinPriorityQueue => it will return and remove the smallest valued element.
     * MaxPriorityQueue => it will return and remove the largest valued element.
     *
     * @return the highest priority item in the priority queue.
     * @throws EmptyQueueException if priority queue is empty.
     */
    public E removeMax() throws EmptyQueueException {
    	if(isEmpty())
    		throw new EmptyQueueException("EmptyQueueException: The queue is empty. Something must be inserted first before calling removeMax().");
    	E max = items[0];
    	items[0] = null;
    	swap(0, size());
    	removeHelper(0);
    	return max;
    }

    /**
     * Returns the number of elements in the priority queue
     * by counting non-null elements.
     *
     * @return number of non-null elements in the queue.
     */
    public int size() {
    	int size = 0;
    	for(int i = 0; i < items.length; i++) {
    		if(items[i] != null)
    			size++;
    	}
    	return size;
    }

}