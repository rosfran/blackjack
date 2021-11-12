package com.acquisbi.blackjack;

public class Card
{

    private final CardValue cardValue;

    private final CardStamp cardStamp;


    public Card(CardValue cardValue, CardStamp cardStamp) {
        this.cardValue = cardValue;
        this.cardStamp = cardStamp;
    }

    public CardValue getRank() {
        return cardValue;
    }


    public CardStamp getCardStamp() {
        return cardStamp;
    }
    

    @Override
    public String toString() {

        return "[ "+cardValue.toString()+" "+cardStamp.toString()+" ]";

    }

    public boolean equals( Card c2 )
    {
        return this.cardStamp == c2.cardStamp && this.cardValue == c2.cardValue;
    }



}
