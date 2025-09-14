/*
문제 2 - 택배 배달과 수거하기

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/150369

[Solution]
매 단계에서 "남아있는 일들 중 가장 먼 곳을 처리하자. 
이왕 가는 김에 트럭을 꽉 채워서 최대한 많은 일을 하자"라는 
탐욕적인 선택이 항상 최적의 결과를 가져옵니다. 
왜냐하면 어차피 지불해야 하는 가장 비싼 비용(가장 먼 거리 이동)을 먼저 처리하면서, 
그 과정에서 더 저렴한 비용의 일들을 '덤'으로 해결하는 구조이기 때문입니다. 
다른 어떤 순서로 처리하더라도 이보다 총 이동 거리를 줄일 수는 없습니다.

마이너스 누적 합의 정확한 의미: "가까운 집을 위한 선처리 용량"
일반적으로 누적 합은 '처리해야 할 상자의 총량'을 의미 
이 값이 양수이면 아직 처리하지 못한 상자가 있다는 뜻
그렇다면 이 누적 합에서 cap만큼 뺐을 때 음수가 되는 것은 무엇을 의미일까?
"가장 먼 곳을 다녀오는 김에, 트럭의 남는 공간을 활용해 
더 가까운 집의 상자를 미리 처리했다"

 */

public class DeliveryAndCollection {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int deliver = 0; // 현재 배달해야 할 택배 수
        int pickup = 0;  // 현재 수거해야 할 택배 수

        // 가장 먼 집부터 처리 (Greedy)
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];

            // 배달/수거가 필요한 동안 반복
            while (deliver > 0 || pickup > 0) {
                deliver -= cap;
                pickup -= cap;
                answer += (i + 1) * 2; // 왕복 거리 추가
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        DeliveryAndCollection deliveryAndCollection = new DeliveryAndCollection();
        long result = deliveryAndCollection.solution(4, 5, 
        new int[]{1, 0, 3, 1, 2}, new int[]{0, 3, 0, 4, 0});

        System.out.println(result);

        long result2 = deliveryAndCollection.solution(2, 7, 
        new int[]{1, 0, 2, 0, 1, 0, 2}, new int[]{0, 2, 0, 1, 0, 2, 0});

        System.out.println(result2);
    }
}