lastIndex = 10
maxGap = 0
answer = []

def updateMaxGap(info, ryan):
    global maxGap, answer
    apeachSumScore = 0
    ryanSumScore = 0
    for i in range(lastIndex + 1):
        if info[i] == 0 and ryan[i] == 0:
            continue
        if info[i] >= ryan[i]:
            apeachSumScore += 10 - i
        else:
            ryanSumScore += 10 - i
    scoreGap = ryanSumScore - apeachSumScore
    if scoreGap <= 0:
        return

    if maxGap < scoreGap:
        maxGap = scoreGap
        answer = ryan[:]
    elif maxGap == scoreGap:
        for i in range(lastIndex, -1, -1):
            if answer[i] > ryan[i]:
                return
            if answer[i] < ryan[i]:
                answer = ryan[:]
                return

def dfs(i, info, ryan, remainingArrows):
    global answer
    if i == lastIndex + 1:
        updateMaxGap(info, ryan)
        return

    ryan[i] = 0
    dfs(i + 1, info, ryan, remainingArrows)

    if i == lastIndex:
        ryan[i] = remainingArrows
    else:
        ryan[i] = info[i] + 1

    if remainingArrows - ryan[i] >= 0:
        dfs(i + 1, info, ryan, remainingArrows - ryan[i])

def solution(n, info):
    global maxGap, answer
    ryan = [0] * 11
    answer = []
    maxGap = 0
    dfs(0, info, ryan, n)
    if maxGap != 0:
        return answer
    return [-1]


info1 = [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]
result1 = solution(5, info1)
print(f"Result 1: {result1}")

info2 = [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
result2 = solution(1, info2)
print(f"Result 2: {result2}")

info3 = [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1]
result3 = solution(9, info3)
print(f"Result 3: {result3}")

info4 = [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]
result4 = solution(10, info4)
print(f"Result 4: {result4}")