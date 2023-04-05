public class Main {

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
                76, 87, 56, 66, 59, 63, 69, 80, 89, 62, 96, 55, 97, 92, 71, 50, 57, 78,
                91, 84, 53, 86, 51, 58, 64, 82, 65, 70, 93, 54 };


    }

    public static int chooseMedianPivot(int[] array, int start, int end) {
        int middle = start; // get middle index (not done yet)

        return middle; // Placeholder
    }

    public static void swap(int[] array, int a, int b) {

    }

    public static int split(int[] array, int start, int end) {
        int lower = start;


        return lower;
    }

    public static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivot = split(array, start, end);

            quickSort(array, start, pivot-1);
            quickSort(array, pivot, end);
        }
    }
}

class Player {
     String[] names;
     int[] scores;

     public Player(String[] names, int[] scores) {
         this.names = names;
         this.scores = scores;
     }

     // Swap values at 2 indexes on both arrays simultaneously.
     public void swap(int a, int b) {
         int temp = a;
         names[a] = names[b];
         names[b] = names[temp];

         scores[a] = scores[b];
         scores[b] = scores[temp];
     }
}
