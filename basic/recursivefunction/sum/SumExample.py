def sum_recursive(n):
    if n == 1:
        return 1
    return n + sum_recursive(n - 1)

if __name__ == "__main__":
    print(sum_recursive(3)) # Output: 6, 3 + 2 + 1 = 6