import sys

# Read input values: N (number of targets), M (total count to be allocated)
N, M = map(int, sys.stdin.readline().split())
sequenceCounts = [0] * (N + 2)  # Array to store allocation counts for each target; index 1 to N
output_lines = []  # List to store each valid sequence as a string

# i: current target index, n: remaining count
def dfs(i, n):
    if i == N + 1:  # All targets have been processed
        if n == 0:  # Process only valid combinations where the remaining count is 0
            line_parts = []
            for num in range(1, N + 1):
                line_parts.extend([str(num)] * sequenceCounts[num])
            output_lines.append(" ".join(line_parts))
        return
    # Distribute counts from n down to 0 for the current target i.
    # (Allocating from n down to 0 results in reverse order output)
    for count in range(n, -1, -1):
        sequenceCounts[i] = count
        dfs(i + 1, n - count)
        sequenceCounts[i] = 0  # Backtracking: restore previous state

dfs(1, M)
# Print all stored sequences at once
sys.stdout.write("\n".join(output_lines))
