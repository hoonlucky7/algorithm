


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MinimaxTree {
    static class Node {
        int id;
        int value;
        List<Node> children;
        
        Node(int id) {
            this.id = id;
            this.value = 0; // 리프가 아니면 초기값은 0, 리프 노드 값은 나중에 입력으로 채워짐.
            this.children = new ArrayList<>();
        }
    }
    private Node[] nodes; 
    private List<Integer>[] graph;     // 입력 받은 무방향 트리의 인접 리스트

    private int minimax(Node node, boolean isMax) {
        if (node.children.isEmpty()) {
            return node.value;
        }
        int bestValue;
        if (isMax) {
            bestValue = Integer.MIN_VALUE;
            for (Node childeNode : nodes[node.id].children) {
                bestValue = Math.max(bestValue, minimax(childeNode, !isMax));
            }
        } else {
            bestValue = Integer.MAX_VALUE;
            for (Node childeNode : nodes[node.id].children) {
                bestValue = Math.min(bestValue, minimax(childeNode, !isMax));
            }
        }
        nodes[node.id].value = bestValue;
        return bestValue;
    }

    void buildTree(int current, int parent) {
        for (int neighbor : graph[current]) {
            if (neighbor == parent) continue;
            // current 노드의 자식으로 neighbor 노드를 추가
            nodes[current].children.add(nodes[neighbor]);
            buildTree(neighbor, current);
        }
    }

    public void solution() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("./basic/minimaxTree/input.txt"));
        //Scanner sc = new Scanner(System.in);
        
        // 입력: 트리의 정점 수 N, 루트 노드 R
        int N = sc.nextInt();
        int R = sc.nextInt();
        
        // nodes 배열과 graph 초기화 (노드 id는 1부터 시작)
        nodes = new Node[N + 1];
        graph = new ArrayList[N + 1];
        //graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
            graph[i] = new ArrayList<>();
        }
        
        // 트리 간선 정보 입력 (양 끝 점 u, v)
        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }
        buildTree(R, -1);
        
        // 리프 노드의 개수 L과 각 리프 노드의 번호와 값을 입력받음
        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            int leafId = sc.nextInt();
            int leafValue = sc.nextInt();
            nodes[leafId].value = leafValue; 
        }
        
        // Minimax 알고리즘 적용: 루트 노드는 MAX player로 시작
        minimax(nodes[R], true);
        
        // 질의 처리: Q개의 노드 번호에 대해 해당 노드의 값을 출력
        int Q = sc.nextInt();
        for (int i = 0; i < Q; i++) {
            int q = sc.nextInt();
            System.out.println(nodes[q].value);
        }
        
        sc.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        MinimaxTree minimaxTree = new MinimaxTree();
        minimaxTree.solution();
    }
}
