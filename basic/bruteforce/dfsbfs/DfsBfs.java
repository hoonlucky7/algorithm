// 문제 링크 : https://www.acmicpc.net/problem/1260

// DFS (Depth-First Search, 깊이 우선 탐색)
// DFS는 한 노드를 방문하면, 해당 노드에서 갈 수 있는 한 깊숙이 탐색한 후 
// 돌아오는 방식의 탐색 알고리즘입니다.
// dfs 핵심 원리
// 1) 시작 노드를 방문하고, 방문 처리를 합니다.
// 2) 현재 노드에서 갈 수 있는 인접 노드 중 방문하지 않은 노드를 
// 재귀적으로 방문합니다.
// 3) 더 이상 방문할 수 있는 노드가 없으면, 이전 노드로 되돌아갑니다.
// 4) 위 과정을 반복하여 모든 연결된 노드를 방문합니다

// BFS (Breadth-First Search, 너비 우선 탐색)
// BFS는 시작 노드에서 가까운 노드를 먼저 탐색하고, 
// 점점 멀리 있는 노드들을 탐색하는 방식입니다.

// bfs 핵심 원리
// 1) 시작 노드를 방문하고 큐(Queue) 에 삽입합니다.
// 2) 큐에서 노드를 꺼내고, 
// 해당 노드의 인접 노드 중 방문하지 않은 노드를 큐에 삽입합니다.
// 3) 큐가 빌 때까지 반복합니다.

import java.io.*;
import java.util.*;

public class DfsBfs {
    static List<Integer>[] graph;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 정점 개수
        int M = Integer.parseInt(st.nextToken()); // 간선 개수
        int V = Integer.parseInt(st.nextToken()); // 시작 정점

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        visited = new boolean[N + 1];
        dfs(V);
        
        sb.append("\n");

        visited = new boolean[N + 1];
        bfs(V);
        
        System.out.println(sb.toString());
    }

    static void dfs(int node) {

        visited[node] = true;
        sb.append(node).append(" ");

        // 2) 현재 노드에서 갈 수 있는 인접 노드 중 방문하지 않은 노드를 
        // 재귀적으로 방문합니다.
        for (int next : graph[node]) {
            if(!visited[next]) {
                dfs(next);
            }
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        visited[start] = true;

        // 3) 큐가 빌 때까지 반복합니다.
        while(!queue.isEmpty()) {
            // 2) 큐에서 노드를 꺼내고, 
            int node = queue.poll();
            sb.append(node).append(" ");

            // 해당 노드의 인접 노드 중 방문하지 않은 노드를 큐에 삽입합니다.
            for (int next : graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }
}