#include <iostream>
#include <vector>
#include <climits>

using namespace std;

// Delta 값: 상, 하, 좌, 우 이동 (행, 열)
const int DELTA_ROW[] = {-1, 1, 0, 0};
const int DELTA_COL[] = {0, 0, -1, 1};

// 게임 결과를 저장하는 구조체
struct GameResult {
    bool canWin;
    int moveCount;
    // 기본 생성자 추가
    GameResult() : canWin(false), moveCount(0) {}
    // 기존 생성자 유지
    GameResult(bool win, int count) : canWin(win), moveCount(count) {}
};

// 보드 경계 확인 함수
bool isOutOfBounds(int row, int col, int boardHeight, int boardWidth) {
    return row < 0 || row >= boardHeight || col < 0 || col >= boardWidth;
}

// 게임을 재귀적으로 진행하며 최적의 결과를 찾는 함수
GameResult playGame(vector<vector<int>>& board, int aRow, int aCol, int bRow, int bCol, 
                    int moveCount, bool isATurn, int boardHeight, int boardWidth) {
    int currentRow = isATurn ? aRow : bRow;
    int currentCol = isATurn ? aCol : bCol;

    // 1. 종료 조건
    // 1.1. 현재 위치가 경계를 벗어나거나 플랫폼이 없으면 패배
    if (isOutOfBounds(currentRow, currentCol, boardHeight, boardWidth) || board[currentRow][currentCol] == 0) {
        return GameResult(false, moveCount);
    }

    // 1.2. 이동 가능한 위치가 없으면 패배
    bool canMove = false;
    for (int i = 0; i < 4; i++) {
        int nextRow = currentRow + DELTA_ROW[i];
        int nextCol = currentCol + DELTA_COL[i];
        if (!isOutOfBounds(nextRow, nextCol, boardHeight, boardWidth) && board[nextRow][nextCol] == 1) {
            canMove = true;
            break;
        }
    }
    if (!canMove) {
        return GameResult(false, moveCount);
    }

    // 2. 재귀 단계
    // 2.1. 현재 플레이어의 플랫폼 제거
    board[currentRow][currentCol] = 0;

    // 2.2. 모든 가능한 이동에 대해 재귀 호출
    bool canWin = false;
    int minMovesToWin = INT_MAX;
    int maxMovesToLose = 0;

    for (int i = 0; i < 4; i++) {
        int nextRow = currentRow + DELTA_ROW[i];
        int nextCol = currentCol + DELTA_COL[i];

        if (isOutOfBounds(nextRow, nextCol, boardHeight, boardWidth) || board[nextRow][nextCol] == 0) {
            continue;
        }

        GameResult nextResult;
        if (isATurn) {
            nextResult = playGame(board, nextRow, nextCol, bRow, bCol, moveCount + 1, false, boardHeight, boardWidth);
        } else {
            nextResult = playGame(board, aRow, aCol, nextRow, nextCol, moveCount + 1, true, boardHeight, boardWidth);
        }

        if (!nextResult.canWin) {
            canWin = true;
            minMovesToWin = min(minMovesToWin, nextResult.moveCount);
        } else {
            maxMovesToLose = max(maxMovesToLose, nextResult.moveCount);
        }
    }

    // 2.3. 백트래킹: 플랫폼 복원
    board[currentRow][currentCol] = 1;

    // 3. 결과 반환
    if (canWin) {
        return GameResult(true, minMovesToWin);
    } else {
        return GameResult(false, maxMovesToLose);
    }
}

// 최적의 이동 횟수를 계산하는 함수
int solution(vector<vector<int>> board, vector<int> aloc, vector<int> bloc) {
    int boardHeight = board.size();
    int boardWidth = board[0].size();

    GameResult result = playGame(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, true, boardHeight, boardWidth);
    return result.moveCount;
}

// 테스트 코드
int main() {
    vector<vector<int>> board1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
    vector<int> aloc1 = {1, 0};
    vector<int> bloc1 = {1, 2};
    cout << "Test Case 1: " << solution(board1, aloc1, bloc1) << " (Expected: 5)" << endl;

    vector<vector<int>> board2 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
    vector<int> aloc2 = {1, 0};
    vector<int> bloc2 = {1, 2};
    cout << "Test Case 2: " << solution(board2, aloc2, bloc2) << " (Expected: 4)" << endl;

    vector<vector<int>> board3 = {{1, 1, 1, 1, 1}};
    vector<int> aloc3 = {0, 0};
    vector<int> bloc3 = {0, 4};
    cout << "Test Case 3: " << solution(board3, aloc3, bloc3) << " (Expected: 4)" << endl;

    vector<vector<int>> board4 = {{1}};
    vector<int> aloc4 = {0, 0};
    vector<int> bloc4 = {0, 0};
    cout << "Test Case 4: " << solution(board4, aloc4, bloc4) << " (Expected: 0)" << endl;

    return 0;
}