// Problem link : 
// https://www.acmicpc.net/problem/1697

// [solution]
// BFS 구현 :
// visited 배열은 boolean[]으로 선언하여 방문 여부를 체크합니다.
// ArrayDeque를 사용하여 큐를 구현하고, Pair 객체를 저장합니다.
// 세 가지 이동(-1, +1, *2)을 탐색하며, 동생의 위치 k에 도달하면 time + 1을 반환합니다.

// 시간 복잡도 
// O(100,001), 즉 상수 시간으로 간주됨

import java.util.*;

// Pair 클래스 (위치, 시간)
class Pair {
    int pos;
    int time;

    Pair(int pos, int time) {
        this.pos = pos;
        this.time = time;
    }
}

public class FindAndSeek {
    private static final int max = 100001;

    public static int findSister(int n, int k) {
        // 수빈이와 동생의 위치가 같으면 시간이 필요 없음
        if (n == k) {
            return 0;
        }

        // 방문 여부를 체크하는 배열
        boolean[] visited = new boolean[max];
        // 큐에 (위치, 시간)을 저장
        Deque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(n, 0));
        visited[n] = true;
        // BFS 탐색
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int pos = current.pos;
            int time = current.time;
            // 가능한 세 가지 이동
            int [] nextPositions = { pos - 1, pos + 1, pos * 2};

            for (int nextPos : nextPositions) {
                // 이동 위치가 범위 내이고 방문하지 않은 경우
                if (nextPos >= 0 && nextPos < max && !visited[nextPos]) {
                    // 동생의 위치에 도달하면 시간 반환
                    if (nextPos == k) {
                        return time + 1;
                    }    
                    // 방문 표시 후 큐에 추가
                    visited[nextPos] = true;
                    queue.add(new Pair(nextPos, time + 1));
                }
            }   
                   
        }
        return -1; // 도달 불가능한 경우 (문제 조건상 발생하지 않음)
    }

    public static void main(String[] args) {
        // 입력 받기
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();

        // 결과 출력
        System.out.println(findSister(n, k));
    }
}