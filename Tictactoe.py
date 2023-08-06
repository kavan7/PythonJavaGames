import random


board = [' ' for _ in range(9)]
players = ['X', 'O']
current_player = players[0]
score = {players[0]: 0, players[1]: 0}


def display_board():
    print('-------------')
    for row in [board[i:i+3] for i in range(0, 9, 3)]:
        print('|', end='')
        for cell in row:
            print(f' {cell} |', end='')
        print('\n-------------')

def get_move():
    while True:
        try:
            position = int(input(f"{current_player}'s turn. Enter a number from 1 to 9: ")) - 1
            if position not in range(9) or board[position] != ' ':
                print('Invalid move. Try again.')
            else:
                return position
        except ValueError:
            print('Invalid input. Try again.')


def check_winner(player):
    winning_combinations = [
        [0, 1, 2], [3, 4, 5], [6, 7, 8], 
        [0, 3, 6], [1, 4, 7], [2, 5, 8], 
        [0, 4, 8], [2, 4, 6]             
    ]
    for combo in winning_combinations:
        if all(board[i] == player for i in combo):
            return True
    return False


def play_game():
    global current_player
    while True:
        display_board()
        move = get_move()
        board[move] = current_player
        if check_winner(current_player):
            display_board()
            print(f'Player {current_player} wins!')
            score[current_player] += 1
            break
        if ' ' not in board:
            display_board()
            print('It\'s a draw!')
            break
        current_player = players[(players.index(current_player) + 1) % 2]


def reset_game():
    global board
    board = [' ' for _ in range(9)]
    print('Score: ', score)


def start_game():
    reset_game()
    while True:
        play_game()
        choice = input('Play again? (y/n): ')
        if choice.lower() != 'y':
            break
        reset_game()


start_game()
