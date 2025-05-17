// problem link : https://www.acmicpc.net/problem/2468
// [solution]
// 1) 최대 높이를 저장(0 ~ 최대높이 - 1)
// 2) bfs를 이용해서 각 물 높이에 대한 안전 영역 계산

// 시간 복잡도는 O(H * N^2)

// 1)을 개선하는 2가지 방법
// 1-1) 최소값도 저장함
// 1-2) HashSet에 모든 물 높이를 기록, 최소값 - 1도 기록

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class SafetyZone {
    static int n;
    static int[][] map;
    static boolean[][] visied;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void bfs(int sr, int sc, int h) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc));
        visied[sr][sc] = true;

        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nc < 0 || nr >= n || nc >= n) {
                    continue;
                }

                if (map[nr][nc] > h && !visied[nr][nc]) {
                    visied[nr][nc] = true;
                    queue.offer(new Point(nr, nc));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        
        map = new int[n][n];
        int maxH = 0;
        int minH = 101;

        //고유한 높이 값 수집
        Set<Integer> heightSet = new HashSet<>();

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                heightSet.add(map[r][c]);

                if (map[r][c] > maxH) {
                    maxH = map[r][c];
                }
                if (map[r][c] < minH) {
                    minH = map[r][c];
                }
            }
        }
        
        int maxSafetyCount = 0;

        heightSet.add(minH - 1);
        for (int h : heightSet) {
            visied = new boolean[n][n];
            int safetyCount = 0;

            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (map[r][c] > h && !visied[r][c]) {
                        bfs(r, c, h);
                        safetyCount++;
                    }
                }
            }
            if (maxSafetyCount < safetyCount) {
                maxSafetyCount = safetyCount;
            }
        }

        System.out.println(maxSafetyCount);
    }
}