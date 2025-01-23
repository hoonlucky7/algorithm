// C++ implementation
#include <iostream>
#include <string>
using namespace std;

string toBaseK(int n, int k) {
    if (n < k) {
        return to_string(n);
    }
    return toBaseK(n / k, k) + to_string(n % k);
}

int main() {
    int number = 25;
    int base = 3;

    string converted = toBaseK(number, base);
    cout << number << " in base " << base << " is " << converted << endl;
    // Output: 25 in base 3 is 221
    return 0;
}