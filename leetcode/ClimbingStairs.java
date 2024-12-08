package org.example;

public class ClimbingStairs {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];

        memo[1] = 1;
        if (n == 1) {
            return memo[1];
        }
        memo[2] = 2;
        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2];
        }
        return memo[n];
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println(climbingStairs.climbStairs(4));
    }
}
