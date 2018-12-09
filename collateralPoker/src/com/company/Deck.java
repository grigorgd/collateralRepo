package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

    public Deck(){
        deck = new ArrayList<Card>();
        for(Suit s : Suit.values()){
            for(Rank r : Rank.values()){
                deck.add(new Card(r, s));
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(deck);
    }

    public int getSize(){
        return deck.size();
    }

    public Card getCard(){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    public void returnCards(ArrayList<Card> cards){
        deck.addAll(cards);
    }

    public ArrayList<Card> getAllCards(){
        return deck;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Object c : deck){
            result.append(c.toString());
        }
        return result.toString();
    }
}
