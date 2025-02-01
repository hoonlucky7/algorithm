const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');
const [N, M] = input[0].split(" ").map(Number);

let sequence = Array(M).fill(0); // Array to store the current sequence
let result = "";                 // String to accumulate the output

// DFS function: Selects numbers at the current depth
function dfs(depth) {
    if (depth === M) {
        result += sequence.join(" ") + "\n";
        return;
    }
    for (let i = 1; i <= N; i++) {
        sequence[depth] = i;
        dfs(depth + 1);
        sequence[depth] = 0; // Reset the value
    }
}

dfs(0);
console.log(result);
