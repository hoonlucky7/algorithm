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

#include <iostream>
#include <vector>
#include <string>
#include <cmath>
#include <sstream>
#include <iomanip>
#include <algorithm>

using namespace std;

struct CarInfo {
    int inTime;
    int totalTime;

    CarInfo() : inTime(-1), totalTime(0) {}
};

// 주차 요금을 계산하는 함수
int getFee(int basicTime, int basicFee, int unitTime, int unitFee, int totalTime) {
    int overTime = totalTime - basicTime;
    if (overTime <= 0) return basicFee;
    int overUnit = ceil(static_cast<double>(overTime) / unitTime);
    return basicFee + (overUnit * unitFee);
}

// "HH:MM" 형식의 시간을 분 단위로 변환하는 함수
int convertTimeToMinute(const string& timeStr) {
    int hour, minute;
    char colon;
    stringstream ss(timeStr);
    ss >> hour >> colon >> minute;
    return hour * 60 + minute;
}

// 주차 요금을 계산하는 메인 함수
vector<int> solution(vector<int> fees, vector<string> records) {
    int basicTime = fees[0];
    int basicFee = fees[1];
    int unitTime = fees[2];
    int unitFee = fees[3];

    // 차량 번호가 0부터 9999까지이므로 배열로 관리
    vector<CarInfo> parkingData(10000);

    // 1. 입/출차 기록 처리
    for (const auto& record : records) {
        stringstream ss(record);
        string timeStr, carNumStr, status;
        ss >> timeStr >> carNumStr >> status;

        int carNum = stoi(carNumStr);
        int timeInt = convertTimeToMinute(timeStr);
        CarInfo& carInfo = parkingData[carNum];

        if (status == "IN") {
            carInfo.inTime = timeInt; // 입차 시간 기록
        } else {
            carInfo.totalTime += (timeInt - carInfo.inTime); // 누적 주차 시간 계산
            carInfo.inTime = -1; // 입차 시간 초기화
        }
    }

    // 2. 입차 중인 차량은 23:59에 출차한 것으로 간주
    int endOfDay = convertTimeToMinute("23:59");
    for (auto& carInfo : parkingData) {
        if (carInfo.inTime != -1) {
            carInfo.totalTime += (endOfDay - carInfo.inTime);
            carInfo.inTime = -1; // 입차 시간 초기화
        }
    }

    // 3. 차량 번호 기준으로 오름차순 정렬 후 최종 요금 계산
    vector<int> answer;
    for (int carNum = 0; carNum < 10000; ++carNum) {
        const CarInfo& carInfo = parkingData[carNum];
        if (carInfo.totalTime > 0) {
            answer.push_back(getFee(basicTime, basicFee, unitTime, unitFee, carInfo.totalTime));
        }
    }

    return answer;
}

// 테스트 함수
void runTest() {
    vector<int> fees = {180, 5000, 10, 600}; // 기본 시간: 180분, 기본 요금: 5000원, 단위 시간: 10분, 단위 요금: 600원
    vector<string> records = {
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

    vector<int> result = solution(fees, records);
    cout << "Test Case 1 Result:";
    for (int fee : result) {
        cout << " " << fee;
    }
    cout << endl;

    // 추가 테스트 케이스
    fees = {120, 0, 60, 591};
    records = {
        "16:00 3961 IN",
        "16:00 0202 IN",
        "18:00 3961 OUT",
        "18:00 0202 OUT",
        "23:58 3961 IN"
    };

    result = solution(fees, records);
    cout << "Test Case 2 Result:";
    for (int fee : result) {
        cout << " " << fee;
    }
    cout << endl;

    fees = {1, 461, 1, 10};
    records = {
        "00:00 1234 IN"
    };

    result = solution(fees, records);
    cout << "Test Case 3 Result:";
    for (int fee : result) {
        cout << " " << fee;
    }
    cout << endl;
}

int main() {
    runTest(); // 테스트 실행
    return 0;
}
