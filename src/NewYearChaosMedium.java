import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class NewYearChaosMedium {

    // Complete the minimumBribes function below.
    /*static void minimumBribes(int[] q) {
        int n = q.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            q[i] = q[i] - 1;
            if (q[i] > i + 2) {
                System.out.printf("Too chaotic\n");
                return;
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            if (q[i] == i) {
                continue;
            }
            if (i - 1 >= 0 && q[i - 1] == i) {
                int temp = q[i];
                q[i] = q[i - 1];
                q[i - 1] = temp;
                count++;
            } else if (i - 2 < n && q[i - 2] == i) {
                int temp = q[i - 2];
                q[i - 2] = q[i - 1];
                q[i - 1] = temp;
                int temp2 = q[i];
                q[i] = q[i - 1];
                q[i - 1] = temp2;
                count += 2;
            }
        }
        System.out.printf("%d\n", count);
    }*/

    static void minimumBribes(int[] q) {
        int n = q.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            q[i] = q[i] - 1;
            if (q[i] > i + 2) {
                System.out.printf("Too chaotic\n");
                return;
            }
        }
        for (int i = 0; i < n; i++) {
            if (q[i] == i) {
                continue;
            }
            if (i + 1 < n && q[i + 1] == i) {
                int temp = q[i];
                q[i] = q[i + 1];
                q[i + 1] = temp;
                count++;
            } else if (i + 2 < n && q[i + 2] == i) {
                int temp = q[i + 2];
                q[i + 2] = q[i + 1];
                q[i + 1] = temp;
                int temp2 = q[i];
                q[i] = q[i + 1];
                q[i + 1] = temp2;
                count += 2;
            }
        }
        System.out.printf("%d\n", count);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }
}
