function solution(n, info) {
    const lastIndex = 10;                     // Last target index (from 0 to 10)
    let maxGap = 0;                           // Maximum score gap found so far
    let answer = new Array(lastIndex + 1).fill(0);  // Best allocation for Ryan's arrows
  
    // Updates the maximum gap and answer based on the current allocation
    function updateMaxGap(ryan) {
      let apeachSumScore = 0;
      let ryanSumScore = 0;
  
      // Calculate scores for each target
      for (let i = 0; i <= lastIndex; i++) {
        // Skip if both players didn't shoot any arrows on this target
        if (info[i] === 0 && ryan[i] === 0) continue;
        // If Apeach scores equal to or more than Ryan, add points to Apeach
        if (info[i] >= ryan[i]) {
          apeachSumScore += (10 - i);
        }
        // If Ryan scores more, add points to Ryan
        if (info[i] < ryan[i]) {
          ryanSumScore += (10 - i);
        }
      }
      const scoreGap = ryanSumScore - apeachSumScore;
      // If Ryan does not win, do nothing
      if (scoreGap <= 0) return;
  
      // If the current gap is greater than the maximum found so far, update maxGap and answer
      if (scoreGap > maxGap) {
        maxGap = scoreGap;
        answer = ryan.slice(); // Create a copy of the array
      } else if (scoreGap === maxGap) {
        // If the gap is the same, choose the allocation with more arrows in lower score zones
        for (let i = lastIndex; i >= 0; i--) {
          if (ryan[i] > answer[i]) {
            answer = ryan.slice();
            break;
          } else if (ryan[i] < answer[i]) {
            break;
          }
        }
      }
    }
  
    // Depth-first search to try all possible arrow allocations for Ryan
    function dfs(i, ryan, remaining) {
      // If all targets have been assigned, update the maximum gap
      if (i === lastIndex + 1) {
        updateMaxGap(ryan);
        return;
      }
  
      // Try allocating from 0 to remaining arrows for the current target (score: 10 - i)
      for (let count = 0; count <= remaining; count++) {
        ryan[i] = count;
        dfs(i + 1, ryan, remaining - count);
        ryan[i] = 0;  // Backtracking
      }
    }
  
    const ryan = new Array(lastIndex + 1).fill(0);
    dfs(0, ryan, n);
    return maxGap !== 0 ? answer : [-1];
  }
  
  // Test cases
  console.log(solution(5, [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0]));
  console.log(solution(1, [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]));
  console.log(solution(9, [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1]));
  console.log(solution(10, [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3]));
  