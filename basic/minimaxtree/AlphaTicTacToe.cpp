#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std; // 모든 std 네임스페이스 사용

// Constants
const int N = 3;
const int WIN = 1;
const int DRAW = 0;
const int LOSS = -1;
const int UNKNOWN = -2;
const int XPLAYER = 1;
const int OPLAYER = 2;
int startPlayer;

// Checks if the board is full (no more empty cells).
bool isBoardFull(const vector<vector<int>>& board) {
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (board[i][j] == 0) {
                return false;
            }
        }
    }
    return true;
}

// Checks if there is a winner on the board.
// Returns the player number (1 or 2) if there's a winner, and UNKNOWN otherwise.
int checkWinner(const vector<vector<int>>& board) {
    // Check rows and columns
    for (int i = 0; i < N; i++) {
        if (board[i][0] != 0 && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
            return board[i][0];
        }
        if (board[0][i] != 0 && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
            return board[0][i];
        }
    }

    // Check diagonals
    if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        return board[0][0];
    }
    if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        return board[0][2];
    }

    return UNKNOWN;
}

// Minimax algorithm to determine the best outcome for the current player.
// Returns 1 for a win, 0 for a draw, and -1 for a loss.
int minimax(vector<vector<int>>& board, int player) {
    int winner = checkWinner(board);
    if (winner != UNKNOWN) {
        if (winner == startPlayer) {
            return WIN;
        } else {
            return LOSS;
        }
    }

    if (isBoardFull(board)) {
        return DRAW;
    }

    if (player == startPlayer) {
        int maxValue = INT_MIN;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = player;
                    int value = minimax(board, (player == XPLAYER) ? OPLAYER : XPLAYER);
                    maxValue = max(maxValue, value);
                    board[i][j] = 0;
                }
            }
        }
        return maxValue;
    } else {
        int minValue = INT_MAX;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = player;
                    int value = minimax(board, (player == XPLAYER) ? OPLAYER : XPLAYER);
                    minValue = min(minValue, value);
                    board[i][j] = 0;
                }
            }
        }
        return minValue;
    }
}

int main() {
    vector<vector<int>> board(N, vector<int>(N));

    // Read the board state from input
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            cin >> board[i][j];
        }
    }

    // Count the number of X's and O's to determine the starting player
    vector<int> count(N, 0);
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            count[board[i][j]]++;
        }
    }
    
    // X always goes first. If the counts are equal, it's X's turn.
    if (count[XPLAYER] == count[OPLAYER]) {
        startPlayer = XPLAYER;
    } else {
        startPlayer = OPLAYER;
    }

    // Run the minimax algorithm to get the result
    int result = minimax(board, startPlayer);

    // Print the result based on the minimax output
    if (result == WIN) {
        cout << "W" << endl;
    } else if (result == LOSS) {
        cout << "L" << endl;
    } else {
        cout << "D" << endl;
    }

    return 0;
}