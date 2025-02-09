lastIndex = 10  # Index of the last target (0-point target)
maxGap = 0      # Maximum score gap achieved by Ryan
answer = []     # Best arrow allocation for Ryan

# Calculates scores and updates maxGap if current allocation is better
def updateMaxGap(info, ryan):
    global maxGap, answer
    apeachSumScore = 0
    ryanSumScore = 0

    # Calculate scores for each player based on arrow allocation
    for i in range(lastIndex + 1):
        if info[i] == 0 and ryan[i] == 0:
            continue  # Skip if both players didn't shoot at this target
        if info[i] >= ryan[i]:
            apeachSumScore += 10 - i  # Apeach gets score for this target
        else:
            ryanSumScore += 10 - i    # Ryan gets score for this target

    scoreGap = ryanSumScore - apeachSumScore  # Calculate the score difference
    if scoreGap <= 0:
        return  # If Ryan didn't win or tie, no need to update maxGap

    # Update maxGap and answer if the current allocation is better
    if maxGap < scoreGap:
        maxGap = scoreGap
        answer = ryan[:]  # Found a better maxGap, update answer with current allocation (copy list)
    elif maxGap == scoreGap:
        # If the maxGap is the same, prioritize allocations with more arrows in lower-scoring targets (problem requirement)
        for i in range(lastIndex, -1, -1):
            if answer[i] > ryan[i]:
                return  # Existing answer is better (more arrows in lower scores), no update needed
            if answer[i] < ryan[i]:
                answer = ryan[:]  # Current answer is better (more arrows in lower scores), update answer
                return

# Depth First Search function to explore all possible arrow allocations
def dfs(i, info, ryan, remainingArrows):
    global answer
    if i == lastIndex + 1:
        updateMaxGap(info, ryan) # Base case: all targets considered, update maxGap if needed
        return

    # Case 1: Don't try to win points at target 'i' (allocate 0 arrows to this target for Ryan)
    ryan[i] = 0
    dfs(i + 1, info, ryan, remainingArrows) # Recursively explore next target

    # Case 2: Try to win points at target 'i' (allocate arrows to win or all remaining for 0-point)
    if i == lastIndex:
        ryan[i] = remainingArrows  # For 0-point target, allocate all remaining arrows
    else:
        ryan[i] = info[i] + 1    # For other targets, allocate 1 more arrow than Apeach to win points

    if remainingArrows - ryan[i] >= 0: # Check if enough arrows are available for this allocation
        dfs(i + 1, info, ryan, remainingArrows - ryan[i]) # Recursively explore next target with remaining arrows

# Main function to solve the archery competition problem
def solution(n, info):
    global maxGap, answer
    ryan = [0] * 11  # Initialize Ryan's arrow allocation array with 0s
    answer = []      # Initialize answer array to empty
    maxGap = 0       # Initialize maxGap to 0
    dfs(0, info, ryan, n) # Start Depth First Search from target 0

    if maxGap != 0:
        return answer  # Return the best arrow allocation if Ryan can win
    return [-1]     # Return [-1] if Ryan cannot win


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