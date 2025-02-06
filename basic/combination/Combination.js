// Recursive function to generate combinations
function dfs(start, depth, combination) {
    if (depth === M) { // If the combination is complete
        output.push(combination.join(" "));
        return;
    }
    for (let i = start; i <= N; i++) {
        combination.push(i);              // Add number to the current combination
        dfs(i + 1, depth + 1, combination); // Recursively generate the next depth
        combination.pop();                // Backtrack: remove the last element
    }
}

// Read input from standard input
const fs = require('fs');
const input = fs.readFileSync('/dev/stdin').toString().trim().split(/\s+/);
const N = parseInt(input[0]); // Read N
const M = parseInt(input[1]); // Read M

let output = [];  // Array to collect all combinations as strings
dfs(1, 0, []);    // Generate combinations
console.log(output.join("\n")); // Print all stored combinations at once
