import sys
def dfs(start, depth, comb):
    if depth == M:  # If the combination is complete
        result.append(" ".join(map(str, comb)))
        return
    for i in range(start, N + 1):
        comb.append(i)             # Add number to the current combination
        dfs(i + 1, depth + 1, comb)  # Recursively generate the next depth
        comb.pop()                 # Backtrack: remove the last element

if __name__ == '__main__':
    input = sys.stdin.readline
    N, M = map(int, input().split())  # Read N and M from input
    
    result = []  # List to store all the output combinations as strings
    dfs(1, 0, [])
    print("\n".join(result))