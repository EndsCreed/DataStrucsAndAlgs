import java.util.HashMap;

public class Main {
    public static HashMap<String, Integer> playerMap = new HashMap<>(); // Used for validation
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

        for (int i = 0; i < names.length; i++) {
            playerMap.put(names[i], scores[i]);
        }

        String[] namesQuick = names.clone();
        int[] scoresQuick = scores.clone();
        String[] namesBubble = names.clone();
        int[] scoresBubble = scores.clone();

        // 1000 runs for testing of average time.
        for (int i = 0; i < 1000; i++) {
            long startTime = System.nanoTime();
            quickSort(namesQuick, scoresQuick, 0, scores.length - 1);
            long endTime = System.nanoTime();
            totalTime += endTime - startTime;
            namesQuick = names.clone();
            scoresQuick = scores.clone();
        }

        for (int i = 0; i < 1000; i++) {
            long startTime = System.nanoTime();
            bubbleSort(namesBubble, scoresBubble);
            long endTime = System.nanoTime();
            totalTimeBubble += endTime - startTime;
            namesBubble = names.clone();
            scoresBubble = scores.clone();
        }

        if (verifySymmetry(namesQuick, scoresQuick)) {
            System.out.println("Sorted Quick:");
            for (int i = 0; i < scoresQuick.length; i++) {
                System.out.println(namesQuick[i] + " | " + scoresQuick[i]);
            }

            for (int i = 0; i < scoresBubble.length; i++) {
                playerMap.put(names[i], scores[i]);
            }
            if (!verifySymmetry(namesBubble, scoresBubble)) {
                System.out.println("Bubble Sort Discrepancy!");
                return;
            }

            System.out.println("\nSorted Buble:");
            for (int i = 0; i < scoresBubble.length; i++) {
                System.out.println(namesBubble[i] + " | " + scoresBubble[i]);
            }
            double averageTime = (double) totalTime / 1000;
            double averageTimeBubble = (double) totalTimeBubble / 1000;
            System.out.println("Average Time Quick: " + averageTime + "\nAverage time Bubble: " + averageTimeBubble);
        } else
            System.out.println("There was a discrepancy!");
    }

    public static void swap(String[] names, int[] scores, int a, int b) {
        String tempS = names[a];
        names[a] = names[b];
        names[b] = tempS;

        int tempI = scores[a];
        scores[a] = scores[b];
        scores[b] = tempI;
    }

    // This method will move the median value to the end of the array to use as the pivot
    public static void chooseMedianPivot(String[] names, int[] scores, int start, int end) {
        int middle = start + (end - start) / 2; // get middle index (not done yet)

        int startValue = scores[start];
        int middleValue = scores[middle];
        int endValue = scores[end];
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

        swap(names, scores, end, index);

        System.out.println("Chosen Middle: " + scores[end]);
    }

    public static int split(String[] names, int[] scores, int start, int end) {
        if ((start - end) > 2)
            chooseMedianPivot(names, scores, start, end); // Set last index to median of 3 value.

        int pivot = end; // Pivot on the last index.

        int splitPoint = start;

        // for every value found within the array that is less than pivot, move it to splitPoint and increment
        // splitPoint
        for (int i = start; i < end; i++) {
            if (scores[i] < scores[pivot]) {
                swap(names, scores, i, splitPoint);
                splitPoint++;
            }
        }

        swap(names, scores, pivot, splitPoint); // Move pivot to after last swap and return split pos
        return splitPoint;
    }

    public static void quickSort(String[] names, int[] scores, int start, int end) {
        // If start is greater than end (more than 1 element in array), call split method to
        // sort around the value found in its median of 3 method, then return the pivot. Take the
        // two halves around this pivot and recursively run again.
        if (start < end) {
            int pivot = split(names, scores, start, end);

            quickSort(names, scores, start, pivot-1);
            quickSort(names, scores, pivot+1, end);
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

    public static boolean verifySymmetry(String[] names, int[] scores) {
        for (int i = 0; i < scores.length; i++) {
            if (playerMap.containsKey(names[i]) && playerMap.get(names[i]) == scores[i])
                playerMap.remove(names[i]);
            else
                return false;
        }
        return playerMap.isEmpty();
    }
}