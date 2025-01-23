// Given the number n (n >=0), find its factorial. 
// Factorial of n is defined as 1 x 2 x … x n. 
// For n = 0, factorial is 1. 
// 숫자 n (n >= 0)이 주어졌을 때, 그 팩토리얼을 구하세요. 
// n의 팩토리얼은 1 x 2 x ... x n으로 정의됩니다.
// n이 0일 경우, 팩토리얼은 1입니다.
// 시간 복잡도 : O(N)

public class FactorialExample {
    public static int factorial(int n) {
        if (n == 1) { // Base case
             return 1;
        }
        return n * factorial(n - 1); // Recursive case
    }

    //example : only n = 3 case
    public static int fac3(int n) {
        return n * fac2(n - 1);
    }

    public static int fac2(int n) {
        return n * fac1(n - 1);
    }

    public static int fac1(int n) {
        return 1;
    }
    public static void main(String[] args) {
        System.out.println(fac3(3)); // Output: 6
        System.out.println(factorial(3)); // Output: 6
    }
}
