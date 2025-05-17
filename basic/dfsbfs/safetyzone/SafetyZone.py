# problem link : https://www.acmicpc.net/problem/2468
# [solution]
# 1) 최대 높이를 저장
# 2) bfs를 이용해서 각 물 높이에 대한 안전 영역 계산

# 시간 복잡도는 O(H * N^2)

# 1)을 개선하는 2가지 방법
# 1-1) 최소값도 저장함
# 1-2) HashSet에 모든 물 높이를 기록, 최소값 - 1도 기록

import sys
from collections import deque

input = sys.stdin.readline

# 입력
n = int(input())
area = [list(map(int, input().split())) for _ in range(n)]

# 최소, 최대 높이 계산 및 고유 높이 수집
max_h = max(max(row) for row in area)
min_h = min(min(row) for row in area)

unique_heights = set()
for row in area:
    unique_heights.update(row)

water_levels = [min_h - 1] + sorted(unique_heights)


# 방향 벡터
dr = (-1, 1, 0, 0)
dc = (0, 0, -1, 1)

#bfs 구현
def bfs(sr, sc, h, visited):
    q = deque([(sr, sc)])
    visited[sr][sc] = True

    while q:
        r, c = q.popleft()

        for d in range(4):
            nr, nc = r + dr[d], c + dc[d]
            if 0 <= nr < n and 0 <= nc < n:
                if not visited[nr][nc] and area[nr][nc] > h:
                    visited[nr][nc] = True
                    q.append((nr,nc))



# 안전 영역 최댓값 계산
result = 0

for water in water_levels:
    visited = [[False] * n for _ in range(n)]
    count = 0
    for r in range(n):
        for c in range(n):
            if not visited[r][c] and area[r][c] > water:
                bfs(r, c, water, visited)
                count += 1

    result = max(result, count)

# 출력
print(result)
