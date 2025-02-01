//https://www.acmicpc.net/problem/15651
//자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
//1부터 N까지 자연수 중에서 M개를 고른 수열 같은 수를 여러 번 골라도 된다.(중복가능)

// Given natural numbers N and M, write a program that generates all sequences of length M that satisfy 
// the following conditions.
// Select M numbers from the natural numbers 1 to N.
// The same number can be chosen multiple times (duplicates allowed).

import java.util.Scanner;

public class PermutationWithRepetition {
    private static int N, M;
    private static int[] sequence;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // Read N (range of numbers)
        M = sc.nextInt(); // Read M (length of sequence)
        sc.close();
        
        sequence = new int[M];
        dfs(0);
        System.out.print(sb.toString()); // Print all results at once
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
            sequence[depth] = i;
            dfs(depth + 1);
            sequence[depth] = 0;
        }
    }
}
