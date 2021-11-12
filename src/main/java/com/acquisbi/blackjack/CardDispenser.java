package com.acquisbi.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CardDispenser extends ArrayList<Card> {


    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public static final int NUM_DECKS = 3;

    public CardDispenser() {

        for (int d=0; d < CardDispenser.NUM_DECKS; d++) {
            Deck deck = new Deck();

            deck.doShuffle();

            addAll( deck );
        }

    }

    public Card getNextRandom() {

        if (isEmpty())
        {
            return null;
        }
        
        int index = (int)(Math.random()*(size()-1));
        Card retVal = get(index);
        remove(index);

        //logger.info("size() = "+size()+", index = "+index+", random card = "+ retVal.toString());

        return retVal;
            
    }

    public void doShuffle()
    {
        Collections.shuffle(this);
    }

    public String displayCardDispenser()
    {
        return this.parallelStream().map(card -> card.toString()).collect(Collectors.joining(", "));
    }


}
