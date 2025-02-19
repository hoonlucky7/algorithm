// 파괴되지 않은 건물
// https://school.programmers.co.kr/learn/courses/30/lessons/92344
// brute force : 주어진 것을 이중 반복문으로 계산 
// 행의 길이 : N
// 열의 길이 : M
// skill행의 길이 : K
// 1 ≤ board의 행의 길이 (= N) ≤ 1,000
// 1 ≤ board의 열의 길이 (= M) ≤ 1,000
// 1 ≤ board의 원소 (각 건물의 내구도) ≤ 1,000
// 1 ≤ skill의 행의 길이 ≤ 250,000
// 시간 복잡도 : O(K * N * M) = 1000 * 1000 * 250,000 = 250,000,000,000
// 이 방식은 타임아웃 발생!!!

// Building Undestroyed
// https://school.programmers.co.kr/learn/courses/30/lessons/92344
// brute force: Calculate using nested loops for the given input.
// Height of the board: N
// Width of the board: M
// Length of the skill array: K
// 1 ≤ Height of the board (= N) ≤ 1,000
// 1 ≤ Width of the board (= M) ≤ 1,000
// 1 ≤ Element of board (durability of each building) ≤ 1,000
// 1 ≤ Length of skill array ≤ 250,000
// Time complexity: O(K * N * M) = 1000 * 1000 * 250,000 = 250,000,000,000
// This approach will result in a timeout!!!

public class BuildingUndamagedBF {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        // board의 범위를 커버할 수 있도록 (n+1) x (m+1) 크기의 차분 배열 생성
        int[][] diff = new int[n + 1][m + 1];
        
        // skill 배열을 순회하며 차분 배열에 영향을 기록합니다.
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = s[5];
            
            // type 1 : 공격 -> 내구도 감소, type 2 : 회복 -> 내구도 증가
            int effect = (type == 1 ? -degree : degree);
            
            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c <= c2; c++) {
                    board[r][c] += effect;
                }
            }
        }
        
        int answer = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] > 0) {
                    answer++;
                }
            }
        }
        
        return answer;
    }

    public static void main(String[] args) {
        BuildingUndamaged buildingUndamaged = new BuildingUndamaged();

        // 테스트 케이스 1
        int[][] board1 = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill1 = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        int expected1 = 10;
        int actual1 = buildingUndamaged.solution(board1, skill1);
        System.out.println("테스트 케이스 1:");
        System.out.println("  기대값: " + expected1 + ", 실제값: " + actual1);
        if (actual1 == expected1) {
            System.out.println("  결과: 성공");
        } else {
            System.out.println("  결과: 실패");
        }
        System.out.println();

        // 테스트 케이스 2
        int[][] board2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] skill2 = {{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}};
        int expected2 = 6;
        int actual2 = buildingUndamaged.solution(board2, skill2);
        System.out.println("테스트 케이스 2:");
        System.out.println("  기대값: " + expected2 + ", 실제값: " + actual2);
        if (actual2 == expected2) {
            System.out.println("  결과: 성공");
        } else {
            System.out.println("  결과: 실패");
        }
    }
}