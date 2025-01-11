#include <iostream>
#include <string>
#include <vector>
#include <cmath>

using namespace std;

std::vector<bool> sieve(int n) {
    std::vector<bool> isPrime(n + 1, true);

    isPrime[0] = false; // 0은 소수가 아님
    isPrime[1] = false; // 1은 소수가 아님

    for (int i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            for (int j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    return isPrime;
}

// 테스트 케이스 실행
void runTests() {
    vector<int> testCases = {100};

    for (size_t i = 0; i < testCases.size(); ++i) {
        int n = testCases[i];

        cout << "Test Case " << i + 1 << ": n=" << n << endl;
        vector<bool> primes = sieve(n);
        for (int i = 2; i <= n; i++) {
            if (primes[i]) {
                cout << i << " ";
            }
        }
        cout << endl;
    }
}

int main() {
    runTests();
    return 0;
}