package org.example;

import java.util.Arrays;

public class MinCostClimbingStairs {

    public int minCostClimbingStairs(int[] cost) {
        int[] C = new int[cost.length + 1];
        int n = cost.length;

        C[0] = 0;
        C[1] = 0;

        for (int i = 2; i <= n; i++) {
            C[i] = Math.min(C[i - 1] + cost[i - 1], C[i - 2] + cost[i - 2]);
        }
        return C[cost.length];
    }

    public static void main(String[] args) {
        MinCostClimbingStairs minCostClimbingStairs = new MinCostClimbingStairs();
        int[] cost1 = new int[]{10,15,20};
        System.out.println(minCostClimbingStairs.minCostClimbingStairs(cost1));

        int[] cost2 = new int[]{1,100,1,1,1,100,1,1,100,1};
        System.out.println(minCostClimbingStairs.minCostClimbingStairs(cost2));
    }
}
