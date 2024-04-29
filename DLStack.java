import java.util.ArrayList;

public class DLStack<T> implements DLStackADT<T> {
	
	/**
	 * This is a reference to the node at the top of the stack.
	 */
	private DoubleLinkedNode<T> top;
	
	/**
	 * The value of this variable is the number of data items stored in the stack
	 */
	private int numItems;
	
	public DLStack() {
		top = null;
		numItems = 0;
	}
	
	public void push(T dataItem) {
		DoubleLinkedNode<T> temp = new DoubleLinkedNode<T>(dataItem);
//		if(top != null){
//			temp.setNext(top);
//			top.setPrevious(temp);
//			temp.setPrevious(null);
//		}
		
		if(top != null) {
			temp.setPrevious(top);
			this.top.setNext(temp);
		}
		
		
//		temp.setNext(top);
//		if(top != null) {
//			top.setPrevious(temp);
//		}
		top = temp;
		numItems++;
	}
	
	public T pop() throws EmptyStackException{
		if(isEmpty())throw new EmptyStackException("Stack");
		
		T result = top.getElement();
		top = top.getPrevious();
		numItems--;
		return result;
		
		
//		T result = top.getElement();
//		top = top.getNext();
//		numItems--;
//		return result;
	}
	
	private DoubleLinkedNode<T> deleteNode(DoubleLinkedNode<T> node) {
		if (top == null || node == null)
            return null;
 
		if(top == node) {
			top = node.getPrevious();
		}
		
        // If node to be deleted is head node
//        if (top == node)
//        	top = node.getNext();
 
        // Change next only if node to be
        // deleted is NOT the last node
//        if (node.getNext() != null)
//        	node.getNext().setPrevious(node.getPrevious());
// 
        
        if(node.getPrevious() != null) {
        	node.getPrevious().setNext(node.getNext());
        }
        
        // Change prev only if node to be
        // deleted is NOT the first node
//        if (node.getPrevious() != null)
//        	node.getPrevious().setNext(node.getNext());

        if(node.getNext() != null) {
        	node.getNext().setPrevious(node.getPrevious());
        }
        
        node = null;
 
        return node;
	}
	
	public T pop(int k) throws InvalidItemException{
		if(k > numItems || k <= 0) {
			throw new InvalidItemException("Invalid k value.");
		}
		if(top == null || k <= 0) {
			return null;
		}
		
		DoubleLinkedNode<T> current = top;
		int i;
		
		for(i = 1; current != null && i < k; i++) {
			current = current.getPrevious();
		}
		
		
//		for(i = 1; current != null && i < k; i++) {
//			current = current.getNext();
//		}
		
		if(current == null) {
			return null;
		}
		
		T result = current.getElement();
		
		deleteNode(current);
		
		return result;
	}

	
	public T peek() throws EmptyStackException{
		if(isEmpty())throw new EmptyStackException("Stack");
		T result = top.getElement();
		return result;
	}
	
	public boolean isEmpty() {
		if(numItems == 0)return true;
		
		return false;
	}
	
	public int size() {
		return numItems;
	}
	
	public DoubleLinkedNode<T> getTop(){
		return top;
	}
	
	public String toString() {
		String result = "[";
		for(int i = 0; i < numItems; i++) {
			result += this.pop(i).toString();
		}
		
		return result + "]";
	}
}