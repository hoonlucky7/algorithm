import java.io.*;
import java.util.*;

public class TreeDiameter {
    // Edge 클래스 정의
    static class Edge {
        int to;    // 목적지 노드
        int weight; // 가중치

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<List<Edge>> adj;
    static boolean[] visited;
    static long maxDist;
    static int farthestNode;

    // DFS 메소드
    static void dfs(int node, long dist) {
        visited[node] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = node;
        }
        for (Edge edge : adj.get(node)) {
            int nextNode = edge.to;
            int weight = edge.weight;
            if (!visited[nextNode]) {
                dfs(nextNode, dist + weight);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 특수 케이스: 노드 1개면 지름 0
        if (n == 1) {
            System.out.println(0);
            return;
        }

        // 인접 리스트와 방문 배열 초기화
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];

        // 간선 정보 입력 (무방향 트리)
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj.get(parent).add(new Edge(child, weight));
            adj.get(child).add(new Edge(parent, weight));
        }

        // 첫 번째 DFS: 임의의 노드(1)에서 가장 먼 노드 찾기
        maxDist = 0;
        farthestNode = 1;
        Arrays.fill(visited, false); // 방문 배열 초기화
        dfs(1, 0);

        // 두 번째 DFS: 가장 먼 노드에서 최대 거리 찾기
        maxDist = 0;
        Arrays.fill(visited, false); // 방문 배열 재초기화
        dfs(farthestNode, 0);

        // 결과 출력
        System.out.println(maxDist);
    }
}