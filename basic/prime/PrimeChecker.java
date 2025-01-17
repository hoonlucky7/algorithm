public class PrimeChecker {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Math.sqrt(17));
        System.out.println(PrimeChecker.isPrime(29)); // Output: true
        System.out.println(PrimeChecker.isPrime(30)); // Output: false
    }
}
