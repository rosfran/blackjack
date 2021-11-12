
package com.acquisbi.blackjack;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Player {


    protected String name;

    protected boolean busted = false;

    protected boolean hitAllowed = true;

    protected ArrayList<Card> hand = new ArrayList<Card>();

    protected int score = 0;

    protected boolean winner = false;

    private int winnerCounting = 0;

    protected GameStatistics winStats = new GameStatistics();

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void putCardOnHand( Card card )
    {
        hand.add( card );
    }

    public CardDispenser getCardDispenser()
    {
        return cardDispenser;
    }

    public void setCardDispenser(CardDispenser cardDispenser)
    {
        this.cardDispenser = cardDispenser;
    }

    protected CardDispenser cardDispenser = null;


    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public Player() {
        this.name = "";
    }

    public Player(String name) {
        this.name = name;
    }

    public Player(CardDispenser cardDispenser) {
        this.cardDispenser = cardDispenser;
        
        doNewAndEmptyHand();
    }

    public Player(CardDispenser cardDispenser, String name) {
        this.cardDispenser = cardDispenser;
        this.name = name;
        doNewAndEmptyHand();
    }

    public String getName()
    {
        return name;
    }

    /**
     * Creates a new, empty hand for the player
     */
    public final void doNewAndEmptyHand() {

        score  = 0;
        busted = false;

        winner = false;
        hitAllowed = true;
        hand   = new ArrayList<Card>(0);

    }
    

    @Override
    public String toString() {
        String cards = "";

        cards +=hand.parallelStream().map( c -> c.toString() ).collect(Collectors.joining(", "));
        cards += " = ";
        cards += getScore();

        return cards;
    }

    public String showHands()
    {
        return toString();
    }
    
    /**
     * Draws a card from the Card Dispenser
     */
    public void doHit() {

        if ((cardDispenser ==null) || (cardDispenser.isEmpty() || (!hitAllowed))) return;

        Card temp = cardDispenser.getNextRandom();
        hand.add(temp);

        CardValue cardValue = temp.getRank();

        score += cardValue.value();

        busted = (score > BoardGame.WIN_VALUE);
        
        checkWannaHit();
    }


    public void checkWannaHit() {
        hitAllowed = (!busted) && (score < BoardGame.HIT_VALUE);
    }
    

    public boolean isHitAllowed() {
        hitAllowed = (!busted) && (score < BoardGame.HIT_VALUE);
        return hitAllowed;
    }
    

    public int getScore() {
        return score;
    }
    
    public boolean isBusted() {
        return busted;
    }
    

    public void promotesWinner() {
        winner = true;
        
        ++winnerCounting;
        if (winStats.get(score) == null) {
            winStats.put(score, 1);
        } else {
            winStats.put(score, ((Integer)winStats.get(score))+1);
        }
    }
    

    public int getWinnerCount() {
        return winnerCounting;
    }
    

    public final GameStatistics getWinStats() {
        return winStats;
    }
    

    public boolean isWinner() {
        return winner;
    }
    

}
