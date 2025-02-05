// N과 M (2) [조합]
// https://www.acmicpc.net/problem/15650
// 이 코드는 1부터 N까지의 숫자 중에서 M개를 중복 없이 선택하여 조합을 생성합니다.
// 재귀 호출 시 다음 선택은 현재 선택한 수보다 큰 값(i+1)부터 진행하여, 
// 순서를 유지하고 중복되는 조합이 나오지 않도록 합니다.
// This code generates combinations by selecting M numbers from 1 to N without repetition. 
// During recursive calls, the next selection starts from a number greater than the current one (i+1), 
// ensuring that the order is maintained and duplicate combinations are avoided.

import java.util.Scanner;

public class Combination {
    private static int N, M;
    private static int[] combination;
    private static StringBuilder sb = new StringBuilder(); // StringBuilder for efficient output

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // Read N (range of numbers from 1 to N)
        M = sc.nextInt(); // Read M (length of the combination)
        combination = new int[M]; // Array to store the current combination
        sc.close(); // Close the scanner to prevent memory leaks

        dfs(1, 0);
        System.out.print(sb.toString()); // Print all stored combinations at once
    }

    private static void dfs(int start, int depth) {
        if (depth == M) { // If the combination reaches length M, store it in StringBuilder
            for (int num : combination) {
                sb.append(num).append(" ");
            }
            sb.append("\n"); // Append a newline after each valid combination
            return;
        }

        for (int i = start; i <= N; i++) {
            combination[depth] = i; // Add number to the current combination
            dfs(i + 1, depth + 1); // Recursively generate the next depth
        }
    }
}
