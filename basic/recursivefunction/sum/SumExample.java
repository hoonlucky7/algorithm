// Given a number n, find sum of first n natural numbers
// To calculate the sum, we will use a recursive function sum(int n)
// Examples : 
// Input : 3
// Output : 6
// Explanation : 1 + 2 + 3 = 6

// 숫자 n이 주어졌을 때, 처음 n개의 자연수의 합을 구하세요.
// 합을 계산하기 위해 재귀 함수 sum(int n)을 사용할 것입니다.
// 예시:
// 입력 : 3
// 출력 : 6
// 설명 : 1 + 2 + 3 = 6
// 시간 복잡도 : O(N)
public class SumExample {
    public static int sum(int n) {
        if (n == 1) { // Base case
             return 1;
        }
        return n + sum(n - 1); // Recursive case
    }

    public static void main(String[] args) {
        System.out.println(sum(3)); // Output: 6, 3+2+1 = 6
    }
}
