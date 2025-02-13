/*
2022 카카오 신입 공채 1차 온라인 코딩테스트
문제 1 - 신고 결과 받기

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/92334

[문제 요약]
유저가 다른 유저를 신고하고, 일정 횟수 이상 신고된 유저가 있을 경우 신고자들에게 알림 메일을 보냅니다.
최종적으로는 각 유저가 받은 알림 메일 수를 출력하면 됩니다

[Solution]

1) 전처리로 중복 제거를 합니다
유저가 같은 사람을 여러 번 신고할 수 있지만 동일한 유저에 대한 신고는 한 번으로 처리해야 합니다
2) 신고당한 ID의 신고 횟수 기록 합니다
각 유저가 몇 번 신고당했는지를 기록합니다
3) 신고당한 ID와 신고한 사람 ID 매핑입니다
누가 누구를 신고했는지, 신고당한 유저마다 신고한 유저의 목록 Set을 저장합니다
4) 각 유저별로 처리 결과 메일을 받은 횟수를 기록합니다.
신고 횟수가 기준치 k 이상이면, 해당 유저를 신고한 사람들에게 알림 메일을 보냈다고 기록합니다.
5) id_list의 순서대로 처리 결과 메일을 받은 횟수 리턴합니다.

 */

import java.util.*;

public class ReportResultReceiver {
    public int[] solution(String[] id_list, String[] report, int k) {
        //1) 전처리로 중복 제거를 합니다
        //유저가 같은 사람을 여러 번 신고할 수 있지만 동일한 유저에 대한 신고는 한 번으로 처리해야 합니다
        Set<String> distinctReports = new HashSet<>(Arrays.asList(report));

        Map<String, Integer> reportedCountMap = new HashMap<>();
        Map<String, Set<String>> reportedToReportersMap = new HashMap<>();

        for (String reportData : distinctReports) {
            String reporter = reportData.split(" ")[0];
            String reported = reportData.split(" ")[1];
            //2) 신고당한 ID의 신고 횟수 기록 합니다
            //각 유저가 몇 번 신고당했는지를 기록합니다
            reportedCountMap.put(reported, reportedCountMap.getOrDefault(reported, 0) + 1);
            //3) 신고당한 ID와 신고한 사람 ID 매핑입니다
            //누가 누구를 신고했는지, 신고당한 유저마다 신고한 유저의 목록 Set을 저장합니다
            reportedToReportersMap.computeIfAbsent(reported, s -> new HashSet<>()).add(reporter);
        }


        //4) 각 유저별로 처리 결과 메일을 받은 횟수를 기록합니다.
        //신고 횟수가 기준치 k 이상이면, 해당 유저를 신고한 사람들에게 알림 메일을 보냈다고 기록합니다.
        Map<String, Integer> receiveMailCount = new HashMap<>();
        for (String id : id_list) {
            receiveMailCount.put(id, 0);
        }

        for(String key :reportedCountMap.keySet()) {
            if (reportedCountMap.get(key) >= k) {
                for (String reporter : reportedToReportersMap.get(key)) {
                    receiveMailCount.put(reporter, receiveMailCount.get(reporter) + 1);
                }
            }
        }

        //5) id_list의 순서대로 처리 결과 메일을 받은 횟수 리턴합니다.
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = receiveMailCount.get(id_list[i]);
        }

        return answer;
    }

