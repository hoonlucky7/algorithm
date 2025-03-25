const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, M, V] = input[0].split(" ").map(Number);
const graph = Array.from({ length: N + 1 }, () => []);
let visited = Array(N + 1).fill(false);

for (let i = 1; i <= M; i++) {
    const [a, b] = input[i].split(" ").map(Number);
    graph[a].push(b);
    graph[b].push(a);
}

graph.forEach(adj => adj.sort((a, b) => a - b));

const dfs = (node) => {
    visited[node] = true;
    process.stdout.write(node + " ");

    for (let next of graph[node]) {
        if (!visited[next]) dfs(next);
    }
};

const bfs = (start) => {
    let queue = [start];
    visited = Array(N + 1).fill(false);
    visited[start] = true;

    while (queue.length) {
        let node = queue.shift();
        process.stdout.write(node + " ");

        for (let next of graph[node]) {
            if (!visited[next]) {
                visited[next] = true;
                queue.push(next);
            }
        }
    }
};

visited.fill(false);
dfs(V);
console.log();
bfs(V);
console.log();
