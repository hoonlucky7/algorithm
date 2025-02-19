// 파괴되지 않은 건물
// https://school.programmers.co.kr/learn/courses/30/lessons/92344
// brute force : 주어진 것을 이중 반복문으로 계산 
// 행의 길이 : N
// 열의 길이 : M
// skill행의 길이 : K
// 시간 복잡도 : O(K * N * M)

// [solution 2]구간합으로 시간 복잡도 개선

// 시작점 추가:
// (r₁, c₁) 위치에 +n을 더합니다.
// → 이 위치를 기준으로 오른쪽과 아래쪽으로 n의 변화가 시작됩니다.

// 오른쪽 경계 제거:
// (r₁, c₂+1) 위치에 –n을 더합니다.
// → (r₁, c₁)에서 오른쪽으로 확산된 n의 영향을 c₂까지 주고, c₂+1부터는 더 이상 영향을 주지 않도록 합니다.

// 아래쪽 경계 제거:
// (r₂+1, c₁) 위치에 –n을 더합니다.
// → (r₁, c₁)에서 아래쪽으로 확산된 n의 영향을 r₂까지 주고, r₂+1부터는 영향을 제거합니다.

// 오른쪽 아래 모서리 보정:
// (r₂+1, c₂+1) 위치에 +n을 더합니다.
// → 위의 두 제거 과정에서 겹쳐서 두 번 제거된 부분을 보정하기 위해 +n을 다시 더해줍니다.

// 즉, 2차원 배열에서 (r₁, c₁)부터 (r₂, c₂)까지 n만큼의 변화를 주기 위해, diff 배열에 아래와 같이 업데이트합니다.

// diff[r1][c1] += n
// diff[r1][c2 + 1] += -n
// diff[r2 + 1][c1] += -n
// diff[rc + 1][c2 + 1] += n

// 이와 같이 업데이트한 후, 세로 및 가로 누적합을 계산하면 원하는 구간 업데이트가 전체 배열에 올바르게 반영됩니다.
// 1 ≤ board의 행의 길이 (= N) ≤ 1,000
// 1 ≤ board의 열의 길이 (= M) ≤ 1,000
// 1 ≤ board의 원소 (각 건물의 내구도) ≤ 1,000
// 1 ≤ skill의 행의 길이 ≤ 250,000
// 완전탐색 시간 복잡도 : O(K * N * M) = 1000 * 1000 * 250,000 = 250,000,000,000
// 구간합 방식 시간복잡도 : O(K + N * M) = 250,000 + 1000 * 1000 = 1,250,000
// 구간합 방식이 200,000배 빠름 

// Building Undestroyed
// https://school.programmers.co.kr/learn/courses/30/lessons/92344
// brute force: Calculate using nested loops for the given input
// Height of the board: N
// Width of the board: M
// Length of the skill array: K
// Time Complexity: O(K * N * M)

// [solution 2] Improve time complexity using prefix sum

// Add starting point:
// Add +n at position (r₁, c₁)
// -> The change of n starts from this position to the right and downwards.

// Remove right boundary:
// Add -n at position (r₁, c₂+1)
// -> The effect of n spreading to the right from (r₁, c₁) is applied up to c₂, and the effect is removed from c₂+1 onwards.

// Remove lower boundary:
// Add -n at position (r₂+1, c₁)
// -> The effect of n spreading downwards from (r₁, c₁) is applied up to r₂, and the effect is removed from r₂+1.

// Correct the bottom-right corner:
// Add +n at position (r₂+1, c₂+1)
// -> Since the above two removal processes overlap and are removed twice, +n is added again to compensate.

// In other words, to give a change of n from (r₁, c₁) to (r₂, c₂) in a 2D array, update the diff array as follows:

// diff[r1][c1] += n
// diff[r1][c2 + 1] += -n
// diff[r2 + 1][c1] += -n
// diff[r2 + 1][c2 + 1] += n

// After updating in this way, calculating the cumulative sum in the vertical and horizontal directions will correctly reflect the desired section update in the entire array.
// 1 ≤ Height of the board (= N) ≤ 1,000
// 1 ≤ Width of the board (= M) ≤ 1,000
// 1 ≤ Element of board (durability of each building) ≤ 1,000
// 1 ≤ Length of skill array ≤ 250,000
// Brute-force time complexity: O(K * N * M) = 1000 * 1000 * 250,000 = 250,000,000,000
// Prefix sum method time complexity: O(K + N * M) = 250,000 + 1000 * 1000 = 1,250,000
// The prefix sum method is 200,000 times faster.

#include <iostream>
#include <vector>

using namespace std;

// Function to calculate the number of undamaged buildings after applying skill effects.
int buildingUndamaged(vector<vector<int>> board, vector<vector<int>> skill) {
    int n = board.size();  // Height of the board
    int m = board[0].size(); // Width of the board

    // Create a difference array to efficiently track changes in durability.
    vector<vector<int>> diff(n + 1, vector<int>(m + 1, 0));

    // Iterate through the skill array to apply the attack/heal effects.
    for (auto& s : skill) {
        int type = s[0];     // 1: Attack, 2: Heal
        int r1 = s[1];
        int c1 = s[2];
        int r2 = s[3];
        int c2 = s[4];
        int degree = s[5];   // Strength of the attack/heal

        // Determine the effect on durability (negative for attack, positive for heal).
        int effect = (type == 1 ? -degree : degree);

        // Apply the effect to the difference array at the corners of the affected region.
        diff[r1][c1] += effect;
        diff[r1][c2 + 1] -= effect;
        diff[r2 + 1][c1] -= effect;
        diff[r2 + 1][c2 + 1] += effect;
    }

    // Calculate prefix sums in the rows to propagate the effects horizontally.
    for (int r = 0; r < n + 1; ++r) {
        for (int c = 1; c < m + 1; ++c) {
            diff[r][c] += diff[r][c - 1];
        }
    }

    // Calculate prefix sums in the columns to propagate the effects vertically.
    for (int c = 0; c < m + 1; ++c) {
        for (int r = 1; r < n + 1; ++r) {
            diff[r][c] += diff[r - 1][c];
        }
    }

    // Count the number of undamaged buildings by applying the cumulative effects.
    int answer = 0;
    for (int r = 0; r < n; ++r) {
        for (int c = 0; c < m; ++c) {
            if (board[r][c] + diff[r][c] > 0) {
                answer++;
            }
        }
    }

    return answer;
}

int main() {
    // Example usage with a sample test case
    vector<vector<int>> board1 = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
    vector<vector<int>> skill1 = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};

    cout << "Result: " << buildingUndamaged(board1, skill1) << endl; // Expected: 10

    return 0;
}