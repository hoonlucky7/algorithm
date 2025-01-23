// 1)나머지는 현재 자리의 값을 구합니다.
// 예를 들어, 숫자를 n으로 나눈 나머지는 가장 낮은 자릿수(일의 자리) 값을 나타냅니다.
// 2)몫은 남은 숫자를 구합니다.
// 나누기를 통해 몫을 구하면, 더 높은 자리의 값을 계산하기 위한 새로운 숫자가 됩니다.

// 재귀 호출 깊이 : O(logk(N))
// n=25, k=2라면, 25 → 12 → 6 → 3 → 1 식으로 약 4회 정도로, 이는 
// log2(25)≈4.64 유사합니다.
public class BaseConversionExample {

    // Recursively converts a non-negative integer n into a base-k string.
    public static String toBaseK(int n, int k) {
        // If n is smaller than the base, return it as a single digit
        if (n < k) {
            return String.valueOf(n);
        }
        // Recursively convert n/k, and append the digit for n%k
        return toBaseK(n / k, k) + (n % k);
    }

    public static void main(String[] args) {
        int number = 25;
        int base = 3;

        String converted = toBaseK(number, base);
        System.out.println(number + " in base " + base + " is " + converted);
        // Output: 25 in base 3 is 221
    }
}