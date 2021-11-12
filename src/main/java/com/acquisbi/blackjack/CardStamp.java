
package com.acquisbi.blackjack;

public enum CardStamp
{
    SPADES   ("Spades"),
    DIAMONDS ("Diamonds"),
    HEARTS   ("Hearts"),
    CLUBS    ("Clubs");
    
    private final String descCardValue;

    private CardStamp(String descCardValue) {

        this.descCardValue = descCardValue;

    }

    @Override
    public String toString() {
        return this.descCardValue;
    }
}
