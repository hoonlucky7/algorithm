// https://www.acmicpc.net/problem/15652
// 중복조합의 핵심은 재귀 호출 시 현재 인덱스부터 다시 선택하여(즉, start 값을 유지) 중복 선택이 가능하도록 하는 것과, 
// 비내림차순을 유지하여 중복되는 조합을 방지하는 것입니다.

// The key point of combination with repetition is to allow duplicate selections by recursively calling starting 
// from the current index (i.e., maintaining the start value),
// and to prevent duplicate combinations by keeping the sequence in non-decreasing order.

// dfs(1, 0)  []  
// │  
// ├─ [i=1] sequence[0] = 1 → dfs(1, 1)  [1]  
// │      │  
// │      ├─ [i=1] sequence[1] = 1 → dfs(1, 2)  [1, 1]  → 출력: "1 1"  
// │      │  
// │      ├─ [i=2] sequence[1] = 2 → dfs(2, 2)  [1, 2]  → 출력: "1 2"  
// │      │  
// │      ├─ [i=3] sequence[1] = 3 → dfs(3, 2)  [1, 3]  → 출력: "1 3"  
// │      │  
// │      └─ [i=4] sequence[1] = 4 → dfs(4, 2)  [1, 4]  → 출력: "1 4"  
// │  
// ├─ [i=2] sequence[0] = 2 → dfs(2, 1)  [2]  
// │      │  
// │      ├─ [i=2] sequence[1] = 2 → dfs(2, 2)  [2, 2]  → 출력: "2 2"  
// │      │  
// │      ├─ [i=3] sequence[1] = 3 → dfs(3, 2)  [2, 3]  → 출력: "2 3"  
// │      │  
// │      └─ [i=4] sequence[1] = 4 → dfs(4, 2)  [2, 4]  → 출력: "2 4"  
// │  
// ├─ [i=3] sequence[0] = 3 → dfs(3, 1)  [3]  
// │      │  
// │      ├─ [i=3] sequence[1] = 3 → dfs(3, 2)  [3, 3]  → 출력: "3 3"  
// │      │  
// │      └─ [i=4] sequence[1] = 4 → dfs(4, 2)  [3, 4]  → 출력: "3 4"  
// │  
// └─ [i=4] sequence[0] = 4 → dfs(4, 1)  [4]  
//        │  
//        └─ [i=4] sequence[1] = 4 → dfs(4, 2)  [4, 4]  → 출력: "4 4"



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
        if (depth == M) {
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = start; i <= N; i++) {
            sequence[depth] = i;
            dfs(i, depth + 1);
            sequence[depth] = 0;
        }
    }
}
