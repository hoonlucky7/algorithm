#include <iostream>
#include <string>
#include <vector>
#include <cmath>
#include <sstream>

using namespace std;
// 숫자를 k진수로 변환하는 메서드
string convertToKBase(int n, int k) {
    string kBase;
    while (n > 0) {
        kBase += to_string(n % k); // 나머지를 추가
        n /= k;                        // 몫을 업데이트
    }
    kBase = string(kBase.rbegin(), kBase.rend());
    return kBase;
}

// 문자열을 0을 기준으로 분리하는 메서드
vector<string> splitByZero(const string& str) {
    vector<string> result;
    stringstream ss(str);
    string token;

    while (getline(ss, token, '0')) {
        result.push_back(token);
    }

    return result;
}

// 소수 판별 메서드
bool isPrime(long n) {
    // 1은 소수가 아님
    if (n <= 1) return false;

    // 2는 소수
    if (n == 2) return true;

    // 2부터 제곱근까지 나누어 떨어지는지 확인
    for (long i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) {
            return false;
        }
    }

    return true;
}

int solution(int n, int k) {
     // n을 k진수로 변환
    string converted = convertToKBase(n, k);

    // 0을 기준으로 문자열 분할
    vector<string> numbers = splitByZero(converted);

    int count = 0;
    for (const string& numStr : numbers) {
        if (!numStr.empty()) { // 빈 문자열 제외
            long num = stol(numStr); // long으로 선언하는 것이 중요
            if (isPrime(num)) {
                count++;
            }
        }
    }

    return count;
}
