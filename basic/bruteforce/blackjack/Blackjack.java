// https://www.acmicpc.net/problem/2798

// solution
// 100C3 = 100P3 / (100 - 3)! =
// 100 * 99 * 98 / 6 = 161700

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Blackjack {

    private static int n, m;
    private static int[] cards;
    private static int max;
    private static StringBuilder sb = new StringBuilder(); // StringBuilder for efficient output

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        cards = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        max = 0;
        dfs(1, 0, 0);
        System.out.print(max);
    }

    private static void dfs(int start, int depth, int sum) {
        if (sum > m) {
            return;
        }

        if (depth == 3) {
            if (sum <= m && sum > max) {
                max = sum;
            }
            return;
        }
        for (int i = start; i <= n; i++) {
            dfs(i + 1, depth + 1, sum + cards[i]);
        }
    }
}
