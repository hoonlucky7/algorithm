// 트리의 지름
// 문제링크 : https://www.acmicpc.net/problem/1967

// [solution]
// 1번째 dfs: 1번 노드에서 가장 먼 노드(A)를 찾음(이 A는 트리 지름을 이루는 양 끝점 중 하나가 됨)
// 2번째 dfs: A에서 가장 먼 점 B를 찾음
// A와 B 사이의 경로가 바로 트리의 지름이 됩니다.

// 예시)
// 직선 형태의 트리가 있다고 가정합시다 : 3-2-1-4-5
// 노드 1에서 시작한다면, 가장 먼 노드는 3 또는 5입니다.
// 노드 5를 찾았다고 합시다. 그리고 5에서 가장 먼 노드는 3입니다.
// 3과 5 사이의 거리가 트리의 지름이 됩니다.

// 시간복잡도 : O(n)

import java.io.*;
import java.util.*;

public class TreeDiameter {
    // Edge 클래스 정의
    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;
            this.w = w;
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
            int w = edge.w;

            if (!visited[nextNode]) {
                dfs(nextNode, dist + w);
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
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj.get(u).add(new Edge(v, w));
            adj.get(v).add(new Edge(u, w));
        }

        // 첫 번째 DFS: 임의의 노드(1)에서 가장 먼 노드 찾기
        maxDist = 0;
        farthestNode = 1;
        Arrays.fill(visited, false);
        dfs(1, 0);

        // 두 번째 DFS: 가장 먼 노드에서 최대 거리 찾기
        maxDist = 0;
        Arrays.fill(visited, false);
        dfs(farthestNode, 0);

        // 결과 출력
        System.out.println(maxDist);
    }
}