import java.util.*;

public class SheepAndWolfWithNode {
    private int[] info;          // Array indicating whether each node is a sheep (0) or a wolf (1)
    private Node[] nodes;        // Array to manage each node as a Node object
    private int maxSheep = 0;    // Maximum number of sheep that can be collected
    private int totalSheep = 0;  // Total number of sheep (calculated using the info array)
    private Set<Node> candidateNodes; // Set of candidate nodes that can be visited next

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        int n = info.length;

        // Create each node (node numbers from 0 to n-1)
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        // Connect children (reachable nodes) for each node using the edges array (directed graph)
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            Node parentNode = nodes[parent];
            Node childNode = nodes[child];

            if (parentNode.left == null) {
                parentNode.left = childNode; // Connect the first child as left
            } else {
                parentNode.right = childNode; // Connect the second child as right (assuming binary tree)
            }
        }

        // Calculate the total number of sheep
        for (int i = 0; i < n; i++) {
            if (info[i] == 0) {
                totalSheep++;
            }
        }

        // Initial candidate nodes: children of the root node (node 0)
        candidateNodes = new HashSet<>();
        Node rootNode = nodes[0];
        if (rootNode.left != null) candidateNodes.add(rootNode.left);
        if (rootNode.right != null) candidateNodes.add(rootNode.right);

        // Root node always has a sheep, so initial state is sheep=1, wolf=0
        dfs(rootNode, 1, 0);

        return maxSheep;
    }

    /**
     * DFS and backtracking to explore all possible paths.
     * @param current The currently visited node
     * @param sheep   Number of sheep collected so far
     * @param wolf    Number of wolves collected so far
     */
    private void dfs(Node current, int sheep, int wolf) {
        maxSheep = Math.max(maxSheep, sheep);

        // If all sheep have been collected, no need to explore further
        if (sheep == totalSheep) {
            return;
        }
        // If there are no more candidate nodes to move to, terminate
        if (candidateNodes.isEmpty()) {
            return;
        }

        // Iterate through a copy of the current candidate nodes set (for backtracking)
        for (Node next : new HashSet<>(candidateNodes)) {
            int nextSheep = sheep;
            int nextWolf = wolf;

            // Update sheep or wolf count based on the animal at the next node
            if (info[next.data] == 0) { // Use data instead of id from Node class
                nextSheep++;
            } else {
                nextWolf++;
            }

            // Move to the next node only if sheep count is greater than wolf count
            if (nextSheep <= nextWolf) {
                continue;
            }

            // Remove the next node from candidate set and add its children to candidates
            candidateNodes.remove(next);
            if (next.left != null) candidateNodes.add(next.left);
            if (next.right != null) candidateNodes.add(next.right);

            dfs(next, nextSheep, nextWolf);

            // Backtracking: restore candidate set to its previous state
            candidateNodes.add(next);
            if (next.left != null) candidateNodes.remove(next.left);
            if (next.right != null) candidateNodes.remove(next.right);
        }
    }

    // Test code
    public static void main(String[] args) {
        SheepAndWolfWithNode solver = new SheepAndWolfWithNode();

        test(solver,
            new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
            new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}},
            5,
            "Test 1");

        test(solver,
            new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0},
            new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}},
            5,
            "Test 2");
    }

    private static void test(SheepAndWolfWithNode solver, int[] info, int[][] edges, int expected, String testName) {
            int result = solver.solution(info, edges);
        System.out.println(testName + ": Expected = " + expected + ", Result = " + result + " → "
                           + (result == expected ? "PASS" : "FAIL"));
    }
}

/**
 * Node class for binary tree structure.
 * data: Node number (id role)
 * left: Left child node
 * right: Right child node
 */
class Node {
    int data; // Node number
    Node left;
    Node right;

    // Constructor: Initializes the node with data value.
    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}