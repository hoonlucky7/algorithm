const N = 3;
const WIN = 1;
const DRAW = 0;
const LOSS = -1;
const UNKNOWN = -2;
const XPLAYER = 1;
const OPLAYER = 2;

// 보드에서 승자를 확인하는 함수
function checkWinner(board) {
    // 가로 확인
    for (let i = 0; i < N; i++) {
        if (board[i][0] !== 0 && board[i][0] === board[i][1] && board[i][1] === board[i][2]) {
            return board[i][0];
        }
    }
    // 세로 확인
    for (let j = 0; j < N; j++) {
        if (board[0][j] !== 0 && board[0][j] === board[1][j] && board[1][j] === board[2][j]) {
            return board[0][j];
        }
    }
    // 대각선 확인
    if (board[0][0] !== 0 && board[0][0] === board[1][1] && board[1][1] === board[2][2]) {
        return board[0][0];
    }
    if (board[0][2] !== 0 && board[0][2] === board[1][1] && board[1][1] === board[2][0]) {
        return board[0][2];
    }
    return UNKNOWN;
}

// 보드가 가득 찼는지 확인하는 함수
function isBoardFull(board) {
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < N; j++) {
            if (board[i][j] === 0) return false;
        }
    }
    return true;
}

// 미니맥스 알고리즘을 사용해 최적의 결과를 반환하는 함수
function minimax(board, player, startPlayer) {
    const winner = checkWinner(board);
    if (winner !== UNKNOWN) {
        return winner === startPlayer ? WIN : LOSS;
    }
    if (isBoardFull(board)) {
        return DRAW;
    }
    if (player === startPlayer) {
        let maxValue = -Infinity;
        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                if (board[i][j] === 0) {
                    board[i][j] = player;
                    const value = minimax(board, player === XPLAYER ? OPLAYER : XPLAYER, startPlayer);
                    maxValue = Math.max(maxValue, value);
                    board[i][j] = 0; // 백트래킹
                }
            }
        }
        return maxValue;
    } else {
        let minValue = Infinity;
        for (let i = 0; i < N; i++) {
            for (let j = 0; j < N; j++) {
                if (board[i][j] === 0) {
                    board[i][j] = player;
                    const value = minimax(board, player === XPLAYER ? OPLAYER : XPLAYER, startPlayer);
                    minValue = Math.min(minValue, value);
                    board[i][j] = 0; // 백트래킹
                }
            }
        }
        return minValue;
    }
}

// 표준 입력에서 데이터 읽기
const input = require('fs').readFileSync('/dev/stdin').toString().split('\n');

// 보드 생성: 첫 3줄을 사용해 2D 배열로 변환
const board = input.slice(0, 3).map(line => line.split(' ').map(Number));

// X와 O의 개수를 세어 현재 플레이어를 결정
let count = [0, 0, 0];
for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
        count[board[i][j]]++;
    }
}
const startPlayer = count[XPLAYER] === count[OPLAYER] ? XPLAYER : OPLAYER;

// 미니맥스 알고리즘 실행
const result = minimax(board, startPlayer, startPlayer);

// 결과 출력
console.log(result === WIN ? "W" : result === LOSS ? "L" : "D");