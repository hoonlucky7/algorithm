public class Eratosthenes {
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
    
    // 소수 출력을 위한 메인 메소드
    public static void main(String[] args) {
        int n = 100; // 1부터 100까지의 소수를 찾음
        boolean[] primes = sieve(n);
        
        System.out.println("1부터 " + n + "까지의 소수:");
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}