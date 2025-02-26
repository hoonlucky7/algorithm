// 미니맥스 알고리즘이란?

// 두 플레이어가 번갈아가며 진행하는 게임에서 사용되는 의사결정 알고리즘입니다
// 한 플레이어는 점수를 최대화(maximize)하려 하고, 상대는 점수를 최소화(minimize)하려 합니다
// 각 플레이어가 최적의 수를 둘 것이라고 가정합니다

// A는 각 선택지에 대해:

// B의 가능한 모든 응수를 고려합니다
// B가 최적의 수를 둘 것이라 가정합니다
// 그 중에서 A가 이길 확률이 가장 높은 수를 선택합니다

// 이 과정이 재귀적으로 반복되면서:

// 이길 수 있는 경우: 최소한의 이동으로 이기려 함
// 질 수밖에 없는 경우: 최대한 많은 이동으로 버티려 함

// 이렇게 미니맥스 알고리즘은 각 플레이어가 최선의 선택을 한다고 가정하고, 
// 그에 따른 최적의 수를 찾아내는 방식으로 동작합니다.

// 시간 복잡도 계산:
// 각 단계별 분석
// 보드 크기: 최대 5×5 = 25칸
// 각 위치에서 가능한 이동: 4방향(상하좌우)
// 발판의 상태: 있음(1) 또는 없음(0)
// 최악의 경우 시나리오
// 각 턴마다 플레이어는 4개의 방향으로 이동 가능
// 게임은 모든 발판이 사라질 때까지 계속될 수 있음
// 보드의 각 칸은 한 번씩 방문될 수 있음
// 재귀 호출 분석
// O(4^n)
// 여기서 n은 발판의 개수(최대 25)

