#include <iostream>
#include <vector>
#include <numeric>
#include <algorithm>

using namespace std;

int n, m;
vector<int> cards;
int max_sum = 0;

void dfs(int start, int depth, int sum) {
    if (sum > m) {
        return;
    }
    if (depth == 3) {
        if (sum <= m && sum > max_sum) {
            max_sum = sum;
        }
        return;
    }
    for (int i = start; i < n; ++i) {
        dfs(i + 1, depth + 1, sum + cards[i]);
    }
}

int main() {
    cin >> n >> m;
    cards.resize(n);
    for (int i = 0; i < n; ++i) {
        cin >> cards[i];
    }

    dfs(0, 0, 0);
    cout << max_sum << endl;

    return 0;
}