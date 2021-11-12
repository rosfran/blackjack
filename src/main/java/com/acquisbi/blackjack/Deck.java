
package com.acquisbi.blackjack;


import java.util.ArrayList;
import java.util.Collections;


public class Deck extends ArrayList<Card>
{

    public static final int NUM_CARDS = 52;

    public Deck() {
        super(Deck.NUM_CARDS);

        for (CardStamp s : CardStamp.values()) {
            for (CardValue r: CardValue.values()) {
                this.add(new Card(r, s));
            }
        }
    }

    public void doShuffle()
    {
        Collections.shuffle(this);
    }


}
