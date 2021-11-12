package com.acquisbi.blackjack;

import java.util.logging.Logger;

public class BoardGame
{

    private static Logger logger = Logger.getLogger(BoardGame.class.getName());

    public static final int WIN_VALUE = 21;

    public static final int HIT_VALUE = 16;

    public static final int DEALER_HIT_VALUE = 21;

    protected  CardDispenser cardDispenser = new CardDispenser();

    private  Dealer dealer = new Dealer(cardDispenser);
    private  User user = new User(cardDispenser);

    public int getNumGames()
    {
        return numGames;
    }

    public void setNumGames(int numGames)
    {
        this.numGames = numGames;
    }

    private int numGames = 0;

    private int push = 0;

    public BoardGame()
    {
    }

    public BoardGame(CardDispenser dispenser)
    {
        this.cardDispenser = dispenser;
    }

    public BoardGame(CardDispenser dispenser, Dealer dealer, User user)
    {
        this.cardDispenser = dispenser;
        this.dealer = dealer;
        this.user = user;
    }

    public CardDispenser getCardDispenser()
    {
        return cardDispenser;
    }

    public Dealer getDealer()
    {
        return dealer;
    }

    public User getUser()
    {
        return user;
    }

    public  String showPlayerSuccessRate()
    {
        logger.info(" "+user.getWinnerCount()+" / "+
                numGames+" = "+( (double)user.getWinnerCount()/
                (double)numGames));
        return String.valueOf((int)(100*(
                (double)user.getWinnerCount()/
                        (double)numGames)));
    }

    public void increasePush()
    {
        ++this.push;
    }


    public  void runAutomaticGame() {

        while (!cardDispenser.isEmpty()) {
            newGame();
            
            while (!cardDispenser.isEmpty())
            {
                if (user.isHitAllowed())
                {
                    user.doHit();
                    if (user.isBusted()) {
                        break;
                    }
                }
                if ((!dealer.isBusted()) && (!user.isHitAllowed()))
                {
                    if (user.getScore() > dealer.getScore())
                    {
                        dealer.doHit();
                        if (dealer.isBusted()) {
                            break;
                        }
                    } else {

                        break;
                    }
                }
            }
            if (cardDispenser.isEmpty()
                && (!user.isBusted()) && (!dealer.isBusted())) {

                push++;
                break;
            } else if (dealer.isBusted() 
                    || ((!user.isHitAllowed()) && (!user.isBusted())
                        && (user.getScore() > dealer.getScore()))) {
                user.promotesWinner();
            } else if (user.isBusted() && (!dealer.isBusted())) {
                dealer.promotesWinner();
            } else if ((!user.isBusted())
                    && (user.getScore() > dealer.getScore())) {
                user.promotesWinner();
            } else if (user.getScore() < dealer.getScore()) {
                dealer.promotesWinner();
            } else {
                // none wins
                push++;
                //dealer.promotesWinner();
                //user.promotesWinner();
            }

            outputGameSummary();
        } // while
        outputSummaryStats();        
    }
    
    public  void outputGameSummary() {

        System.out.println(user.toString());
        System.out.println(dealer.toString());
        if (user.isWinner()) {
            System.out.println("Player wins!\n");
        } else if (dealer.isWinner()) {
            System.out.println("Dealer wins!\n");
        } else {
            System.out.println("It's a Tie - do Push...\n");
        }
    }
    
    public  void outputSummaryStats() {

        if (numGames == 0) {
            System.out.println( "No games played");
            return;
        }
        
        System.out.println("Number of games: "+numGames+"\n");

        System.out.println("Number of games won by Player: "+user.getWinnerCount()+"\n");
        System.out.println("Number of games won by Dealer: "+dealer.getWinnerCount()+"\n");
        System.out.println("Player success: "+showPlayerSuccessRate()+"%\n");

        GameStatistics playerWinStats = user.getWinStats();
        //System.out.println(playerWinStats.showSummary());

    }
    
    public  void newGame() {
        numGames++;

        //logger.info("\n\n\nCardDispenser = "+cardDispenser.toString()+"\n\n\n");
        dealer.doNewAndEmptyHand();
        user.doNewAndEmptyHand();
        
        user.doHit();
        dealer.doHit();

        user.doHit();
        dealer.doHit();
    }

    public  void newGame( Card[] playerCards, Card[] dealerCards ) {
        numGames++;

        //logger.info("\n\n\nCardDispenser = "+cardDispenser.toString()+"\n\n\n");
        dealer.doNewAndEmptyHand();
        user.doNewAndEmptyHand();

        for ( Card c : playerCards )
        {
            user.putCardOnHand(c);
        }

        for ( Card c : dealerCards )
        {
            dealer.putCardOnHand(c);
        }

    }

}
