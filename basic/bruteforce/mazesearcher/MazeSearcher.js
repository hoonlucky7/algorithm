const fs = require("fs");
const input = fs.readFileSync("/dev/stdin").toString().trim().split("\n");

// 첫 줄의 "4 6"을 공백으로 분리하여 n과 m에 할당
const [n, m] = input[0].split(" ").map(Number);

// 이후 n개의 줄을 읽어 maze 배열에 저장
let maze = [];
for (let i = 1; i <= n; i++) {
    // 각 줄의 문자열을 문자 단위로 분리 후 숫자로 변환
    maze.push(input[i].trim().split("").map(Number));
}

function bfs(maze, n, m) {
    const queue = [];
    queue.push([0, 0]);
    const directions = [[-1, 0], [1, 0], [0, -1], [0, 1]];
    
    while (queue.length > 0) {
        const [r, c] = queue.shift();
        for (const [dr, dc] of directions) {
            const nr = r + dr;
            const nc = c + dc;
            // 범위 확인과 이동 가능한 칸(값이 1) 체크
            if (nr >= 0 && nr < n && nc >= 0 && nc < m && maze[nr][nc] === 1) {
                maze[nr][nc] = maze[r][c] + 1;
                queue.push([nr, nc]);
            }
        }
    }
    
    return maze[n - 1][m - 1];
}

console.log(bfs(maze, n, m));
