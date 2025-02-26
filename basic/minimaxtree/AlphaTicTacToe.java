// Problem Link: https://www.acmicpc.net/problem/16571

// 알파 틱택토
// 틱택토(Tic-tac-toe) 게임은 두 플레이어가 번갈아가며 O와 X를 3x3판에 써서 같은 글자를 가로, 세로, 
// 혹은 대각선 상에 놓이도록 하는 게임이다.
// 먼저 3개의 연속 된 O 또는 X를 완성시킨 플레이어가 승리하게 된다. 이 게임은 무승부가 가능하다.
// Figure: 게임의 진행과정 예시
// 틱택토 초보 승현이와 기영이는 틱택토 게임을 플레이하고 있었다. 그런데 뒤에서 지켜보고 있던 
// 틱택토 초고수 윤영이와 진욱이가 너무나 답답한 나머지 본인들이 대신 플레이를 해주겠다고 나섰다.
// 지금까지 진행 된 틱택토 게임 보드가 주어졌을 때, 
// 이번에 착수하는 플레이어가 얻을 수 있는 최선의 게임 결과는 무엇일까? 
// 단, 두 플레이어는 항상 모든 경우를 고려하여 최선의 수를 둔다고 가정한다.

// 입력
// 현재까지 진행된 틱택토 게임 보드가 띄어쓰기로 구분되어 3줄에 걸쳐 주어진다.
//  0은 빈칸, 1은 X, 2는 O를 의미한다. 단, 항상 X가 선공이다. 
// 그리고 이미 게임이 종료된 상태는 입력으로 주어지지 않는다.

// 출력
// 첫째 줄에 주어진 게임 보드에서 이번에 착수하는 플레이어가 얻을 수 있는 최선의 게임 결과를 출력한다.
// 이기는 경우 "W", 무승부인 경우 "D", 지는 경우 "L"을 출력한다.

// [solution] 
// 미니맥스 알고리즘은 게임 트리를 탐색하여, 자신(현재 플레이어)에게는 최선의 결과를, 
// 상대방에게는 최악의 결과를 가져오는 수를 찾는 전략입니다. 
// 틱택토와 같은 2인용 제로섬 게임(zero-sum game, 한쪽의 이득이 다른 쪽의 손실이 되는 게임)에서 
// 최적의 수를 결정하는 데 사용됩니다.

// 1) 재귀적 탐색: minimax 함수는 재귀적으로 호출됩니다. 
// 현재 게임 상태(board)에서 가능한 모든 수를 놓아보면서 게임 트리의 깊이 우선 탐색(Depth-First Search)을 수행합니다.
// 2) Base Cases (기저 사례): 재귀 호출은 다음 조건 중 하나를 만나면 종료됩니다.
// 게임 종료: checkWinner 함수를 통해 승자가 결정된 경우 (WIN, LOSS).
// 무승부: isBoardFull 함수를 통해 보드가 가득 차서 더 이상 놓을 곳이 없는 경우 (DRAW).
// 이 경우, 해당 상태의 "점수"를 반환합니다 (WIN = 1, DRAW = 0, LOSS = -1).

// 3)Maximizing Player (현재 플레이어):
// 현재 플레이어는 자신의 점수를 최대화하려고 합니다.
// 빈칸(board[i][j] == 0)에 자신의 돌을 놓습니다.
// 상대방의 차례로 minimax 함수를 재귀 호출합니다.
// 재귀 호출에서 반환된 값(상대방의 최선의 결과) 중 가장 큰 값을 선택합니다. 이것이 현재 플레이어가 얻을 수 있는 최선의 결과입니다.
// 놓았던 돌을 다시 제거합니다 (backtracking). 이렇게 해야 다른 가능한 수도 탐색할 수 있습니다.

// 4) Minimizing Player (상대 플레이어):
// 상대 플레이어는 현재 플레이어의 점수를 최소화하려고 합니다 (즉, 자신의 점수를 최대화).
// 빈칸에 상대방의 돌을 놓습니다.
// 현재 플레이어의 차례로 minimax 함수를 재귀 호출합니다.
// 재귀 호출에서 반환된 값(현재 플레이어의 최선의 결과) 중 가장 작은 값을 선택합니다. 이것이 상대방이 얻을 수 있는 최선의 결과입니다.
// 놓았던 돌을 다시 제거합니다 (backtracking).

// 5) Backtracking (백트래킹): 각 재귀 호출에서 돌을 놓았다가 
// 다시 제거하는 과정(board[i][j] = player; ... board[i][j] = 0;)이 핵심입니다. 이 과정을 통해 모든 가능한 경우의 수를 탐색하고, 
// 각 수에 대한 결과를 비교할 수 있습니다.

// 6) 최종 결과: 최초 minimax 함수 호출은 현재 게임 상태에서 현재 플레이어가 얻을 수 있는 최선의 결과(WIN, DRAW, or LOSS)를 반환합니다.

// Alpha Tic-Tac-Toe
// Tic-tac-toe is a game where two players take turns placing O's and X's on a 3x3 board,
// aiming to get three of their marks in a row (horizontally, vertically, or diagonally).
// The player who first completes a line of three consecutive O's or X's wins.  The game can end in a draw.

// Figure: Example of game progress

// Seung-hyun and Ki-young, Tic-tac-toe beginners, were playing a game of Tic-tac-toe.  
// Yoon-young and Jin-wook, Tic-tac-toe experts, were watching from behind and,
// feeling frustrated, offered to take over and play for them.

