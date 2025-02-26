#include <iostream>
#include <vector>
#include <climits>
using namespace std;
vector<vector<int>> graph;
vector<int> nodeValues;

int dfs(int currentNode, int parentNode, int depth){
	if (nodeValues[currentNode] >= 0) return nodeValues[currentNode];

	if (depth % 2 == 0) {
		int bestValue = 0;
		for (int child : graph[currentNode])
		if (child != parentNode)
			bestValue = max(bestValue, dfs(child, currentNode, depth + 1));
		return nodeValues[currentNode] = bestValue;
	}
	else{
		int bestValue = INT_MAX;
		for (int child : graph[currentNode])
		if (child != parentNode)
			bestValue = min(bestValue, dfs(child, currentNode, depth + 1));
		return nodeValues[currentNode] = bestValue;
	}
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int N, R;
    cin >> N >> R;
    nodeValues = vector<int>(N + 1, -1);
    graph = vector<vector<int>>(N + 1, vector<int>());

    for (int i = 1; i < N; ++i)
    {
        int u, v;
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    int L;
    cin >> L;

    for (int i = 0; i < L; ++i)
    {
        int leafId;
        int leafValue;
        cin >> leafId >> leafValue;
        nodeValues[leafId] = leafValue;
    }

    dfs(R, 0, 0);

    int Q;
    cin >> Q;

    for (int i = 0; i < Q; ++i)
    {
        int q;
        cin >> q;

        cout << nodeValues[q] << "\n";
    }

    return 0;
}