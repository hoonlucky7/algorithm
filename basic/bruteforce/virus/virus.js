const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const n = parseInt(input[0]);
const m = parseInt(input[1]);
const graph = Array.from({ length: n + 1 }, () => []);
const visited = new Array(n + 1).fill(false);
let count = 0;

for (let i = 2; i < 2 + m; i++) {
    const [u, v] = input[i].split(" ").map(Number);
    graph[u].push(v);
    graph[v].push(u);
}

function dfs(node) {
    visited[node] = true;
    for (const neighbor of graph[node]) {
        if (!visited[neighbor]) {
            count++;
            dfs(neighbor);
        }
    }
}

dfs(1);
console.log(count);