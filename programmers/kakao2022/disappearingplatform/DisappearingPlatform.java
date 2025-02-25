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

class DisappearingPlatform {
    int[] dr = {-1, 1, 0, 0};  // 상하좌우 이동을 위한 행 변화량
    int[] dc = {0, 0, -1, 1};  // 상하좌우 이동을 위한 열 변화량
    int n, m;  // 보드의 크기
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        
        // 게임 실행 결과를 반환받음
        Result result = play(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, true);
        return result.count;
    }
    
    // 게임의 결과를 저장하는 클래스
    class Result {
        boolean win;  // 이길 수 있는지 여부
        int count;    // 이동 횟수
        
        Result(boolean win, int count) {
            this.win = win;
            this.count = count;
        }
    }
    
    private Result play(int[][] board, int ar, int ac, int br, int bc, int depth, boolean isATurn) {
        // 현재 차례인 플레이어의 위치
        int curR = isATurn ? ar : br;
        int curC = isATurn ? ac : bc;
        
        // 현재 위치의 발판이 이미 없거나 게임판을 벗어난 경우
        if (curR < 0 || curR >= n || curC < 0 || curC >= m || board[curR][curC] == 0) {
            return new Result(false, depth);
        }
        
        // 이동 가능한 방향이 있는지 확인
        boolean canMove = false;
        for (int i = 0; i < 4; i++) {
            int nr = curR + dr[i];
            int nc = curC + dc[i];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;
            canMove = true;
            break;
        }
        
        // 이동할 수 없는 경우
        if (!canMove) {
            return new Result(false, depth);
        }
        
        // 최적의 결과를 저장할 변수들
        boolean canWin = false;
        int minCount = Integer.MAX_VALUE;  // 이기는 경우 중 최소 이동 횟수
        int maxCount = 0;                  // 지는 경우 중 최대 이동 횟수
        
        // 현재 위치의 발판을 임시로 제거
        board[curR][curC] = 0;
        
        // 4방향으로 이동해보며 최적의 결과 탐색
        for (int i = 0; i < 4; i++) {
            int nr = curR + dr[i];
            int nc = curC + dc[i];
            
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || board[nr][nc] == 0) continue;
            
            Result result;
            if (isATurn) {
                result = play(board, nr, nc, br, bc, depth + 1, false);
            } else {
                result = play(board, ar, ac, nr, nc, depth + 1, true);
            }
            
            // 이기는 경우
            if (!result.win) {
                canWin = true;
                minCount = Math.min(minCount, result.count);
            }
            // 지는 경우
            else {
                maxCount = Math.max(maxCount, result.count);
            }
        }
        
        // 발판 복구
        board[curR][curC] = 1;
        
        // 결과 반환
        if (canWin) {
            return new Result(true, minCount);
        } else {
            return new Result(false, maxCount);
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
        int[] aloc4 = {1, 0};
        int[] bloc4 = {1, 2};
        int expected4 = 0;
        int actual4 = game.solution(board4, aloc4, bloc4);
        
        System.out.println("Test Case 4:");
        System.out.println("  Expected: " + expected4 + ", Actual: " + actual4);
        System.out.println("  Result: " + (actual4 == expected4 ? "Success" : "Failure"));
    }
}