public class DLinkedList {

	private DNode head;
	private DNode tail;
	int size;

	public DLinkedList() {
		head = tail = null;
		size = 0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void insert(int data) {
		DNode n = new DNode();
		n.setData(data);
		if (isEmpty()) {
			head = n;
		} else {
			tail.setNextLink(n);
			n.setPrevLink(tail);
		}
		tail = n;
		size++;
	}
	
	public boolean remove() {
		if (isEmpty()) {
			System.out.println("Cannot remove tail. List is already empty");
			return false;
		}
		if (size == 1) {
			head = tail = null;
			size = 0;
		} else {
			tail = tail.getPrevLink();
			tail.setNextLink(null);
			size--;
		}
		return true;
	}
	
	public boolean remove(int n) {
		if (isEmpty()) { // If empty, don't try and remove.
			System.out.println("Cannot remove [" + n + "]. List is already empty");
			return false;
		}
		if (size == 1) {
			if (head.getData() != n) { // If size 1 but n doesn't match the data, don't remove.
				System.out.println("Cannot remove. [" + n + "] not found.");
				return false;
			}
			// Otherwise, remove.
			head = tail = null;
			size = 0;
			return true;
		} else {
			DNode temp = head;
			if (temp != null && temp.getData() == n) { // In case the first entry is n.
				head = temp.getNextLink();
				size--;
				return true;
			}
			while (temp != null) {
				if (temp.getData() == n) {
					temp.getPrevLink().setNextLink(temp.getNextLink()); // Bridge the previous link forwards over the temp.
					temp.getNextLink().setPrevLink(temp.getPrevLink()); // Bridge the next link backwards over the temp.
					size--;
					return true;
				}
				temp = temp.getNextLink();
			}
		}
		System.out.println("Cannot remove. [" + n + "] not found.");
		return false; // If the list isn't empty, it isn't of size 1 with a matching value, and the value isn't found from head to tail then it must not exist.
	}

	String toStringForward() {
		String result;
		StringBuilder s = new StringBuilder();
		if (!isEmpty()) {
			DNode temp = head;
			for (int i = 0; temp != null; i++) {
				s.append(temp.getData());
				if (temp.getNextLink() != null)
					s.append(" ");
				temp = temp.getNextLink();
			}
			result = s.toString();
		} else {
			result = "Cannot print. List is empty.";
		}
		return result;
	}

	String toStringReverse() {
		String result;
		StringBuilder s = new StringBuilder();
		if (!isEmpty()) {
			DNode temp = tail;
			for (int i = 0; temp != null; i++) {
				s.append(temp.getData());
				if (temp.getPrevLink() != null)
					s.append(" ");
				temp = temp.getPrevLink();
			}
			result = s.toString();
		} else {
			result = "Cannot print. List is empty.";
		}
		return result;
	}


	public static void main(String[] args) {
		DLinkedList dll = new DLinkedList();
		dll.insert(3);
		dll.insert(65);
		dll.insert(21);
		System.out.println(dll.toStringForward());
		System.out.println(dll.toStringReverse());
		dll.remove(64);
		System.out.println(dll.toStringForward());
		dll.remove(65);
		System.out.println(dll.toStringForward());
		dll.insert(117);
		System.out.println(dll.toStringForward());
		dll.remove();
		System.out.println(dll.toStringForward());
		dll.remove();
		System.out.println(dll.toStringForward());
		dll.remove();
		System.out.println(dll.toStringForward());
		dll.remove();
		System.out.println(dll.toStringForward());
		dll.insert(21);
		System.out.println(dll.toStringForward());
		dll.remove(32);
		dll.remove(21);
		dll.remove(21);
		System.out.println(dll.toStringForward());
	}
}
