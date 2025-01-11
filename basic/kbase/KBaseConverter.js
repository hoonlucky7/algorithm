function toKBase(n, k) {
    let kBase = "";
    while (n > 0) {
        kBase += (n % k).toString(); // 나머지를 문자열로 추가
        n = Math.floor(n / k);
    }
    return kBase.split("").reverse().join(""); // 결과 문자열 뒤집기
}

// 테스트 코드
const n = 437674;
const k = 3;
console.log(`Number ${n} in base ${k} is: ${toKBase(n, k)}`);
