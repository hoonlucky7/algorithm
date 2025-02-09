let lastIndex = 10;
let maxGap = 0;
let answer = [];

function updateMaxGap(info, ryan) {
    let apeachSumScore = 0;
    let ryanSumScore = 0;
    for (let i = 0; i <= lastIndex; i++) {
        if (info[i] === 0 && ryan[i] === 0) {
            continue;
        }
        if (info[i] >= ryan[i]) {
            apeachSumScore += 10 - i;
        } else {
            ryanSumScore += 10 - i;
        }
    }
    let scoreGap = ryanSumScore - apeachSumScore;
    if (scoreGap <= 0) {
        return;
    }

    if (maxGap < scoreGap) {
        maxGap = scoreGap;
        answer = [...ryan];
    } else if (maxGap === scoreGap) {
        for (let i = lastIndex; i >= 0; i--) {
            if (answer[i] > ryan[i]) {
                return;
            }
            if (answer[i] < ryan[i]) {
                answer = [...ryan];
                return;
            }
        }
    }
}

function dfs(i, info, ryan, remainingArrows) {
    if (i === lastIndex + 1) {
        updateMaxGap(info, ryan);
        return;
    }

    ryan[i] = 0;
    dfs(i + 1, info, ryan, remainingArrows);

    if (i === lastIndex) {
        ryan[i] = remainingArrows;
    } else {
        ryan[i] = info[i] + 1;
    }

    if (remainingArrows - ryan[i] >= 0) {
        dfs(i + 1, info, ryan, remainingArrows - ryan[i]);
    }
}

function solution(n, info) {
    answer = [];
    maxGap = 0;
    let ryan = new Array(11).fill(0);
    dfs(0, info, ryan, n);

    if (maxGap !== 0) {
        return answer;
    }
    return [-1];
}


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