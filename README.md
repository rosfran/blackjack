# Blackjack Game

Blackjack is a game of cards. It is also called a casino banked game, meaning that players compete against the
house rather than each other. 

# Reference

Blackjack Game Rules - https://www.pagat.com/banking/blackjack.html

# How it works

You, as a Player (User), will receive 2 cards, and the same number of cards will be given to the Dealer.
You should use the keyboard to enter with your name, or the name you will be identified in the game, and
after that you will have 3 options - to start a new game, shuffle cards, or exit.

After the game starts, you, as a Player (also User), will be asked to give an action, which may be: 
to Hit, that means to take a card from the Card Dispenser (which is a set of 3 Decks), and see 
how much it scores; or to Stand, which means to give the opportunity to your opponent, the Dealer, to play.

The Dealer takes his actions with no user interaction. There is no way to take control of the Dealer actions,
they are automatic.

The objective is to get a hand total of closer to 21 than the dealer without going over 21 (busting).

If no player could won the game, this is called a draw or a tie, and the game will be "pushed".



# How to execute

In the main directory, after unzipping the file, you can use one of the following commands.

To run the Blackjack from the command line:

_./run_blackjack.sh_

In order to run the tests, you can start them by using the command below:

_./run_blackjack_tests.sh_

You can choose to run it inside a Docker container:

_./run_blackjack_docker.sh_

# Object Oriented Design decisions

There is a class named BoardGame, where all business logic of the game is implemented. This
class is referenced as a attribute (class field) by the class BlackjackGame (com.acquisbi.blackjack.BlackjackGame). 
This class runs all the User Interface to the User. The User Interface works on the command line.

The Card class implements a normal Card, which is represented by a CardStamp (Spades, Diamonds, etc.) 
and a CardValue (One, Two, Aces, etc.).

There are two players: one is the Dealer, and another is the User. Both inherits from class Player, from into
comes all the main player's actions, like Hit and Stand.

The class Deck represents a normal card deck containing 52 cards. The game uses 3 card decks, and these 3 decks
are stored into a strutcture called CardDispenser, which can do actions like to shuffle all the cards.





