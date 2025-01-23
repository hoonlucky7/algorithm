def to_base_k(n, k):
    if n < k:
        return str(n)
    return to_base_k(n // k, k) + str(n % k)

if __name__ == "__main__":
    number = 25
    base = 3

    converted = to_base_k(number, base)
    print(f"{number} in base {base} is {converted}")
    # Output: 25 in base 3 is 221