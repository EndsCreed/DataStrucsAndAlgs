import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lab5 {
    public static void main(String[] args) {
        String fileName = "input.txt";
        CustomHashTable hashTable = new CustomHashTable(10);
        try {
            BufferedReader reader = new BufferedReader(new
                    FileReader("./" + fileName));
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

/*
_____________________________________________________________________________________________________
|   CustomHashTable class ( table implementing a binary tree of words found in an input )           |
|                                                                                                   |
|   This class will contain binary tree "table" which will be a tree of nodes with hash values      |
|   Each node will contain a custom WordFrequency Object which will contain the string word         |
|   and the frequency that this word has appeared.                                                  |
|                                                                                                   |
|   This table will use polynomial hashing using Horner's method to create hashes for each node     |
|   Seperate chaining will be used to handle any collisions should they appear.                     |
|   A custom quick sort will be implemented to sort the data by frequencies from the tree by        |
|   taking, as input, a WordFrequency[] created during an inorder traversal method.                 |
|___________________________________________________________________________________________________|
 */
class CustomHashTable {
    private WordTree table; // Table represented by a Binary Tree
    private static WordFrequency[] words; // Words array for sorting based on frequency
    private int pos = 0; // Global var to save the position in words[] during print.
    private static QuickSort q = new QuickSort(); // instantiating the containerized QuickSort class for method access.

    public CustomHashTable(int size) {
        table = new WordTree(size);
    }
    public void incrementFrequency(String word) {
        table.add(hash(word), word);
    }
    public void printFrequenciesDescending() {
        words = new WordFrequency[table.getSize()];
    // ^ Words array to store all the objects found during traversal. Essential for QuickSort
        inorderTraversal(table.getRoot());
    // ^ Method that traverses the tree in order (lowest to highest hash value)

        q.quickSort(words, 0, table.getSize()-1);
    // ^ Sort the new array gather from the inorderTraversal in descending order

        for (WordFrequency word : words)
            System.out.println(word);
    }

    // This method recursively scans through the Binary Tree first traveling through left nodes, then chains, then right.
    // This should allow all entries in the tree to be collected. Even with separate chaining collision handling.
    private void inorderTraversal(Node node) {
        if (node == null)
            return;

        inorderTraversal(node.getLeft());
        inorderTraversal(node.getChain());
        words[pos++] = node.getWord(); // Adds left most chain first, then left, right will be last added.
        inorderTraversal(node.getRight());
    }

    // Hashing using Horner's Method.
    private int hash(String key) { // Hashing method
        int hash = 0;
        int MULT = 33;
        int MAX = 1049; // Assuming most of the time we won't have more than a thousand words
        for (int i = 0; i < key.length(); i++) {
            int value = (int) key.charAt(i);
            hash = ((value * MULT) + value) % MAX;
        }
        return hash;
    }
}

/*
_________________________________________________________________________________________________________________
|   CLASS: WordTree                                                                                             |
|                                                                                                               |
|   An implementation of a Binary tree which stores hashed nodes.                                               |
|   These hashed nodes contain data hash and WordFrequency for the purpose of tree sorting and word storage.    |
|   This tree also implements the concept of separate chaining in the event of a hash collision.                |
|_______________________________________________________________________________________________________________|
 */
class WordTree {
    private int maxSize;
    private int size = 0; // Used for sorting later
    private Node root;
    public WordTree() { this.maxSize = -1; }
    public WordTree(int maxSize) {
        this.maxSize = maxSize;
    }

/*
    Method: findNextNode

        Takes, as input, the hash values of the current word then traverses through the tree.
        It will find either a node with similar hash or traverse until the last leaf in the direction it needs to
        go based on hash value. Once it reaches this end, it returns the current node.
 */
    private Node findNextNode(int hash) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.getHash() == hash) { // If collision/word already there
                break;
            }
            if (hash < currentNode.getHash()) { // Traverse left
                if (currentNode.getLeft() == null) // Return current node if no next
                    break;
                currentNode = currentNode.getLeft();
            } else {
                if (currentNode.getRight() == null)
                    break;
                currentNode = currentNode.getRight();
            }
        }
        return currentNode;
    }

/*
    Method: add

        Takes, as input, the hash value of the current word and the String Literal.
        This method calls the findNextNode method to find the last leaf or matching hash for the current word.
        Once found, it determines one of three results:
        - Same hash value and same word, therefore, increment word frequency
        - Same hash value but different word, therefore, use separate chaining to handle the collision
        - Different hash value, therefore, add a new node on  left or right depending on if hash is lesser or greater
 */
    public void add(int hash, String word) { // Will add a node based on the hash and word
        if (root == null) {
            setRoot(new Node(hash, word));
            size++;
            return;
        }

        Node currentNode = findNextNode(hash); // Find node at end of the tree where the word goes

        // Determine if we need to use seperate chaining or place to left/right
        if (currentNode.getHash() == hash) {
            if (currentNode.getWord().getWordString().equals(word)) { // Word is the same. No Collision
                currentNode.getWord().incrementFrequency();
            } else { // Different word therefore collision! use separate chaining
                while (currentNode.getChain() != null) { // Keep moving down the chain until we find similar word or get to last link
                    currentNode = currentNode.getChain();
                    if (currentNode.getWord().getWordString().equals(word)) { // Find similar word
                        currentNode.getWord().incrementFrequency();
                        return;
                    }
                }
                currentNode.setChain(new Node(hash, word));
                size++;
            }
        } else { // Hash is different. MUST be a different word. Determine left or right placement
            if (hash < currentNode.getHash() && currentNode.getLeft() == null) { // Check if left is null then either set as left or traverse left
                currentNode.setLeft(new Node(hash, word));
            } else { // Check if right is null then either set as right or traverse right
                currentNode.setRight(new Node(hash, word));
            }
            size++;
        }
    }

    // Getters
    public Node getRoot() { return root; }
    public int getSize() { return size; }

    //Setters
    private void setRoot(Node node) { this.root = node; }
}

