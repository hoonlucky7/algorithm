import java.util.Scanner;

public class CombinationWithRepetitionModified {
    private static int N, M;
    private static int[] sequenceCounts;
    private static StringBuilder sb = new StringBuilder(); // StringBuilder for efficient output

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // N: number of targets (or segments), M: total count to be allocated
        N = sc.nextInt();
        M = sc.nextInt();
        sequenceCounts = new int[N + 1]; // Array to store the allocation counts for each target
        sc.close();

        dfs(1, M);
        System.out.print(sb.toString()); // Print all stored sequences at once
    }

    // i: current target index, n: remaining count
    private static void dfs(int i, int n) {
        if (i == N + 1) { // When decisions have been made for all targets
            // Process only valid combinations where the remaining count is 0
            if (n == 0) {
                for (int num = 1; num <= N; num++) {
                    for (int c = 1; c <= sequenceCounts[num]; c++) {
                        sb.append(num).append(" ");
                    }
                }
                sb.append("\n");
            }
            return;
        }
        // Distribute counts from n down to 0 for the current target i.
        // (Allocating from n down to 0 results in reverse order output)
        for (int count = n; count >= 0; count--) {
            sequenceCounts[i] = count;
            dfs(i + 1, n - count);
            sequenceCounts[i] = 0; // Backtracking: restore the previous state
        }
    }
}
