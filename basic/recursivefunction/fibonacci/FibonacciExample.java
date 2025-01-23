//Given a positive integer n, the task is to find the nth Fibonacci number.
//The Fibonacci sequence is a sequence where the next term is the sum of the previous two terms. 
//The first two terms of the Fibonacci sequence are 0 followed by 1. 
//The Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21
// 양의 정수 n이 주어졌을 때, n번째 피보나치 수를 찾는 것이 목표입니다.
// 피보나치 수열은 다음 항이 이전 두 항의 합인 수열입니다.
// 피보나치 수열의 첫 두 항은 0과 1입니다.
// 피보나치 수열: 0, 1, 1, 2, 3, 5, 8, 13, 21
// 시간 복잡도 : O(2^N)

public class FibonacciExample {
    // 1 1 2 3 5 ...
    public static int fibo(int n) {
        // Base case: if n is 0 or 1, return n
        if (n <= 1) {
            return n;
        }

        // Recursive case: sum of the two preceding Fibonacci numbers
        return fibo(n - 1) + fibo(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibo(3)); // Output: 2
    }
}
