#include <iostream>
#include <vector>
#include <set>

using namespace std;

// Global variables to store the maximum number of sheep and the total number of sheep
int maxSheep = 0;  // Maximum number of sheep collected so far
int totalSheep = 0; // Total number of sheep in the forest

// DFS function to explore the forest and collect sheep
void dfs(int node, int sheep, int wolf, const vector<int>& info, const vector<vector<int>>& graph, set<int>& nextNodes) {
    // Update the maximum number of sheep if the current count is higher
    if (sheep > maxSheep) {
        maxSheep = sheep;
    }
    // If we have collected all the sheep, no need to continue
    if (totalSheep == sheep) {
        return;
    }
    // If there are no more nodes to explore, stop the search
    if (nextNodes.empty()) {
        return;
    }

    // Iterate through a copy of the nextNodes set to avoid concurrent modification
    for (int next : set<int>(nextNodes)) {
        int nextSheep = sheep; // Number of sheep after visiting the next node
        int nextWolf = wolf;   // Number of wolves after visiting the next node

        // Check if the next node is a sheep or a wolf
        if (info[next] == 0) {
            nextSheep++; // If it's a sheep, increment the sheep count
        } else {
            nextWolf++; // If it's a wolf, increment the wolf count
        }

        // If the number of sheep is not greater than the number of wolves, we cannot proceed further
        if (nextSheep <= nextWolf) {
            continue;
        }

        // Remove the current node from the set of next possible nodes
        nextNodes.erase(next);

        // Add the children of the current node to the set of next possible nodes
        for (int neighbor : graph[next]) {
            nextNodes.insert(neighbor);
        }

        // Recursively call DFS for the next node
        dfs(next, nextSheep, nextWolf, info, graph, nextNodes);

        // Backtrack: Add the current node back to the set of next possible nodes
        nextNodes.insert(next);

        // Backtrack: Remove the children of the current node from the set
        for (int neighbor : graph[next]) {
            nextNodes.erase(neighbor);
        }
    }
}

// Function to solve the sheep and wolf problem
int solution(const vector<int> info, const vector<vector<int>> edges) {
    // Reset the global variables
    maxSheep = 0;
    totalSheep = 0;
    int n = info.size(); // Number of nodes in the forest

    // Build the graph (adjacency list)
    vector<vector<int>> graph(n);
    for (const auto& edge : edges) {
        graph[edge[0]].push_back(edge[1]); // Add an edge from node edge[0] to node edge[1]
    }

    // Calculate the total number of sheep
    for (int i : info) {
        if (i == 0) {
            totalSheep++; // Increment the total sheep count if the node is a sheep
        }
    }

    // Initialize the set of next possible nodes (children of the root node)
    set<int> nextNodes;
    for (int neighbor : graph[0]) {
        nextNodes.insert(neighbor); // Add the neighbors of the root node (node 0)
    }

    // Start the DFS traversal from the root node (node 0)
    dfs(0, 1, 0, info, graph, nextNodes);

    // Return the maximum number of sheep collected
    return maxSheep;
}

int main() {
    // Test cases
    vector<int> info1 = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
    vector<vector<int>> edges1 = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
    cout << "Test Case 1: " << solution(info1, edges1) << endl;  // Expected Output: 5

    vector<int> info2 = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
    vector<vector<int>> edges2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};
    cout << "Test Case 2: " << solution(info2, edges2) << endl;  // Expected Output: 5

    return 0;
}