#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

vector<vector<pair<int, int>>> adj;  // 인접 리스트: (노드, 가중치)
vector<bool> visited;
int max_dist = 0;
int farthest_node = 1;

void dfs(int node, int dist) {
    visited[node] = true;
    if (dist > max_dist) {
        max_dist = dist;
        farthest_node = node;
    }
    for (auto next : adj[node]) {
        int next_node = next.first;
        int weight = next.second;
        if (!visited[next_node]) {
            dfs(next_node, dist + weight);
        }
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int n;
    cin >> n;
    
    // 인접 리스트와 방문 배열 초기화
    adj.resize(n + 1);
    visited.resize(n + 1, false);
    
    // 간선 정보 입력 (무방향 트리)
    for (int i = 0; i < n - 1; i++) {
        int parent, child, weight;
        cin >> parent >> child >> weight;
        adj[parent].push_back({child, weight});
        adj[child].push_back({parent, weight});
    }
    
    // 첫 번째 DFS: 임의의 노드(1)에서 가장 먼 노드 찾기
    max_dist = 0;
    farthest_node = 1;
    dfs(1, 0);
    
    // 두 번째 DFS: 가장 먼 노드에서 최대 거리 찾기
    max_dist = 0;
    fill(visited.begin(), visited.end(), false);  // 방문 배열 재초기화
    dfs(farthest_node, 0);
    
    cout << max_dist << endl;
    
    return 0;
}