// 사라지는 발판
// 문제 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/92345

// 문제 요약
// 두 플레이어 A와 B가 각각 캐릭터를 하나씩 가지고, 발판이 있는 칸만 이동 가능한 격자 보드 위에서 게임을 진행합니다. 
// 게임 규칙은 다음과 같습니다:

// 초기 상태: 각 플레이어는 발판이 있는 칸에 자신의 캐릭터를 배치합니다.
// 이동 규칙: 플레이어는 자신의 차례에 상하좌우 인접한 발판이 있는 칸으로 이동합니다. 
// 이동한 후에는 원래 서있던 발판이 사라집니다.
// 종료 조건:
// 자신의 차례에 이동할 수 있는 인접 발판이 없으면 그 플레이어가 패배합니다.
// 두 캐릭터가 같은 발판 위에 있을 때, 한 플레이어가 이동하여 상대방이 서 있던 발판이 사라지면,
// 그 상대방 플레이어가 패배합니다.
// 플레이어 전략: A가 먼저 시작하며, 양 플레이어는 최적의 전략을 사용합니다. 
// 즉, 이길 수 있는 플레이어는 가능한 빨리 승리하고, 
// 질 수밖에 없는 플레이어는 최대한 많은 이동 횟수를 만들어 게임을 지연시키려고 합니다.
// 문제의 목적은 게임이 종료될 때까지 두 플레이어가 캐릭터를 총 몇 번 움직이는지 예측하는 것입니다.

// 게임 진행 과정:

// 각 턴마다 현재 플레이어는 이동 가능한 모든 방향(상, 하, 좌, 우)을 탐색합니다.
// 이동 불가능한 경우(보드 경계 벗어나거나 이미 사라진 발판)는 즉시 제외됩니다.
// 승리할 수 있는 경우, 가능한 최소 이동 수로 승리하고, 
// 반대로 패배할 경우 가능한 한 많은 이동으로 게임을 지연시키려 합니다.
// 재귀적 탐색 및 백트래킹

// 종료 조건 :
// 현재 플레이어의 위치가 보드 범위를 벗어나거나, 이미 발판이 사라진 경우 → 패배.
// 현재 플레이어가 이동할 수 있는 유효한 방향이 없는 경우 → 패배.

// 재귀 단계:
// 현재 플레이어의 발판을 일시적으로 제거(방문 처리).
// 4가지 방향에 대해 재귀 호출을 수행하여 상대방의 반응을 예측합니다.
// 상대방이 패배하는 결과가 있다면, 현재 플레이어는 승리할 수 있으며, 최소 이동 수를 기록합니다.
// 모든 경우에서 상대방이 승리하면, 현재 플레이어는 패배하며, 최대 이동 수를 기록합니다.

// 복원:
// 재귀 호출 후, 원래 상태로 보드를 복원하여 다른 경로의 탐색에 영향을 주지 않도록 합니다.

// 시간 복잡도 분석
// 보드 크기: 최대 5×5 (25칸)
// 가능한 이동: 각 칸에서 최대 4방향 (상하좌우)
// 최악의 경우: 모든 발판을 방문하며 O(4^n) (n은 발판 수, 최대 25)
// 실제 시간:
// 가지치기(pruning) 효과 및 발판 소멸로 인해 실제 탐색 공간은 크게 줄어듭니다.
// 대부분의 게임이 모든 발판이 사용되기 전에 종료되므로, 
// 실제 실행 시간은 이론적 최악의 경우보다 훨씬 낮습니다.

// What is the Minimax Algorithm?

// It's a decision-making algorithm used in turn-based games with two players.
// One player aims to maximize their score, while the opponent aims to minimize it.
// It assumes that each player will make the optimal move.

// For each choice A makes:

// Consider all of B's possible responses.
// Assume B will make the optimal move.
// Choose the move that gives A the highest probability of winning.

// This process is repeated recursively:

// If winning is possible: Try to win with the minimum number of moves.
// If losing is inevitable: Try to survive for the maximum number of moves.

// The minimax algorithm operates by assuming each player will make the best possible choice,
// and finds the optimal move accordingly.

// Time Complexity Calculation:
// Analysis per step
// Board size: Maximum 5x5 = 25 cells
// Possible moves from each position: 4 directions (up, down, left, right)
// Platform state: present (1) or absent (0)
// Worst-case scenario
// Each turn, a player can move in 4 directions.
// The game can continue until all platforms disappear.
// Each cell on the board can be visited once.
// Recursive call analysis
// O(4^n)
// where n is the number of platforms (maximum 25)

