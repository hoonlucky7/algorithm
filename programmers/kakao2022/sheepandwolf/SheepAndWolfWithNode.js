class Node {
    constructor(data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

let maxSheepCount = 0;
let totalSheepCount = 0;
let infoData = [];
let nodeList = [];
let candidateNodesSet = new Set();

function dfs(current, sheep, wolf) {
    maxSheepCount = Math.max(maxSheepCount, sheep);

    if (sheep === totalSheepCount) return;
    if (candidateNodesSet.size === 0) return;

    const currentCandidateNodes = new Set(candidateNodesSet);
    for (const next of currentCandidateNodes) {
        let nextSheep = sheep;
        let nextWolf = wolf;

        if (infoData[next.data] === 0) {
            nextSheep++;
        } else {
            nextWolf++;
        }

        if (nextSheep <= nextWolf) {
            continue;
        }

        candidateNodesSet.delete(next);
        if (next.left) candidateNodesSet.add(next.left);
        if (next.right) candidateNodesSet.add(next.right);

        dfs(next, nextSheep, nextWolf);

        candidateNodesSet.add(next);
        if (next.left) candidateNodesSet.delete(next.left);
        if (next.right) candidateNodesSet.delete(next.right);
    }
}

function solution(info, edges) {
    maxSheepCount = 0; // Reset maxSheepCount for each call to solution
    totalSheepCount = 0;
    infoData = info;
    nodeList = [];
    candidateNodesSet = new Set();


    const n = info.length;
    nodeList = Array.from({ length: n }, (_, i) => new Node(i));

    for (const edge of edges) {
        const parent = edge[0];
        const child = edge[1];
        const parentNode = nodeList[parent];
        const childNode = nodeList[child];

        if (!parentNode.left) {
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
    }

    totalSheepCount = 0;
    for (let i = 0; i < n; i++) {
        if (info[i] === 0) {
            totalSheepCount++;
        }
    }

    candidateNodesSet = new Set();
    const rootNode = nodeList[0];
    if (rootNode.left) candidateNodesSet.add(rootNode.left);
    if (rootNode.right) candidateNodesSet.add(rootNode.right);


    dfs(rootNode, 1, 0);
    return maxSheepCount;
}


// Example Usage:
const info1 = [0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1];
const edges1 = [[0, 1], [1, 2], [1, 4], [0, 8], [8, 7], [9, 10], [9, 11], [4, 3], [6, 5], [4, 6], [8, 9]];
console.log("Test 1 Result:", solution(info1, edges1));

const info2 = [0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0];
const edges2 = [[0, 1], [0, 2], [1, 3], [1, 4], [2, 5], [2, 6], [3, 7], [4, 8], [6, 9], [9, 10]];
console.log("Test 2 Result:", solution(info2, edges2));