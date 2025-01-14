def solution(id_list, report, k):
    # 1) 전처리로 중복 제거
    distinct_reports = set(report)

    # 2) 신고당한 ID의 신고 횟수 기록 및 3) 신고당한 ID와 신고한 사람 ID 매핑
    reported_count = {}
    reported_to_reporters = {}

    for report_data in distinct_reports:
        reporter, reported = report_data.split()
        reported_count[reported] = reported_count.get(reported, 0) + 1
        if reported not in reported_to_reporters:
            reported_to_reporters[reported] = set()
        reported_to_reporters[reported].add(reporter)

    # 4) 각 유저별로 처리 결과 메일을 받은 횟수를 기록
    receive_mail_count = {id: 0 for id in id_list}

    for reported, count in reported_count.items():
        if count >= k:
            for reporter in reported_to_reporters[reported]:
                receive_mail_count[reporter] += 1

    # 5) id_list의 순서대로 처리 결과 메일을 받은 횟수 리턴
    return [receive_mail_count[id] for id in id_list]

if __name__ == "__main__":
    id_list = ["muzi", "frodo", "apeach", "neo"]
    report = ["muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"]
    k = 2

    result = solution(id_list, report, k)
    print(result)
