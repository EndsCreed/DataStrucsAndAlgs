import java.util.Arrays;

class Stack {
    // Lab requires max of 5
    static int MAX = 5;
    int top;

    // Changed to a 2D array that contains ann array of int arrays.
    // Both single entries and pairs can now be ordered together.
    int[][] array;
    public Stack() {
        // Define max size.
        array = new int[MAX][];
        top = -1;
    }
    public Stack(int capacity) {
        // Allows for a specific capacity.
        // Since the lab requires max size of 5
        // the MAX will be set to the capacity
        // only if the entered capacity is <= 5
        MAX = Math.min(capacity, MAX);
        array = new int[MAX][];
        top = -1;
    }
    public boolean isEmpty() {
        return (top < 0);
    }
    // For single int entries.
    // This method creates an int array with one
    // element and then increments top and pushes
    // that into onto the stack
    // Both push methods were changed to void because
    // the bool function was not being utilized.
    public void push(int x) {
        if (top >= (MAX - 1)) {
            System.out.println("\nStack overflow");
            displayStack();
        } else {
            array[++top] = new int[] {x};
            System.out.println("\n" + x + " pushed into stack");
            displayStack();
        }
    }
    // Does the same as the above push method
    // but instead takes two ints as parameters
    // and makes an int[] with 2 elements to push
    // onto the stack.
    public void push(int x, int y) {
        if (top >= (MAX - 1)) {
            System.out.println("\nStack overflow");
            displayStack();
        } else {
            array[++top] = new int[] {x, y};
            System.out.println("\nPair (" + x + ", " + y + ") pushed to stack");
            displayStack();
        }
    }
    // Sets a temp variable to the value at the current
    // top then decrements top. Then returns the value
    // stored in the temp variable.
    // Will return an int[] of size 0 if the stack is
    // empty.
    public int[] pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow");
            return new int[0];
        } else {
            int[] x = array[top--];
            System.out.println(Arrays.toString(x) + " popped from stack");
            displayStack();
            return x;
        }
    }
    // Iterates through the array and returns the string
    // value of the array stored at each index.
    public void displayStack() {
        System.out.print("Stack: ");
        if (isEmpty())
            System.out.print("[]");
        for (int i = top; i >= 0; i--) {
            System.out.print(Arrays.toString(array[i]) + " ");
        }
        System.out.println();
    }
}
class Main {
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1, 48392);
        stack.push(2, 1);
        stack.push(3,5);
        stack.push(4,437);
        stack.push(5, 0);
        stack.push(5, 6);
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        stack.push(5);
        stack.push(7,2);
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
        System.out.println("Popped item: " + Arrays.toString(stack.pop()) + "\n");
    }
}