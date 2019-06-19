import mylib as m
from datetime import datetime


pcAlgorithm = 'min'
choice = ''
game = 'default'


while choice != 'q':
    
    print('***************************************** SCRABBLE *****************************************')
    print('--------------------------------------------------------------------------------------------')
    print('1: Score')
    print('2: Settings')
    print('3: Game')
    print('q: Exit')
    choice = input('Select your choice (1/ 2/ 3/ q): ')

    if choice == '1':
        print()
        try:
            with open('score.txt', 'r') as score:
                print(score.read())
        except FileNotFoundError:
            print('There are no past games\n')
    elif choice == '2':
        print('\n1: AI level')
        print('2: Selection of players')
        setting = input('Select your choice (1/ 2): ')
        if setting == '1':
            print('\n1: low')
            print('2: medium')
            print('3: hard')
            level = input('Select your choice (1/ 2/ 3): ')
            print()
            if level == '2':
                pcAlgorithm = 'max'
            elif level == '1':
                pcAlgorithm = 'min'
            elif level == '3':
                pcAlgorithm = 'smart'
        elif setting == '2':
            print('\n1: Player vs AI')
            print('2: Player vs Player')
            players = input('Select your choice (1/ 2): ')
            print()
            if players == '2':
                game = '2players'
            elif players == '1':
                game = 'default'
    elif choice == '3':
        if game == 'default':
            print('\nStart of the game\n')
            name = input('Player name: ')
            # παιχνίδι
            
            m.makeFile()
            
            sak = m.SakClass()
            human = m.Player(name)
            pc = m.PcPlayer()

            turn = 0
            
            new = sak.randomLetters(7 - len(human.playerLetters))
            human.playerLetters += new

            new = sak.randomLetters(7 - len(pc.playerLetters))
            pc.playerLetters += new

            while sak.letters > 0:
                if turn % 2 == 0:
                    
                    turn += 1
                    if len(human.playerLetters) < 7:
                        new = sak.randomLetters(7 - len(human.playerLetters))
                        human.playerLetters += new
                    print('In the bag: ', sak.letters, ' letters - You are playing: (p for pass, q for quit)')
                    print('Available letters: ', human.playerLetters)
                    answer = input('Word: ')
                    if answer == 'p':
                        sak.letters += len(human.playerLetters)
                        for i in human.playerLetters:
                            if ord(list(i.keys())[0]) - ord('Α') > 17:
                                sak.lettersList[ord(list(i.keys())[0]) - ord('Α') - 1] += 1
                            else:
                                sak.lettersList[ord(list(i.keys())[0]) - ord('Α')] += 1
                        human.playerLetters = []
                        print(
                            '--------------------------------------------------------------------------------------------')
                        continue
                    elif answer == 'q':
                        break
                    else:
                        wordOk = True
                        temp = []
                        for k in human.playerLetters:
                            temp.append(list(k.keys())[0])
                        for i in answer:
                            exists = False
                            for j in temp:
                                if i == j:
                                    temp.remove(j)
                                    exists = True
                                    break
                            wordOk = wordOk and exists
                        if wordOk:
                            if answer in m.lexiko:
                                scoreLexis = m.lexiko.get(answer)
                                human.score += scoreLexis
                                for i in answer:
                                    for x in human.playerLetters:
                                        if i == list(x.keys())[0]:
                                            human.playerLetters.remove(x)
                                            break
                                print('Accepted Word - Points: ', scoreLexis, ' - Score: ', human.score)
                                input('Enter to continue')
                                print(
                                    '--------------------------------------------------------------------------------------------')
                            else:
                                turn -= 1
                                print('Type a word that belongs in the accepted words of the dictionary.')
                                continue
                        else:
                            turn -= 1
                            print('Type a word that consists of the available letters.')
                            continue
                else:
                    turn += 1
                    if len(pc.playerLetters) < 7:
                        new = sak.randomLetters(7 - len(pc.playerLetters))
                        pc.playerLetters += new
                    print('In the bag: ', sak.letters, ' letters - The AI is playing:')
                    # temp
                    print('Available letters: ', pc.playerLetters)
                    if pcAlgorithm == 'max':
                        answer = pc.maxLetters()
                    elif pcAlgorithm == 'smart':
                        answer = pc.smart()
                    else:
                        answer = pc.minLetters()
                    if answer == 'q':
                        break
                    else:
                        scoreLexis = m.lexiko.get(answer)
                        pc.score += scoreLexis
                        for i in answer:
                            for j in pc.playerLetters:
                                if i == list(j.keys())[0]:
                                    pc.playerLetters.remove(j)
                        print('Word AI: ', answer, ' Points: ', scoreLexis, ' - AI score: ', pc.score)
                        print(
                            '--------------------------------------------------------------------------------------------')

            if human.score >= pc.score:
                winner = name
            else:
                winner = 'AI'

            scoreFile = open('score.txt', 'a', encoding='utf8')
            scoreFile.write('Game: ' + datetime.now().strftime('%Y-%m-%d %H:%M:%S') + '\n')
            scoreFile.write('Winner: ' + winner + '\n')
            scoreFile.write('Player score: ' + str(human.score) + '\n')
            scoreFile.write('AI score: ' + str(pc.score) + '\n')
            scoreFile.write('Total # of moves: ' + str(turn) + '\n')
            scoreFile.write('\n')
            scoreFile.close()

            print('--------------------------------------------------------------------------------------------')
            print('End of game\n')
            print('Winner: ' + winner)
            print('Player score: ', human.score)
            print('AI score: ', pc.score)
            print('--------------------------------------------------------------------------------------------')
        elif game == '2players':
            two.scrabble()
