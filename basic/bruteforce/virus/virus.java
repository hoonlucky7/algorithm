// 문제링크 : https://www.acmicpc.net/problem/2606
// solution 1 : dfs 활용
// dfs를 활용 할때는 call stack의 깊이가 1000 이하 일때만 구현 고려
// 인접 노드 중 방문하지 않은 노드가 있을 때 count를 증가

// solution 2 : bfs 활용
// 인접 노드 중 방문하지 않은 노드가 있을 때 count를 증가

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class virus {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int count;

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[n + 1];
        count = 0;

        dfs(1);

        System.out.println(count);
    }

    static void dfs(int node) {
        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                count++;
                dfs(next);
            }
        }
    }
}
