// Problem Link: https://www.acmicpc.net/problem/28472

// Minimax 알고리즘은 체스나 바둑, 틱택토와 같이 상대방과 번갈아가며 하는 게임에서 사용하는 알고리즘이다. 
// 이 알고리즘에서 사용되는 Minimax 트리는 상대방의 최고의 수가 나에게 가장 최소의 영향을 끼치게 하기 위해 
// 만든 게임트리이다. 즉, 한 사람을 기준으로 시작하는 사람이 모든 자식 노드 중 노드의 값이 큰 값을 선택하고 
// 다른 사람은 모든 자식 노드 중 노드의 값이 가장 작은 값을 선택하여 최선의 전략을 찾아보는 방법이다.

// Minimax Tree는 다음과 같이 구성된다.

// 트리에서 리프 노드의 값들은 모두 계산되어 있다.
// 게임에서 선 플레이어는 MAX player로 루트 노드부터 시작한다.
// 루트 노드부터 자식 노드로 내려갈 때마다 MAX player와 MIN player가 번갈아가며 바뀐다.
// 리프 노드가 아니면서 MAX player인 노드의 값은 자식 노드의 값들 중 최댓값으로 계산한다.
// 리프 노드가 아니면서 MIN player인 노드의 값은 자식 노드의 값들 중 최솟값으로 계산한다. 

// 수민이는 친구와 게임을 하기로 하였다. 게임에서 이기면 받은 점수만큼 상품을 주기로 한다. 
// 수민이는 최대한의 상품을 얻기 위해 인공지능 수업에서 배운 Minimax 트리를 그려 최적의 전략을 찾아 놓았다. 
// 하지만 친구가 이를 눈치채고 트리에 있는 숫자들에 색칠을 하여 수민이를 방해하기 시작하였다. 
// 그래도 일말의 양심은 남아있어 리프 노드는 건들지 않아서 값을 알 수 있다고 한다.
//  Minimax 트리를 완성시켜 주기로 하자. 
// 값을 구해야 할 노드들이 주어지면 그 노드의 값을 출력해 보자.

// The Minimax algorithm is used in turn-based games like chess, Go, and tic-tac-toe, where players alternate moves.
// The Minimax tree used in this algorithm is a game tree created to ensure that the opponent's best move
// has the least impact on the current player. In other words, from the perspective of one player,
// the starting player chooses the child node with the largest value, and the other player chooses
// the child node with the smallest value, thereby exploring the best strategies.

// A Minimax Tree is structured as follows:

// The values of all leaf nodes in the tree are already computed.
// The first player in the game is the MAX player, starting from the root node.
// As you descend from the root node to child nodes, the MAX player and MIN player alternate turns.
// The value of a non-leaf node that is a MAX player is calculated as the maximum of its children's values.
// The value of a non-leaf node that is a MIN player is calculated as the minimum of its children's values.

// Sumin decided to play a game with a friend. They agreed to give prizes based on the score obtained 
// from winning the game.
// To maximize her prizes, Sumin drew a Minimax tree, which she learned in her AI class, 
// to find the optimal strategy.
// However, her friend noticed this and started to interfere by coloring the numbers in the tree.
// Luckily, the friend had a bit of conscience left and didn't touch the leaf nodes, 
// so their values are known.
// Let's help Sumin complete the Minimax tree. Given the nodes whose values need to be determined, 
// let's output those values.

// 1.  First Line:
// `N`: The total number of nodes (vertices) in the tree.  (2 ≤ N ≤ 10^5)
// `R`: The node number of the root node of the tree. (1 ≤ R ≤ N)
// `N` and `R` are separated by a space.

// 2.  Next `N - 1` Lines (Tree Edges):
// Each line describes an edge in the tree, connecting two nodes.
// `u`: The node number of one endpoint of the edge. (1 ≤ u ≤ N)
// `v`: The node number of the other endpoint of the edge. (1 ≤ v ≤ N, u ≠ v)
// `u` and `v` are separated by a space.

// 3.  Leaf Node Count:
// `L`: The number of leaf nodes in the tree. (1 ≤ L ≤ N)

// 4. Next `L` Lines (Leaf Node Values):
// Each line describes a leaf node and its associated value.
// `k_i`: The node number of the leaf node. (1 ≤ k_i ≤ N)
// `t_i`: The value assigned to the leaf node `k_i`. (0 ≤ t_i ≤ 10^9)
// `k_i` and `t_i` are separated by a space.

// 5.  Query Count:
// `Q`: The number of nodes for which you need to calculate the Minimax value. (1 ≤ Q ≤ 10^5)


// 6.  Next `Q` Lines (Queries):
// Each line contains a single integer representing a query.
// `q_i`: The node number for which you need to calculate the Minimax value. (1 ≤ q_i ≤ N)

// Output Format:

// For each query `q_i`, output a single line containing the calculated Minimax value for that node.    

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
            this.value = 0; // Initialize non-leaf nodes to 0, leaf node values are set later.
            this.children = new ArrayList<>();
        }
    }

    private Node[] nodes;
    private List<Integer>[] graph; // Adjacency list to represent the undirected tree.

    // Minimax algorithm to calculate the value of a node.
    private int minimax(Node node, boolean isMax) {
        // If it's a leaf node, return its value.
        if (node.children.isEmpty()) {
            return node.value;
        }

        int bestValue;
        if (isMax) { // Maximizing player
            bestValue = Integer.MIN_VALUE;
            for (Node childNode : nodes[node.id].children) {
                bestValue = Math.max(bestValue, minimax(childNode, !isMax));
            }
        } else { // Minimizing player
            bestValue = Integer.MAX_VALUE;
            for (Node childNode : nodes[node.id].children) {
                bestValue = Math.min(bestValue, minimax(childNode, !isMax));
            }
        }
        nodes[node.id].value = bestValue; // Store the calculated value in the node.
        return bestValue;
    }

    // Builds the tree structure from the undirected graph representation.
    void buildTree(int current, int parent) {
        for (int neighbor : graph[current]) {
            if (neighbor == parent) continue; // Avoid going back to the parent.

            // Add the neighbor as a child of the current node.
            nodes[current].children.add(nodes[neighbor]);
            // Recursively build the subtree rooted at the neighbor.
            buildTree(neighbor, current);
        }
    }

    public void solution() throws FileNotFoundException {
        //Scanner sc = new Scanner(new File("./basic/minimaxTree/inputMinimaxTree.txt"));  // For local testing
        Scanner sc = new Scanner(System.in);

        // Input: Number of nodes (N) and root node (R).
        int N = sc.nextInt();
        int R = sc.nextInt();

        // Initialize the nodes array and the adjacency list (node IDs start from 1).
        nodes = new Node[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
            graph[i] = new ArrayList<>();
        }

        // Input: Tree edge information (endpoints u and v).
        for (int i = 0; i < N - 1; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph[u].add(v); // Add edge to the adjacency list (undirected graph).
            graph[v].add(u);
        }

        // Build the tree structure starting from the root node.
        buildTree(R, -1);  // -1 indicates no parent for the root.


        // Input: Number of leaf nodes (L) and their IDs and values.
        int L = sc.nextInt();
        for (int i = 0; i < L; i++) {
            int leafId = sc.nextInt();
            int leafValue = sc.nextInt();
            nodes[leafId].value = leafValue; // Assign the value to the leaf node.
        }

        // Apply the Minimax algorithm, starting with the root node as the MAX player.
        minimax(nodes[R], true);

        // Process queries: For each query node, print its calculated Minimax value.
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