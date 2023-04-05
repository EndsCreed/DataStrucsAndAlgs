import java.util.HashMap;

public class Main {
    public static Players players;
    private static long totalTime = 0;
    private static long totalTimeBubble = 0;

    public static void main(String[] args) {
        String[] names = { "Alice", "Bob", "Charlie", "David", "Emily",
                "Frank", "George", "Hannah", "Isaac", "Jack", "Kate", "Lucy", "Mike",
                "Nancy", "Oliver", "Peter", "Queenie", "Ryan", "Sarah", "Tom", "Uma",
                "Vera", "Wendy", "Xander", "Yara", "Zoe", "Aaron", "Brianna", "Chloe",
                "Daniel", "Eva", "Felix", "Gina", "Helen", "Ivy", "Jacob", "Katie",
                "Liam", "Megan", "Nina", "Oscar", "Patrick", "Quinn", "Riley",
                "Samantha", "Tyler", "Violet", "William", "Xavier", "Yasmine", "Zack",
                "Adam", "Benjamin", "Caroline", "Dylan", "Ethan", "Freya", "Grace",
                "Henry", "Isla", "Jessica", "Kevin", "Lily", "Mia", "Noah", "Olivia",
                "Penelope", "Quentin", "Rebecca", "Sophia", "Taylor", "Victoria",
                "Wyatt", "Xin", "Yolanda", "Zara" };
        int[] scores = { 75, 92, 80, 63, 88, 71, 96, 83, 68, 82, 77, 90,
                69, 94, 85, 73, 78, 87, 91, 65, 89, 84, 76, 70, 81, 72, 93, 79, 67, 86,
                62, 99, 60, 97, 57, 95, 59, 98, 58, 66, 61, 74, 55, 56, 54, 53, 52, 51,
                50, 49, 95, 98, 88, 74, 85, 67, 72, 81, 79, 94, 73, 90, 61, 77, 83, 99,
                76, 87, 56, 66, 59, 63, 69, 80, 89, 62 };

        players = new Players(names.clone(), scores.clone());

        // 1000 runs for testing of average time.
        for (int i = 0; i < 1000; i++) {
            long startTime = System.nanoTime();
            quickSort(players.getScores(), 0, players.getScores().length - 1);
            long endTime = System.nanoTime();
            totalTime += endTime - startTime;
            players.setNames(names); players.setScores(scores);
        }

        for (int i = 0; i < 1000; i++) {
            long startTime = System.nanoTime();
            quickSort(players.getScores(), 0, players.getScores().length - 1);
            long endTime = System.nanoTime();
            totalTimeBubble += endTime - startTime;
            players.setNames(names); players.setScores(scores);
        }

        if (players.verifySymmetry()) {
            System.out.println("Sorted:\n");
            for (int i = 0; i < players.getScores().length; i++) {
                System.out.println(players.getNames()[i] + " | " + players.getScores()[i]);
            }
            double averageTimeBubble = (double) totalTimeBubble / 1000;
            double averageTime = (double) totalTime / 1000;
            System.out.println("Average time Bubble: " + averageTimeBubble + "\nAverage Time Quick: " + averageTime);
        } else
            System.out.println("There was a discrepancy!");
    }

    // This method will move the median value to the end of the array to use as the pivot
    public static void chooseMedianPivot(int[] array, int start, int end) {
        int middle = start + (end - start) / 2; // get middle index (not done yet)

        int startValue = array[start];
        int middleValue = array[middle];
        int endValue = array[end];
        int index;

        System.out.println("StartValue: " + startValue + "\nMiddleValue: " + middleValue + "\nEndValue: " + endValue);

        if (startValue > endValue) {
            if (startValue < middleValue) {
                index = start;
            } else
                index = middle;
        } else if (endValue > middleValue)
            index = end;
        else
            index = middle;

        players.swap(end, index);

        System.out.println("Chosen Middle: " + players.getScores()[end]);
    }

    public static int split(int[] array, int start, int end) {
        if ((start - end) > 2)
            chooseMedianPivot(array, start, end); // Set last index to median of 3 value.

        int pivot = end; // Pivot on the last index.

        int splitPoint = start;

        // for every value found within the array that is less than pivot, move it to splitPoint and increment
        // splitPoint
        for (int i = start; i < end; i++) {
            if (array[i] < array[pivot]) {
                players.swap(i, splitPoint);
                splitPoint++;
            }
        }

        players.swap(pivot, splitPoint); // Move pivot to after last swap and return split pos
        return splitPoint;
    }

    public static void quickSort(int[] array, int start, int end) {
        // If start is greater than end (more than 1 element in array), call split method to
        // sort around the value found in its median of 3 method, then return the pivot. Take the
        // two halves around this pivot and recursively run again.
        if (start < end) {
            int pivot = split(array, start, end);

            quickSort(array, start, pivot-1);
            quickSort(array, pivot+1, end);
        }
    }

    public static void bubbleSort(String[] names, int[] scores) {
        int n = names.length;
        for (int i = 0; i < n - 1; i++) {
// Inner loop to compare adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
// Swap elements if they are in the wrong order
                if (scores[j] < scores[j + 1]) {
                    int tempScore = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = tempScore;
                    String tempName = names[j];
                    names[j] = names[j + 1];
                    names[j + 1] = tempName;
                }
            }
        }
    }
}

class Players {
    private String[] names;
    private int[] scores;
    private HashMap<String, Integer> playerMap = new HashMap<>();

    public Players(String[] names, int[] scores) {
        this.names = names;
        this.scores = scores;
        for (int i = 0; i < names.length && i < scores.length; i++) {
            addPlayer(this.names[i], this.scores[i]);
        }
    }

    // Swap values at 2 indexes on both arrays simultaneously.
    public void swap(int a, int b) {
        String tempS = names[a];
        names[a] = names[b];
        names[b] = tempS;

        int tempI = scores[a];
        scores[a] = scores[b];
        scores[b] = tempI;
    }

    // Method to check that my quicksort is sorting and not losing/duplicating values.
    // Since all the names in the array are unique, we can iterate through the names array and check
    // first, if it's already contained in the map, and second, if the value associated with that name in the
    // map matches the value found in the score array with the same index of the name in the names array.
    // If the name is contained in the map that means it hasn't been compared yet. Once compared, we remove it
    // to signify that it has been checked. Should the same name show up again, we know the name was somehow duplicated
    // as there is no key to match it to and there was only one occurrence of that name.
    // Lastly, we check if the map is empty. If it is empty, then all checks have passed and the before and after
    // sort values and connections have remained intact.
    public boolean verifySymmetry() {
        for (int i = 0; i < scores.length; i++) {
            if (playerMap.containsKey(names[i]) && playerMap.get(names[i]) == scores[i])
                playerMap.remove(names[i]);
            else
                return false;
        }
        return playerMap.isEmpty();
    }

    public void addPlayer(String name, int score) {
        playerMap.put(name, score);
    }
    public void setNames(String[] names) { this.names = names; }
    public void setScores(int[] scores) { this.scores = scores; }
    public String[] getNames() { return names; }
    public int[] getScores() { return scores; }
}
