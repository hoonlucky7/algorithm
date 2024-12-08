package org.example;


/*
greedy 방식으로 풀기 :
심을 수 있으면 꽃을 심자!

시간복잡도 : O(N)
공간복잡도 : O(1)


dp로 풀기
1) 부분문제 정의 : C[i] = i번째 영역까지의 최대 심는 꽃의 개수

2) 점화식 :
C[i] = max {
C[i -1],  // i번째 영역에 심지 않았을떄
C[i - 2] + 1 // i번째 영역에 심었을때(i번째 영역이 0이고 인접한 영역이 0일때)
}

시간복잡도 : O(N)
공간복잡도 : O(N)

 */

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {

        if (n == 0) {
            return true;
        }

        int length = flowerbed.length;
        int[] C = new int[length + 1];

        //초기조건
        C[0] = flowerbed[0] == 0 && (length == 1 || flowerbed[1] == 0) ? 1: 0;
        if (length >= 2) {
            C[1] = flowerbed[1] == 0 && flowerbed[0] == 0 && (length >= 3 && flowerbed[2] == 0)? 1: C[0];
        }


        for (int i = 2; i < length; i++) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == length - 1 || flowerbed[i + 1] == 0)) { // i번째 영역에 꽃을 심었을때
                C[i] = Math.max(C[i - 1], C[i - 2] + 1);
            } else { // i번째 영역에 꽃을 심지 않았을떄
                C[i] = C[i - 1];
            }
        }
        return C[length - 1] >= n;
    }

    public static void main(String[] args) {
        CanPlaceFlowers canPlaceFlowers = new CanPlaceFlowers();
        int[] flowerbed1 = new int[]{0,0,1,0,1};
        System.out.println(canPlaceFlowers.canPlaceFlowers(flowerbed1,1));
    }
}
