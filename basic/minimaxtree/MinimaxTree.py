import sys

def dfs(current_node, parent_node, depth):
    if node_values[current_node] >= 0:
        return node_values[current_node]
    
    if depth % 2 == 0:
        best_value = 0
        for child in graph[current_node]:
            if child != parent_node:
                best_value = max(best_value, dfs(child, current_node, depth + 1))
        node_values[current_node] = best_value
        return best_value
    else:
        best_value = float('inf')
        for child in graph[current_node]:
            if child != parent_node:
                best_value = min(best_value, dfs(child, current_node, depth + 1))
        node_values[current_node] = best_value
        return best_value

# For faster input
input = sys.stdin.readline

# Read input
N, R = map(int, input().split())
node_values = [-1] * (N + 1)
graph = [[] for _ in range(N + 1)]

for _ in range(N - 1):
    u, v = map(int, input().split())
    graph[u].append(v)
    graph[v].append(u)

L = int(input())

for _ in range(L):
    leaf_id, leaf_value = map(int, input().split())
    node_values[leaf_id] = leaf_value

dfs(R, 0, 0)

Q = int(input())

for _ in range(Q):
    q = int(input())
    print(node_values[q])