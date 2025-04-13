import sys
from collections import deque

def bfs(maze, n, m):
    visited = [[False] * m for _ in range(n)]

    dq = deque([(0, 0)])
    visited[0][0] = True
    while dq:
        r, c = dq.popleft()
        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < n and 0 <= nc < m and maze[nr][nc] == 1:
                maze[nr][nc] = maze[r][c] + 1
                dq.append((nr, nc))
    
    return maze[n - 1][m - 1]

input_data = sys.stdin.read().splitlines()
# 첫 줄: n과 m
n, m = map(int, input_data[0].split())

maze = []
# 이후 n줄을 읽어서 maze에 저장 (각 줄의 문자를 정수로 변환)
for i in range(1, n + 1):
    # 각 줄의 공백을 제거한 후 각 문자마다 정수 변환
    row = list(map(int, list(input_data[i].strip())))
    maze.append(row)

result = bfs(maze, n, m)
print(result)
