public class DisappearingPlatform {
    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        return play(board, aloc[0], aloc[1], bloc[0], bloc[1])[1];
    }

    private static int[] play(int[][] board, int ax, int ay, int bx, int by) {
        // 현재 플레이어가 이동할 수 없으면 패배
        if (board[ax][ay] == 0) return new int[]{0, 0};

        int[] directions = {-1, 0, 1, 0, 0, -1, 0, 1}; // 상하좌우
        int maxMoves = 0; // 최대로 이동 가능한 횟수
        int minMoves = Integer.MAX_VALUE; // 최소로 이동 가능한 횟수
        boolean canWin = false;

        // 현재 위치 발판 제거
        board[ax][ay] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = ax + directions[i * 2];
            int ny = ay + directions[i * 2 + 1];

            // 이동 가능한 범위 체크
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] == 1) {
                int[] opponentResult = play(board, bx, by, nx, ny);
                
                // 상대방 결과를 분석하여 최대/최소 이동 횟수를 갱신
                if (opponentResult[0] == 0) {
                    canWin = true;
                    minMoves = Math.min(minMoves, opponentResult[1] + 1);
                } else {
                    maxMoves = Math.max(maxMoves, opponentResult[1] + 1);
                }
            }
        }

        // 발판 복구
        board[ax][ay] = 1;

        // 이길 수 있는 경우와 아닌 경우 처리
        if (canWin) {
            return new int[]{1, minMoves};
        } else {
            return new int[]{0, maxMoves};
        }
    }

    
}
