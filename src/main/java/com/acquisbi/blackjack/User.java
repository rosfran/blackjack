
package com.acquisbi.blackjack;

import java.util.stream.Collectors;

public class User extends Player
{

    private User() {}


    public User(CardDispenser cardDispenser) {
        super(cardDispenser);
    }


    @Override
    public String toString() {

        if ( hand == null )
        {
            return "";
        }
        String cards = "Player Hand: ";
        cards +=hand.parallelStream().map( c -> c.toString() ).
                collect(Collectors.joining(", "));
        cards += " = ";
        cards += getScore();

        return cards;
    }

}
