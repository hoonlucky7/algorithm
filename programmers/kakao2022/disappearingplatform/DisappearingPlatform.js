// Delta 값: 상, 하, 좌, 우 이동 (행, 열)
const DELTA_ROW = [-1, 1, 0, 0];
const DELTA_COL = [0, 0, -1, 1];

// 보드 경계 확인 함수
function isOutOfBounds(row, col, boardHeight, boardWidth) {
    return row < 0 || row >= boardHeight || col < 0 || col >= boardWidth;
}

// 게임을 재귀적으로 진행하며 최적의 결과를 찾는 함수
function playGame(board, aRow, aCol, bRow, bCol, moveCount, isATurn, boardHeight, boardWidth) {
    let currentRow = isATurn ? aRow : bRow;
    let currentCol = isATurn ? aCol : bCol;

    // 1. 종료 조건
    // 1.1. 현재 위치가 경계를 벗어나거나 플랫폼이 없으면 패배
    if (isOutOfBounds(currentRow, currentCol, boardHeight, boardWidth) || board[currentRow][currentCol] === 0) {
        return { canWin: false, moveCount };
    }

    // 1.2. 이동 가능한 위치가 없으면 패배
    let canMove = false;
    for (let i = 0; i < 4; i++) {
        let nextRow = currentRow + DELTA_ROW[i];
        let nextCol = currentCol + DELTA_COL[i];
        if (!isOutOfBounds(nextRow, nextCol, boardHeight, boardWidth) && board[nextRow][nextCol] === 1) {
            canMove = true;
            break;
        }
    }
    if (!canMove) {
        return { canWin: false, moveCount };
    }

    // 2. 재귀 단계
    // 2.1. 현재 플레이어의 플랫폼 제거
    board[currentRow][currentCol] = 0;

    // 2.2. 모든 가능한 이동에 대해 재귀 호출
    let canWin = false;
    let minMovesToWin = Infinity;
    let maxMovesToLose = 0;

    for (let i = 0; i < 4; i++) {
        let nextRow = currentRow + DELTA_ROW[i];
        let nextCol = currentCol + DELTA_COL[i];

        if (isOutOfBounds(nextRow, nextCol, boardHeight, boardWidth) || board[nextRow][nextCol] === 0) {
            continue;
        }

        let nextResult;
        if (isATurn) {
            nextResult = playGame(board, nextRow, nextCol, bRow, bCol, moveCount + 1, false, boardHeight, boardWidth);
        } else {
            nextResult = playGame(board, aRow, aCol, nextRow, nextCol, moveCount + 1, true, boardHeight, boardWidth);
        }

        if (!nextResult.canWin) {
            canWin = true;
            minMovesToWin = Math.min(minMovesToWin, nextResult.moveCount);
        } else {
            maxMovesToLose = Math.max(maxMovesToLose, nextResult.moveCount);
        }
    }

    // 2.3. 백트래킹: 플랫폼 복원
    board[currentRow][currentCol] = 1;

    // 3. 결과 반환
    if (canWin) {
        return { canWin: true, moveCount: minMovesToWin };
    } else {
        return { canWin: false, moveCount: maxMovesToLose };
    }
}

// 최적의 이동 횟수를 계산하는 함수
function solution(board, aloc, bloc) {
    let boardHeight = board.length;
    let boardWidth = board[0].length;

    let result = playGame(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, true, boardHeight, boardWidth);
    return result.moveCount;
}

// 테스트 코드
let board1 = [[1, 1, 1], [1, 1, 1], [1, 1, 1]];
let aloc1 = [1, 0];
let bloc1 = [1, 2];
console.log(`Test Case 1: ${solution(board1, aloc1, bloc1)} (Expected: 5)`);

let board2 = [[1, 1, 1], [1, 0, 1], [1, 1, 1]];
let aloc2 = [1, 0];
let bloc2 = [1, 2];
console.log(`Test Case 2: ${solution(board2, aloc2, bloc2)} (Expected: 4)`);

let board3 = [[1, 1, 1, 1, 1]];
let aloc3 = [0, 0];
let bloc3 = [0, 4];
console.log(`Test Case 3: ${solution(board3, aloc3, bloc3)} (Expected: 4)`);

let board4 = [[1]];
let aloc4 = [0, 0];
let bloc4 = [0, 0];
console.log(`Test Case 4: ${solution(board4, aloc4, bloc4)} (Expected: 0)`);