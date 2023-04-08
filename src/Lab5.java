import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Lab5 {
    public static void main(String[] args) {
        String fileName = "input.txt";
        CustomHashTable hashTable = new CustomHashTable(10);
        try {
            BufferedReader reader = new BufferedReader(new
                    FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    word = word.toLowerCase();
                    hashTable.incrementFrequency(word);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
// Print the contents of the hash table
        System.out.println("Word Frequencies:");
        hashTable.printFrequenciesDescending();
    }
}
class CustomHashTable {
    private WordTree table;
    public CustomHashTable(int size) {
        table = new WordTree(size);
    }
    public void incrementFrequency(String word) {
// TODO: Implement this method
    }
    public void printFrequenciesDescending() {

    }

    // Hashing using Horner's Method.
    private int hash(String key) {
        char[] keyChars = key.toCharArray();
        int hash = 0;
        int MULT = 33;
        int MAX = 1049;
        for (int i = 0; i < key.length(); i++) {
            int value = (int) key.charAt(i);
            hash = ((value * MULT) + value) % MAX;
        }
        return hash;
    }
}
class WordFrequency {
    String word;
    int frequency;
    public WordFrequency(String word) {
        this.word = word;
        this.frequency = 1;
    }
    public void incrementFrequency() {
        frequency++;
    }
    public String toString() {
        return word + ": " + frequency;
    }
}

class WordTree {
    int maxSize;
    Node root;
    public WordTree() {
        this.maxSize = -1;
    }
    public WordTree(int maxSize) {
        this.maxSize = maxSize;
    }

    public void add(String word) {

    }
    public Node addRecursive(String word) {

        return new Node();
    }
}

class Node {
    int hash;
    WordFrequency word;
    Node left;
    Node right;
    Node parent;
}

// TODO implement an AVL tree using the hash values.

class QuickSort {
    WordFrequency[] words;

    public void quickSort(WordFrequency[] wordsIn, int start, int end) {
        if (start < end) {
            int pivot = split(wordsIn, start, end);

            quickSort(wordsIn, start, pivot-1);
            quickSort(wordsIn, pivot+1, end);
        }
    }

    private int split(WordFrequency[] array, int start, int end) {
        setPivot(array, start, end);
        WordFrequency pivot = array[end];
        int pos = start;

        for (int i = 0; i < pivot.frequency; i++) {
            swap(array, start, i);
            pos++;
        }

        swap(array, pos, end);
        return pos;
    }

    private void setPivot(WordFrequency[] array, int start, int end) {
        int middle = start + (end - start) / 2;

        int startValue = array[start].frequency;
        int middleValue = array[middle].frequency;
        int endValue = array[end].frequency;
        int index;

        if (startValue > endValue) {
            if (startValue < middleValue)
                index = start;
            else
                index = middle;
        } else if (endValue > middleValue)
            index = end;
        else
            index = middle;

        swap(array, end, index);
    }

    private void swap(WordFrequency[] array, int a, int b) {
        WordFrequency temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}