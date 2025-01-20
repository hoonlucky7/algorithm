/*
MIT License

Copyright (c) 2025 github.com/hoonlucky7

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/*문제 3 - 주차 요금 계산

문제 링크 :
https://school.programmers.co.kr/learn/courses/30/lessons/92341?language=java

[문제 요약]
주차장의 요금표와 차량의 입/출차 기록을 기반으로, 차량별로 주차 요금을 계산하는 문제입니다.

1. 요금 계산 방식
기본 시간(분): 지정된 시간까지는 기본 요금만 부과.
기본 요금(원): 기본 시간 내 주차 시 부과되는 요금.
단위 시간(분): 기본 시간을 초과할 경우, 추가 요금을 계산하기 위한 시간 단위.
단위 요금(원): 단위 시간당 부과되는 추가 요금.

2. 입/출차 기록
형식: "시각 차량번호 내역" (예: "05:34 5961 IN")
시각: HH:MM (24시간 기준)
차량번호: 4자리 숫자
내역: IN(입차), OUT(출차)
차량이 입차된 후 출차 기록이 없으면 23:59에 출차된 것으로 간주.

3. 계산 규칙
주차 시간 계산
한 차량의 모든 입/출차 기록을 바탕으로 누적 주차 시간을 계산.
초 단위는 없으며, 분 단위로 계산.
요금 계산
누적 시간이 기본 시간 이하일 경우: 기본 요금 부과.
누적 시간이 기본 시간 초과일 경우:
초과 시간에 대해 단위 시간마다 추가 요금 부과.
초과 시간이 단위 시간으로 나누어 떨어지지 않으면 올림.
basicFee + ⌈(totalTime - basicTime) / unitTime⌉ x unitFee
기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
     180	       5000	           10	        600
ex) 5000 + ⌈(334 - 180) / 10⌉ x 600 = 14600

4. 출력
차량 번호가 작은 순서대로 주차 요금을 정수 배열로 반환.

[Solution]
[자료구조 및 함수 설계]
1) array를 쓸지? map을 쓸지? 
1-1) array가능 (차량번호는 자동차를 구분하기 위한, `0'~'9'로 구성된 길이 4인 문자열입니다)
1-2) map도 가능함, 구현 복잡도 살짝 증가
2) CarInfo class를 만들어 inTime, totalTime을 저장
3) getFee : 주차 요금 계산 함수 작성
4) convertTimeToMinute : "HH:MM" 형식의 시간을 분 단위로 변환, 그래야 주차요금계산이 쉬움

[알고리즘]
1) 입/출차 기록 처리
2) 입차 중인 차량은 23:59에 출차한 것으로 간주
3) 차량 번호 기준으로 오름차순 정렬 후 최종 요금 계산

*/

import java.util.*;

public class ParkingFeeCalculator {

    static class CarInfo {
        Integer inTime;
        int totalTime;

        public CarInfo() {
            this.inTime = null;
            this.totalTime = 0;
        }
    }

    // 주차 요금을 계산하는 메서드
    // 누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구합니다.
    // 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
    // 초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
    // ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
    // basicFee + ⌈(totalTime - basicTime) / unitTime⌉ x unitFee
    // 기본 시간(분)	기본 요금(원)	단위 시간(분)	단위 요금(원)
    //   180	            5000	       10	          600
    // ex) 5000 + ⌈(334 - 180) / 10⌉ x 600 = 14600
    private static int getFee(int basicTime, int basicFee, int unitTime, int unitFee, int totalTime) {
        int overTime = totalTime - basicTime;
        if (overTime <= 0) return basicFee;
        int overUnit = (int) Math.ceil((double) overTime / unitTime);
        return basicFee + (overUnit * unitFee);
    }

    // "HH:MM" 형식의 시간을 분 단위로 변환하는 메서드
    private static int convertTimeToMinute(String timeStr) {
        String[] parts = timeStr.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    // 주차 요금을 계산하는 메인 메서드
    public static int[] solution(int[] fees, String[] records) {
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        CarInfo[] parkingData = new CarInfo[10000];
        for (int i = 0; i < parkingData.length; i++) {
            parkingData[i] = new CarInfo();
        }

        for (String record : records) {
            String[] parts = record.split(" ");
            String timeStr = parts[0];
            int carNum = Integer.parseInt(parts[1]);
            String status = parts[2];

            int time = convertTimeToMinute(timeStr);
            CarInfo carInfo = parkingData[carNum];

            if (status.equals("IN")) {
                carInfo.inTime = time;
            } else {
                carInfo.totalTime += (time - carInfo.inTime);
                carInfo.inTime = null;
            }
        }

        int endOfDay = convertTimeToMinute("23:59"); 
        for (CarInfo carInfo : parkingData) {
            if (carInfo.inTime != null) {
                carInfo.totalTime += (endOfDay - carInfo.inTime);
                carInfo.inTime = null;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int carNum = 0; carNum < parkingData.length; carNum++) {
            CarInfo carInfo = parkingData[carNum];
            if (carInfo.totalTime > 0) {
                result.add(getFee(basicTime, basicFee, unitTime, unitFee, carInfo.totalTime));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] fees1 = {180, 5000, 10, 600};
        String[] records1 = {
            "05:34 5961 IN",
            "06:00 0000 IN",
            "06:34 0000 OUT",
            "07:59 5961 OUT",
            "07:59 0148 IN",
            "18:59 0000 IN",
            "19:09 0148 OUT",
            "22:59 5961 IN",
            "23:00 5961 OUT"
        };
        System.out.println(Arrays.toString(solution(fees1, records1)));

        int[] fees2 = {120, 0, 60, 591};
        String[] records2 = {
            "16:00 3961 IN",
            "16:00 0202 IN",
            "18:00 3961 OUT",
            "18:00 0202 OUT",
            "23:58 3961 IN"
        };
        System.out.println(Arrays.toString(solution(fees2, records2)));

        int[] fees3 = {1, 461, 1, 10};
        String[] records3 = {
            "00:00 1234 IN"
        };
        System.out.println(Arrays.toString(solution(fees3, records3)));
    }
}
