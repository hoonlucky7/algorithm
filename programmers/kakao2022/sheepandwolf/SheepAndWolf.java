/*
2022 KAKAO BLIND RECRUITMENT
문제 5 - 양과 늑대

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/92343

[문제 요약]

2진 트리 형태의 초원에서 루트 노드부터 시작해 노드를 방문하며 양을 모으는 문제입니다. 
각 노드에는 양 또는 늑대가 있으며, 노드를 방문하면 해당 동물이 당신을 따라오게 됩니다. 
양의 수보다 늑대의 수가 많거나 같아지면 모든 양을 잃게 됩니다. 
늑대에게 양을 잃지 않으면서 최대한 많은 양을 모을 수 있는 양의 최대 마릿수를 구하는 것이 목표입니다. 
루트 노드에는 항상 양이 있습니다. 연결 정보는 edges에, 
각 노드에 양(0) 또는 늑대(1)가 있는지 여부는 info에 주어집니다.

You're given a binary tree-shaped meadow. Each node has either a sheep or a wolf. 
You start at the root node and move around, collecting sheep from each node you visit. 
When you visit a node, the sheep or wolf at that node joins your group. 
Wolves want to eat the sheep, 
and if the number of wolves you've collected is equal to or greater than the number of sheep, 
the wolves eat all the sheep. 
The goal is to find the maximum number of sheep you can collect 
while avoiding having your sheep eaten by the wolves, and then returning to the root. 
You always start with a sheep at the root node. 
The connections between nodes are given in edges, 
and the information about whether each node has a sheep (0) or a wolf (1) is given in info. 
You need to return the maximum number of sheep you can gather.


💡핵심 아이디어💡 

1) 그래프 구성 및 초기화

1-1) Node 클래스, 인접행렬, 인접리스트, 

이 문제의 경우:
트리 구조이므로 간선의 수가 적음 (희소 그래프)
노드 번호가 0부터 N-1까지 연속적
자식 노드들을 자주 순회해야 함

따라서 List<Integer>[] 방식이 적절한 선택입니다. 

info 배열은 각 노드가 양(0)인지 늑대(1)인지를 나타내며, 
edges 배열로부터 인접 리스트 형태의 방향 그래프를 구성합니다.
게임의 시작은 항상 노드 0에서 시작하며, 초기 양의 개수는 1입니다.
nextNodes 집합에는 현재 이동 가능한 후보 노드들이 저장되는데, 
초기에는 노드 0의 자식 노드들이 들어갑니다.

2) BackTracking

백트래킹(Backtracking)은 한 경로로 진행하며 문제를 해결하려고 시도하다가, 
해당 경로가 올바른 해답이나 최적의 해답을 주지 못할 때, 
이전 상태로 되돌아가 다른 선택지를 시도하는 탐색 기법입니다.

DFS 함수는 현재 노드, 지금까지 모은 양과 늑대의 수를 인자로 받아 재귀적으로 탐색합니다.
각 DFS 단계에서, 현재까지 모은 양의 수가 최대값을 갱신할 수 있으면 업데이트합니다.
만약 현재 노드의 주변(다음 이동 후보)에 더 이상 이동할 곳이 없으면 탐색을 종료합니다.
nextNodes 집합에 있는 각 후보 노드를 순회하면서, 해당 노드로 이동 시 양/늑대의 수를 업데이트합니다.
이동 후, 조건(양의 수가 늑대의 수보다 많아야 함)을 만족하면, 
현재 후보 노드를 제거하고 그 자식 노드들을 후보에 추가한 후 DFS를 재귀 호출합니다.
재귀 호출이 끝나면 백트래킹을 통해 nextNodes 집합을 이전 상태로 복원합니다.

3) 종료 조건

모든 양을 다 모은 경우 또는 더 이상 이동할 수 있는 후보 노드가 없을 때 DFS를 종료합니다.
최종적으로, 탐색 중에 얻은 최대 양의 수(maxSheep)를 결과로 리턴합니다.


노드의 개수(N) = info의 길이
2 ≤ N ≤ 17
노드에는 양(0) 또는 늑대(1)가 존재

💡상태 공간 분석💡
상태 공간은 문제 해결 과정에서 우리가 탐색해야 하는 모든 가능한 상황 또는 상태의 집합을 의미합니다.
각 상태는 (현재 노드, 현재 양의 수, 현재 늑대의 수, 다음에 방문 가능한 노드들의 집합)
방문 가능한 노드들의 집합은 각 노드가 포함되거나 포함되지 않을 수 있으므로 2^N 가지


💡시간복잡도💡
O(N * 2^N)
각 상태에서의 연산: O(N)
가능한 상태의 수: O(2^N)
N의 최대값이 17이므로, 최악의 경우 약 O(17 * 2^17) = O(2,228,224)

실제로는 "양의 수가 늑대의 수보다 커야 한다"는 제약 조건으로 인해 탐색하는 상태의 수가 
2^N보다 훨씬 적어질 수 있습니다. 
또한 N ≤ 17이라는 제한이 있어 실행 시간이 매우 크지 않습니다.

💡정리💡
1) 모든 가능한 길을 다 가본다. (DFS)
2) 늑대에게 잡혀먹히지 않도록 안전하게 다닌다. (양 > 늑대 조건 체크)
3) 가장 많은 양을 모을 수 있는 길을 찾는다. (최대 양 갱신)

[Solution] : Using Brute-force Search

Graph Construction: Store the children connected to each node using the given edge information.

DFS Traversal:

1)Manage the current location, the number of sheep/wolves collected so far, 
and the candidate nodes that can be visited next.

2)Explore only valid paths by checking the condition 
"the number of sheep must be greater than the number of wolves" at each step.

3)Maximum Sheep Update: Search all possible paths 
and return the maximum number of sheep that can be collected.

 */

