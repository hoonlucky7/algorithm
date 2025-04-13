#include <iostream>
#include <queue>
#include <vector>
#include <string>
using namespace std;

int bfs(vector<vector<int>>& maze, int n, int m) {
    // 상하좌우 이동을 위한 방향 벡터
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};

    queue<pair<int, int>> q;
    q.push({0, 0});

    while (!q.empty()) {
        auto [r, c] = q.front();
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 미로 범위 체크 및 이동 가능한 칸 (1)인지 확인
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && maze[nr][nc] == 1) {
                maze[nr][nc] = maze[r][c] + 1;
                q.push({nr, nc});
            }
        }
    }
    
    return maze[n - 1][m - 1];
}

int main() {
    int n, m;
    cin >> n >> m;
    // 개행 문자 제거를 위해 cin.ignore() 사용
    cin.ignore();

    vector<vector<int>> maze(n, vector<int>(m));
    string line;
    for (int i = 0; i < n; i++) {
        getline(cin, line);
        for (int j = 0; j < m; j++) {
            // 문자 하나씩 정수로 변환 (문자 '0' 또는 '1'을 정수로 변환)
            maze[i][j] = line[j] - '0';
        }
    }
    
    cout << bfs(maze, n, m) << endl;
    return 0;
}
