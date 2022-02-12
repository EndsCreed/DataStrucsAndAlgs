import java.util.Arrays;

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
			System.out.println("Cannot remove. List is already empty");
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
			System.out.println("Cannot remove. List is already empty");
			return false;
		}
		if (size == 1) {
			if (head.getData() != n) { // If size 1 but n doesn't match the data, don't remove.
				System.out.println("Specified integer not found. Failed to remove.");
				return false;
			}
			// Otherwise, remove.
			head = tail = null;
			size = 0;
		} else {
			DNode temp = head;
			if (temp != null && temp.getData() == n) { // In case the first entry is n.
				head = temp.getNextLink();
				size--;
				return true;
			}
			while (temp.getNextLink() != null) {
				if (temp.getData() == n) {
					temp.getPrevLink().setNextLink(temp.getNextLink()); // Bridge the previous link forwards over the temp.
					temp.getNextLink().setPrevLink(temp.getPrevLink()); // Bridge the next link backwards over the temp.
					size--;
					return true;
				}
			}
			if (tail.getData() == n) { // The while loop won't ever check the tail. This checks the tail at the end.
				tail = tail.getPrevLink();
				tail.getNextLink().setPrevLink(null);
				tail.setNextLink(null);
				size--;
				return true;
			}
		}
		System.out.println("Specified integer not found. Failed to remove.");
		return false; // If the list isn't empty, it isn't 1 with a matching value, the value isn't found from head to tail - 1, and thw tail isn't the value. It must not exist.
	}

	String toStringForward() {
		String result;
		int[] list;
		list = new int[size];
		if (!isEmpty()) {
			DNode temp = head;
			for (int i = 0; temp != null; i++) {
				list[i] = temp.getData();
				temp = temp.getNextLink();
			}
			result = Arrays.toString(list);
		} else {
			result = "List is empty.";
		}
		return result;
	}

	String toStringReverse() {
		String result;
		int[] list;
		list = new int[size];
		if (!isEmpty()) {
			DNode temp = tail;
			for (int i = 0; temp != null; i++) {
				list[i] = temp.getData();
				temp = temp.getPrevLink();
			}
			result = Arrays.toString(list);
		} else {
			result = "List is empty.";
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