import java.util.*;

class SheepAndWolf {
    private int[] info; // Array indicating whether each node is a sheep (0) or a wolf (1)
    private List<Integer>[] graph; // Adjacency list representing the graph
    private int maxSheep = 0; // Maximum number of sheep that can be collected
    private int totalSheep = 0; // Total number of sheep in the graph
    private Set<Integer> nextNodes; // Set of nodes that can be visited next

    @SuppressWarnings("unchecked")
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        
        // Initialize the graph
        graph = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Build the graph (directed graph)
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }
        
        // Calculate the total number of sheep
        for (int i : info) {
            if (i == 0) totalSheep++;
        }

        // Initialize the set of next possible nodes (initially, the children of the root node)
        nextNodes = new HashSet<>(graph[0]);
        
        dfs(0, 1, 0); // Start DFS from the root node (node 0), 
        // with 1 sheep and 0 wolves
        
        return maxSheep; // Return the maximum number of sheep collected
    }
    
    private void dfs(int node, int sheep, int wolf) {
        
        // Update the maximum number of sheep if the current number of sheep is greater
        if (maxSheep < sheep) {
            maxSheep = sheep;
        }
        // If we've collected all the sheep, no need to explore further
        if (totalSheep == sheep) {
            return;
        }
        // If there are no more nodes to visit, terminate the search
        if (nextNodes.size() == 0) {
            return;
        }
        // Iterate through a copy of the nextNodes set to avoid ConcurrentModificationException
        for (int next : new HashSet<>(nextNodes)) {
            int nextSheep = sheep;
            int nextWolf = wolf;
            if (info[next] == 0) {
                nextSheep++; // If it's a sheep, increment the sheep count
            }
            if (info[next] == 1) {
                nextWolf++; // If it's a wolf, increment the wolf count
            }
        
            // If the number of sheep is not greater than the number of wolves, we cannot proceed further
            if (nextSheep <= nextWolf) {
                continue;
            }
            nextNodes.remove(next);  // Remove the current node from the set of next possible nodes
            nextNodes.addAll(graph[next]);  // Add the children of the current node to the set of next possible nodes
            dfs(next, nextSheep, nextWolf); // Recursively call DFS for the next node
            nextNodes.add(next);  // Backtrack: add the current node back to the set of next possible nodes
            nextNodes.removeAll(graph[next]);  // Backtrack: remove the children of the current node from the set
        }
    }

    public static void main(String[] args) {
        SheepAndWolf sheepAndWolf = new SheepAndWolf();
        
        test(sheepAndWolf, 
            new int[]{0,0,1,1,1,0,1,0,1,0,1,1},
            new int[][]{{0,1},{1,2},{1,4},{0,8},{8,7},{9,10},{9,11},{4,3},{6,5},{4,6},{8,9}},
            5,
            "test 1");

        test(sheepAndWolf,
        new int[]{0,1,0,1,1,0,1,0,0,1,0},
        new int[][]{{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}},
        5,
        "test 2");
    }
    
    private static void test(SheepAndWolf sheepAndWolf, int[] info, int[][] edges, int expected, String testName) {
        int result = sheepAndWolf.solution(info, edges);
        System.out.println("test case : " + testName);
        System.out.println("expected : " + expected);
        System.out.println("result : " + result);
        System.out.println("result: " + (result == expected ? "pass" : "fail"));
        System.out.println("------------------------");
    }
}