// Therefore, the theoretical worst-case time complexity is:
// O(4^25)
// Reasons why the actual execution time is much less:
// Pruning effect
// Many cases are excluded because they are invalid moves.
// As platforms disappear, the number of possible moves continuously decreases.
// Game characteristics
// Most games end before all platforms are used.
// Both players are often close to each other, reducing the actual search space.
// Optimization factors
// Movement to a cell without a platform is not allowed.
// Moves that go out of bounds are immediately excluded.
// Further exploration is unnecessary if the outcome (win/lose) is already determined.

// The actual runtime complexity is much lower than the theoretical worst case,
// and it is well within an executable range under the given constraints (5x5 board).


class DisappearingPlatform {

    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    private int h;
    private int w;

    private static class GameResult {
        private boolean canWin;
        private int moveCount;

        GameResult(boolean canWin, int moveCount) {
            this.canWin = canWin;
            this.moveCount = moveCount;
        }
    }

    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= h || col < 0 || col >= w;
    }

    private GameResult playGame(int[][] board, int aRow, int aCol, int bRow, int bCol, 
    int moveCount, boolean isAturn) {
        int currentRow = isAturn ? aRow : bRow;
        int currentCol = isAturn ? aCol : bCol;

        if (isOutOfBounds(currentRow, currentCol) || board[currentRow][currentCol] == 0) {
            return new GameResult(false, moveCount);
        }

        boolean canMove = false;

        for (int i = 0; i < 4; i++) {
            int nextRow = currentRow + DELTA_ROW[i];
            int nextCol = currentCol + DELTA_COL[i];
            if (!isOutOfBounds(nextRow, nextCol) && board[nextRow][nextCol] == 1) {
                canMove = true;
                break;
            }
        }

        if (!canMove) {
            return new GameResult(false, moveCount);
        }

        board[currentRow][currentCol] = 0;

        boolean canWin = false;
        int minMovesToWin = Integer.MAX_VALUE;
        int maxmovesToLoss = 0;

        for (int i = 0; i < 4; i++) {
            int nextRow = currentRow + DELTA_ROW[i];
            int nextCol = currentCol + DELTA_COL[i];

            if (isOutOfBounds(nextRow, nextCol) || board[nextRow][nextCol] == 0) {
                continue;
            }

            GameResult nextResult;
            if (isAturn) {
                nextResult = playGame(board, nextRow, nextCol, bRow, bCol, moveCount + 1, false);
            } else {
                nextResult = playGame(board, aRow, aCol, nextRow, nextCol, moveCount + 1, true);
            }

            if (!nextResult.canWin) {
                canWin = true;
                minMovesToWin = Math.min(minMovesToWin, nextResult.moveCount);
            } else {
                maxmovesToLoss = Math.max(maxmovesToLoss, nextResult.moveCount);
            }
        }

        board[currentRow][currentCol] = 1;

        if (canWin) {
            return new GameResult(true, minMovesToWin);
        } else {
            return new GameResult(false, maxmovesToLoss);
        }
    }

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.h = board.length;
        this.w = board[0].length;

        GameResult gameResult = playGame(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, true);
        return gameResult.moveCount;
    }
    
     public static void main(String[] args) {
        DisappearingPlatform game = new DisappearingPlatform();

        // Test Case 1
        int[][] board1 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[] aloc1 = {1, 0};
        int[] bloc1 = {1, 2};
        int expected1 = 5;
        int actual1 = game.solution(board1, aloc1, bloc1);

        System.out.println("Test Case 1:");
        System.out.println("  Expected: " + expected1 + ", Actual: " + actual1);
        System.out.println("  Result: " + (actual1 == expected1 ? "Success" : "Failure"));

        // Test Case 2
        int[][] board2 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[] aloc2 = {1, 0};
        int[] bloc2 = {1, 2};
        int expected2 = 4;
        int actual2 = game.solution(board2, aloc2, bloc2);

        System.out.println("Test Case 2:");
        System.out.println("  Expected: " + expected2 + ", Actual: " + actual2);
        System.out.println("  Result: " + (actual2 == expected2 ? "Success" : "Failure"));

        // Test Case 3
        int[][] board3 = {{1, 1, 1, 1, 1}};
        int[] aloc3 = {0, 0};
        int[] bloc3 = {0, 4};
        int expected3 = 4;
        int actual3 = game.solution(board3, aloc3, bloc3);

        System.out.println("Test Case 3:");
        System.out.println("  Expected: " + expected3 + ", Actual: " + actual3);
        System.out.println("  Result: " + (actual3 == expected3 ? "Success" : "Failure"));

        // Test Case 4
        int[][] board4 = {{1}};
        int[] aloc4 = {0, 0}; 
        int[] bloc4 = {0, 0};
        int expected4 = 0;
        int actual4 = game.solution(board4, aloc4, bloc4);

        System.out.println("Test Case 4:");
        System.out.println("  Expected: " + expected4 + ", Actual: " + actual4);
        System.out.println("  Result: " + (actual4 == expected4 ? "Success" : "Failure"));
    }
}