#include <iostream>
using namespace std;

int sum(int n) {
    if (n == 1) {
        return 1;
    }
    return n + sum(n - 1);
}

int main() {
    cout << sum(3) << endl; // Output: 6, 3 + 2 + 1 = 6
    return 0;
}