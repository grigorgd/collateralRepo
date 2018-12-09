package com.company;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HandTest {
    Hand hand;

    @Before
    public void setUp(){
        hand = new Hand();
    }

    @Test
    public void shouldAddCardToHand() {
        hand.addCard(new Card(Rank.NINE,Suit.SPADE));
        assertEquals(new Card(Rank.NINE,Suit.SPADE), hand.getCard(0));
    }

    @Test
    public void shouldAddAllCardsToHand(){
        ArrayList<Card> otherHand = new ArrayList<Card>();
        otherHand.add(new Card(Rank.NINE,Suit.SPADE));
        otherHand.add(new Card(Rank.TEN,Suit.DIAMONDS));
        hand.addAllCards(otherHand);
        assertEquals(2, hand.getSize());
    }

    @Test
    public void shouldBeThreeCardsInHand(){
        hand.addCard(new Card(Rank.TEN,Suit.CLUBS));
        hand.addCard(new Card(Rank.QUEEN,Suit.CLUBS));
        hand.addCard(new Card(Rank.JACK, Suit.SPADE));
        assertEquals(3, hand.getSize());
    }

    @Test
    public void shouldReturnedTwoCardsInString(){
        hand.addCard(new Card(Rank.QUEEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.CLUBS));
        assertEquals("[QUEEN_HEARTS][TEN_CLUBS]", hand.toString());
    }

    @Test
    public void shouldBeHighCardKingInHand(){
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertTrue(hand.isHighCard(Rank.KING));
    }

    @Test
    public void shouldNotBeHighCardJackInHand(){
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isHighCard(Rank.JACK));
    }

    @Test
    public void shouldBePairOfJacksInHand(){
        hand.addCard(new Card(Rank.JACK,Suit.SPADE));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        assertTrue(hand.isPair(Rank.JACK));
    }

    @Test
    public void shouldNotBePairOfKings(){
        hand.addCard(new Card(Rank.NINE,Suit.CLUBS));
        hand.addCard(new Card(Rank.KING,Suit.DIAMONDS));
        assertFalse(hand.isPair(Rank.KING));
    }

    @Test
    public void shouldBeTwoPair9AndT(){
        hand.addCard(new Card(Rank.NINE,Suit.SPADE));
        hand.addCard(new Card(Rank.NINE,Suit.CLUBS));
        hand.addCard(new Card(Rank.TEN,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        assertTrue(hand.is2Pair(Rank.NINE,Rank.TEN));
    }

    @Test
    public void shouldNotBeTwoPair(){
        hand.addCard(new Card(Rank.NINE,Suit.SPADE));
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.QUEEN,Suit.DIAMONDS));
        assertFalse(hand.is2Pair(Rank.NINE,Rank.TEN));
    }

    @Test
    public void shouldBeSmallStraight(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertTrue(hand.isStraight(0));
    }

    @Test
    public void shouldNotBeSmallStraight(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isStraight(0));
    }

    @Test
    public void shouldBeBigStraight(){
        hand.addCard(new Card(Rank.ACE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertTrue(hand.isStraight(1));
    }

    @Test
    public void shouldNotBeBigStraight(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isStraight(1));
    }

    @Test
    public void shouldBeFlush(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.HEARTS));
        hand.addCard(new Card(Rank.QUEEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        assertTrue(hand.isFlush(Suit.HEARTS));
    }

    @Test
    public void shouldNotBeFlush(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isFlush(Suit.SPADE));
    }

    @Test
    public void shouldBeFull(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.NINE,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.TEN,Suit.SPADE));
        hand.addCard(new Card(Rank.TEN,Suit.CLUBS));
        assertTrue(hand.isFull(Rank.TEN,Rank.NINE));
    }

    @Test
    public void shouldNotBeFull(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isFull(Rank.KING,Rank.NINE));
    }

    @Test
    public void shouldBeStraightFlush(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.HEARTS));
        hand.addCard(new Card(Rank.QUEEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        assertTrue(hand.isStraightFlush(Suit.HEARTS,0));
    }

    @Test
    public void shouldNotBeStraightFlush(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isStraightFlush(Suit.DIAMONDS,0));
    }

    @Test
    public void shouldBeRoyal(){
        hand.addCard(new Card(Rank.ACE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.HEARTS));
        hand.addCard(new Card(Rank.QUEEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        assertTrue(hand.isStraightFlush(Suit.HEARTS,1));
    }

    @Test
    public void shouldNotBeRoyal(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.ACE,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        assertFalse(hand.isStraightFlush(Suit.SPADE,1));
    }

    @Test
    public void callTestPairOfJacks(){
        hand.addCard(new Card(Rank.JACK,Suit.SPADE));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.compute();
        assertTrue(hand.call(Hands.P_J));
    }

    @Test
    public void callTestSmallStraight(){
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        hand.addCard(new Card(Rank.JACK,Suit.SPADE));
        hand.compute();
        assertTrue(hand.call(Hands.S_SMALL));
    }

    @Test
    public void callTestFullJonK(){
        hand.addCard(new Card(Rank.KING,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        hand.addCard(new Card(Rank.JACK,Suit.SPADE));
        hand.compute();
        assertTrue(hand.call(Hands.FH_JK));
    }

    @Test
    public void toStringTest(){
        String set = "[NINE_HEARTS][TEN_HEARTS][JACK_DIAMONDS]";
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.JACK,Suit.DIAMONDS));
        assertEquals(set,hand.toString());
    }

    @Test
    public void getAllPossibleHandsTest(){
        String set = "HC_9 HC_T HC_Q HC_K P_Q ";
        hand.addCard(new Card(Rank.NINE,Suit.HEARTS));
        hand.addCard(new Card(Rank.TEN,Suit.HEARTS));
        hand.addCard(new Card(Rank.QUEEN,Suit.DIAMONDS));
        hand.addCard(new Card(Rank.QUEEN,Suit.SPADE));
        hand.addCard(new Card(Rank.KING,Suit.SPADE));
        hand.compute();
        assertEquals(set,hand.getAllPossibleHands());
    }

}