#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

class Node {
public:
    int data; // Node number
    Node* left;
    Node* right;

    // Constructor: Initializes the node with data value.
    Node(int data) : data(data), left(nullptr), right(nullptr) {}
};

int maxSheepCount = 0;
int totalSheepCount = 0;
vector<int> infoData;
vector<Node*> nodeList;
set<Node*> candidateNodesSet;

void dfs(Node* current, int sheep, int wolf) {
    maxSheepCount = max(maxSheepCount, sheep);

    if (sheep == totalSheepCount) return;
    if (candidateNodesSet.empty()) return;

    set<Node*> currentCandidateNodes = candidateNodesSet;
    for (Node* next : currentCandidateNodes) {
        int nextSheep = sheep;
        int nextWolf = wolf;

        if (infoData[next->data] == 0) {
            nextSheep++;
        } else {
            nextWolf++;
        }

        if (nextSheep <= nextWolf) {
            continue;
        }

        candidateNodesSet.erase(next);
        if (next->left != nullptr) candidateNodesSet.insert(next->left);
        if (next->right != nullptr) candidateNodesSet.insert(next->right);

        dfs(next, nextSheep, nextWolf);

        candidateNodesSet.insert(next);
        if (next->left != nullptr) candidateNodesSet.erase(next->left);
        if (next->right != nullptr) candidateNodesSet.erase(next->right);
    }
}

int solution(vector<int> info, vector<vector<int>> edges) {
    infoData = info;
    int n = info.size();

    nodeList.resize(n);
    for (int i = 0; i < n; i++) {
        nodeList[i] = new Node(i);
    }

    for (const auto& edge : edges) {
        int parent = edge[0];
        int child = edge[1];
        Node* parentNode = nodeList[parent];
        Node* childNode = nodeList[child];

        if (parentNode->left == nullptr) {
            parentNode->left = childNode;
        } else {
            parentNode->right = childNode;
        }
    }

    totalSheepCount = 0;
    for (int i = 0; i < n; i++) {
        if (info[i] == 0) {
            totalSheepCount++;
        }
    }

    candidateNodesSet.clear();
    Node* rootNode = nodeList[0];
    if (rootNode->left != nullptr) candidateNodesSet.insert(rootNode->left);
    if (rootNode->right != nullptr) candidateNodesSet.insert(rootNode->right);

    maxSheepCount = 0; // Reset maxSheepCount for each call to solution
    dfs(rootNode, 1, 0);
    return maxSheepCount;
}

// Example Usage (main function - you can add tests here)
int main() {
    vector<int> info1 = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
    vector<vector<int>> edges1 = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
    cout << "Test 1 Result: " << solution(info1, edges1) << endl;

    vector<int> info2 = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
    vector<vector<int>> edges2 = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}};
    cout << "Test 2 Result: " << solution(info2, edges2) << endl;

    return 0;
}