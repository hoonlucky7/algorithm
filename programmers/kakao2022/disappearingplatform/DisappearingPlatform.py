# Delta 값: 상, 하, 좌, 우 이동 (행, 열)
DELTA_ROW = [-1, 1, 0, 0]
DELTA_COL = [0, 0, -1, 1]

# 보드 경계 확인 함수
def is_out_of_bounds(row, col, board_height, board_width):
    return row < 0 or row >= board_height or col < 0 or col >= board_width

# 게임을 재귀적으로 진행하며 최적의 결과를 찾는 함수
def play_game(board, a_row, a_col, b_row, b_col, move_count, is_a_turn, board_height, board_width):
    current_row = a_row if is_a_turn else b_row
    current_col = a_col if is_a_turn else b_col

    # 1. 종료 조건
    # 1.1. 현재 위치가 경계를 벗어나거나 플랫폼이 없으면 패배
    if is_out_of_bounds(current_row, current_col, board_height, board_width) or board[current_row][current_col] == 0:
        return (False, move_count)

    # 1.2. 이동 가능한 위치가 없으면 패배
    can_move = False
    for i in range(4):
        next_row = current_row + DELTA_ROW[i]
        next_col = current_col + DELTA_COL[i]
        if not is_out_of_bounds(next_row, next_col, board_height, board_width) and board[next_row][next_col] == 1:
            can_move = True
            break
    if not can_move:
        return (False, move_count)

    # 2. 재귀 단계
    # 2.1. 현재 플레이어의 플랫폼 제거
    board[current_row][current_col] = 0

    # 2.2. 모든 가능한 이동에 대해 재귀 호출
    can_win = False
    min_moves_to_win = float('inf')
    max_moves_to_lose = 0

    for i in range(4):
        next_row = current_row + DELTA_ROW[i]
        next_col = current_col + DELTA_COL[i]

        if is_out_of_bounds(next_row, next_col, board_height, board_width) or board[next_row][next_col] == 0:
            continue

        if is_a_turn:
            next_result = play_game(board, next_row, next_col, b_row, b_col, move_count + 1, False, board_height, board_width)
        else:
            next_result = play_game(board, a_row, a_col, next_row, next_col, move_count + 1, True, board_height, board_width)

        if not next_result[0]:
            can_win = True
            min_moves_to_win = min(min_moves_to_win, next_result[1])
        else:
            max_moves_to_lose = max(max_moves_to_lose, next_result[1])

    # 2.3. 백트래킹: 플랫폼 복원
    board[current_row][current_col] = 1

    # 3. 결과 반환
    if can_win:
        return (True, min_moves_to_win)
    else:
        return (False, max_moves_to_lose)

# 최적의 이동 횟수를 계산하는 함수
def solution(board, aloc, bloc):
    board_height = len(board)
    board_width = len(board[0])

    result = play_game(board, aloc[0], aloc[1], bloc[0], bloc[1], 0, True, board_height, board_width)
    return result[1]

# 테스트 코드
if __name__ == "__main__":
    board1 = [[1, 1, 1], [1, 1, 1], [1, 1, 1]]
    aloc1 = [1, 0]
    bloc1 = [1, 2]
    print(f"Test Case 1: {solution(board1, aloc1, bloc1)} (Expected: 5)")

    board2 = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
    aloc2 = [1, 0]
    bloc2 = [1, 2]
    print(f"Test Case 2: {solution(board2, aloc2, bloc2)} (Expected: 4)")

    board3 = [[1, 1, 1, 1, 1]]
    aloc3 = [0, 0]
    bloc3 = [0, 4]
    print(f"Test Case 3: {solution(board3, aloc3, bloc3)} (Expected: 4)")

    board4 = [[1]]
    aloc4 = [0, 0]
    bloc4 = [0, 0]
    print(f"Test Case 4: {solution(board4, aloc4, bloc4)} (Expected: 0)")