import sys

def dfs(start, depth, comb):
    if depth == M:  # When the sequence reaches length M, add the combination to the output list
        output.append(" ".join(map(str, comb)))
        return
    for i in range(start, N + 1):  # Ensure non-decreasing order
        comb.append(i)           # Add number to the current combination
        dfs(i, depth + 1, comb)    # Recursive call with the same 'i' to allow duplicates
        comb.pop()               # Backtrack: remove the last element

input = sys.stdin.readline
N, M = map(int, input().split())  # Read N and M from input
output = []  # List to accumulate all combinations

dfs(1, 0, [])
print("\n".join(output))  # Print all combinations at once
