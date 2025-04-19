const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

const [N, K] = input[0].split(" ").map(Number);

function findSister(n, k) {
    // 수빈이와 동생의 위치가 같으면 시간이 필요 없음
    if (n === k) {
        return 0;
    }

    const MAX = 100001;
    // 방문 여부를 체크하는 배열
    const visited = Array(MAX).fill(false);
    // 큐에 [위치, 시간]을 저장
    const queue = [[n, 0]];
    visited[n] = true;
    let front = 0; // 큐의 앞쪽 인덱스

    // BFS 탐색
    while (front < queue.length) {
        const [pos, time] = queue[front++];

        // 가능한 세 가지 이동
        const nextPositions = [pos - 1, pos + 1, pos * 2];

        for (const nextPos of nextPositions) {
            // 이동 위치가 범위 내이고 방문하지 않은 경우
            if (nextPos >= 0 && nextPos < MAX && !visited[nextPos]) {
                // 동생의 위치에 도달하면 시간 반환
                if (nextPos === k) {
                    return time + 1;
                }
                // 방문 표시 후 큐에 추가
                visited[nextPos] = true;
                queue.push([nextPos, time + 1]);
            }
        }
    }

    return -1; // 도달 불가능한 경우 (문제 조건상 발생하지 않음)
}

// 결과 출력
console.log(findSister(N, K));