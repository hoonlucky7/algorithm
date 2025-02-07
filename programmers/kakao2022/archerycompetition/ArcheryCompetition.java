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
점수가 동일하면 어피치가 승리.
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

solution 2) 2^n 알고리즘 (점수를 가져가? 말아?)

실제 구현 시 최적화 방법 완전 탐색을 최적화하려면,
해당 점수대 점수를 가져가려면 어피치보다 +1발, 아니면 0발정도로만 시도해도 충분합니다.
그리고 마지막(0점 과녁)에는 남은 화살을 몰아넣는 식으로 처리하면,
불필요한 배분(예: 이미 +1발이면 점수를 확보했는데, +2, +3, ...)을 건너뛸 수 있어 탐색 범위를 많이 줄일 수 있습니다.
이처럼, “어피치보다 1발 더 쓰거나(점수 확보), 아예 안 쓰거나(점수 포기)”만 고려해도 
이론상 점수 면에서는 동일한 결과를 얻을 수 있습니다.

***0점 과녁을 어떻게 처리 할지 정리***
라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return
0점 과녁은 점수에 영향이 없지만, 0점 쪽에서 남은 화살을 많이 배분 해야함

 */

import java.util.Arrays;

public class ArcheryCompetition {
    int lastTargetPosition = 10;
    int maxGap = 0;
    int[] answer;

    public void updateMaxGap(int[] info, int[] ryan) {
        int apeachSumScore = 0;
        int ryanSumScore = 0;
        for (int i = 0; i <= lastTargetPosition; i++) {
            if (info[i] == 0 && ryan[i] == 0) {
                continue;
            }
            if (info[i] >= ryan[i]) {
                apeachSumScore += 10 - i;
            }
            if (info[i] < ryan[i]) {
                ryanSumScore += 10 - i;
            }
        }
        int scoreGap = ryanSumScore - apeachSumScore;
        if (scoreGap <= 0) {
            return;
        }

        if (maxGap < scoreGap) {
            maxGap = scoreGap;
            System.arraycopy(ryan, 0, answer, 0, ryan.length);
        }
        if (maxGap == scoreGap) {
            for (int i = lastTargetPosition; i >= 0; i--) {
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

    //solution 2 : 점수를 가져가? 말아? 전략
    public void dfs(int i, int[] info, int[] ryan, int n) {
        if (i == lastTargetPosition + 1) {
            updateMaxGap(info, ryan);
            return;
        }

        //10 - i점 과녁에 못 맞추는 경우
        ryan[i] = 0;
        dfs(i + 1, info, ryan, n);

        //10 - i점 과녁에 맞추는 경우는 무조건 어피치보다 한발을 더 맞추면 됨
        if (i == lastTargetPosition) { 
            // 라이언이 가장 큰 점수 차이로 우승할 수 있는 방법이 여러 가지 일 경우, 가장 낮은 점수를 더 많이 맞힌 경우를 return
            // 0점 과녁은 점수에 영향이 없지만, 0점 쪽에서 남은 화살을 많이 배분 해야함
            ryan[i] = n;
        } else {
            ryan[i] = info[i] + 1;
        }
        if (n - ryan[i] >= 0) {
            dfs(i + 1, info, ryan, n - ryan[i]);
        }
    }

    // solution 1 : 중복 조합 완전 탐색
    // public void dfs(int i, int[] info, int[] ryan, int n) {
    //     if (i == lastTargetPosition + 1) {
    //         updateMaxGap(info, ryan);
    //         return;
    //     }
    //     for (int count = 0; count <= n; count++) { // 현재 10 - i점 과녁에 몇개 맞출래?
    //         ryan[i] = count;
    //         dfs(i + 1, info, ryan, n - count);
    //         ryan[i] = 0;
    //     }
    // }

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[]{0,0,0,0,0,0,0,0,0,0,0};
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
        int[] info1 = new int[]{2,1,1,1,0,0,0,0,0,0,0};
        System.out.println(Arrays.toString(archeryCompetition.solution(5, info1)));

        int[] info2 = new int[]{1,0,0,0,0,0,0,0,0,0,0};
        System.out.println(Arrays.toString(archeryCompetition.solution(1,	info2)));

        int[] info3 = new int[]{0,0,1,2,0,1,1,1,1,1,1};
        System.out.println(Arrays.toString(archeryCompetition.solution(9,	info3)));
 
        int[] info4 = new int[]{0,0,0,0,0,0,0,0,3,4,3};
        System.out.println(Arrays.toString(archeryCompetition.solution(10, info4)));
        
    }
}