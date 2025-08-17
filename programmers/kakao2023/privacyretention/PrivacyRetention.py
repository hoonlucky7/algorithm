def solution(today, terms, privacies):
    # 1) 약관 종류 → 보관 개월 수 매핑
    term_months = {}
    for t in terms:
        kind, months = t.split()
        term_months[kind] = int(months)

    # 2) 날짜를 총 일수로 변환하는 함수 (모든 달은 28일로 가정)
    def to_days(date):
        y, m, d = map(int, date.split('.'))
        return y * 12 * 28 + m * 28 + d

    today_days = to_days(today)

    # 3) 각 개인정보의 파기 여부 확인
    result = []
    for i, p in enumerate(privacies, 1):  # 개인정보 번호는 1부터 시작
        collected_date, kind = p.split()
        start_days = to_days(collected_date)
        months = term_months[kind]

        destroy_start = start_days + months * 28  # 파기 시작일

        if destroy_start <= today_days:
            result.append(i)

    return result

today = "2022.05.19"
terms = ["A 6", "B 12", "C 3"]
privacies = [
    "2021.05.02 A",
    "2021.07.01 B",
    "2022.02.19 C",
    "2022.02.20 C"
]

print(solution(today, terms, privacies))  # [1, 3]