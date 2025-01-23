#include <iostream>
using namespace std;

int factorial(int n) {
    if (n == 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

int fac1(int n) {
    return 1;
}

int fac2(int n) {
    return n * fac1(n - 1);
}

int fac3(int n) {
    return n * fac2(n - 1);
}

int main() {
    cout << fac3(3) << endl; // Output: 6
    cout << factorial(3) << endl; // Output: 6
    return 0;
}