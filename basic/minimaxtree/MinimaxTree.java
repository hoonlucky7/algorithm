// Problem Link: https://www.acmicpc.net/problem/28472

// [자료구조]

// 그래프 표현 (graph):
// ArrayList<ArrayList<Integer>>를 사용하여 
// 인접 리스트 형태로 그래프를 표현
// 각 노드마다 연결된 이웃 노드들의 리스트를 저장

// 트리를 그래프 이용해서 초기화 : 
// https://www.youtube.com/watch?v=sF7CULfofoU&t=120s

// 노드 값 저장 (nodeValues):
// 정수 배열을 사용하여 각 노드의 값을 저장
// 초기값 -1은 아직 계산되지 않은 노드를 의미
// 0 이상의 값은 계산 완료된 노드의 값

// [Solution] : Minimax
// 게임 이론에서 사용되는 미니맥스(Minimax) 알고리즘을 트리에 
// 적용한 것입니다:
// 깊이 우선 탐색(DFS):
// 트리를 깊이 우선으로 탐색하며 각 노드의 값을 계산
// 부모 노드 정보를 추적하여 무방향 그래프에서 사이클을 방지

// 교대 전략(Alternating Strategy):
// 짝수 깊이(depth % 2 == 0): MAX 노드, 자식 중 최대값을 선택
// 홀수 깊이(depth % 2 == 1): MIN 노드, 자식 중 최소값을 선택

// 메모이제이션(Memoization):
// 한 번 계산한 노드 값은 저장해두고 재사용
// nodeValues[currentNode] >= 0 조건으로 이미 계산된 값을 확인


// 미니맥스 알고리즘 개요
// 미니맥스 알고리즘은 두 명의 플레이어가 번갈아 최적의 수를 선택하는 게임에서 사용됩니다.
// 한 플레이어는 자신의 이득(최대화)을 극대화하고, 상대는 손해(최소화)를 유도하는 방식으로 진행됩니다.
// 결국, 전체 게임 트리를 탐색해 최종 승패를 결정짓는 핵심 전략이죠.
// 알고리즘 동작 원리
// 게임 트리 구성: 가능한 모든 게임 상태를 노드로 표현해 트리 구조로 만듭니다.
// 리프 노드 평가: 게임이 종료된 상태 또는 정해진 깊이에 도달하면, 평가 함수를 통해 해당 상태의 점수를 계산합니다.
// 재귀적 선택:
// 최대화 플레이어: 가능한 자식 노드 중 가장 높은 평가 값을 선택합니다.
// 최소화 플레이어: 가능한 자식 노드 중 가장 낮은 평가 값을 선택합니다.
// 이러한 과정을 재귀적으로 수행해, 루트 노드까지 도달하면 최적의 수가 결정됩니다.

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
import java.util.*;

public class MinimaxTree {
    
    private static List<List<Integer>> graph;
    private static int[] nodeValues;

    private static int dfs(int currentNode, int parentNode, 
    int depth) {
        if (nodeValues[currentNode] >= 0) {
            return nodeValues[currentNode];
        }

        if (depth % 2 == 0) {
            int bestValue = 0;
            for (int child : graph.get(currentNode)) {
                if (parentNode != child) {
                    bestValue = Math.max(bestValue, 
                    dfs(child, currentNode, depth + 1));
                }
            }
            nodeValues[currentNode] = bestValue;
            return bestValue;
        } else {
            int bestValue = Integer.MAX_VALUE;
            for (int child : graph.get(currentNode)) {
                if (parentNode != child) {
                    bestValue = Math.min(bestValue, 
                    dfs(child, currentNode, depth + 1));
                }
            }
            nodeValues[currentNode] = bestValue;
            return bestValue;
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Scanner scanner = new Scanner(new File("./basic/minimaxTree/inputMinimaxTree.txt"));
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        int R = scanner.nextInt();

        nodeValues = new int[N + 1];
        Arrays.fill(nodeValues, -1);

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N - 1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int L = scanner.nextInt();

        for (int i = 0; i < L; i++) {
            int leafId = scanner.nextInt();
            int leafValue = scanner.nextInt();
            nodeValues[leafId] = leafValue;
        }

        dfs(R, 0, 0);

        int Q = scanner.nextInt();

        for (int i = 0; i < Q; i++) {
            int q = scanner.nextInt();
            System.out.println(nodeValues[q]);
        }
        scanner.close();
    }
}