/* stream api 버전 (chatgpt가 구현)
  public int[] solution(String[] id_list, String[] report, int k) {
               // 1) 전처리로 중복 제거
        Set<String> distinctReports = Arrays.stream(report).collect(Collectors.toSet());

        // 2) 신고당한 ID의 신고 횟수 기록 및 3) 신고당한 ID와 신고한 사람 ID 매핑
        Map<String, Integer> reportedCountMap = new HashMap<>();
        Map<String, Set<String>> reportedToReportersMap = new HashMap<>();

        distinctReports.forEach(reportData -> {
            String[] parts = reportData.split(" ");
            String reporter = parts[0];
            String reported = parts[1];

            reportedCountMap.merge(reported, 1, Integer::sum);
            reportedToReportersMap
                .computeIfAbsent(reported, s -> new HashSet<>())
                .add(reporter);
        });

        // 4) 각 유저별로 처리 결과 메일을 받은 횟수를 기록
        Map<String, Integer> receiveMailCount = Arrays.stream(id_list)
            .collect(Collectors.toMap(id -> id, id -> 0));

        reportedCountMap.entrySet().stream()
            .filter(entry -> entry.getValue() >= k)
            .forEach(entry -> {
                String reported = entry.getKey();
                Set<String> reporters = reportedToReportersMap.get(reported);
                reporters.forEach(reporter ->
                    receiveMailCount.merge(reporter, 1, Integer::sum)
                );
            });

        // 5) id_list의 순서대로 처리 결과 메일을 받은 횟수 리턴
        return Arrays.stream(id_list)
            .mapToInt(receiveMailCount::get)
            .toArray();
    }
*/

    public static void main(String[] args) {

        ReportResultReceiver reportResultReceiver = new ReportResultReceiver();
        String[] idList = new String[]{"muzi", "frodo", "apeach", "neo"};
        String[] report = new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        System.out.println(Arrays.toString(reportResultReceiver.solution(idList, report, 2)));

        String[] idList2 = new String[]{"con", "ryan"};
        String[] report2 = new String[]{"ryan con", "ryan con", "ryan con", "ryan con"};
        System.out.println(Arrays.toString(reportResultReceiver.solution(idList2, report2, 3)));
    }
}



/*
claude을 이용한 stream api(Collectors)

한줄 코드
public int[] solution(String[] id_list, String[] report, int k) {
    // 1) 중복 제거된 신고 데이터를 스트림으로 변환
    return Arrays.stream(id_list)
        .map(id -> {
            // 2) 신고 데이터를 스트림으로 처리
            Map<String, Set<String>> reportMap = Arrays.stream(report)
                .distinct()
                .map(r -> r.split(" "))
                .collect(Collectors.groupingBy(
                    r -> r[1], // reported user
                    Collectors.mapping(r -> r[0], Collectors.toSet()) // reporters
                ));

            // 3) k번 이상 신고된 유저들을 필터링하고 해당 유저를 신고한 사람들의 Set을 얻기
            return reportMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() >= k)
                .map(Map.Entry::getValue)
                .flatMap(Set::stream)
                .filter(reporter -> reporter.equals(id))
                .count();
        })
        .mapToInt(Long::intValue)
        .toArray();
}


import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
          Map<String, Set<String>> reportMap = Arrays.stream(report)
        .distinct()
        .map(r -> r.split(" "))
        .collect(Collectors.groupingBy(
            r -> r[1],
            Collectors.mapping(r -> r[0], Collectors.toSet())
        ));

    Set<String> banned = reportMap.entrySet().stream()
        .filter(entry -> entry.getValue().size() >= k)
        .map(Map.Entry::getKey)
        .collect(Collectors.toSet());

    return Arrays.stream(id_list)
        .mapToInt(reporter -> (int)reportMap.entrySet().stream()
            .filter(entry -> banned.contains(entry.getKey()))
            .filter(entry -> entry.getValue().contains(reporter))
            .count())
        .toArray();
    }
}

// R = report 배열 길이
// N = id_list 배열 길이

[Stream API 버전]

시간 복잡도: O(N * R)
상대적으로 높은 시간 복잡도
함수형 프로그래밍 방식으로 가독성이 좋음
각 ID마다 전체 report 배열을 반복 처리하므로 비효율적

[Stream API 버전 최적화된 버전]

신고 데이터를 한 번만 처리
정지된 사용자 목록을 미리 계산
시간 복잡도를 O(R + N)으로 개선
 */