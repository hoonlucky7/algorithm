#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

string toKBase(int n, int k) {
    string kBase;
    while (n > 0) {
        kBase += to_string(n % k); // 나머지를 문자열로 추가
        n = n / k;
    }
    reverse(kBase.begin(), kBase.end()); // 결과 문자열 뒤집기
    return kBase;
}

int main() {
    int n = 437674, k = 3;

    cout << "Number " << n << " in base " << k << " is: " << toKBase(n, k) << endl;

    return 0;
}
