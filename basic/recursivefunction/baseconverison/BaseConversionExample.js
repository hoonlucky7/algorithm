function toBaseK(n, k) {
    if (n < k) {
        return n.toString();
    }
    return toBaseK(Math.floor(n / k), k) + (n % k).toString();
}

const number = 25;
const base = 3;

const converted = toBaseK(number, base);
console.log(`${number} in base ${base} is ${converted}`);
// Output: 25 in base 3 is 221