// 따라서 이론적인 최악의 시간 복잡도는:
// O(4^25)
// 실제 실행시간이 이보다 훨씬 적은 이유:
// 가지치기(Pruning) 효과
// 많은 경우가 유효하지 않은 이동이라 제외됨
// 발판이 사라지면서 가능한 이동이 계속 줄어듦
// 게임의 특성
// 대부분의 게임이 모든 발판을 사용하기 전에 종료
// 양 플레이어가 서로 가까이 있는 경우가 많아 실제 탐색 공간이 줄어듦
// 최적화 요소
// 발판이 없는 곳으로는 이동 불가
// 보드 경계를 벗어나는 이동은 즉시 제외
// 이미 승패가 결정된 경우 추가 탐색 불필요
// 실제 동작 시간 복잡도는 이론적 최악의 경우보다 훨씬 낮으며, 
// 주어진 제약 조건(5×5 보드)에서는 충분히 실행 가능한 수준입니다.

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

    // Delta values for up, down, left, and right movements (row, column)
    private static final int[] DELTA_ROW = {-1, 1, 0, 0};
    private static final int[] DELTA_COL = {0, 0, -1, 1};

    private int boardHeight;  // Board height (number of rows)
    private int boardWidth;   // Board width (number of columns)

    /**
     * Calculates the optimal number of moves for the disappearing platform game.
     *
     * @param board Game board (1: platform exists, 0: platform doesn't exist)
     * @param aloc  Initial position of player A [row, column]
     * @param bloc  Initial position of player B [row, column]
     * @return The minimum number of moves for player A to win. If A cannot win, the maximum number of moves (among losing cases).
     */
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.boardHeight = board.length;
        this.boardWidth = board[0].length;

        // Start the game (A's turn first)
        GameResult result = playGame(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, true);
        return result.moveCount;
    }

    /**
     * Recursively plays the game and finds the optimal result.
     *
     * @param board      Current game board state
     * @param aRow       Current row position of player A
     * @param aCol       Current column position of player A
     * @param bRow       Current row position of player B
     * @param bCol       Current column position of player B
     * @param moveCount  Current number of moves (depth)
     * @param isATurn    Whether it's A's turn (true: A's turn, false: B's turn)
     * @return Game result (whether A can win and the number of moves)
     */
    private GameResult playGame(int[][] board, int aRow, int aCol, int bRow, int bCol, 
    int moveCount, boolean isATurn) {
        // 1. Base Case

        // Current player's position
        int currentRow = isATurn ? aRow : bRow;
        int currentCol = isATurn ? aCol : bCol;

        // 1.1. If the current position is out of bounds or there is no platform -> current player loses
        if (isOutOfBounds(currentRow, currentCol) || board[currentRow][currentCol] == 0) {
            return new GameResult(false, moveCount); // (Lose, current move count)
        }

        // 1.2. If the current player has no more moves -> current player loses
        boolean canMove = false;
        for (int i = 0; i < 4; i++) {
            int nextRow = currentRow + DELTA_ROW[i];
            int nextCol = currentCol + DELTA_COL[i];
            if (!isOutOfBounds(nextRow, nextCol) && board[nextRow][nextCol] == 1) {
                canMove = true; // Can move
                break;
            }
        }
        if (!canMove) {
            return new GameResult(false, moveCount); // (Lose, current move count)
        }

        // 2. Recursive Step

        // 2.1. Temporarily remove the platform of the current player (assuming it's stepped on)
        board[currentRow][currentCol] = 0;

        // 2.2. Recursively call for all possible moves
        boolean canWin = false;       // Whether the current player can win in this turn
        int minMovesToWin = Integer.MAX_VALUE; // Minimum number of moves to win
        int maxMovesToLose = 0;     // Maximum number of moves to lose


        for (int i = 0; i < 4; i++) {
            int nextRow = currentRow + DELTA_ROW[i];
            int nextCol = currentCol + DELTA_COL[i];

            // Skip if the position is invalid
            if (isOutOfBounds(nextRow, nextCol) || board[nextRow][nextCol] == 0) {
                continue;
            }

            GameResult nextResult; // Result of the next turn
            if (isATurn) {
                // If it's A's turn, pass to B's turn
                nextResult = playGame(board, nextRow, nextCol, bRow, bCol, moveCount + 1, false);
            } else {
                // If it's B's turn, pass to A's turn
                nextResult = playGame(board, aRow, aCol, nextRow, nextCol, moveCount + 1, true);
            }

            // 2.3. Update the optimal result based on the result of the recursive call

            if (!nextResult.canWin) { // If the opponent loses in the next turn -> current player can win
                canWin = true;
                minMovesToWin = Math.min(minMovesToWin, nextResult.moveCount); // Update minimum move count
            } else { // If the opponent wins in the next turn -> current player loses
                maxMovesToLose = Math.max(maxMovesToLose, nextResult.moveCount); // Update maximum move count
            }
        }
        // 2.4. Restore the platform (backtracking)
        board[currentRow][currentCol] = 1;

        // 3. Return result

        // 3.1. If can win -> (Win, minimum move count)
        if (canWin) {
            return new GameResult(true, minMovesToWin);
        } else { // 3.2. If can't win -> (Lose, maximum move count)
            return new GameResult(false, maxMovesToLose);
        }
    }

    /**
     * Checks if the given position is within the board boundaries.
     *
     * @param row Row position
     * @param col Column position
     * @return True if within the board, false otherwise
     */
    private boolean isOutOfBounds(int row, int col) {
        return row < 0 || row >= boardHeight || col < 0 || col >= boardWidth;
    }

    /**
     * Inner class to store the game result.
     */
    private static class GameResult {
        boolean canWin;    // Whether A can win
        int moveCount;     // Number of moves

        GameResult(boolean canWin, int moveCount) {
            this.canWin = canWin;
            this.moveCount = moveCount;
        }
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
        int[] aloc4 = {0, 0}; // Modified aloc4
        int[] bloc4 = {0, 0}; // Modified bloc4
        int expected4 = 0;
        int actual4 = game.solution(board4, aloc4, bloc4);

        System.out.println("Test Case 4:");
        System.out.println("  Expected: " + expected4 + ", Actual: " + actual4);
        System.out.println("  Result: " + (actual4 == expected4 ? "Success" : "Failure"));
    }
}