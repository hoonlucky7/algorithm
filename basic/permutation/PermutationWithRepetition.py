import sys
input = sys.stdin.readline

# Read N (range of numbers) and M (length of sequence)
N, M = map(int, input().split())
sequence = [0] * M      # List to store the current sequence
result_lines = []       # List to accumulate the output lines

def dfs(depth):
    if depth == M:
        # Append the current sequence (converted to a space-separated string) to the result list
        result_lines.append(" ".join(map(str, sequence)))
        return
    for i in range(1, N + 1):
        sequence[depth] = i
        dfs(depth + 1)
        sequence[depth] = 0  # Reset the value

dfs(0)
print("\n".join(result_lines))
