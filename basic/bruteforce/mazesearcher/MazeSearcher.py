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
            if (0 <= nr < n and 0 <= nc < m and maze[nr][nc] == 1 and not visited[nr][nc]):
                maze[nr][nc] = maze[r][c] + 1
                visited[nr][nc] = True
                dq.append((nr, nc))

    return maze[n - 1][m - 1]

input_data = sys.stdin.read().splitlines()
n, m = map(int, input_data[0].split())

maze = []

for i in range(1, n + 1):
    row = list(map(int, list(input_data[i].strip())))
    maze.append(row)

result = bfs(maze, n, m)

print(result)