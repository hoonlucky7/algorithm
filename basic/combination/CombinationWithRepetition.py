# The key point of combination with repetition is to allow duplicate selections 
# by recursively calling starting from the current index (i.e., maintaining the start value),
# and to prevent duplicate combinations by keeping the sequence in non-decreasing order.

def dfs(start, depth, comb):
    if depth == M:  # If the sequence reaches length M, print the combination
        print(" ".join(map(str, comb)))
        return
    for i in range(start, N + 1):  # Ensure non-decreasing order
        comb.append(i)           # Add number to the current combination
        dfs(i, depth + 1, comb)    # Recursive call with same 'i' for duplicates
        comb.pop()               # Backtrack

import sys
input = sys.stdin.readline
N, M = map(int, input().split())  # Read N and M from input
dfs(1, 0, [])
