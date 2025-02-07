import java.util.Scanner;

public class CombinationWithRepetitionModified {
    private static int N, M; // N: Range of selectable numbers (1~N), M: Total numbers to choose
    private static int[] count; // Array to store how many times each number is selected
    private static StringBuilder sb = new StringBuilder(); // StringBuilder for efficient output storage

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // Maximum number range (1~N)
        M = sc.nextInt(); // Total count of numbers to be selected
        count = new int[N + 1]; // Allocate an array of size N+1 (1-based index)
        sc.close();

        dfs(1, M); // Start from 1 and choose M numbers
        System.out.print(sb.toString()); // Print all results at once
    }

    /**
     * Recursive function to generate combinations with repetition
     * @param currentNumber The number currently being considered for selection
     * @param remainingCount The number of remaining selections to be made
     */
    private static void dfs(int currentNumber, int remainingCount) {
        if (currentNumber == N + 1) {
            if (remainingCount == 0) {
                saveCombination();
            }
            return;
        }

        for (int countOfCurrent = remainingCount; countOfCurrent >= 0; countOfCurrent--) {
            count[currentNumber] = countOfCurrent;
            dfs(currentNumber + 1, remainingCount - countOfCurrent);
            count[currentNumber] = 0;
        }
    }

    /**
     * Save the currently selected combination into StringBuilder
     */
    private static void saveCombination() {
        for (int num = 1; num <= N; num++) {
            for (int i = 0; i < count[num]; i++) {
                sb.append(num).append(" ");
            }
        }
        sb.append("\n");
    }
}
