public class LinkedListTest {
    public static void main(String[] args) {
        LinkedListLab ll = new LinkedListLab();
        testArray(ll);
    }

    public static void testArray(LinkedListLab ll) {
        if (ll.isEmpty()) {
            System.out.println("LinkedList is empty!");
            return;
        }
        for (int data : ll.toArray()) {
            System.out.print(data + " ");
        }
    }
}
