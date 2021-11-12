package com.acquisbi.blackjack;

import java.util.Scanner;

public class BlackjackGame  {

    static final private String HIT_ACTION = "H";

    static final private String STAND_ACTION = "S";

    static String userName;

    String userAction;

    static boolean gameOver = false;

    int dealerWinCount = 0;

    int playerWinCount = 0;

    static Scanner input = new Scanner(System.in);

    private BoardGame boardGame;

    public BlackjackGame()
    {
        boardGame = new BoardGame();

        dealerWinCount = 0;

        playerWinCount = 0;

    }

    public static void main(String[] args) {

        BlackjackGame b = new BlackjackGame();

        System.out.println("Blackjack Game!\n");

        String response = "";
        System.out.print("Enter player's name: ");
        userName = input.next();
        System.out.println("\nBlackjack started! " + userName + " versus Dealer");

        do {

            System.out.println("Choose your action from the numeric options below:\n");
            System.out.println("1 -> Start BlackJack");
            System.out.println("2 -> Shuffle Cards");
            System.out.println("3 -> Quit");
            int line = input.nextInt();

            switch (line) {

                case 1: {
                    b.doStartGame();
                    gameOver = false;
                    System.out.println(
                            "\nPlayer" + userName + ", wanna play again? \nPress key 'Y' for Yes, or 'N' for No");
                    response = input.next();
                    if (!response.equalsIgnoreCase("Y")) {
                        System.out.println("\nThank you for playing BlackJack!");
                        return;
                    }

                    break;
                }
                case 2: {
                    System.out.println("Shuffle cards.\nConstructing new Deck.");

                    System.out.println("Deck");
                    b.boardGame.getCardDispenser().doShuffle();
                    System.out.println("Deck Shuffled");
                    b.boardGame.getCardDispenser().displayCardDispenser();
                    break;
                }
                case 4: {
                    System.out.println("\n\n\nLeaving BlackJack...\n\n");
                    response = "N";
                    break;
                }
                default: {
                    System.out.println("Option could not be identified.");
                    break;
                }
            }
        } while (response.equalsIgnoreCase("Y"));

        b.boardGame.outputSummaryStats();
    }

    public void doStartGame() {

        boardGame.newGame();
        System.out.println("-- It's User's (" + userName + ") Turn");

        showBothPlayerHands();

        while (!gameOver) {
            boolean gameOn = true;
            while (!boardGame.getCardDispenser().isEmpty() && gameOn)
            {
                gameOn = askPlayer() && dealerPlays();
            }

            if (boardGame.getCardDispenser().isEmpty()
                    && (!boardGame.getUser().isBusted()) && (!boardGame.getDealer().isBusted())) {

                boardGame.increasePush();
                break;
            } else if (boardGame.getDealer().isBusted()
                    || ((!boardGame.getUser().isHitAllowed()) && (!boardGame.getUser().isBusted())
                    && (boardGame.getUser().getScore() > boardGame.getDealer().getScore())))
            {
                boardGame.getUser().promotesWinner();
                doPlayerWins();
            } else if (boardGame.getUser().isBusted() && (!boardGame.getDealer().isBusted()))
            {
                //boardGame.getDealer().promotesWinner();
                //gameOver = true;
                doDealerWins();
            } else if ((!boardGame.getUser().isBusted())
                    && (boardGame.getUser().getScore() > boardGame.getDealer().getScore()))
            {
                //boardGame.getUser().promotesWinner();
                //gameOver = true;
                doPlayerWins();
            } else if (boardGame.getUser().getScore() < boardGame.getDealer().getScore())
            {
                //boardGame.getDealer().promotesWinner();
                //gameOver = true;
                doDealerWins();
            } else {
                // none wins
                boardGame.increasePush();
                System.out.println("It's a draw!");
                doGameover();
                gameOver=true;
            }
        }
    }

    public boolean askPlayer() {
        boolean gameOn = true;
        System.out.println("\nPlease press key H(to Hit), or S (to Stay):");
        userAction = input.next();
        if (userAction.equalsIgnoreCase(HIT_ACTION )) {

            if (boardGame.getUser().isHitAllowed())
            {
                boardGame.getUser().doHit();
                if (boardGame.getUser().isBusted())
                {
                    System.out.println("\n" + userName + "'s total is over 21!");
                    doDealerWins();
                    gameOn = false;
                }
                else
                {
                    showBothPlayerHands();
                }
            } else {
                gameOn = false;
            }
        } else if (userAction.equalsIgnoreCase(STAND_ACTION)) {
            System.out.println("\n" + userName + " Stands");

            System.out.println("\nIt's Dealer's Turn");
            System.out.println("Dealer played cards: " + boardGame.getDealer().showHands());

            gameOn = dealerPlays();

        } else {
            System.out.println("Press keys H or S.");
        }

        return gameOn;
    }

    public boolean dealerPlays() {

        boolean gameOn = true;

        if ((!boardGame.getDealer().isBusted()) && (!boardGame.getUser().isHitAllowed())) 
        {
            if (boardGame.getUser().getScore() > boardGame.getDealer().getScore()) {
                
                boardGame.getDealer().doHit();
                
                if (boardGame.getDealer().isBusted()) {
                    gameOn = false;
                }
            } else {

                gameOn =  false;
            }
            

        }

        return gameOn;

    }

    public  void showBothPlayerHands() {

        System.out.println("Dealer's Hand "+boardGame.getDealer().showHands());
        System.out.println("User's Hand "+boardGame.getUser().showHands());

    }

    public  void doDealerWins() {

        dealerWinCount += 1;
        boardGame.getDealer().promotesWinner();
        doGameover();
        System.out.println("Dealer wins!");
        gameOver = true;
    }

    public  void doPlayerWins() {

        playerWinCount += 1;
        boardGame.getUser().promotesWinner();
        doGameover();
        System.out.println("User "+ userName + " wins!");
        gameOver = true;
    }

    public void doGameover() {

        boardGame.outputGameSummary();

        System.out.println(
                "\nDealer won " + dealerWinCount + " times.\nPlayer " + userName + " won " + playerWinCount + " times\n");
    }

}