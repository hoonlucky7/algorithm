def factorial(n):
    if n == 1:
        return 1
    return n * factorial(n - 1)

def fac3(n):
    return n * fac2(n - 1)

def fac2(n):
    return n * fac1(n - 1)

def fac1(n):
    return 1

if __name__ == "__main__":
    print(fac3(3)) # Output: 6
    print(factorial(3)) # Output: 6