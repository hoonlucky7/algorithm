def dfs(start, depth, comb):
    if depth == M:  # If the combination is complete
        print(" ".join(map(str, comb)))
        return
    for i in range(start, N + 1):
        comb.append(i)            # Add number to the current combination
        dfs(i + 1, depth + 1, comb) # Recursively generate the next depth
        comb.pop()                # Backtrack: remove the last element

if __name__ == '__main__':
    import sys
    input = sys.stdin.readline
    N, M = map(int, input().split())  # Read N and M from input
    dfs(1, 0, [])
