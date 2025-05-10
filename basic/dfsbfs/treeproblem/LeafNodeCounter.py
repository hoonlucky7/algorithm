import sys
from collections import deque

input = sys.stdin.readline

# 전역 변수
adj = []
visited = []
delete_node = 0
leaf_count = 0

#dfs 구현
def dfs(node):
    global leaf_count, adj, visited, delete_node
    visited[node] = True
    has_child = False
    
    for next_node in adj[node]:
        # 삭제된 노드는 건너뛰기
        if next_node == delete_node:
            continue
        
        # 아직 방문하지 않은 노드가 있다면
        if not visited[next_node]:
            has_child = True
            dfs(next_node)
    
    # 자식 노드가 없다면 리프 노드
    if not has_child:
        leaf_count += 1

#bfs 구현
def bfs(start):
    global leaf_count, adj, visited, delete_node

    queue = deque([start])
    visited[start] = True
    while queue:
        node = queue.popleft()
        has_child = False

        for next in adj[node]:
            # 삭제된 노드는 건너뛰기
            if next == delete_node:
                continue
            if (not visited[next]):
                has_child = True
                visited[next] = True
                queue.append(next)

        # 자식 노드가 없다면 리프 노드
        if not has_child:
            leaf_count += 1

# 노드의 개수 입력
n = int(input())

# 트리 초기화 (인접 리스트)
adj = [[] for _ in range(n)]

# 부모 노드 정보 입력
parents = list(map(int, input().split()))
root_node = -1

# 트리 구성
for i in range(n):
    parent = parents[i]
    if parent == -1:
        root_node = i  # 루트 노드 기록
    else:
        adj[parent].append(i)  # 부모 노드에 자식 추가
        adj[i].append(parent)  # 양방향으로 연결 (트리 탐색용)

# 삭제할 노드 입력
delete_node = int(input())

# 방문 배열 초기화
visited = [False] * n
leaf_count = 0

# 삭제할 노드가 루트 노드인 경우, 트리가 없어지므로 리프 노드는 0개
if delete_node == root_node:
    print(0)
else:
    #dfs(root_node)
    bfs(root_node)
    print(leaf_count)