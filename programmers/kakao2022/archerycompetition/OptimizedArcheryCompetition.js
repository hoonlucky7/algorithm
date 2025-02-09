let lastIndex = 10;  // Index of the last target (0-point target)
let maxGap = 0;      // Maximum score gap achieved by Ryan
let answer = [];     // Best arrow allocation for Ryan

// Calculates scores and updates maxGap if current allocation is better
function updateMaxGap(info, ryan) {
    let apeachSumScore = 0;
    let ryanSumScore = 0;

    // Calculate scores for each player
    for (let i = 0; i <= lastIndex; i++) {
        if (info[i] === 0 && ryan[i] === 0) {
            continue; // Skip if both players didn't shoot at this target
        }
        if (info[i] >= ryan[i]) {
            apeachSumScore += 10 - i; // Apeach gets score for this target
        } else {
            ryanSumScore += 10 - i;   // Ryan gets score for this target
        }
    }

    let scoreGap = ryanSumScore - apeachSumScore; // Calculate score difference
    if (scoreGap <= 0) {
        return; // Not a winning case for Ryan, no update needed
    }

    // Update maxGap and answer if current allocation is better
    if (maxGap < scoreGap) {
        maxGap = scoreGap;
        answer = [...ryan]; // Found a better maxGap, update answer
    } else if (maxGap === scoreGap) {
        // If same maxGap, prefer allocation with more arrows in lower scores
        for (let i = lastIndex; i >= 0; i--) {
            if (answer[i] > ryan[i]) {
                return; // Existing answer is better (more arrows in lower scores)
            }
            if (answer[i] < ryan[i]) {
                answer = [...ryan]; // Current answer is better (more arrows in lower scores)
                return;
            }
        }
    }
}

// Depth First Search to explore all possible arrow allocations
function dfs(i, info, ryan, remainingArrows) {
    if (i === lastIndex + 1) {
        updateMaxGap(info, ryan); // Base case: all targets considered, update maxGap
        return;
    }

    // Case 1: Don't try to win points at target 'i'
    ryan[i] = 0;
    dfs(i + 1, info, ryan, remainingArrows); // Explore next target

    // Case 2: Try to win points at target 'i'
    if (i === lastIndex) {
        ryan[i] = remainingArrows; // For 0-point target, allocate all remaining arrows
    } else {
        ryan[i] = info[i] + 1;   // Shoot 1 more arrow than Apeach to win points
    }

    if (remainingArrows - ryan[i] >= 0) { // Check if enough arrows are available
        dfs(i + 1, info, ryan, remainingArrows - ryan[i]); // Explore next target with remaining arrows
    }
}

// Main function to find the best arrow allocation for Ryan
function solution(n, info) {
    answer = []; // Initialize answer array
    maxGap = 0;  // Initialize maxGap
    let ryan = new Array(11).fill(0); // Initialize Ryan's arrow allocation array

    dfs(0, info, ryan, n); // Start Depth First Search from target 0

    if (maxGap !== 0) {
        return answer; // Return best allocation if Ryan can win
    }
    return [-1]; // Return [-1] if Ryan cannot win
}


// Example usage:
let info1 = [2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0];
let result1 = solution(5, info1);
console.log(`Result 1: ${result1}`);

let info2 = [1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
let result2 = solution(1, info2);
console.log(`Result 2: ${result2}`);

let info3 = [0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1];
let result3 = solution(9, info3);
console.log(`Result 3: ${result3}`);

let info4 = [0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3];
let result4 = solution(10, info4);
console.log(`Result 4: ${result4}`);