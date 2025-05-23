// 문제 링크 : 
// https://www.acmicpc.net/problem/2178

// [solution] : bfs를 이용
// 미로를 2차원 배열 maze에 저장하고,
// Point 클래스를 이용해 
// 현재 위치 (r, c)를 큐에 넣어 BFS를 수행
// 이동 가능한 칸을 방문할 때, 
// 현재 칸까지의 거리 값에 1을 더해 maze에 저장, visited 배열에 체크 표시


import java.io.*;
import java.util.*;

public class MazeSearcher {
    static int n, m;
    static int[][] maze;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(
            br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        maze = new int[n][m];
        visited = new boolean[n][m];
        
        for (int r = 0; r < n; r++) {
            String line = br.readLine().trim();
            for (int c = 0; c < m; c++) {
                maze[r][c] = line.charAt(c) - '0';
            }
        }

        bfs(0, 0);
    
        System.out.println(maze[n - 1][m - 1]);
    }

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static void bfs(int sr, int sc) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(sr, sc));
        visited[sr][sc] = true;
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int cr = cur.r;
            int cc = cur.c;

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if (nr < 0 || nc < 0 || nr >= n 
                || nc >= m) {
                    continue;
                }

                if (maze[nr][nc] == 1 
                && !visited[nr][nc]) {
                    maze[nr][nc] = maze[cr][cc] + 1;
                    visited[nr][nc] = true;
                    queue.offer(new Point(nr, nc));
                }
            }
        }
    }
}
