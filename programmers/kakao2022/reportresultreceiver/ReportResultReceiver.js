function solution(id_list, report, k) {
    // 1) 전처리로 중복 제거
    const distinctReports = new Set(report);

    // 2) 신고당한 ID의 신고 횟수 기록 및 3) 신고당한 ID와 신고한 사람 ID 매핑
    const reportedCount = {};
    const reportedToReporters = {};

    distinctReports.forEach(reportData => {
        const [reporter, reported] = reportData.split(" ");
        reportedCount[reported] = (reportedCount[reported] || 0) + 1;
        if (!reportedToReporters[reported]) {
            reportedToReporters[reported] = new Set();
        }
        reportedToReporters[reported].add(reporter);
    });

    // 4) 각 유저별로 처리 결과 메일을 받은 횟수를 기록
    const receiveMailCount = id_list.reduce((acc, id) => {
        acc[id] = 0;
        return acc;
    }, {});

    Object.entries(reportedCount).forEach(([reported, count]) => {
        if (count >= k) {
            reportedToReporters[reported].forEach(reporter => {
                receiveMailCount[reporter] += 1;
            });
        }
    });

    // 5) id_list의 순서대로 처리 결과 메일을 받은 횟수 리턴
    return id_list.map(id => receiveMailCount[id]);
}

// 테스트
const id_list = ["muzi", "frodo", "apeach", "neo"];
const report = ["muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"];
const k = 2;

const result = solution(id_list, report, k);
console.log(result);
