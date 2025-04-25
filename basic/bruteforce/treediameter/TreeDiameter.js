const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const n = parseInt(input[0]);
let adj = Array.from({ length: n + 1 }, () => []);
let visited = Array(n + 1).fill(false);
let max_dist = 0;
let farthest_node = 1;

// 간선 정보 입력 (무방향 트리)
for (let i = 1; i <= n - 1; i++) {
  const [parent, child, weight] = input[i].split(" ").map(Number);
  adj[parent].push([child, weight]);
  adj[child].push([parent, weight]);
}

function dfs(node, dist) {
  visited[node] = true;
  if (dist > max_dist) {
    max_dist = dist;
    farthest_node = node;
  }
  for (let i = 0; i < adj[node].length; i++) {
    const [next_node, weight] = adj[node][i];
    if (!visited[next_node]) {
      dfs(next_node, dist + weight);
    }
  }
}

// 첫 번째 DFS: 임의의 노드(1)에서 가장 먼 노드 찾기
max_dist = 0;
farthest_node = 1;
dfs(1, 0);

// 두 번째 DFS: 가장 먼 노드에서 최대 거리 찾기
max_dist = 0;
visited = Array(n + 1).fill(false); // 방문 배열 재초기화
dfs(farthest_node, 0);

console.log(max_dist);