/*
_________________________________________________________________________________________
|   Class: Node                                                                         |
|                                                                                       |
|   A Binary Tree node with the follow declared fields:                                 |
|   - Hash: The identifier of the node and, in a sense, its "key".                      |
|   - Word: An instance of the WordFrequency object. The main stored data of the node.  |
|   - left: A pointer to a node with a hash value lesser than itself.                   |
|   - right: A pointer to a node with a hash value greater than itself.                 |
|_______________________________________________________________________________________|
 */
class Node {
    private int hash; // Hashed value for placement in the word tree
    private WordFrequency word; // Main data of the node. Contains the string word and freq of appearance
    private Node left; // Node with lower hash
    private Node right; // Node with higher hash
    private Node chain; // For use in separate chaining collision handling.

    // Constructor which also creates a new WordFrequency object based on the string passed in.
    public Node(int hash, String word) {
        this.hash = hash;
        this.word = new WordFrequency(word);
    }

    // Getters
    public int getHash() { return hash; }
    public WordFrequency getWord() { return word; }
    public Node getLeft() { return left; }
    public Node getRight() { return right; }
    public Node getChain() { return chain; }

    // Setters
    public void setLeft(Node node) { this.left = node; }
    public void setRight(Node node) { this.right = node; }
    public void setChain(Node node) { this.chain = node; }
}

/*
_____________________________________________________________________________________________________________________
|   Class: WordFrequency                                                                                            |
|                                                                                                                   |
|   This object stores the string literal pulled from input and the frequency that the word has appeared to help    |
|   combat repeat word entries in the Tree. It only has two fields:                                                 |
|   - word: The actual String literal of the word we want to store.                                                 |
|   - frequency: The amount of times the word has appeared in the input.                                            |
|___________________________________________________________________________________________________________________|
 */
class WordFrequency {
    private String word; // String of the word
    private int frequency; // How many times it has appeared
    public WordFrequency(String word) {
        this.word = word;
        this.frequency = 1;
    }
    public void incrementFrequency() { frequency++; }
    public String toString() { return word + ": " + frequency; }

    public String getWordString() { return word; }
    public int getFrequency() { return frequency; }
}


/*
_____________________________________________________________________________________________________________________
|   Class: QuickSort                                                                                                |
|                                                                                                                   |
|   This QuickSort class is specifically designed to sort WordFrequency Objects by their frequency                  |
|   It implements the MedianOfThree method to choose its pivot and then splits on a "greater to lesser" basis       |
|___________________________________________________________________________________________________________________|
 */
class QuickSort { // For sorting the frequencies after and printing in descending order of freq.
    public void quickSort(WordFrequency[] wordsIn, int start, int end) {
        if (start < end) {
            int pivot = split(wordsIn, start, end);

            quickSort(wordsIn, start, pivot-1);
            quickSort(wordsIn, pivot+1, end);
        }
    }

    // Split on a "greater to lesser" basis
    private int split(WordFrequency[] array, int start, int end) {
        if ((end - start) > 2) // Use median of three if there are 3 or more elements
            setPivot(array, start, end);

        int pivot = end; // Pivot on last index
        int pos = start;

        for (int i = start; i < end; i++) {
            if (array[i].getFrequency() > array[pivot].getFrequency()) {
                swap(array, pos, i);
                pos++;
            }
        }

        swap(array, pos, pivot);
        return pos;
    }

    // Choose pivot based on MedianOfThree method
    private void setPivot(WordFrequency[] array, int start, int end) {
        int middle = start + (end - start) / 2;

        int startValue = array[start].getFrequency();
        int middleValue = array[middle].getFrequency();
        int endValue = array[end].getFrequency();
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