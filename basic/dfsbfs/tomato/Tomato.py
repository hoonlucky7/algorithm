#  problem link : https://www.acmicpc.net/problem/7569
#  [solution]

#  문제 요약
# 창고에는 가로 M × 세로 N × 높이 H인 박스들이 층으로 쌓임
# 각 칸에는 토마토가 있는데,
# 1: 익은 토마토
# 0: 안 익은 토마토
# -1: 토마토 없음
# 하루가 지나면 익은 토마토의 상하좌우, 위층, 아래층에 있는 토마토가 익음
# 모든 토마토가 익는 데 며칠 걸리는지 구하는 게 목표

# 알고리즘 순서 
# 1. 익은 토마토를 먼저 찾기
# 3차원 공간을 돌면서 1인 토마토를 queue에 넣음
# 이걸 BFS의 출발점으로 생각

# 2. BFS로 전염시키기
# 큐에서 하나씩 꺼내서, 그 토마토 주변 6방향을 확인
# 주변에 0 (안 익은 토마토)이 있으면, 
#  그 칸을 1로 바꿔서 익혔다고 표시
# 그리고 days 배열에 며칠째 익은 건지 기록
# 그 칸도 큐에 넣어서, 다음에 또 주변을 익히게 함

# 3. BFS가 끝나면 모든 전염이 끝난 상태!

# 결과 계산
# 4. 아직 안 익은 게 있으면 → -1 출력
# BFS가 끝났는데도 0이 남아 있다면,
# 아무리 기다려도 못 익는 토마토가 있다는 뜻
# 이럴 땐 정답은 -1

# 5. 최대 날짜 출력
# 모든 칸을 돌면서 days[h][r][c] 값 중 가장 큰 값을 찾음
# 그게 모든 토마토가 다 익기까지 걸린 시간

# 왜 BFS를 쓰나요?
# BFS는 퍼져 나가는 것을 처리할 때 아주 좋음
# 익은 토마토가 동시에 여러 방향으로 퍼지니까, 
# 하루 단위로 순서대로 퍼뜨릴 수 있음

from collections import deque
import sys

input = sys.stdin.readline

# 방향 설정 (위, 아래, 왼쪽, 오른쪽, h - 1, h + 1)
dr = [-1, 1, 0, 0, 0, 0]
dc = [0, 0, -1, 1, 0, 0]
dh = [0, 0, 0, 0, -1, 1]

# 입력
M, N, H = map(int, input().split())
boxes = [[[0] * M for _ in range(N)] for _ in range(H)]
days = [[[0] * M for _ in range(N)] for _ in range(H)]

queue = deque()

for h in range(H):
    for r in range(N):
        row = list(map(int, input().split()))
        for c in range(M):
            boxes[h][r][c] = row[c]
            if row[c] == 1:
                queue.append((h, r, c))  # 익은 토마토 위치 저장

# BFS 함수
def bfs():
    while queue:
        ch, cr, cc = queue.popleft()
        for i in range(6):
            nh = ch + dh[i]
            nr = cr + dr[i]
            nc = cc + dc[i]
            if 0 <= nh < H and 0 <= nr < N and 0 <= nc < M:
                if boxes[nh][nr][nc] == 0:
                    boxes[nh][nr][nc] = 1
                    days[nh][nr][nc] = days[ch][cr][cc] + 1
                    queue.append((nh, nr, nc))

bfs()

# 결과 계산
max_day = 0
for h in range(H):
    for r in range(N):
        for c in range(M):
            if boxes[h][r][c] == 0:  # 익지 못한 토마토가 있음
                print(-1)
                sys.exit(0)
            max_day = max(max_day, days[h][r][c])

print(max_day)
