// problem link : https://www.acmicpc.net/problem/7569
// [solution]

// 문제 요약
//창고에는 가로 M × 세로 N × 높이 H인 박스들이 층으로 쌓임
//각 칸에는 토마토가 있는데,
//1: 익은 토마토
//0: 안 익은 토마토
//-1: 토마토 없음
//하루가 지나면 익은 토마토의 상하좌우, 위층, 아래층에 있는 토마토가 익음
//모든 토마토가 익는 데 며칠 걸리는지 구하는 게 목표

//알고리즘 순서 
//1. 익은 토마토를 먼저 찾기
//3차원 공간을 돌면서 1인 토마토를 queue에 넣음
//이걸 BFS의 출발점으로 생각

//2. BFS로 전염시키기
//큐에서 하나씩 꺼내서, 그 토마토 주변 6방향을 확인
//주변에 0 (안 익은 토마토)이 있으면, 
// 그 칸을 1로 바꿔서 익혔다고 표시
//그리고 days 배열에 며칠째 익은 건지 기록
//그 칸도 큐에 넣어서, 다음에 또 주변을 익히게 함

//3. BFS가 끝나면 모든 전염이 끝난 상태!

//결과 계산
//4. 아직 안 익은 게 있으면 → -1 출력
//BFS가 끝났는데도 0이 남아 있다면,
//아무리 기다려도 못 익는 토마토가 있다는 뜻
//이럴 땐 정답은 -1

//5. 최대 날짜 출력
//모든 칸을 돌면서 days[h][r][c] 값 중 가장 큰 값을 찾음
//그게 모든 토마토가 다 익기까지 걸린 시간

//왜 BFS를 쓰나요?
//BFS는 퍼져 나가는 것을 처리할 때 아주 좋음
//익은 토마토가 동시에 여러 방향으로 퍼지니까, 
//하루 단위로 순서대로 퍼뜨릴 수 있음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Tomato {
    static int M;
    static int N;
    static int H;
    static int[][][] boxes;
    static boolean[][][] visited;
    static int[][][] days;
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static Queue<Point> queue = new LinkedList<>();

    static class Point {
        int h, r, c;
        public Point(int h, int r, int c) {
            this.h = h;
            this.r = r;
            this.c = c;
        }
        
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            int cr = cur.r;
            int cc = cur.c;
            int ch = cur.h;

            for (int i = 0; i < 6; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                int nh = ch + dh[i];
                
                if (nr < 0 || nc < 0 || nh < 0 || nr >= N || nc >= M || nh >= H) {
                    continue;
                }

                if (boxes[nh][nr][nc] == 0 && !visited[nh][nr][nc]) {
                    visited[nh][nr][nc] = true;
                    days[nh][nr][nc] = days[ch][cr][cc] + 1;
                    boxes[nh][nr][nc] = 1;
                    queue.offer(new Point(nh, nr, nc));
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 가로
        N = Integer.parseInt(st.nextToken()); // 세로
        H = Integer.parseInt(st.nextToken()); // 높이
        
        boxes = new int[H][N][M];
        visited = new boolean[H][N][M];
        days = new int[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    boxes[h][r][c] = Integer.parseInt(st.nextToken());
                    if (boxes[h][r][c] == 1) {
                        visited[h][r][c] = true;
                        queue.offer(new Point(h, r, c));
                    }
                }
            }
        }

        bfs();

        int maxDays = 0;

        for (int h = 0; h < H; h++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (boxes[h][r][c] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    maxDays = Math.max(maxDays, days[h][r][c]);
                }
            }
        }

        System.out.println(maxDays);
    }
}