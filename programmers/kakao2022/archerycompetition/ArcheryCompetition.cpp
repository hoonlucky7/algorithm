#include <iostream>
#include <vector>
using namespace std;

int lastIndex = 10;                 // Last target index (from 0 to 10)
int maxGap = 0;                     // Maximum score gap found so far
vector<int> answer(11, 0);          // Best allocation for Ryan's arrows

// Updates the maximum gap and answer array based on the current allocation
void updateMaxGap(const vector<int>& info, const vector<int>& ryan) {
    int apeachSumScore = 0;
    int ryanSumScore = 0;
    
    // Calculate scores for each target
    for (int i = 0; i <= lastIndex; i++) {
        // Skip if both players didn't shoot any arrows on this target
        if (info[i] == 0 && ryan[i] == 0)
            continue;
        // If Apeach scores equal to or more than Ryan, add points to Apeach
        if (info[i] >= ryan[i])
            apeachSumScore += (10 - i);
        // If Ryan scores more, add points to Ryan
        if (info[i] < ryan[i])
            ryanSumScore += (10 - i);
    }
    
    int scoreGap = ryanSumScore - apeachSumScore;
    // If Ryan does not win, do nothing
    if (scoreGap <= 0) return;
    
    // If the current gap is greater than the maximum found so far, update
    if (scoreGap > maxGap) {
        maxGap = scoreGap;
        answer = ryan;
    }
    // If the gap is the same, choose the allocation with more arrows in lower score zones
    else if (scoreGap == maxGap) {
        for (int i = lastIndex; i >= 0; i--) {
            if (ryan[i] > answer[i]) {
                answer = ryan;
                break;
            } else if (ryan[i] < answer[i]) {
                break;
            }
        }
    }
}

// Depth-first search to try all possible arrow allocations for Ryan
void dfs(int i, vector<int>& ryan, int remaining, const vector<int>& info) {
    // If all targets have been assigned, update the maximum gap
    if (i == lastIndex + 1) {
        updateMaxGap(info, ryan);
        return;
    }
    
    // Try allocating from 0 to remaining arrows for the current target (score: 10 - i)
    for (int count = 0; count <= remaining; count++) {
        ryan[i] = count;
        dfs(i + 1, ryan, remaining - count, info);
        ryan[i] = 0;  // Backtracking
    }
}

// Solution function that sets up the DFS search
vector<int> solution(int n, const vector<int> info) {
    maxGap = 0;
    answer.assign(11, 0);
    vector<int> ryan(11, 0);
    dfs(0, ryan, n, info);
    return (maxGap != 0 ? answer : vector<int>{-1});
}

int main() {
    vector<vector<int>> testInfos = {
        {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1},
        {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3}
    };
    vector<int> ns = {5, 1, 9, 10};

    for (size_t t = 0; t < testInfos.size(); t++) {
        vector<int> result = solution(ns[t], testInfos[t]);
        for (int score : result) {
            cout << score << " ";
        }
        cout << "\n";
    }
    return 0;
}
