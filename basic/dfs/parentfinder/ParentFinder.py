import sys
sys.setrecursionlimit(10 ** 8)
input = sys.stdin.readline

# 전역 변수
N = 0
adj = []
parent = []
visited = []

def dfs(node):
    global visited, parent, adj
    visited[node] = True
    for next_node in adj[node]:
        if not visited[next_node]:
            parent[next_node] = node
            dfs(next_node)

# 입력 처리
N = int(input())
adj = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    a, b = map(int, input().split())
    adj[a].append(b)
    adj[b].append(a)

# 전역 변수 초기화
parent = [0] * (N + 1)
visited = [False] * (N + 1)

# DFS 실행
dfs(1)

# 결과 출력 (최적화된 출력)
output = []
for i in range(2, N + 1):
    output.append(str(parent[i]))
print('\n'.join(output) + '\n')