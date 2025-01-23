// When you call countdown(5), it will print 5 → 4 → 3 → 2 → 1 → 0 in sequence
// and keep calling itself. Once n reaches 0, it prints "Go!" and stops.
// This illustrates how a recursive function handles the smallest subproblem,
// solves it, and then terminates.
// 시간복잡도 : O(N)
public class CountdownExample {

    // A recursive function that counts down from n to 0.
    public static void countdown(int n) {
        if (n == 0) {
            // Base case: when n is 0, print "Go!" and stop recursion
            System.out.println("Go!");
        } else {
            // Print the current number
            System.out.println(n);
            // Recursively call countdown with n - 1
            countdown(n - 1);
        }
    }

    public static void main(String[] args) {
        // Start counting down from 5
        countdown(5);
    }
}
