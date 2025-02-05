// The key point of combination with repetition is to allow duplicate selections 
// by recursively calling starting from the current index (i.e., maintaining the start value),
// and to prevent duplicate combinations by keeping the sequence in non-decreasing order.

const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split(/\s+/);
const N = parseInt(input[0]); // Read N (range of numbers from 1 to N)
const M = parseInt(input[1]); // Read M (length of the sequence)

function dfs(start, depth, comb) {
    if (depth === M) { // If the sequence reaches length M, output the combination
        console.log(comb.join(" "));
        return;
    }
    for (let i = start; i <= N; i++) { // Ensure non-decreasing order
        comb.push(i);             // Add number to the current combination
        dfs(i, depth + 1, comb);    // Recursive call with same 'i' to allow duplicates
        comb.pop();               // Backtrack: remove the last element
    }
}

dfs(1, 0, []);
