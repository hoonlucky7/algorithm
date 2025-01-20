'''
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
SOFTWARE.'''

import math

def get_fee(basic_time, basic_fee, unit_time, unit_fee, total_time):
    """주차 요금을 계산하는 함수"""
    over_time = total_time - basic_time
    if over_time <= 0:
        return basic_fee
    over_unit = math.ceil(over_time / unit_time)
    return basic_fee + (over_unit * unit_fee)

def convert_time_to_minute(time_str):
    """HH:MM 형식의 시간을 분 단위로 변환"""
    hour, minute = map(int, time_str.split(':'))
    return hour * 60 + minute

def solution(fees, records):
    """주차 요금을 계산하는 메인 함수"""
    basic_time, basic_fee, unit_time, unit_fee = fees

    # 차량 번호가 0부터 9999까지이므로 배열을 사용하여 관리
    MAX_CARS = 10000
    IN_TIME = 'in_time'
    TOTAL_TIME = 'total_time'
    parking_data = [{IN_TIME: None, TOTAL_TIME: 0} for _ in range(MAX_CARS)]

    # 1. 입/출차 기록 처리
    for record in records:
        time_str, car_num_str, status = record.split()
        car_num = int(car_num_str)
        time_int = convert_time_to_minute(time_str)

        if status == "IN":
            parking_data[car_num][IN_TIME] = time_int  # 입차 시간 기록
        else:
            in_time = parking_data[car_num][IN_TIME]
            parking_data[car_num][TOTAL_TIME] += (time_int - in_time)  # 누적 주차 시간 계산
            parking_data[car_num][IN_TIME] = None  # 입차 시간 초기화

    # 2. 입차 중인 차량은 23:59에 출차한 것으로 간주
    end_of_day = convert_time_to_minute("23:59")
    for car_info in parking_data:
        if car_info[IN_TIME] is not None:
            car_info[TOTAL_TIME] += (end_of_day - car_info[IN_TIME])
            car_info[IN_TIME] = None  # 입차 시간 초기화

    # 3. 차량 번호 기준으로 오름차순 정렬 후 최종 요금 계산
    answer = []
    for car_num, car_info in enumerate(parking_data):
        if car_info[TOTAL_TIME] > 0:
            total_time = car_info[TOTAL_TIME]
            answer.append(get_fee(basic_time, basic_fee, unit_time, unit_fee, total_time))

    return answer

# 테스트 함수
def run_test():
    fees = [180, 5000, 10, 600]  # 기본 시간: 180분, 기본 요금: 5000원, 단위 시간: 10분, 단위 요금: 600원
    records = [
        "05:34 5961 IN",
        "06:00 0000 IN",
        "06:34 0000 OUT",
        "07:59 5961 OUT",
        "07:59 0148 IN",
        "18:59 0000 IN",
        "19:09 0148 OUT",
        "22:59 5961 IN",
        "23:00 5961 OUT"
    ]

    result = solution(fees, records)
    print("Test Case 1 Result:", result)

    # 추가 테스트 케이스
    fees = [120, 0, 60, 591]
    records = [
        "16:00 3961 IN",
        "16:00 0202 IN",
        "18:00 3961 OUT",
        "18:00 0202 OUT",
        "23:58 3961 IN"
    ]

    result = solution(fees, records)
    print("Test Case 2 Result:", result)

    fees = [1, 461, 1, 10]
    records = [
        "00:00 1234 IN"
    ]

    result = solution(fees, records)
    print("Test Case 3 Result:", result)

if __name__ == "__main__":
    run_test()
