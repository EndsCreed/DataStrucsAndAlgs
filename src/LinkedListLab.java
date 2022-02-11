public class LinkedListLab {
	private Node head;
	private Node tail;
	int size;
	
	public LinkedListLab() {
		head = tail = null;
		size = 0;
	}

	public boolean isEmpty() {
		if(size == 0)
			return true;
		else
			return false;
	}

	public void insert(int data) {
		Node n = new Node();
		n.setData(data);
		if(isEmpty())
			head = n;
		else
			tail.setLink(n);
		tail = n;
		size++;
	}
	
	public void remove() {
		if(isEmpty())
			System.out.println("Can't remove. List is empty.");
		else if(size == 1) {
			head = tail = null;
			size = 0; 
		} else {
			Node temp = head;
			while(temp.getLink() != tail)
				temp = temp.getLink();
			tail = temp;
			tail.setLink(null);
			size--; 
		}
	}
	
	public void print() {
		if(isEmpty())
			System.out.println("List is empty.");
		else {
			Node temp = head;
			while(temp != null) {
				System.out.print(temp.getData() + " ");
				temp = temp.getLink();
			}
			System.out.println();
		}
	}

	public int[] toArray() {
		/* Complete this method */
	}
}
