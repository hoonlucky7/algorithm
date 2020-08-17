import java.io.*;
import java.util.*;

public class DecodeTheString {

    static boolean isDigit(Character d) {
        return d >= '0' && d <= '9';
    }

    static String alternatingCharacters(String data) {
        String result = "";
        Stack<Integer> intSt = new Stack<>();
        Stack<Character> chSt = new Stack<>();

        for (int i = 0; i < data.length(); i++) {
            if (data.charAt(i) == '[') {
                chSt.push(data.charAt(i));
            } else if (data.charAt(i) == ']') {
                String temp = "";
                while (!chSt.isEmpty()) {
                    if (chSt.peek() == '[') {
                        chSt.pop();
                        break;
                    }
                    temp = chSt.peek() + temp;
                    chSt.pop();
                }
                int n = 1;
                if (!intSt.isEmpty()) {
                    n = intSt.pop();
                }
                result = "";
                for (int j = 0; j < n; j++) {
                    result = result + temp;
                }
                // Push it in the character stack.
                for (int j = 0; j < result.length(); j++) {
                    chSt.push(result.charAt(j));
                }

            } else if (isDigit(data.charAt(i))) {
                int end = i;
                int d = 1;
                int num = 0;
                while (isDigit(data.charAt(end + 1))) {
                    end++;
                }
                for (int k = end; k >= i; k--) {
                    num = num + (data.charAt(k) - '0') * d;
                    d = d * 10;
                }
                i = end;
                intSt.push(num);
            } else {
                chSt.push(data.charAt(i));
            }
        }

        result = "";
        while (!chSt.isEmpty()) {
            result = chSt.peek() + result;
            chSt.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //Scanner scanner = new Scanner(System.in);
        //int n = scanner.nextInt();
        int n = 1;
        for (int i = 0; i < n; i++) {
            //String myString = scanner.next();
            System.out.println(alternatingCharacters("1[b2[c3[z]a]]2[t][3[y]]"));
        }
        //scanner.close();
    }
}