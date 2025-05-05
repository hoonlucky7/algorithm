// problem link : https://www.acmicpc.net/problem/11725
// [solution]
// 왜 DFS와 BFS를 사용할까?
// 트리의 특성: 트리는 사이클이 없고, 각 노드는 정확히 하나의 부모를 가짐(루트 제외). 
// DFS는 트리의 구조를 따라 한 갈래씩 탐색하므로 부모를 쉽게 찾을 수 있음

// BFS로 이 문제를 풀 수 있는 이유는 트리의 단일 경로 특성 
// BFS의 층별 탐색 방식이 부모-자식 관계를 자연스럽게 드러내기 때문
// BFS는 노드를 방문할 때 처음 발견한 노드를 부모로 설정

// 효율성: DFS는 각 노드와 간선을 한 번씩만 방문하므로 시간 복잡도는 O(N)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class ParentFinder {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int[] parent;

    public static void dfs(int node) {
        // 현재 노드를 방문 처리
        visited[node] = true;

        // 현재 노드와 연결된 모든 인접 노드를 탐색
        for (int next : adj[node]) {
            // 인접 노드가 아직 방문되지 않았다면
            if (!visited[next]) {
                // 인접 노드의 부모를 현재 노드로 설정
                parent[next] = node;   
                // 인접 노드에서 DFS를 재귀적으로 호출하여 탐색 계속
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        // 큐가 빌 때까지 반복합니다.
        while(!queue.isEmpty()) {
            // 큐에서 노드를 꺼내고, 
            int node = queue.poll();

            // 해당 노드의 인접 노드 중 방문하지 않은 노드를 큐에 삽입합니다.
            for (int next : adj[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = node;
                    queue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수

        // 인접리스트 초기화
        adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        parent = new int[n + 1];
        visited = new boolean[n + 1];
        //dfs
        //dfs(1);
        //bfs
        bfs(1);

        // 빠르게 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.print(sb);
    }
}


