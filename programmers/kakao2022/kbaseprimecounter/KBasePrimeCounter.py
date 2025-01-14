def solution(n, k):
    answer = -1
    converted = convert_to_k_base(n, k)

    # 0을 기준으로 문자열 분할
    numbers = converted.split("0")

    count = 0
    for num_str in numbers:
        if num_str:  # 빈 문자열 제외
            num = int(num_str)  # 정수로 변환
            if is_prime(num):
                count += 1

    return count

def convert_to_k_base(n, k):
    k_base = ""
    while n > 0:
        k_base += str(n % k)  # 나머지를 추가
        n //= k               # 몫을 업데이트
    return k_base[::-1]  # 결과를 뒤집음

def is_prime(n):
    # 1은 소수가 아님
    if n <= 1:
        return False

    # 2는 소수
    if n == 2:
        return True

    # 2부터 제곱근까지 나누어 떨어지는지 확인
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False

    return True