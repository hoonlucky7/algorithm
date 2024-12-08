package org.example;

/*
1)candies[i] + extracandy를 더한값이 가장큰지 판단, 맞으면 true, false
시간 복잡도는 O(N^2)
공간 복잡도는 O(N)

2)
2-1) candies 배열에서 max를 찾는다.
2-2) candies[i] + extracandy >= max보다 큰지 확인함
시간 복잡도 O(N)
공간 복잡도 O(N)
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class KidsWithTheGreatestNumberOfCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        int max = Arrays.stream(candies).max().orElse(0);
        return Arrays.stream(candies).mapToObj(candy -> candy + extraCandies >= max)
                .collect(Collectors.toList());
    }
    public static void main(String[] args) {
        KidsWithTheGreatestNumberOfCandies kids = new KidsWithTheGreatestNumberOfCandies();
        int[] candies = new int[]{2,3,5,1,3};
        System.out.println(kids.kidsWithCandies(candies, 3));
    }
}
