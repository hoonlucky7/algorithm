function factorial(n) {
    if (n === 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

function fac3(n) {
    return n * fac2(n - 1);
}

function fac2(n) {
    return n * fac1(n - 1);
}

function fac1(n) {
    return 1;
}

console.log(fac3(3)); // Output: 6
console.log(factorial(3)); // Output: 6