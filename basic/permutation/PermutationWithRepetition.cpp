#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

int N, M;
vector<int> sequence;
ostringstream oss; // String stream to accumulate the output

// DFS function: Selects numbers at the current depth
void dfs(int depth) {
    if (depth == M) {
        // Append the current sequence to the output stream
        for (int num : sequence) {
            oss << num << " ";
        }
        oss << "\n";
        return;
    }
    for (int i = 1; i <= N; i++) {
        sequence[depth] = i;
        dfs(depth + 1);
        sequence[depth] = 0; // Reset the value (optional, useful for debugging)
    }
}

int main() {
    cin >> N >> M;
    sequence.resize(M); // Set the size of the sequence array
    dfs(0);
    cout << oss.str(); // Print the accumulated output at once
    return 0;
}
