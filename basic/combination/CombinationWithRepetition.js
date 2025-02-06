const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split(/\s+/);
const N = parseInt(input[0]); // Read N (range of numbers from 1 to N)
const M = parseInt(input[1]); // Read M (length of the sequence)

let output = []; // Array to store all combinations as strings

// Recursive function to generate combinations with repetition (duplicates allowed)
// It ensures non-decreasing order by starting the loop from 'start'
function dfs(start, depth, comb) {
    if (depth === M) { // When the sequence reaches length M, add it to the output array
        output.push(comb.join(" "));
        return;
    }
    for (let i = start; i <= N; i++) { // Loop from 'start' to 'N'
        comb.push(i);            // Add number to the current combination
        dfs(i, depth + 1, comb);   // Recursively call with the same index to allow duplicates
        comb.pop();              // Backtrack: remove the last element
    }
}

dfs(1, 0, []); // Start DFS with starting number 1 and initial depth 0

// Output all combinations at once
console.log(output.join("\n"));
