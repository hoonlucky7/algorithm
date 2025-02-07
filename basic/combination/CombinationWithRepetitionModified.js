const fs = require("fs");
// 백준
const fileAddr = '/dev/stdin'
// 로컬
//const fileAddr = __dirname + '/input.txt'
const input = fs.readFileSync(fileAddr).toString().trim().split(/\s+/);
const N = parseInt(input[0]); // Read N (range of numbers from 1 to N)
const M = parseInt(input[1]); // Read M (length of the sequence)

const sequenceCounts = new Array(N + 2).fill(0); // Array to store allocation counts (indices 1 to N)
const output = []; // Array to store each valid sequence as a string

// i: current target index, n: remaining count
function dfs(i, n) {
  if (i === N + 1) { // All targets have been processed
    if (n === 0) { // Process only valid combinations where the remaining count is 0
      let lineParts = [];
      for (let num = 1; num <= N; num++) {
        for (let c = 1; c <= sequenceCounts[num]; c++) {
          lineParts.push(num);
        }
      }
      output.push(lineParts.join(" "));
    }
    return;
  }
  // Distribute counts from n down to 0 for the current target i.
  // (Allocating from n down to 0 results in reverse order output)
  for (let count = n; count >= 0; count--) {
    sequenceCounts[i] = count;
    dfs(i + 1, n - count);
    sequenceCounts[i] = 0; // Backtracking: restore previous state
  }
}

dfs(1, M);
console.log(output.join("\n"));
