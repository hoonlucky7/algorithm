// N과 M (2) [조합]
// https://www.acmicpc.net/problem/15650
// 이 코드는 1부터 N까지의 숫자 중에서 M개를 중복 없이 선택하여 조합을 생성합니다.
// 재귀 호출 시 다음 선택은 현재 선택한 수보다 큰 값(i+1)부터 진행하여, 
// 순서를 유지하고 중복되는 조합이 나오지 않도록 합니다.
// This code generates combinations by selecting M numbers from 1 to N without repetition. 
// During recursive calls, the next selection starts from a number greater than the current one (i+1), 
// ensuring that the order is maintained and duplicate combinations are avoided.

// dfs(1, 0)
// ├── [1]  → dfs(2, 1)
// │      ├── [1, 2]  → dfs(3, 2)
// │      │      ├── [1, 2, 3]  → dfs(4, 3)   → Output: 1 2 3
// │      │      ├── [1, 2, 4]  → dfs(5, 3)   → Output: 1 2 4
// │      │      └── [1, 2, 5]  → dfs(6, 3)   → Output: 1 2 5
// │      ├── [1, 3]  → dfs(4, 2)
// │      │      ├── [1, 3, 4]  → dfs(5, 3)   → Output: 1 3 4
// │      │      └── [1, 3, 5]  → dfs(6, 3)   → Output: 1 3 5
// │      ├── [1, 4]  → dfs(5, 2)
// │      │      └── [1, 4, 5]  → dfs(6, 3)   → Output: 1 4 5
// │      └── [1, 5]  → dfs(6, 2)   → (Incomplete, no output)
// ├── [2]  → dfs(3, 1)
// │      ├── [2, 3]  → dfs(4, 2)
// │      │      ├── [2, 3, 4]  → dfs(5, 3)   → Output: 2 3 4
// │      │      └── [2, 3, 5]  → dfs(6, 3)   → Output: 2 3 5
// │      ├── [2, 4]  → dfs(5, 2)
// │      │      └── [2, 4, 5]  → dfs(6, 3)   → Output: 2 4 5
// │      └── [2, 5]  → dfs(6, 2)   → (Incomplete, no output)
// ├── [3]  → dfs(4, 1)
// │      ├── [3, 4]  → dfs(5, 2)
// │      │      └── [3, 4, 5]  → dfs(6, 3)   → Output: 3 4 5
// │      └── [3, 5]  → dfs(6, 2)   → (Incomplete, no output)
// ├── [4]  → dfs(5, 1)
// │      └── [4, 5]  → dfs(6, 2)   → (Incomplete, no output)
// └── [5]  → dfs(6, 1)   → (Incomplete, no output)

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
        if (depth == M) {
            for (int num : combination) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            combination[depth] = i;
            dfs(i + 1, depth + 1);
            combination[depth] = 0;
        }
    }
}
