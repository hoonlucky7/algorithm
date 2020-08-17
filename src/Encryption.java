import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Encryption {

    // Complete the encryption function below.
    static String encryption(String s) {
        int length = s.length();
        int rows = (int)Math.floor(Math.sqrt(length));
        int columns = (int)Math.ceil(Math.sqrt(length));

        if (rows * columns < length) {
            rows = columns;
        }

        char matrix[][] = new char[rows][columns];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = 0;
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = s.charAt(count++);
                if (count == length) {
                    break;
                }
            }
            if (count == length) {
                break;
            }
        }

        String result = "";
        count = 0;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (matrix[i][j] == 0) {
                    break;
                }
                result = result + matrix[i][j];
                count++;
                if (count == length) {
                    break;
                }
            }
            if (count == length) {
                break;
            }
            result = result + " ";
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output.txt"));

        String s = scanner.nextLine();

        String result = encryption(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
