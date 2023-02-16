import java.util.*;

public class Main {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.enqueue(7);
        queue.enqueue(8);
        queue.enqueue(9);
        queue.enqueue(10);
        queue.enqueue(11);
        queue.enqueue(12);
        queue.enqueue(13);
        queue.enqueue(14);
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.enqueue(15);
        System.out.println(queue.print());
    }
}

class CircularQueue {
    int front, rear, size;
    List<Integer> array;

    /*
    Removed the capacity arguement since this queue dynamically expands.
    I can just use array.getSize() to find the size
     */
    public CircularQueue() {
        front = rear = 0; // Front will always be 0, rear will be the last index of the array.
        array = new ArrayList<>(); // Use an ArrayList Object instead so I can dynamically expand it.
    }
    public void enqueue(int data) {
        if (array.size() >= 10) { // If the array lists size is 10 or more, refuse to add more objects.
            System.out.println("Queue is full");
        } else { // Otherwise, append the data to the end of the array and increment rear.
            array.add(data);
            rear++;
        }
    }
    public int dequeue() {
        if (array.isEmpty()) { // If the array is empty, refuse to attempt removal of object and return -1.
            System.out.println("Queue is empty");
            return -1;
        } else {
            int temp = array.get(front); // Temp variable to return after removal of the front object
            array.remove(front); // Remove the first object
            rear--; // Decrement rear since the last index value will drop by 1.
            return temp; // Return what the first objects value was.
        }
    }
    public String print() {
        return Arrays.toString(array.toArray());
    }
}