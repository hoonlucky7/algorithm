/*
2022 KAKAO BLIND RECRUITMENT
문제 4 - 양궁대회 개선 버전

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/92342

solution 2) 2^n 알고리즘 (점수를 가져가? 말아?)

실제 구현 시 최적화 방법 완전 탐색을 최적화하려면,
해당 점수대 점수를 가져가려면 어피치보다 +1발, 아니면 0발정도로만 시도해도 충분합니다.
그리고 마지막(0점 과녁)에는 남은 화살을 몰아넣는 식으로 처리하면,
불필요한 배분(예: 이미 +1발이면 점수를 확보했는데, +2, +3, ...)을 
건너뛸 수 있어 탐색 범위를 많이 줄일 수 있습니다.
이처럼, “어피치보다 1발 더 쓰거나(점수 확보), 아예 안 쓰거나(점수 포기)”만 고려해도 
이론상 점수 면에서는 동일한 결과를 얻을 수 있습니다.

2^n으로 완전탐색 하는 모든 경우의 수
2^11 = 2048

***0점 과녁을 어떻게 처리 할지 정리***
라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 
가장 낮은 점수를 더 많이 맞힌 경우를 return
0점 과녁은 점수에 영향이 없지만, 0점 쪽에서 남은 화살을 많이 배분 해야함

 */

/*
english version
2022 KAKAO BLIND RECRUITMENT
Problem 4 - Optimized Archery Competition
Problem Link:
https://school.programmers.co.kr/learn/courses/30/lessons/92342

Solution 2) 2^n Algorithm (To take the score or not?)

Optimization Explanation:
To optimize the brute-force search, for each scoring target, you only need to consider:
- using enough arrows to beat Apeach by exactly one arrow (to secure the score), or
- not using any arrows at that target (to concede the score). 
For the last target (0 points), assign all remaining arrows. 
This way, you can skip unnecessary distributions.
By only considering "using exactly one more arrow than Apeach" or "not using any arrows", 
you can achieve the same outcome in terms of the score. 
Total number of cases in the 2^n brute-force search: 2^11 = 2048

*** Note on handling the 0-point target ***
If there are multiple ways for Ryan to win, 
return the one where Ryan scores more in the lower scoring targets. 
The 0-point target doesn't affect the score 
but should receive as many of the remaining arrows as possible.
*/

import java.util.Arrays;

public class OptimizedArcheryCompetition {
    int lastIndex = 10; // Index of the last target (0 points)
    int maxGap = 0;     // Maximum score difference achieved by Ryan so far
    int[] answer;        // Best arrow allocation for Ryan that maximizes the score gap

    /**
     * Updates the global `maxGap` and `answer` if the current `ryan` allocation
     * results in a larger score gap or the same score gap with a better allocation
     * (prioritizing more arrows in lower-scoring targets).
     *
     * @param info Apeach's arrow counts for each target (index 0 to 10).
     * @param ryan Ryan's arrow counts for each target (index 0 to 10) for the current allocation.
     */
    public void updateMaxGap(int[] info, int[] ryan) {
        int apeachSumScore = 0; // Initialize Apeach's total score
        int ryanSumScore = 0;   // Initialize Ryan's total score

        // Calculate scores for each target (0 to lastIndex)
        for (int i = 0; i <= lastIndex; i++) {
            // If neither Apeach nor Ryan shot any arrows at this target, skip to the next target
            if (info[i] == 0 && ryan[i] == 0) {
                continue;
            }
            // If Apeach shot more or equal arrows, Apeach gets the points for this target
            if (info[i] >= ryan[i]) {
                apeachSumScore += 10 - i; // Points are (10 - target index)
            }
            // If Ryan shot more arrows, Ryan gets the points for this target
            if (info[i] < ryan[i]) {
                ryanSumScore += 10 - i;   // Points are (10 - target index)
            }
        }

        int scoreGap = ryanSumScore - apeachSumScore; // Calculate the score difference between Ryan and Apeach
        // If the score gap is not positive (Ryan didn't win), no need to update maxGap
        if (scoreGap <= 0) {
            return;
        }

        // If the current score gap is greater than the current maxGap, update maxGap and answer
        if (maxGap < scoreGap) {
            maxGap = scoreGap;
            System.arraycopy(ryan, 0, answer, 0, ryan.length); // Copy ryan's allocation to answer
            return;
        }
        // If the current score gap is equal to the current maxGap,
        // check if the current allocation is better based on lower-scoring targets
        if (maxGap == scoreGap) {
            // Iterate from the lowest scoring target (index 10) to the highest (index 0)
            for (int i = lastIndex; i >= 0; i--) {
                // If the existing answer has more arrows in a lower-scoring target, 
                // it's still better
                if (answer[i] > ryan[i]) {
                    return; // No update needed, existing answer is preferred
                }
                // If the current allocation has more arrows in a lower-scoring target, update answer
                if (answer[i] < ryan[i]) {
                    System.arraycopy(ryan, 0, answer, 0, ryan.length); // Copy ryan's allocation to answer
                    return; // Update done, exit the function
                }
                // If arrow counts are the same for this target, continue to the next lower-scoring target
            }
        }
    }

    public void dfs(int i, int[] info, int[] ryan, int remainingArrows) {
        if (i == lastIndex + 1) {
            updateMaxGap(info, ryan);
            return;
        }

        // Case: Do not attempt to win the (10 - i) point target
        ryan[i] = 0;
        dfs(i + 1, info, ryan, remainingArrows);

        // Case: Attempt to win the (10 - i) point target by shooting one more arrow than Apeach
        if (i == lastIndex) {
            // For the 0-point target:
            // If multiple allocations yield the same maximum score gap,
            // return the allocation with more arrows in the lower scoring targets.
            // Although the 0-point target doesn't affect the score,
            // assign all remaining arrows to this target.
            ryan[i] = remainingArrows;
        } else {
            ryan[i] = info[i] + 1;
        }
        if (remainingArrows - ryan[i] >= 0) {
            dfs(i + 1, info, ryan, remainingArrows - ryan[i]);
        }
    }

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        answer = new int[ryan.length];
        maxGap = 0;
        dfs(0, info, ryan, n);
        if (maxGap != 0) {
            return answer;
        }
        return new int[] { -1 };
    }

    public static void main(String[] args) {

        OptimizedArcheryCompetition archeryCompetition = new OptimizedArcheryCompetition();
        int[] info1 = new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };
        System.out.println(Arrays.toString(archeryCompetition.solution(5, info1)));

        int[] info2 = new int[] { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        System.out.println(Arrays.toString(archeryCompetition.solution(1, info2)));

        int[] info3 = new int[] { 0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1 };
        System.out.println(Arrays.toString(archeryCompetition.solution(9, info3)));

        int[] info4 = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3 };
        System.out.println(Arrays.toString(archeryCompetition.solution(10, info4)));

    }
}