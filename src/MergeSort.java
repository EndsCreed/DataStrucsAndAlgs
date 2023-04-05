public class MergeSort {

    private int[] a;

    public MergeSort (int[] a) {
        this.a = a;
    }

    public void sort() {
        divide(0, a.length - 1);
    }

    private void divide(int p, int r) {
        int q = (p + r) / 2; // Start + end index / 2. Splits array in half

        if (p < r) {
            divide(p, q); // Divide from 0 to 1/2 way
            divide(q + 1, r); // Divide from 1/2 way + 1 to end
            merge(p, q, r); // Merge start to middle, and middle to end of the fed array
        }
    }

    private void merge(int p, int q, int r) {
        int[] merged = new int[r - q + 1]; // Create array of size end to middle + 1
        int i = p; // Current pos in old array
        int j = q + 1; // Start of 2nd half of split array
        int k = 0; // Current Pos in new array

        while (i <= q && j <= r) { // As long as old pos is less or equal to the middle and middle +1 is less than or equal to the end
            if (a[i] <= a[j]) { // If value at old pos is less than or equal to value at middle +1
                merged[k] = a[i]; // set current pos to value at old pos
                i++; // Move old pos up one
            } else {
                merged[k] = a[j]; // Otherwise, set new pos value to the middle +1. The first half is done.
                j++;
            }
            k++;
        }

        while (i <= q) {
            merged[k] = a[i];
            i++; k++;
        }
        while (j <= r) {
            merged[k] = a[j];
            j++; k++;
        }

        for (i = p; i <= r; i++) {
            a[i] = merged[i - p];
        }
    }

    public void print() {
        for (int num : a) {
            System.out.print(num + " ");
        }
    }
}
