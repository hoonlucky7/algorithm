
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakingAnagramsEasy {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        int[] countA = new int[30];
        int[] countB = new int[30];
        int count = 0;

        for (int i = 0; i < 30; i++) {
            countA[i] = countB[i] = 0;
        }

        for (int i = 0; i < a.length(); i++) {
            countA[a.charAt(i) - 'a']++;
        }

        for (int i = 0; i < b.length(); i++) {
            countB[b.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            count += Math.abs(countA[i] - countB[i]);
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
