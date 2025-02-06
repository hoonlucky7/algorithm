#include <iostream>
#include <sstream>
using namespace std;

int N, M;
int sequence[101]; // Array to store the current combination
ostringstream output; // Stream to accumulate all output at once

// Recursive function to generate combinations with repetition (duplicates allowed)
// The function maintains non-decreasing order by starting the loop from 'start'
void dfs(int start, int depth) {
    if (depth == M) { // When the sequence reaches length M, add it to the output stream
        for (int i = 0; i < M; i++) {
            output << sequence[i] << " ";
        }
        output << "\n";
        return;
    }
    // Loop from 'start' to 'N' to ensure non-decreasing order
    for (int i = start; i <= N; i++) {
        sequence[depth] = i;  // Add the number to the current sequence
        dfs(i, depth + 1);    // Recursively call with the current index to allow duplicates
    }
}

int main(){
    cin >> N >> M; // Read values of N and M from input
    dfs(1, 0);     // Start DFS with starting number 1 and initial depth 0
    
    // Print the accumulated output all at once
    cout << output.str();
    return 0;
}
