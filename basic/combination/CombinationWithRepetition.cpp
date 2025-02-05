#include <iostream>
using namespace std;

int N, M;
int sequence[101]; // Array to store the current combination; adjust size as needed

// The key point of combination with repetition is to allow duplicate selections 
// by recursively calling starting from the current index (i.e., maintaining the start value),
// and to prevent duplicate combinations by keeping the sequence in non-decreasing order.
void dfs(int start, int depth) {
    if (depth == M) { // If the sequence reaches length M, output the sequence
        for (int i = 0; i < M; i++) {
            cout << sequence[i] << " ";
        }
        cout << "\n";
        return;
    }
    for (int i = start; i <= N; i++) { // Ensure non-decreasing order
        sequence[depth] = i;         // Add number to the current sequence
        dfs(i, depth + 1);           // Recursive call with same 'i' to allow duplicates
    }
}

int main(){
    cin >> N >> M; // Read N and M from input
    dfs(1, 0);
    
    return 0;
}
