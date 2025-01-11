import math

def sieve(n):
    is_prime = [True] * (n + 1)
    is_prime[0] = False  # 0은 소수가 아님
    is_prime[1] = False  # 1은 소수가 아님

    for i in range(2, int(math.sqrt(n)) + 1):
        if is_prime[i]:
            for j in range(i * i, n + 1, i):
                is_prime[j] = False

    return is_prime

# 테스트 케이스 실행
def run_tests():
    test_cases = [100]

    for idx, n in enumerate(test_cases):
        print(f"Test Case {idx + 1}: n={n}")
        primes = sieve(n)
        prime_numbers = [str(i) for i in range(2, n + 1) if primes[i]]
        print(" ".join(prime_numbers))  # 소수를 공백으로 구분해 한 줄로 출력

if __name__ == "__main__":
    run_tests()
