const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
const [N, R] = input[0].split(" ").map(Number);

const nodeValues = Array(N + 1).fill(-1);
const graph = Array(N + 1).fill().map(() => []);

// Build the graph
for (let i = 1; i < N; i++) {
    const [u, v] = input[i].split(" ").map(Number);
    graph[u].push(v);
    graph[v].push(u);
}

// Read leaf values
const L = Number(input[N]);
for (let i = 0; i < L; i++) {
    const [leafId, leafValue] = input[N + 1 + i].split(" ").map(Number);
    nodeValues[leafId] = leafValue;
}

// DFS function
function dfs(currentNode, parentNode, depth) {
    if (nodeValues[currentNode] >= 0) return nodeValues[currentNode];

    if (depth % 2 === 0) {
        let bestValue = 0;
        for (const child of graph[currentNode]) {
            if (child !== parentNode) {
                bestValue = Math.max(bestValue, dfs(child, currentNode, depth + 1));
            }
        }
        return nodeValues[currentNode] = bestValue;
    } else {
        let bestValue = Number.MAX_SAFE_INTEGER;
        for (const child of graph[currentNode]) {
            if (child !== parentNode) {
                bestValue = Math.min(bestValue, dfs(child, currentNode, depth + 1));
            }
        }
        return nodeValues[currentNode] = bestValue;
    }
}

// Process the DFS
dfs(R, 0, 0);

// Handle queries
const Q = Number(input[N + L + 1]);
let result = "";

for (let i = 0; i < Q; i++) {
    const q = Number(input[N + L + 2 + i]);
    result += nodeValues[q] + "\n";
}

console.log(result.trim());