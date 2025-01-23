function sum(n) {
    if (n === 1) {
        return 1;
    }
    return n + sum(n - 1);
}

console.log(sum(3)); // Output: 6, 3 + 2 + 1 = 6