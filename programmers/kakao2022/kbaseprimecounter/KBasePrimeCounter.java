/*
문제 2 - k 진수에서 소수의 개수 구하기

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/92335

[문제 요약]
n을 k진수로 변환하고, 변환된 k진수 문자열에서 0을 기준으로 나눔
각 구간을 확인하고 각 구간의 숫자가 소수인지 판별

[Solution]

1) n을 k진수로 변환
2) 0을 기준으로 문자열 분할
3) 구간의 숫자가 소수인지 판별하고 개수 기록

*** k진수로 변환 방법 ***
1) n을 k로 나눈다.
2) 나머지를 기록한다.
3) 몫이 0이 될 때까지 반복한다.
4) 나머지들을 역순으로 나열하면 결과.

 */

public class KBasePrimeCounter {
    public int solution(int n, int k) {
        // n을 k진수로 변환
        String converted = convertToKBase(n, k);

        // 0을 기준으로 문자열 분할
        String[] numbers = converted.split("0");

        int count = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].isEmpty()) {  // 빈 문자열 제외
                long num = Long.parseLong(numbers[i]); // long으로 선언 하는 것이 중요 함 : int 넘어가는 경우가 있음
                if (isPrime(num)) {
                    count++;
                }
            }
        }

        return count;
    }

    public String convertToKBase(int n, int k) {
        StringBuilder kBase = new StringBuilder();
        while (n > 0) {
            kBase.append(n % k);  // 나머지를 추가
            n /= k;               // 몫을 업데이트
        }
        return kBase.reverse().toString();  // 결과를 뒤집어 반환
    }

    private boolean isPrime(long n) {
        // 1은 소수가 아님
        if (n <= 1) return false;

        // 2는 소수
        if (n == 2) return true;

        // 2부터 제곱근까지 나누어 떨어지는지 확인
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        KBasePrimeCounter kBasePrimeCounter = new KBasePrimeCounter();
        System.out.println(kBasePrimeCounter.solution(437674,	3));
        System.out.println(kBasePrimeCounter.solution(110011,	10));
    }
}