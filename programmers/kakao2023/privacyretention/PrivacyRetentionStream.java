/*
문제 1 - 개인정보 수집 유효기간

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/150370

[문제 요약]
개인정보 n개가 있고, 각각은 수집일자와 약관 종류로 구분됩니다.
각 약관 종류는 보관 가능 개월 수가 정해져 있습니다.
개인정보는 수집일자 + 보관 개월 수까지 보관할 수 있고, 그 다음 날부터 파기해야 합니다.
모든 달은 28일 고정이라고 가정합니다.
오늘 날짜 today와 약관 정보 terms, 개인정보 정보 privacies가 주어졌을 때,
오늘 기준 파기해야 할 개인정보 번호를 오름차순으로 반환하는 문제입니다.

[Solution]

1) 입력 파싱

2) 날짜 계산 단순화
days = year * 12 * 28 + month * 28 + day

3) 만료일 계산

4) 파기 대상 리스트

 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrivacyRetentionStream {
    private int toDays(String date) {
        StringTokenizer st = new StringTokenizer(date, ".");
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        return y * 12 * 28 + m * 28 + d;
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMonths = Arrays.stream(terms).map(t -> t.split(" "))
        .collect(Collectors.toMap(arr -> arr[0], arr -> Integer.parseInt(arr[1])));

        int todayDays = toDays(today);

        return IntStream.range(0, privacies.length)
        .filter(i -> {
            String[] parts = privacies[i].split(" ");
            int startDays = toDays(parts[0]);
            int month = termMonths.get(parts[1]);
            return startDays + month * 28 <= todayDays;
        })
        .map(i -> i + 1)
        .toArray();
    }

    public static void main(String[] args) {
        String today = "2022.05.19";
        String[] terms = {"A 6", "B 12", "C 3"};
        String[] privacies = {
            "2021.05.02 A",
            "2021.07.01 B",
            "2022.02.19 C",
            "2022.02.20 C"
        };

        PrivacyRetention privacyRetention = new PrivacyRetention();
        int[] result = privacyRetention.solution(today, terms, privacies);

        System.out.println(Arrays.toString(result)); // [1, 3]
    }
}