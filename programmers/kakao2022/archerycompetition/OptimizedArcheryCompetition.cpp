#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int lastIndex = 10;
int maxGap = 0;
vector<int> answer;

void updateMaxGap(const vector<int>& info, const vector<int>& ryan) {
    int apeachSumScore = 0;
    int ryanSumScore = 0;
    for (int i = 0; i <= lastIndex; i++) {
        if (info[i] == 0 && ryan[i] == 0) {
            continue;
        }
        if (info[i] >= ryan[i]) {
            apeachSumScore += 10 - i;
        }
        if (info[i] < ryan[i]) {
            ryanSumScore += 10 - i;
        }
    }
    int scoreGap = ryanSumScore - apeachSumScore;
    if (scoreGap <= 0) {
        return;
    }

    if (maxGap < scoreGap) {
        maxGap = scoreGap;
        answer = ryan;
    } else if (maxGap == scoreGap) {
        for (int i = lastIndex; i >= 0; i--) {
            if (answer[i] > ryan[i]) {
                return;
            }
            if (answer[i] < ryan[i]) {
                answer = ryan;
                return;
            }
        }
    }
}

void dfs(int i, const vector<int>& info, vector<int>& ryan, int remainingArrows) {
    if (i == lastIndex + 1) {
        updateMaxGap(info, ryan);
        return;
    }

    // Case: Do not attempt to win the (10 - i) point target
    ryan[i] = 0;
    dfs(i + 1, info, ryan, remainingArrows);

    // Case: Attempt to win the (10 - i) point target by shooting one more arrow than Apeach
    if (i == lastIndex) {
        ryan[i] = remainingArrows;
    } else {
        ryan[i] = info[i] + 1;
    }
    if (remainingArrows - ryan[i] >= 0) {
        dfs(i + 1, info, ryan, remainingArrows - ryan[i]);
    }
}

vector<int> solution(int n, vector<int> info) {
    vector<int> ryan(11, 0);
    answer.clear();
    maxGap = 0;
    dfs(0, info, ryan, n);

    if (maxGap != 0) {
        return answer;
    }
    return {-1};
}

int main() {
    vector<int> info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
    vector<int> result1 = solution(5, info1);
    cout << "Result 1: ";
    for (int val : result1) {
        cout << val << " ";
    }
    cout << endl;

    vector<int> info2 = {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    vector<int> result2 = solution(1, info2);
    cout << "Result 2: ";
    for (int val : result2) {
        cout << val << " ";
    }
    cout << endl;

    vector<int> info3 = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
    vector<int> result3 = solution(9, info3);
    cout << "Result 3: ";
    for (int val : result3) {
        cout << val << " ";
    }
    cout << endl;

    vector<int> info4 = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
    vector<int> result4 = solution(10, info4);
    cout << "Result 4: ";
    for (int val : result4) {
        cout << val << " ";
    }
    cout << endl;

    return 0;
}