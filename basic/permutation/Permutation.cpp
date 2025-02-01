#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

int N, M;
vector<int> sequence;
vector<bool> visited;
ostringstream oss; // Stream to store the output

// DFS function to generate permutations
void dfs(int depth) {
    if (depth == M) {
        // Append the current sequence to the output buffer
        for (int num : sequence) {
            oss << num << " ";
        }
        oss << "\n";
        return;
    }
    for (int i = 1; i <= N; i++) {
        if (!visited[i]) {
            visited[i] = true;
            sequence[depth] = i;
            dfs(depth + 1);
            visited[i] = false;  // Backtracking
        }
    }
}

int main() {
    cin >> N >> M;
    sequence.resize(M);
    visited.assign(N + 1, false);

    dfs(0);
    cout << oss.str();
    
    return 0;
}
