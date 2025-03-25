from collections import deque
import sys

def dfs(node):
    visited[node] = True
    print(node, end=" ")

    for next in graph[node]:
        if not visited[next]:
            dfs(next)

def bfs(start):
    queue = deque([start])
    visited = [False] * (N + 1)
    visited[start] = True

    while queue:
        node = queue.popleft()
        print(node, end=" ")

        for next in graph[node]:
            if not visited[next]:
                visited[next] = True
                queue.append(next)

N, M, V = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(N + 1)]

for _ in range(M):
    a, b = map(int, sys.stdin.readline().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1, N + 1):
    graph[i].sort()

visited = [False] * (N + 1)
dfs(V)
print()
bfs(V)
print()
