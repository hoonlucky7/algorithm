#include <iostream>
#include <vector>
#include <vector>

using namespace std;

int n, m;
vector<vector<int>> graph;
vector<bool> visited;
int count_infected = 0;

void dfs(int node) {
    visited[node] = true;
    for (int next : graph[node]) {
        if (!visited[next]) {
            count_infected++;
            dfs(next);
        }
    }
}

int main() {
    cin >> n >> m;

    graph.resize(n + 1);
    visited.resize(n + 1, false);

    for (int i = 0; i < m; ++i) {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    dfs(1);

    cout << count_infected << endl;

    return 0;
}