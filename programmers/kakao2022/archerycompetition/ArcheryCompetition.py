def solution(n, info):
    last_index = 10
    max_gap = 0
    answer = [0] * (last_index + 1)

    def update_max_gap(ryan):
        nonlocal max_gap, answer
        apeach_sum_score = 0
        ryan_sum_score = 0

        for i in range(last_index + 1):
            # Skip if both players didn't hit any arrows on this target
            if info[i] == 0 and ryan[i] == 0:
                continue
            if info[i] >= ryan[i]:
                apeach_sum_score += 10 - i
            if info[i] < ryan[i]:
                ryan_sum_score += 10 - i

        score_gap = ryan_sum_score - apeach_sum_score
        if score_gap <= 0:
            return

        # Update answer if the current score gap is greater than the maximum found so far
        if score_gap > max_gap:
            max_gap = score_gap
            answer = ryan[:]  # Create a copy of the list
        # If the score gap is equal, choose the allocation with more arrows in the lower score zones
        elif score_gap == max_gap:
            for i in range(last_index, -1, -1):
                if answer[i] > ryan[i]:
                    return
                if answer[i] < ryan[i]:
                    answer = ryan[:]  # Create a copy of the list
                    return

    def dfs(i, ryan, remaining):
        # When allocations for all targets are complete, update the score gap
        if i == last_index + 1:
            update_max_gap(ryan)
            return

        # For the current target (score: 10 - i), try allocating from 0 to remaining arrows
        for count in range(remaining + 1):
            ryan[i] = count
            dfs(i + 1, ryan, remaining - count)
            ryan[i] = 0  # Backtracking

    ryan = [0] * (last_index + 1)
    dfs(0, ryan, n)
    return answer if max_gap != 0 else [-1]


if __name__ == "__main__":
    info1 = [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]
    print(solution(5, info1))  # Example output

    info2 = [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    print(solution(1, info2))

    info3 = [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1]
    print(solution(9, info3))

    info4 = [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]
    print(solution(10, info4))
