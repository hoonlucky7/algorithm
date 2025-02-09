#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int lastIndex = 10; // Index of the last target (0-point target)
int maxGap = 0;     // Maximum score gap achieved by Ryan
vector<int> answer;    // Best arrow allocation for Ryan

// Calculates scores and updates maxGap if current allocation is better
void updateMaxGap(const vector<int>& info, const vector<int>& ryan) {
    int apeachSumScore = 0;
    int ryanSumScore = 0;

    // Calculate scores for each player
    for (int i = 0; i <= lastIndex; i++) {
        if (info[i] == 0 && ryan[i] == 0) continue; // Skip if both players didn't shoot
        if (info[i] >= ryan[i]) apeachSumScore += 10 - i; // Apeach gets score
        if (info[i] < ryan[i]) ryanSumScore += 10 - i;   // Ryan gets score
    }

    int scoreGap = ryanSumScore - apeachSumScore; // Calculate score difference
    if (scoreGap <= 0) return; // Not a winning case for Ryan

    // Update maxGap and answer if better result
    if (maxGap < scoreGap) {
        maxGap = scoreGap;
        answer = ryan;
    } else if (maxGap == scoreGap) {
        // If same gap, prefer allocation with more arrows in lower scores
        for (int i = lastIndex; i >= 0; i--) {
            if (answer[i] > ryan[i]) return; // Existing answer is better
            if (answer[i] < ryan[i]) {
                answer = ryan; // Current answer is better
                return;
            }
        }
    }
}

// Depth First Search to explore all arrow allocations
void dfs(int i, const vector<int>& info, vector<int>& ryan, int remainingArrows) {
    if (i == lastIndex + 1) { // Base case: all targets considered
        updateMaxGap(info, ryan);
        return;
    }

    // Case 1: Don't take points for this target
    ryan[i] = 0;
    dfs(i + 1, info, ryan, remainingArrows);

    // Case 2: Take points for this target
    if (i == lastIndex) {
        ryan[i] = remainingArrows; // Allocate remaining arrows to 0-point target
    } else {
        ryan[i] = info[i] + 1;   // Shoot 1 more arrow than Apeach
    }
    if (remainingArrows - ryan[i] >= 0) { // Check if enough arrows remain
        dfs(i + 1, info, ryan, remainingArrows - ryan[i]);
    }
}

// Main solution function
vector<int> solution(int n, vector<int> info) {
    vector<int> ryan(11, 0); // Initialize Ryan's arrow allocation
    answer.clear();          // Reset answer
    maxGap = 0;              // Reset maxGap
    dfs(0, info, ryan, n);   // Start DFS

    if (maxGap != 0) {
        return answer;       // Return best allocation if winnable
    }
    return {-1};            // Return -1 if unwinnable
}

int main() {
    vector<int> info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
    vector<int> result1 = solution(5, info1);
    cout << "Result 1: ";
    for (int val : result1) cout << val << " ";
    cout << endl;

    vector<int> info2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    vector<int> result2 = solution(1, info2);
    cout << "Result 2: ";
    for (int val : result2) cout << val << " ";
    cout << endl;

    vector<int> info3 = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
    vector<int> result3 = solution(9, info3);
    cout << "Result 3: ";
    for (int val : result3) cout << val << " ";
    cout << endl;

    vector<int> info4 = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
    vector<int> result4 = solution(10, info4);
    cout << "Result 4: ";
    for (int val : result4) cout << val << " ";
    cout << endl;

    return 0;
}