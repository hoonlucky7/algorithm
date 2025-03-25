import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DfsBfs {
    private static int N, M, S;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); 
        M = sc.nextInt();
        S = sc.nextInt();

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }
        sc.close();

        visited = new boolean[N + 1]; // Boolean array to track visited numbers
        
        dfs(S, 0); // Start generating permutations from depth 0
        System.out.print(sb.toString()); // Print all results at once for efficiency
    }

    private static void dfs(int vertex, int depth) {
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