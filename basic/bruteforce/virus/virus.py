import sys

def dfs(graph, visited, node):
    visited[node] = True
    count = 0
    for neighbor in graph[node]:
        if not visited[neighbor]:
            count += 1 + dfs(graph, visited, neighbor)
    return count

def solve():
    n = int(sys.stdin.readline())
    m = int(sys.stdin.readline())
    graph = [[] for _ in range(n + 1)]
    for _ in range(m):
        u, v = map(int, sys.stdin.readline().split())
        graph[u].append(v)
        graph[v].append(u)

    visited = [False] * (n + 1)
    infected_count = dfs(graph, visited, 1)
    print(infected_count)

if __name__ == "__main__":
    solve()