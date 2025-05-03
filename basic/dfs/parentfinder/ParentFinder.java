// problem link : https://www.acmicpc.net/problem/11725
// [solution]
// 왜 DFS를 사용할까?
// 트리의 특성 : 트리는 사이클이 없고, 각 노드는 정확히 하나의 부모를 가짐(루트 제외). 
// DFS는 트리의 구조를 따라 한 갈래씩 탐색하므로 부모를 쉽게 찾을 수 있음
// 시간 복잡도 : O(N)
// DFS는 각 노드와 간선을 한 번씩만 방문하므로 


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ParentFinder {
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[] result;
    static int count;

    public static void dfs(int node) {
        // 현재 노드를 방문 처리
        visited[node] = true;
        
        // 현재 노드와 연결된 모든 인접 노드를 탐색
        for (int next : graph[node]) {
            // 인접 노드가 아직 방문되지 않았다면
            if (!visited[next]) {
                // 인접 노드의 부모를 현재 노드로 설정
                result[next] = node;
                // 인접 노드에서 DFS를 재귀적으로 호출하여 탐색 계속
                dfs(next);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수

        graph = new ArrayList[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        result = new int[n + 1];
        visited = new boolean[n + 1];
        dfs(1);

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.print(sb);
    }
}


