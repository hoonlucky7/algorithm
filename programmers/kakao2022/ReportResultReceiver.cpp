#include <iostream>
#include <vector>
#include <string>
#include <set>
#include <map>
#include <sstream>
using namespace std;

vector<int> solution(vector<string> id_list, vector<string> report, int k) {
    // 1) 전처리로 중복 제거
    set<string> distinctReports(report.begin(), report.end());

    // 2) 신고당한 ID의 신고 횟수 기록 및 3) 신고당한 ID와 신고한 사람 ID 매핑
    map<string, int> reportedCountMap;
    map<string, set<string>> reportedToReportersMap;

    for (const string& reportData : distinctReports) {
        stringstream ss(reportData);
        string reporter, reported;
        ss >> reporter >> reported;

        reportedCountMap[reported]++;
        reportedToReportersMap[reported].insert(reporter);
    }

    // 4) 각 유저별로 처리 결과 메일을 받은 횟수를 기록
    map<string, int> receiveMailCount;
    for (const string& id : id_list) {
        receiveMailCount[id] = 0;
    }

    for (const auto& entry : reportedCountMap) {
        if (entry.second >= k) {
            const string& reported = entry.first;
            for (const string& reporter : reportedToReportersMap[reported]) {
                receiveMailCount[reporter]++;
            }
        }
    }

    // 5) id_list의 순서대로 처리 결과 메일을 받은 횟수 리턴
    vector<int> answer;
    for (const string& id : id_list) {
        answer.push_back(receiveMailCount[id]);
    }

    return answer;
}

int main() {
    vector<string> id_list = {"muzi", "frodo", "apeach", "neo"};
    vector<string> report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
    int k = 2;

    vector<int> result = solution(id_list, report, k);
    for (int count : result) {
        cout << count << " ";
    }
    return 0;
}
