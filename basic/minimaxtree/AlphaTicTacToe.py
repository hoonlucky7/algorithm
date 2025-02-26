N = 3
WIN = 1
DRAW = 0
LOSS = -1
UNKNOWN = -2
XPLAYER = 1
OPLAYER = 2

def check_winner(board):
    # Check rows
    for i in range(N):
        if board[i][0] != 0 and board[i][0] == board[i][1] == board[i][2]:
            return board[i][0]
    # Check columns
    for j in range(N):
        if board[0][j] != 0 and board[0][j] == board[1][j] == board[2][j]:
            return board[0][j]
    # Check diagonals
    if board[0][0] != 0 and board[0][0] == board[1][1] == board[2][2]:
        return board[0][0]
    if board[0][2] != 0 and board[0][2] == board[1][1] == board[2][0]:
        return board[0][2]
    return UNKNOWN

def is_board_full(board):
    for i in range(N):
        for j in range(N):
            if board[i][j] == 0:
                return False
    return True

def minimax(board, player, start_player):
    winner = check_winner(board)
    if winner != UNKNOWN:
        return WIN if winner == start_player else LOSS
    if is_board_full(board):
        return DRAW
    if player == start_player:
        max_value = float('-inf')
        for i in range(N):
            for j in range(N):
                if board[i][j] == 0:
                    board[i][j] = player
                    value = minimax(board, OPLAYER if player == XPLAYER else XPLAYER, start_player)
                    max_value = max(max_value, value)
                    board[i][j] = 0  # Backtrack
        return max_value
    else:
        min_value = float('inf')
        for i in range(N):
            for j in range(N):
                if board[i][j] == 0:
                    board[i][j] = player
                    value = minimax(board, OPLAYER if player == XPLAYER else XPLAYER, start_player)
                    min_value = min(min_value, value)
                    board[i][j] = 0  # Backtrack
        return min_value

# Main logic
board = [list(map(int, input().split())) for _ in range(N)]
count = [0] * 3
for i in range(N):
    for j in range(N):
        count[board[i][j]] += 1
start_player = XPLAYER if count[XPLAYER] == count[OPLAYER] else OPLAYER
result = minimax(board, start_player, start_player)
print("W" if result == WIN else "L" if result == LOSS else "D")