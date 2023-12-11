/** A class that implements a queue of objects by using
   a chain of linked nodes that has both head and tail references. */
public final class LinkedQueue<T> implements QueueInterface<T>
{
   private Node firstNode; // References node at front of queue
   private Node lastNode;  // References node at back of queue
   private int size;	// Track the size of the queue
  	
	public LinkedQueue()
	{
		firstNode = null;
		lastNode = null;
		size = 0;
	} // end default constructor

	private class Node
	{
		private T data;
		private Node next;
		
		private Node(T dataPart, Node nextNode) {
            data = dataPart;
            next = nextNode;
        }
		
		private T getData() {
            return data;
        }

        private void setData(T newData) {
            data = newData;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }
	} // end Node
	
	
	public void enqueue(T newEntry)
	{
		Node newNode = new Node(newEntry, null);
		if (isEmpty())
			firstNode = newNode;
		else
			lastNode.setNextNode(newNode);
		lastNode = newNode;
		
		size++;

	} // end enqueue
	
	public T dequeue()
		{
		T front = getFront(); // Might throw EmptyQueueException
		// Assertion: firstNode != null
		firstNode.setData(null);
		firstNode = firstNode.getNextNode();
		if (firstNode == null)
			lastNode = null;
		
		size--;
		
		return front;
	} // end dequeue
	
	public T getFront(){
		if (isEmpty())
			throw new EmptyQueueException();
		else
			return firstNode.getData();
	} // end getFront
	
	public boolean isEmpty()
	{
		return (firstNode == null) && (lastNode == null);
	} // end isEmpty
	
	public void clear()
	{
		firstNode = null;
		lastNode = null;
	} // end clear
	

	public Object[] getAll() {
	    Object[] items = new Object[size]; // Create an array of the current size
	    Node currentNode = firstNode;
	    int index = 0;

	    while (currentNode != null) {
	        items[index] = currentNode.getData();
	        currentNode = currentNode.getNextNode();
	        index++;
	    }

	    return items;
	}
	
} // end LinkedQueue


