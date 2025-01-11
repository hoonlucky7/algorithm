#include <iostream>
#include <string>
#include <vector>
#include <cmath>

using namespace std;

bool isPrime(int number) {
    if (number <= 1) return false;
    for (int i = 2; i <= std::sqrt(number); i++) {
        if (number % i == 0) return false;
    }
    return true;
}

// 테스트 케이스 실행
void runTests() {
    vector<int> testCases = {2, 3, 4, 5};

    for (size_t i = 0; i < testCases.size(); ++i) {
        int n = testCases[i];

        cout << "Test Case " << i + 1 << ": n=" << n << endl;
        bool result = isPrime(n);
        cout << "Result: " << result << endl;
    }
}

int main() {
    runTests();
    return 0;
}