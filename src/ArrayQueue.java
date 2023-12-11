import java.util.ArrayList;
import java.util.List;

//Circular array
public final class ArrayQueue<T> implements QueueInterface<T> {
	private T[] queue; // Circular array of queue entries and one unused element
	private int frontIndex;
	private int backIndex;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	public ArrayQueue()
	{
	this(DEFAULT_CAPACITY);
	} // end default constructor
	
	public ArrayQueue(int initialCapacity)
	{
		integrityOK = false;
		checkCapacity(initialCapacity);
		
		 // The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		integrityOK = true;
	} // end constructor
	
	public void enqueue(T newEntry)
	{
		checkIntegrity();
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
	} // end enqueue
	
	public T getFront()
	{
		checkIntegrity();
		if (isEmpty())
			throw new EmptyQueueException();
		else
			return queue[frontIndex];
	} // end getFront
	
	public T dequeue()
	{
		checkIntegrity();
		if (isEmpty())
			throw new EmptyQueueException();
		else
		{
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		} // end if
	} // end dequeue
	

	public List<T> getAll() {
	    checkIntegrity();
	    List<T> list = new ArrayList<>();
	    
	    // If the queue is empty, return the empty list
	    if (isEmpty()) {
	        return list;
	    }
	    
	    // Calculate the number of elements in the queue
	    int numberOfElements = frontIndex <= backIndex ?
	                           backIndex - frontIndex + 1 :
	                           queue.length - frontIndex + backIndex + 1;
	    
	    // Iterate through the queue array and add elements to the list
	    int currentIndex = frontIndex;
	    for (int i = 0; i < numberOfElements; i++) {
	        list.add(queue[currentIndex]);
	        currentIndex = (currentIndex + 1) % queue.length;
	    }
	    
	    return list;
	}

	
	private void ensureCapacity()
	{
		if (frontIndex == ((backIndex + 2) % queue.length)) // If array is full,
		{ // double size of array
		T[] oldQueue = queue;
		int oldSize = oldQueue.length;
		int newSize = 2 * oldSize;
		checkCapacity(newSize - 1);
		integrityOK = false;
		
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[]) new Object[newSize];
		queue = tempQueue;
		for (int index = 0; index < oldSize - 1; index++)
		{
			queue[index] = oldQueue[frontIndex];
			frontIndex = (frontIndex + 1) % oldSize;
		} // end for
			frontIndex = 0;
			backIndex = oldSize - 2;
			integrityOK = true;
		} // end if
	} // end ensureCapacity
	
	public boolean isEmpty()
	{
		checkIntegrity();
		return frontIndex == ((backIndex + 1) % queue.length);
	} // end isEmpty

	@Override
	public void clear() {
	    checkIntegrity();
	    // If queue is not empty, remove all entries
	    if (frontIndex <= backIndex) { // Contiguous queue
	        for (int index = frontIndex; index <= backIndex; index++) {
	            queue[index] = null;
	        }
	    } else { // Wrapped queue
	        for (int index = frontIndex; index < queue.length; index++) {
	            queue[index] = null;
	        }
	        for (int index = 0; index <= backIndex; index++) {
	            queue[index] = null;
	        }
	    }
	    
	    frontIndex = 0;
	    backIndex = queue.length - 1;
	}
	
	private void checkCapacity(int capacity) {
	    if (capacity > MAX_CAPACITY)
	        throw new IllegalStateException("Attempt to create a queue whose " +
	                                        "capacity exceeds allowed maximum.");
	}
	
	private void checkIntegrity() {
	    if (!integrityOK)
	        throw new SecurityException("ArrayQueue object is corrupt.");
	}

}
