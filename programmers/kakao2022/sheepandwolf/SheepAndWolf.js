let maxSheep = 0;  // Global variable to store the maximum number of sheep collected
let totalSheep = 0; // Global variable to store the total number of sheep

// DFS function to explore the forest and collect sheep
function dfs(node, sheep, wolf, info, graph, nextNodes) {
    // Update the maximum number of sheep if the current count is higher
    if (sheep > maxSheep) {
        maxSheep = sheep;
    }
    // If we have collected all the sheep, no need to continue
    if (totalSheep === sheep) {
        return;
    }
    // If there are no more nodes to explore, stop the search
    if (nextNodes.size === 0) {
        return;
    }

    // Iterate through a copy of the nextNodes set to avoid concurrent modification
    for (let next of new Set(nextNodes)) {
        let nextSheep = sheep; // Number of sheep after visiting the next node
        let nextWolf = wolf;   // Number of wolves after visiting the next node

        // Check if the next node is a sheep or a wolf
        if (info[next] === 0) {
            nextSheep++; // If it's a sheep, increment the sheep count
        } else {
            nextWolf++; // If it's a wolf, increment the wolf count
        }

        // If the number of sheep is not greater than the number of wolves, we cannot proceed further
        if (nextSheep <= nextWolf) {
            continue;
        }

        // Remove the current node from the set of next possible nodes
        nextNodes.delete(next);

        // Add the children of the current node to the set of next possible nodes
        for (let neighbor of graph[next]) {
            nextNodes.add(neighbor);
        }

        // Recursively call DFS for the next node
        dfs(next, nextSheep, nextWolf, info, graph, nextNodes);

        // Backtrack: Add the current node back to the set of next possible nodes
        nextNodes.add(next);

        // Backtrack: Remove the children of the current node from the set
        for (let neighbor of graph[next]) {
            nextNodes.delete(neighbor);
        }
    }
}

// Function to solve the sheep and wolf problem
function solution(info, edges) {
    // Reset the global variables
    maxSheep = 0;
    totalSheep = 0;
    let n = info.length; // Number of nodes in the forest

    // Build the graph (adjacency list)
    let graph = Array(n).fill(null).map(() => []);
    for (let edge of edges) {
        graph[edge[0]].push(edge[1]); // Add an edge from node edge[0] to node edge[1]
    }

    // Calculate the total number of sheep
    for (let i of info) {
        if (i === 0) {
            totalSheep++; // Increment the total sheep count if the node is a sheep
        }
    }

    // Initialize the set of next possible nodes (children of the root node)
    let nextNodes = new Set(graph[0]);

    // Start the DFS traversal from the root node (node 0)
    dfs(0, 1, 0, info, graph, nextNodes);

    // Return the maximum number of sheep collected
    return maxSheep;
}

// Test cases
const info1 = [0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1];
const edges1 = [[0, 1], [1, 2], [1, 4], [0, 8], [8, 7], [9, 10], [9, 11], [4, 3], [6, 5], [4, 6], [8, 9]];
console.log("Test Case 1:", solution(info1, edges1));  // Expected Output: 5

const info2 = [0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0];
const edges2 = [[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6], [3, 7], [4, 8], [6, 9], [9, 10]];
console.log("Test Case 2:", solution(info2, edges2));  // Expected Output: 5