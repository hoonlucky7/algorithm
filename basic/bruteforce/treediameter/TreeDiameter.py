import sys
sys.setrecursionlimit(20000)  # 재귀 깊이 제한 적당히 증가

# 글로벌 변수 선언
adj = []
visited = []
max_dist = 0
farthest_node = 1

def dfs(node, dist):
    global max_dist, farthest_node
    visited[node] = True
    if dist > max_dist:
        max_dist = dist
        farthest_node = node
    for next_node, weight in adj[node]:
        if not visited[next_node]:
            dfs(next_node, dist + weight)


n = int(input())

# 인접 리스트와 방문 배열 초기화
adj = [[] for _ in range(n + 1)]
visited = [False] * (n + 1)

# 간선 정보 입력 (무방향 트리)
for _ in range(n - 1):
    parent, child, weight = map(int, input().split())
    adj[parent].append((child, weight))
    adj[child].append((parent, weight))

# 첫 번째 DFS: 임의의 노드(1)에서 가장 먼 노드 찾기
max_dist = 0
farthest_node = 1
dfs(1, 0)

# 두 번째 DFS: 가장 먼 노드에서 최대 거리 찾기
max_dist = 0
visited = [False] * (n + 1)  # 방문 배열 재초기화
dfs(farthest_node, 0)

print(max_dist)