function solution(n, k) {
    // n을 k진수로 변환
    const converted = convertToKBase(n, k);

    // 0을 기준으로 문자열 분할
    const numbers = converted.split('0');

    let count = 0;
    for (const numStr of numbers) {
        if (numStr) { // 빈 문자열 제외
            const num = parseInt(numStr, 10); // 정수로 변환
            if (isPrime(num)) {
                count++;
            }
        }
    }

    return count;
}

function convertToKBase(n, k) {
    let kBase = '';
    while (n > 0) {
        kBase = (n % k) + kBase; // 나머지를 추가 (앞에 추가)
        n = Math.floor(n / k);   // 몫을 업데이트
    }
    return kBase;
}

function isPrime(n) {
    // 1은 소수가 아님
    if (n <= 1) return false;

    // 2는 소수
    if (n === 2) return true;

    // 2부터 제곱근까지 나누어 떨어지는지 확인
    for (let i = 2; i <= Math.sqrt(n); i++) {
        if (n % i === 0) {
            return false;
        }
    }

    return true;
}

// 사용 예시
const n = 437674;
const k = 3;
console.log("Result:", solution(n, k));
