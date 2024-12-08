package org.example;

/*
그냥 풀기
1) inputVowels를 만들고 거기에 모음을 담는다.
2) 반복문으로 0~length -1, i가 모음인지 확인 하고 inputVowels에 있는 것을 거꾸로 바꿔준다.
시간복잡도 : O(N)
공간복잡도 : O(N)


Two pointer전략 사용
1) l = 0, r = length - 1, 반복문을 이용해서 (left pointer) 왼쪽부터 오른쪽으로 모음을 찾는다.
right pointer는 오른쪽에서 왼쪽으로 모음을 찾는다.
그리고 왼쪽에서 찾은 모음과 오른쪽에서 찾은 모음을 swap한다.
시간복잡도: O(N)
공간복잡도: O(1)

*/

public class ReverseVowels {

    public String reverseVowels(String s) {
        String vowels = "aeiouAEIOU";
        char[] input = s.toCharArray();
        int l = 0, r = input.length - 1;

        while(l < r) {
            while (l < r && !vowels.contains(String.valueOf(input[l]))) {
                l++;
            }
            while (l < r && !vowels.contains(String.valueOf(input[r]))) {
                r--;
            }
            if (l < r) {
                char temp = input[l];
                input[l] = input[r];
                input[r] = temp;
                l++;
                r--;
            }
        }

        return new String(input);
    }

    public static void main(String[] args) {
        ReverseVowels reverseVowels = new ReverseVowels();
        System.out.println(reverseVowels.reverseVowels("IceCreAm"));
    }
}
