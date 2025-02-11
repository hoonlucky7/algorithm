/*
2022 KAKAO BLIND RECRUITMENT
문제 4 - 양궁대회

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/92342

[문제 요약]

문제 요약
목표: 어피치를 가장 큰 점수 차이로 이기기 위해 라이언이 화살을 쏠 과녁 점수를 결정해야 합니다.

주어진 조건:

과녁 점수 계산:
각 점수(k점, k=10~0)에 대해 더 많은 화살을 쏜 선수가 해당 점수를 획득.
어피치와 라이언이 동일한 화살 수를 쐈다면 어피치가 해당 점수를 가져감.
둘 다 화살을 쏘지 않았다면 점수 없음.
최종 점수 합이 높은 선수가 승리.
라이언이 가장 큰 점수 차이로 우승할 수 있는 화살 분배를 찾아야 함.
여러 가지 방법이 있을 경우, 낮은 점수를 더 많이 맞힌 경우를 선택.
입력:

n: 라이언이 사용할 수 있는 화살의 수 (1 ≤ n ≤ 10)
info: 어피치가 10점~0점에 맞힌 화살 수를 나타내는 배열 (길이 11, 합은 n)
출력:

라이언의 화살 분배를 나타내는 배열 (길이 11, 합은 n) 또는 [-1] (우승 불가 시)


[Solution]

solution 1) 완전탐색 (중복 조합) 
nHr = n-1+rCr
11H10 = 20C10
20! = 2,432,902,008,176,640,000
10! = 3,628,800

중복조합으로 완전탐색 하는 모든 경우의 수 = 184,756

10 ~ 0 까지의 점수중에 중복으로 화살 n개를 맞추는 경우의 수
화살이 3개면 
10 10 10
10 10 9
10 10 8
10 9 9 
...

 */

/*
english version
2022 KAKAO BLIND RECRUITMENT
Problem 4 - Archery Competition

Problem Link:
https://school.programmers.co.kr/learn/courses/30/lessons/92342

[Problem Summary]

Goal: Ryan must determine the scoring target allocation for his arrows 
so that he wins against Apeach by the largest possible score difference.

Rules:
- For each score (k points, where k = 10 to 0), 
the player who shoots more arrows at that target wins that score.
- If Apeach and Ryan shoot the same number of arrows at a target, Apeach wins that score.
- If neither player shoots any arrows at a target, no points are awarded.
- The winner is the player with the higher total score.
- Ryan must find the arrow allocation that wins by the largest score difference.
- If multiple allocations yield the same maximum score difference, 
choose the one where Ryan hits more arrows at lower score targets.

Input:
- n: The number of arrows Ryan can shoot (1 ≤ n ≤ 10)
- info: An array representing the number of arrows Apeach hit for scores from 10 to 0 
(length 11, sum equals n)

Output:
- An array representing Ryan's arrow allocation (length 11, sum equals n), 
or [-1] if winning is impossible.


[Solution Approach]

Solution 1) Brute-force search (combinations with repetition)
- Total number of cases using combinations with repetition: 184,756

For example, if there are 3 arrows, possible allocations (with repetition allowed) 
for scores 10 to 0 could be:
10 10 10  
10 10 9  
10 10 8  
10 9 9  
...
*/

import java.util.Arrays;

public class ArcheryCompetition {
    int lastIndex = 10;
    int maxGap = 0;
    int[] answer;

    // Updates the maximum score gap and the answer array based on the current allocation
    public void updateMaxGap(int[] info, int[] ryan) {
        int apeachSumScore = 0;
        int ryanSumScore = 0;
        for (int i = 0; i <= lastIndex; i++) {
            // Skip this target if both players did not hit any arrows
            if (info[i] == 0 && ryan[i] == 0) {
                continue;
            }
            // If Apeach has hit equal to or more arrows, Apeach wins the score for this target
            if (info[i] >= ryan[i]) {
                apeachSumScore += 10 - i;
            }
            // If Ryan has hit more arrows, Ryan wins the score for this target
            if (info[i] < ryan[i]) {
                ryanSumScore += 10 - i;
            }
        }
        int scoreGap = ryanSumScore - apeachSumScore;
        // If Ryan does not win, do not update the result
        if (scoreGap <= 0) {
            return;
        }

        // Update if a larger score gap is found
        if (maxGap < scoreGap) {
            maxGap = scoreGap;
            System.arraycopy(ryan, 0, answer, 0, ryan.length);
            return;
        }
        // If the score gap is equal, choose the allocation with more arrows in the lower score targets
        if (maxGap == scoreGap) {
            for (int i = lastIndex; i >= 0; i--) {
                if (answer[i] > ryan[i]) {
                    return;
                }
                if (answer[i] < ryan[i]) {
                    System.arraycopy(ryan, 0, answer, 0, ryan.length);
                    return;
                }
            }
        }
    }

    // DFS (brute-force search) to try all possible arrow allocations (combinations with repetition)
    public void dfs(int i, int[] info, int[] ryan, int remainingArrows) {
        // When all targets (from 10 to 0) have been assigned, update the maximum score gap
        if (i == lastIndex + 1) {
            updateMaxGap(info, ryan);
            return;
        }
        // For the current target (score: 10 - i), try allocating from 0 to n arrows
        for (int count = 0; count <= remainingArrows; count++) {
            ryan[i] = count;
            dfs(i + 1, info, ryan, remainingArrows - count);
            // Backtracking: reset the arrow count for this target
            ryan[i] = 0;
        }
    }

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        answer = new int[ryan.length];
        maxGap = 0;
        dfs(0, info, ryan, n);
        if (maxGap != 0) {
            return answer;
        }
        return new int[]{-1};
    }

    public static void main(String[] args) {
        ArcheryCompetition archeryCompetition = new ArcheryCompetition();
        int[] info1 = new int[]{2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(archeryCompetition.solution(5, info1)));

        int[] info2 = new int[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println(Arrays.toString(archeryCompetition.solution(1, info2)));

        int[] info3 = new int[]{0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};
        System.out.println(Arrays.toString(archeryCompetition.solution(9, info3)));

        int[] info4 = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};
        System.out.println(Arrays.toString(archeryCompetition.solution(10, info4)));
    }
}
