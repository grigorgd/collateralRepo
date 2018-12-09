package com.company;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards;
    private boolean visible = true;//if it is visible the cards will be shown
    private int cardsNumber = 1;

    public Player(String name){
        this.name = name;
        cards = new ArrayList<Card>();
    }

    public String getName(){
        return name;
    }

    public int getCardsNumber(){
        return cardsNumber;
    }

    public void setCardNumber(){
        cardsNumber++;
    }

    public boolean getVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void removeAll(){
        cards.removeAll(cards);
    }

}
