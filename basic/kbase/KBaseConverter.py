def to_k_base(n, k):
    k_base = ""
    while n > 0:
        k_base += str(n % k)  # 나머지를 문자열로 추가
        n = n // k
    return k_base[::-1]  # 결과 문자열 뒤집기

if __name__ == "__main__":
    n = 437674
    k = 3
    print(f"Number {n} in base {k} is: {to_k_base(n, k)}")
