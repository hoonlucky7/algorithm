// https://www.acmicpc.net/problem/15649
// 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
// 이미뽑은건 못 쓰니까 visited라는 배열을 만들어서 체크 함

// A sequence of M numbers selected from the natural numbers 1 to N without duplicates.
// Since already chosen numbers cannot be used again, a visited array is used to keep track.

import java.util.Scanner;

public class Permutation {
    private static int N, M;
    private static int[] sequence;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); 
        M = sc.nextInt();
        sc.close();

        sequence = new int[M]; // Array to store the current sequence
        visited = new boolean[N + 1]; // Boolean array to track visited numbers
        
        dfs(0); // Start generating permutations from depth 0
        System.out.print(sb.toString()); // Print all results at once for efficiency
    }

    private static void dfs(int depth) {
        if (depth == M) {
            for (int num : sequence) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = i;
                dfs(depth + 1);
                visited[i] = false;
                sequence[depth] = 0;
            }
        }
    }
}