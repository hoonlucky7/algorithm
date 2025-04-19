from collections import deque

def find_sister(n, k):
    # 수빈이와 동생의 위치가 같으면 시간이 필요 없음
    if n == k :
        return 0
    # 위치의 최대 범위 설정
    max = 100001
    # 방문 여부를 체크하는 배열
    visited = [False] * max
    # 큐에 (위치, 시간)을 저장
    queue = deque([(n, 0)])
    visited[n] = True

    # BFS 탐색
    while queue:
        pos, time = queue.popleft()
        for next_p in [pos - 1, pos + 1, pos * 2]:    
            # 이동 위치가 범위 내이고 방문하지 않은 경우
            if 0 <= next_p < max and not visited[next_p]:
                # 동생의 위치에 도달하면 시간 반환
                if next_p == k:
                    return time + 1
                # 방문 표시 후 큐에 추가
                visited[next_p] = True
                queue.append((next_p, time + 1))

# 입력 받기
n, k = map(int, input().split())
# 결과 출력
print(find_sister(n, k))
# 5 17