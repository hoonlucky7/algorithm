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


[solution] : 완전 탐색 방법을 사용
그래프 구성: 주어진 간선 정보로 각 노드에 연결된 자식들을 저장합니다.
DFS 탐색:
현재 위치와 지금까지 모은 양/늑대 수, 그리고 앞으로 갈 수 있는 후보 노드를 관리하며 탐색합니다.
각 단계마다 “양의 수가 늑대보다 많아야 한다”는 조건을 체크하여 유효한 경로만 탐색합니다.
최대 양 업데이트: 모든 가능한 경로를 탐색하면서 모을 수 있는 최대 양을 찾아 반환합니다.

2 ≤ info의 길이 ≤ 17
N = info의 길이
시간 복잡도 : O(2^N)
2^17 = 131,072

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