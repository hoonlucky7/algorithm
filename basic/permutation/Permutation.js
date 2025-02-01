// 백준
const fileAddr = '/dev/stdin'
// 로컬
//const fileAddr = __dirname + '/input.txt'

const input = require('fs')
  .readFileSync(fileAddr)
  .toString()
  .split('\n')
  .slice(0, -1);

const NM = input[0].split(" ");
const [n, m] = NM.map(Number);

function solution(n, m) {
    // Array to store the current sequence (length m)
    const seq = Array(m).fill(0);
    // Boolean array to track used numbers (1-indexed, so length n+1)
    const visited = Array(n + 1).fill(false);
    let result = "";

    // DFS function to generate all permutations
    function dfs(k) {
        if (k === m) {
            result += seq.join(' ') + "\n";
            return;
        }
        for (let i = 1; i <= n; i++) {
            if (!visited[i]) {
                seq[k] = i;
                visited[i] = true;
                dfs(k + 1);
                visited[i] = false;  // Backtracking
            }
        }
    }

    dfs(0);
    return result;
}

console.log(solution(n, m));
