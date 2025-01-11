function sieve(n) {
    const isPrime = new Array(n + 1).fill(true);

    isPrime[0] = false; // 0은 소수가 아님
    isPrime[1] = false; // 1은 소수가 아님

    for (let i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            for (let j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    return isPrime;
}

// 테스트 케이스 실행
function runTests() {
    const testCases = [100];

    testCases.forEach((n, idx) => {
        console.log(`Test Case ${idx + 1}: n=${n}`);
        const primes = sieve(n);
        const primeNumbers = [];
        for (let i = 2; i <= n; i++) {
            if (primes[i]) {
                primeNumbers.push(i);
            }
        }
        console.log(primeNumbers.join(" ")); // 한 줄로 출력
    });
}

runTests();
