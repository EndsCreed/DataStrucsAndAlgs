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
    private int hash(String key) {
        char[] keyChars = key.toCharArray();
        int hash = 0;
        int multiplier = 0;
        for (int i = 0; i < key.length(); i++) {
            if (i == 0 || i == (key.length()-1))
                multiplier += keyChars[i];
            if (i == ((key.length() - 1)/2))
                multiplier -= keyChars[i];
            hash += keyChars[i];
        }
        hash *= multiplier;
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

