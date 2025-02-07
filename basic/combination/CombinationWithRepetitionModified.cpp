#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

int N, M;
vector<int> sequenceCounts; // Array to store allocation counts for each target
ostringstream oss;         // ostringstream for efficient output

// i: current target index, n: remaining count
void dfs(int i, int n) {
    if (i == N + 1) { // All targets have been processed
        if (n == 0) { // Process only valid combinations where the remaining count is 0
            for (int num = 1; num <= N; num++) {
                for (int c = 1; c <= sequenceCounts[num]; c++) {
                    oss << num << " ";
                }
            }
            oss << "\n";
        }
        return;
    }
    // Distribute counts from n down to 0 for the current target i.
    // (Allocating from n down to 0 results in reverse order output)
    for (int count = n; count >= 0; count--) {
        sequenceCounts[i] = count;
        dfs(i + 1, n - count);
        sequenceCounts[i] = 0; // Backtracking: restore previous state
    }
}

int main() {
    cin >> N >> M;
    sequenceCounts.resize(N + 2, 0); // Using index 1 to N; extra space for convenience
    dfs(1, M);
    cout << oss.str();
    return 0;
}
