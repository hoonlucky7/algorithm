#include <iostream>
#include <queue>
#include <vector>

using namespace std;

const int MAX = 100001;

int findSister(int n, int k) {
    // 수빈이와 동생의 위치가 같으면 시간이 필요 없음
    if (n == k) {
        return 0;
    }

    // 방문 여부를 체크하는 배열
    vector<bool> visited(MAX, false);
    // 큐에 {위치, 시간}을 저장
    queue<pair<int, int>> q;
    q.push({n, 0});
    visited[n] = true;

    // BFS 탐색
    while (!q.empty()) {
        int pos = q.front().first;
        int time = q.front().second;
        q.pop();

        // 가능한 세 가지 이동
        int nextPositions[] = {pos - 1, pos + 1, pos * 2};

        for (int nextPos : nextPositions) {
            // 이동 위치가 범위 내이고 방문하지 않은 경우
            if (nextPos >= 0 && nextPos < MAX && !visited[nextPos]) {
                // 동생의 위치에 도달하면 시간 반환
                if (nextPos == k) {
                    return time + 1;
                }
                // 방문 표시 후 큐에 추가
                visited[nextPos] = true;
                q.push({nextPos, time + 1});
            }
        }
    }

    return -1; // 도달 불가능한 경우 (문제 조건상 발생하지 않음)
}

int main() {
    // 입력 받기
    int n, k;
    cin >> n >> k;

    // 결과 출력
    cout << findSister(n, k) << endl;

    return 0;
}