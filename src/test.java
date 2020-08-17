import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class test {

    static int min_cost = Integer.MAX_VALUE;
    static int result[][][] = new int[100][3][3];
    static int result_count = 0;

    static int sumY(int y, int temp[][]) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += temp[y][i];
        }
        return sum;
    }

    static int sumX(int x, int temp[][]) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += temp[i][x];
        }
        return sum;
    }

    static boolean allCheck(int temp[][]) {
        int diag1 = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            if (sumX(i, temp) != 15) {
                return false;
            }
            if (sumY(i, temp) != 15) {
                return false;
            }
            diag1 += temp[i][i];
            diag2 += temp[i][2-i];
        }
        if (diag1 != 15 || diag2 != 15) {
            return false;
        }
        return true;
    }

    static void recur(int y, int x, int temp[][], int check[]) {
        if (y == 3 && x == 0) {
            if (allCheck(temp)) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        result[result_count][i][j] = temp[i][j];
                    }
                }
                result_count++;
            }
            return;
        }

        if (x == 3) {
            recur(y + 1,  0, temp, check);
        }
        for (int j = 1; j <= 9; j++) {
            temp[y][x] = j;
            if (check[j] == 0) {
                check[j] = 1;
                recur(y,  x + 1, temp, check);
                check[j] = 0;
            }
            temp[y][x] = 0;
        }

    }
    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int check[] = new int[10];
        for (int i = 0; i < 10; i++) {
            check[i] = 0;
        }
        int temp[][] = new int[10][10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = 0;
            }
        }
        recur(0, 0, temp, check);

        for (int count = 0; count < result_count; count++) {
            int cost = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cost += Math.abs(result[count][i][j] - s[i][j]);
                }
            }
            if (min_cost > cost) {
                min_cost = cost;
            }
        }
        return min_cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
