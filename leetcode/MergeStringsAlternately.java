package org.example;

/*
* 1) word1의 현재 index, word2의 현재 index를 움직이면서 merged에 문자 추가
* 2) word2의 현재 index가 word2보다 크다면 종료
* 3) w1의 길이와 w2의 길이가 같다면 merged출력
* 4) w1의 길이가 w2의 길이보다 길다면 merged + w1의 남은 문자열 합쳐서 출력
* 5) w2의 길이가 w1의 길이보다 길다면 merged + w2의 남은 문자열 합쳐서 출력
* */

public class MergeStringsAlternately {

    public String mergeAlternately(String word1, String word2) {
        StringBuilder merged = new StringBuilder();

        int i;
        for (i = 0; i < word1.length(); i++) {
            if (i >= word2.length()) {
                break;
            }
            merged.append(word1.charAt(i)).append(word2.charAt(i));
        }
        if (word1.length() == word2.length()) {
            return merged.toString();
        }
        if (word1.length() > word2.length()) {
            return merged.append(word1.substring(i)).toString();
        }
        return merged.append(word2.substring(i)).toString();
    }

    public static void main(String[] args) {
        MergeStringsAlternately mergeStringsAlternately = new MergeStringsAlternately();

        System.out.println(mergeStringsAlternately.mergeAlternately("abc", "pqr"));
        System.out.println(mergeStringsAlternately.mergeAlternately("ab", "pqrs"));
        System.out.println(mergeStringsAlternately.mergeAlternately("abcd", "pq"));
    }
}
