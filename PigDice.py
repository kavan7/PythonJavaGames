import random
import time
import os

scoreone = 0
scoretwo = 0

print("Welcome To Pig Dice! First Player to get 30 accumulative points wins! (Or however much you set it to)")
nameone = input("Please enter player one's username: ").capitalize()
nametwo = input("Please enter player two's username: ").capitalize()
limitask = input("Do you want to set a custom limit, 'Yes' or 'No' (Default limit is 30 points): ").upper()
if limitask == 'YES':
    limit = int(input("Please enter the score limit: "))
else:
    limit = 30


def clear_screen():
    os.system("cls" if os.name == "nt" else "clear")


def roll_dice():
    return random.randint(1, 6)


def print_dice(roll):
    if roll == 1:
        print("[-----]")
        print("[     ]")
        print("[  0  ]")
        print("[     ]")
        print("[-----]")
    elif roll == 2:
        print("[-----]")
        print("[ 0   ]")
        print("[     ]")
        print("[   0 ]")
        print("[-----]")
    elif roll == 3:
        print("[-----]")
        print("[     ]")
        print("[0 0 0]")
        print("[     ]")
        print("[-----]")
    elif roll == 4:
        print("[-----]")
        print("[0   0]")
        print("[     ]")
        print("[0   0]")
        print("[-----]")
    elif roll == 5:
        print("[-----]")
        print("[0   0]")
        print("[  0  ]")
        print("[0   0]")
        print("[-----]")
    elif roll == 6:
        print("[-----]")
        print("[0 0 0]")
        print("[     ]")
        print("[0 0 0]")
        print("[-----]")


def roll_effect():
    print("Rolling...")
    for _ in range(5):
        clear_screen()
        print_dice(roll_dice())
        time.sleep(0.1)
    clear_screen()
    return roll_dice()


def playerone():
    global scoreone
    print(f"{nameone} is rolling...")
    time.sleep(1)
    roll = roll_effect()
    print_dice(roll)
    time.sleep(2)
    clear_screen()
    if roll == 1:
        print("Oh no... You lose all your points")
        scoreone = 0
    else:
        scoreone += roll
    return roll


def playertwo():
    global scoretwo
    print(f"{nametwo} is rolling... ")
    time.sleep(1)
    roll = roll_effect()
    print_dice(roll)
    time.sleep(2)
    clear_screen()
    if roll == 1:
        print("Oh no... You lose all your points")
        scoretwo = 0
    else:
        scoretwo += roll
    return roll


def score_check(nameone, nametwo):
    print("Current Score")
    print(f"{nameone}: {scoreone} | {nametwo}: {scoretwo}")
    return ""


while True:
    cont = input(f"Type 'roll' when {nameone} wants to roll: ").upper()
    if cont == 'ROLL':
        roll = playerone()
        print(f"You got {roll}")
        if roll == 1:
            print(f"Onto {nametwo}")
            scoreone = 0
        else:
            response = input("Would you like to hold or roll again?: ").upper()
            if response == 'HOLD':
                scoreone += roll
                if scoreone >= limit:
                    print(f"Winner!\n{nameone} wins with a score of {scoreone}")
                    print(score_check(nameone, nametwo))
                    break
                if scoretwo >= limit:
                    print(f"Winner!\n{nametwo} wins with a score of {scoretwo}")
                    print(score_check(nameone, nametwo))
                    break
                print(f"Okay, onto {nametwo}...")
            else:
                continue

        print(score_check(nameone, nametwo))
        cont = input(f"Type 'roll' when {nametwo} wants to roll: ").upper()
        if cont == 'ROLL':
            roll = playertwo()
            print(f"You got {roll}")
            if roll == 1:
                print(f"Back to {nameone}")
                scoretwo = 0
            else:
                response = input("Would you like to hold or roll again?: ").upper()
                if response == 'HOLD':
                    scoretwo += roll
                    if scoreone >= limit:
                        print(f"Winner!\n{nameone} wins with a score of {scoreone}")
                        print(score_check(nameone, nametwo))
                        break
                    if scoretwo >= limit:
                        print(f"Winner!\n{nametwo} wins with a score of {scoretwo}")
                        print(score_check(nameone, nametwo))
                        break
                    print(f"Okay, onto {nameone}...")
                else:
                    continue

        print(score_check(nameone, nametwo))
        time.sleep(1)