// Given a Tic-tac-toe board representing the current state of the game,
// what is the best possible outcome for the player whose turn it is to move?
// Assume that both players always play optimally, considering all possibilities.

// Input
// The currently played Tic-tac-toe game board is given over three lines, separated by spaces.
// 0 represents an empty cell, 1 represents an X, and 2 represents an O.  X always goes first.
// The input will never represent a game board where the game has already ended.

// Output
// On the first line, print the best possible game result for the player whose turn it is
// to move on the given game board.
// Print "W" for a win, "D" for a draw, and "L" for a loss.


// [Solution]
// The Minimax algorithm is a strategy for searching a game tree to find the move that leads to
// the best outcome for the current player (maximizing their gain) and the worst outcome for
// the opponent (minimizing the current player's loss, which is equivalent to maximizing the opponent's gain).
// It's used to determine the optimal move in two-player, zero-sum games (games where one player's gain
// is equal to the other player's loss), such as Tic-Tac-Toe.

// 1) Recursive Search: The minimax function is called recursively. It performs a depth-first search (DFS)
//    of the game tree, exploring all possible moves from the current game state (board).

// 2) Base Cases: The recursive calls terminate when one of the following conditions is met:
//    - Game Over: A winner is determined by the checkWinner function (WIN, LOSS).
//    - Draw: The board is full, and there are no more empty cells (DRAW), determined by the isBoardFull function.
//    In these base cases, the "score" of the state is returned (WIN = 1, DRAW = 0, LOSS = -1).

// 3) Maximizing Player (Current Player):
//    - The current player aims to maximize their score.
//    - They place their marker on an empty cell (board[i][j] == 0).
//    - The minimax function is called recursively for the opponent's turn.
//    - The maximum value among the values returned from the recursive calls (representing the opponent's best outcomes) is selected.
//      This is the best possible outcome for the current player.
//    - The marker is removed (backtracking) to explore other possible moves.

// 4) Minimizing Player (Opponent):
//    - The opponent aims to minimize the current player's score (which is equivalent to maximizing their own score).
//    - They place their marker on an empty cell.
//    - The minimax function is called recursively for the current player's turn.
//    - The minimum value among the values returned from the recursive calls (representing the current player's best outcomes) is selected.
//      This is the best possible outcome for the opponent.
//    - The marker is removed (backtracking).

// 5) Backtracking: The process of placing a marker and then removing it in each recursive call
//    (board[i][j] = player; ... board[i][j] = 0;) is crucial.  This allows exploration of all possible move sequences
//    and comparison of the outcomes for each move.

// 6) Final Result: The initial call to the minimax function returns the best possible outcome (WIN, DRAW, or LOSS)
//    for the current player from the current game state.

import java.io.FileNotFoundException;
import java.util.Scanner;

public class AlphaTicTacToe {
    public static final int N = 3;
    public static final int WIN = 1;
    public static final int DRAW = 0;
    public static final int LOSS = -1;
    public static final int UNKNOWN = -2;
    public static final int XPLAYER = 1;
    public static final int OPLAYER = 2;
    private int startPlayer;

    // Checks if the board is full (no more empty cells).
    private boolean isBoardFull(int[][] board) {
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
    private int checkWinner(int[][] board) {
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
    private int minimax(int[][] board, int player) {
        int winner = checkWinner(board);
        if (winner != UNKNOWN) {
            // If there's a winner, return WIN if it's the starting player, LOSS otherwise.
            if (winner == startPlayer) {
                return WIN;
            } else {
                return LOSS;
            }
        }

        // If the board is full and there's no winner, it's a draw.
        if (isBoardFull(board)) {
            return DRAW;
        }

        // If it's the starting player's turn, maximize the score.
        if (player == startPlayer) {
            int maxValue = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0) {
                        // Make a move
                        board[i][j] = player;
                        // Recursively call minimax for the opponent
                        int value = minimax(board, (player == XPLAYER) ? OPLAYER : XPLAYER);
                        // Update maxValue with the best possible outcome
                        maxValue = Math.max(maxValue, value);
                        // Undo the move (backtracking)
                        board[i][j] = 0;
                    }
                }
            }
            return maxValue;
        } else { // If it's the opponent's turn, minimize the score.
            int minValue = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == 0) {
                        // Make a move
                        board[i][j] = player;
                        // Recursively call minimax for the starting player
                        int value = minimax(board, (player == XPLAYER) ? OPLAYER : XPLAYER);
                        // Update minValue with the best possible outcome for the opponent
                        minValue = Math.min(minValue, value);
                        // Undo the move (backtracking)
                        board[i][j] = 0;
                    }
                }
            }
            return minValue;
        }
    }

    public void solution() throws FileNotFoundException {
        //Scanner sc = new Scanner(new File("./basic/minimaxTree/inputAlphaTicTacToe.txt"));
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[N][N];

        // Read the board state from input
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        sc.close();

        // Count the number of X's and O's to determine the starting player
        int[] count = new int[N];
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
            System.out.println("W");
        } else if (result == LOSS) {
            System.out.println("L");
        } else {
            System.out.println("D");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        AlphaTicTacToe AlphaTicTacToe = new AlphaTicTacToe();
        AlphaTicTacToe.solution();
    }
}