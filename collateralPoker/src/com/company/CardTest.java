package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CardTest {
    Card card;

    @Before
    public void setUp(){
        card = new Card(Rank.JACK, Suit.CLUBS);
    }

    @Test
    public void getSameRank() {
        assertEquals(Rank.JACK, card.getRank());
    }

    @Test
    public void getDifferentRank() {
        assertNotEquals(Rank.ACE, card.getRank());
    }

    @Test
    public void getSameSuit() {
        assertEquals(Suit.CLUBS, card.getSuit());
    }

    @Test
    public void getDifferentSuit() {
        assertNotEquals(Suit.DIAMONDS, card.getSuit());
    }

    @Test
    public void notEqualsDifferentSuitAndRank() {
        Card otherCard = new Card(Rank.KING, Suit.SPADE);
        assertNotEquals(otherCard, card);
    }

    @Test
    public void notEqualsDifferentSuit() {
        Card otherCard = new Card(Rank.JACK, Suit.SPADE);
        assertNotEquals(otherCard, card);
    }

    @Test
    public void notEqualsDifferentRank() {
        Card otherCard = new Card(Rank.KING, Suit.CLUBS);
        assertNotEquals(otherCard, card);
    }

    @Test
    public void isEqual() {
        Card otherCard = new Card(Rank.JACK, Suit.CLUBS);
        assertEquals(otherCard, card);
    }
}