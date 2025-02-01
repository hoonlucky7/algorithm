import sys
sys.setrecursionlimit(10**6)

# Read input values for N and M
N, M = map(int, sys.stdin.readline().split())

sequence = [0] * M        # List to store the current sequence
visited = [False] * (N+1) # List to track if a number is used (1-indexed)
result_lines = []         # List to store the resulting sequences as strings

def dfs(depth):
    if depth == M:
        # Append the current sequence as a space-separated string
        result_lines.append(" ".join(map(str, sequence)))
        return
    for i in range(1, N+1):
        if not visited[i]:
            visited[i] = True
            sequence[depth] = i
            dfs(depth + 1)
            visited[i] = False  # Backtracking

dfs(0)
# Print all the results at once
sys.stdout.write("\n".join(result_lines))
