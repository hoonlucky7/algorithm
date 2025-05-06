const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");
const N = Number(input[0]);

const adj = Array.from({ length: N + 1 }, () => []);
const parent = new Array(N + 1).fill(0);
const visited = new Array(N + 1).fill(false);

for (let i = 1; i < N; i++) {
    const [a, b] = input[i].split(" ").map(Number);
    adj[a].push(b);
    adj[b].push(a);
}

function dfs(node) {
    visited[node] = true;
    for (const next of adj[node]) {
        if (!visited[next]) {
            parent[next] = node;
            dfs(next);
        }
    }
}

dfs(1);

const output = [];
for (let i = 2; i <= N; i++) {
    output.push(parent[i]);
}
console.log(output.join('\n'));