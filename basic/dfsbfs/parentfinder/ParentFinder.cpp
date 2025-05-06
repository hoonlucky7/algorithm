#include <iostream>
#include <vector>
using namespace std;

int N;
vector<vector<int>> adj;
vector<int> parent;
vector<bool> visited;

void dfs(int node) {
    visited[node] = true;
    for (int next : adj[node]) {
        if (!visited[next]) {
            parent[next] = node;
            dfs(next);
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> N;
    adj.resize(N + 1);
    parent.resize(N + 1, 0);
    visited.resize(N + 1, false);

    for (int i = 0; i < N - 1; ++i) {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1);

    for (int i = 2; i <= N; ++i) {
        cout << parent[i] << '\n';
    }

    return 0;
}