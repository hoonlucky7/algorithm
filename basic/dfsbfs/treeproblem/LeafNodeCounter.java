// problem link : https://www.acmicpc.net/problem/1068
// [solution]
// DFS를 사용하여 트리를 탐색합니다.
// 각 노드에서 자식이 있는지 확인하고, 자식이 없다면 리프 노드로 카운트합니다.
// 삭제된 노드는 탐색하지 않습니다.

// 시간 복잡도는 O(N)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LeafNodeCounter {
    static List<Integer>[] adj;
    static boolean[] visited;
    static int rootNode;
    static int leafCount;
    static int deleteNode;

    public static void dfs(int node) {
        // 현재 노드를 방문 처리
        visited[node] = true;
        boolean hasChild = false;

        // 현재 노드와 연결된 모든 인접 노드를 탐색
        for (int next : adj[node]) {
            if (next == deleteNode) {
                continue;
            }
            // 인접 노드가 아직 방문되지 않았다면
            if (!visited[next]) {
                hasChild = true;
                // 인접 노드에서 DFS를 재귀적으로 호출하여 탐색 계속
                dfs(next);
            }
        }
        
        if (!hasChild) {
            leafCount++;
        }
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수

        // 인접리스트 초기화
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(st.nextToken());
            if (p == -1) { // 루트
                rootNode = i;
                continue;
            }
            adj[p].add(i);
            adj[i].add(p);
        }

        deleteNode = Integer.parseInt(br.readLine());

        if (deleteNode == rootNode) {
            System.out.println(0);
            return;
        }

        visited = new boolean[n + 1];
        leafCount = 0;
        dfs(rootNode);
        
        System.out.println(leafCount);
    }
}