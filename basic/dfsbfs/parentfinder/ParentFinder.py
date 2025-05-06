import sys
from collections import deque

sys.setrecursionlimit(10 ** 8)
input = sys.stdin.readline

# 전역 변수
n = 0
adj = []
parent = []
visited = []

#dfs 구현
def dfs(node):
    global visited, parent, adj
    visited[node] = True

    for next_node in adj[node]:
        if not visited[next_node]:
            parent[next_node] = node
            dfs(next_node)
#bfs 구현
def bfs(start):
    global visited, parent, adj

    queue = deque([start])
    visited[start] = True
    while queue:
        node = queue.popleft()

        for next in adj[node]:
            if (not visited[next]):
                parent[next] = node
                visited[next] = True
                queue.append(next)

# 입력 처리
n = int(input())

# 인접리스트를 초기화
adj = [[] for _ in range(n + 1)]
for _ in range(n - 1):
    a, b = map(int, input().split())
    adj[a].append(b)
    adj[b].append(a)


# 전역 변수 초기화
parent = [0] * (n + 1)
visited = [False] * (n + 1)

# dfs 실행
#dfs(1)

# bfs 실행
bfs(1)

# 결과 출력 (최적화된 출력)
output = []
for i in range(2, n + 1):
    output.append(str(parent[i]))
print('\n'.join(output) + '\n')