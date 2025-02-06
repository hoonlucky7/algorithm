#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

int N, M;
vector<int> sequence; // Vector to store the current combination
ostringstream sb;     // ostringstream to collect output

// Recursive function to generate combinations
void dfs(int start, int depth) {
    if (depth == M) { // If the combination is complete
        for (int num : sequence) {
            sb << num << " ";
        }
        sb << "\n";
        return;
    }
    for (int i = start; i <= N; i++) {
        sequence.push_back(i); // Add number to the current combination
        dfs(i + 1, depth + 1);   // Recursively generate the next depth
        sequence.pop_back();     // Backtrack: remove the last added element
    }
}

int main(){
    cin >> N >> M; // Read N and M from input
    dfs(1, 0);
    cout << sb.str(); // Print all stored combinations at once
    return 0;
}
