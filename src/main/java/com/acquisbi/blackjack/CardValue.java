
package com.acquisbi.blackjack;


public enum CardValue
{
    ONE   ("One",    1),
    TWO   ("Two",    2),
    THREE ("Three",  3),
    FOUR  ("Four",   4),
    FIVE  ("Five",   5),
    SIX   ("Six",    6),
    SEVEN ("Seven",  7),
    EIGHT ("Eight",  8),
    NINE  ("Nine",   9),
    TEN   ("Ten",   10),
    JACK  ("Jack",  10),
    QUEEN ("Queen", 10),
    KING  ("King",  10),
    ACE   ("Ace",   11),;


    private final String descValue;

    private final int value;


    private CardValue(String descValue, int value) {
        this.descValue = descValue;
        this.value = value;
    }

    public int value() {

        return this.value;

    }

    @Override
    public String toString() {
        return this.descValue;
    }
}
