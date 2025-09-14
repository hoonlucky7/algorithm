# 가장 먼 집부터 역순으로 순회하며 그리디 전략을 사용합니다.

def solution(cap, n, deliveries, pickups):
    
    answer = 0
    deliver = 0  # 현재 처리해야 할 총 배달량
    pickup = 0   # 현재 처리해야 할 총 수거량

    # 가장 먼 집부터 처리 (Greedy)
    for i in range(n - 1, -1, -1):
        # 현재 집의 배달/수거량을 누적
        deliver += deliveries[i]
        pickup += pickups[i]

        # 처리해야 할 배달 또는 수거가 하나라도 남아있는 동안 반복
        # 이는 현재 위치(i)까지 최소 한 번은 와야 함을 의미
        while deliver > 0 or pickup > 0:
            # 트럭 용량(cap)만큼 배달 및 수거량을 처리했다고 가정
            deliver -= cap
            pickup -= cap
            
            # 현재 위치(i)까지의 왕복 거리를 더함 (거리 = 인덱스 + 1)
            answer += (i + 1) * 2
            
    return answer

# 첫 번째 테스트 케이스
result1 = solution(4, 5, [1, 0, 3, 1, 2], [0, 3, 0, 4, 0])
print(result1)  # 예상 출력: 16

# 두 번째 테스트 케이스
result2 = solution(2, 7, [1, 0, 2, 0, 1, 0, 2], [0, 2, 0, 1, 0, 2, 0])
print(result2)  # 예상 출력: 30