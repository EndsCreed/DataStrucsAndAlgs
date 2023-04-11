public class Main {
    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        MinPathSum solution = new MinPathSum();
        System.out.println(solution.printMinPath(grid)); // Changed to my new method call
    }
}

// Changed this to an individual class as I think this is the way it was meant to be used. The code did
// not compile before and containerizing the path methods away from the main class and method makes more sense.
class MinPathSum {
// I made below methods public to allow access from both methods
    private int m; // Get number of secondary Arrays (height)
    private int n; // get number of elements in the secondary array (width)
    private int[][] dp; // Second 2d Array to store the traversal

// NEW METHOD: printMinPath
//
// This method is responsible for finding the optimal path through the new "dp" array and building the
// output strings based on the path it takes.
    public String printMinPath(int[][] grid) {
        int sum = minPathSum(grid); // Use the existing method to get the final traversal time
        if (dp == null)
            return "An Error has Occurred";
    // ^ Null check for good practise. Just incase the given method malfunctions
        StringBuilder path = new StringBuilder("Path: [0,0]");
        StringBuilder values = new StringBuilder("Values: " + dp[0][0]);
        StringBuilder sumOfValues = new StringBuilder("Sum of values along the path: " + dp[0][0]);
        int y = 0;
        int x = 0;
    // ^ StringBuilders for piecing together the output and y, x ints for the current x and y position in the 2d array.

        while(true) {
            if (y+1 == m) { // In the event we have reached the bottom
                if (x+1 == n) { // Reached bottom right corner
                    sumOfValues.append(" = ").append(sum);
                    break;
                }
                x++;
                path.append(" -> [").append(y).append(",").append(x).append("]");
                values.append(" -> ").append(grid[y][x]);
                sumOfValues.append(" -> ").append(grid[y][x]);
            } else if (x+1 == n) { // Haven't reached the bottom but reached the right edge
                y++;
                path.append(" -> [").append(y).append(",").append(x).append("]");
                values.append(" -> ").append(grid[y][x]);
                sumOfValues.append(" -> ").append(grid[y][x]);
            } else { // Not on the edge so we can compare both sides
                if (dp[y+1][x] < dp[y][x+1])
                    y++;
                else
                    x++;

                path.append(" -> [").append(y).append(",").append(x).append("]");
                values.append(" -> ").append(grid[y][x]);
                sumOfValues.append(" -> ").append(grid[y][x]);
            }
        }

        return path + "\n" + values + "\n" + sumOfValues;
    }

    // Given method which fills out the new 2d arrays first column then row using relative addition and then fills the
    // remaining spaces relative to the ones it knows. Example:
    // dp[1,0] is dp[0,0] + grid[1,0].
    // dp[2,0] is dp[1,0] + grid[2,0].
    // and so on...
    // I changed this method to private as I didn't need it to be used in the main method with my new print method.
    private int minPathSum(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new int[m][n];
        dp[0][0] = grid[0][0];
        // Explore down and add up the values along the way
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // Explore right and add up the values along the way
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        // Starting at [1,1], add the lowest value of the two positions you could have come from to the current space.
        // By having the lowest value added, you find the lowest value you could be at when arriving at that space.
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        // Return the last slot (Which should have the lowest possible value)
        return dp[m - 1][n - 1];
    }
}