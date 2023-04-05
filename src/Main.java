public class Main {
    public static void main(String[] args) {
        int[] a = { 12, 35, 64, 21, 5, 8, 99, 69, 1 };
        MergeSort ms = new MergeSort(a);
        ms.print();
        ms.sort();
        ms.print();
    }
}
