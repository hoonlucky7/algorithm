import sys

def solve():
    n, m = map(int, sys.stdin.readline().split())
    cards = list(map(int, sys.stdin.readline().split()))

    max_sum = 0

    def dfs(start, depth, current_sum):
        nonlocal max_sum
        if current_sum > m:
            return

        if depth == 3:
            if current_sum <= m and current_sum > max_sum:
                max_sum = current_sum
            return

        for i in range(start, n):
            dfs(i + 1, depth + 1, current_sum + cards[i])

    dfs(0, 0, 0)
    print(max_sum)

if __name__ == "__main__":
    solve()