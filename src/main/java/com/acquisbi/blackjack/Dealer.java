
package com.acquisbi.blackjack;

import java.util.stream.Collectors;

public class Dealer extends Player {


    public static final int DEALER_HIT_VALUE = 21;

    public Dealer(CardDispenser cardDispenser) {

        super(cardDispenser);

    }

    public void checkWannaHit() {
        if ( (score < Dealer.DEALER_HIT_VALUE) && !busted )
        {
            hitAllowed = true;
        } else {
            hitAllowed = false;
        }

    }
    

    public void setWinner() {
        winner = true;
    }


    @Override
    public String toString() {

        if ( hand == null )
        {
            return "";
        }
        String cards = "Dealer Hand: ";
        cards +=hand.parallelStream().map( c -> c.toString() ).
                collect(Collectors.joining(", "));
        cards += " = ";
        cards += getScore();
        
        return cards;
    }


}
