package com.acquisbi.blackjack;

import java.util.logging.Logger;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BoardGameTests
{

    private static Logger logger = Logger.getLogger(BoardGameTests.class.getName());

    BoardGame boardGame;

    @BeforeEach
    void setUp() {
        boardGame = new BoardGame();
    }

    @Test
    @DisplayName("Games won by Player and Dealer never surpasses total games")
    void testPlayerScores4DealerScores17NeverSurpassesTotal() {

        boardGame.newGame( new Card[] {
                new Card(CardValue.TWO, CardStamp.SPADES),
                new Card(CardValue.TWO, CardStamp.DIAMONDS)
        } , new Card[] {
                new Card(CardValue.THREE, CardStamp.HEARTS),
                new Card(CardValue.FIVE, CardStamp.SPADES),
                new Card(CardValue.EIGHT, CardStamp.SPADES)

        } );

        boardGame.runAutomaticGame();

        assertTrue( boardGame.getUser().getWinnerCount() + boardGame.getDealer().getWinnerCount() <= boardGame.getNumGames(),
                "Games won by Player and Dealer never surpasses total games" );

    }

    @Test
    @DisplayName("In each case, at least one player is a winner")
    void testPlayerScores12DealerScores10() {

        boardGame.newGame( new Card[] {
                                        new Card(CardValue.JACK, CardStamp.CLUBS),
                                        new Card(CardValue.TWO, CardStamp.DIAMONDS)
                        } , new Card[] {
                                        new Card(CardValue.FIVE, CardStamp.HEARTS),
                                        new Card(CardValue.FIVE, CardStamp.SPADES)

                        } );

        boardGame.runAutomaticGame();

        assertTrue( boardGame.getCardDispenser().size() == 0
                        || boardGame.getUser().isWinner() || boardGame.getDealer().isWinner(),
                "In each case, at least one player is a winner" );

    }

    @Test
    @DisplayName("Some player is a winner")
    void testPlayerScores20DealerScores17() {

        boardGame.newGame( new Card[] {
                new Card(CardValue.JACK, CardStamp.SPADES),
                new Card(CardValue.JACK, CardStamp.DIAMONDS)
        } , new Card[] {
                new Card(CardValue.THREE, CardStamp.HEARTS),
                new Card(CardValue.FIVE, CardStamp.SPADES),
                new Card(CardValue.EIGHT, CardStamp.SPADES)

        } );

        boardGame.runAutomaticGame();

        assertTrue( boardGame.getCardDispenser().size() == 0
                        || boardGame.getUser().isWinner() || boardGame.getDealer().isWinner(),
                "Repeating 5 times, in each case, at least one player is a winner" );

    }

    @RepeatedTest(3)
    @DisplayName("Tests alleatory distribution of cards in a Card Dispenser")
    void testCardDispenserShuffler() {

        boardGame.getCardDispenser().doShuffle();

        Card c1 = boardGame.getCardDispenser().get(0);

        Card c2 = boardGame.getCardDispenser().getNextRandom();

        System.out.println("c1 = "+c1+", c2 = "+c2);

        assertNotEquals( c1, c2, "Tests aleatory distribution of cards in a Card Dispenser");

    }

}
