import java.util.Scanner;

public class CombinationWithRepetition {
    private static int N, M;
    private static int[] sequence;
    private static StringBuilder sb = new StringBuilder(); // StringBuilder for efficient output

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // Read N (range of numbers from 1 to N)
        M = sc.nextInt(); // Read M (length of the sequence)
        sequence = new int[M]; // Array to store the current sequence
        sc.close(); // Close the scanner

        dfs(1, 0);
        System.out.print(sb.toString()); // Print all stored sequences at once
    }

    private static void dfs(int start, int depth) {
        if (depth == M) { // If the sequence reaches length M, store it in StringBuilder
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append("\n"); // Append a newline after each valid sequence
            return;
        }

        for (int i = start; i <= N; i++) { // Ensure non-decreasing order
            sequence[depth] = i; // Add number to the current sequence
            dfs(i, depth + 1); // Recursive call with same 'i' for duplicates
        }
    }
}
