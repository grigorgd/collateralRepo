package com.company;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    private boolean[] handsArray;//actual hands that can be set from @hand

    public Hand(){
        cards = new ArrayList<>();
        handsArray = new boolean[Hands.values().length];
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public void addAllCards(ArrayList<Card> cards){
        this.cards.addAll(cards);
    }

    public int getSize(){
        return cards.size();
    }

    public Card getCard(int index){
        return cards.get(index);
    }

    public void removeAll(){
        cards.clear();
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for(Card c : cards){
            result.append(c.toString());
        }
        return result.toString();
    }

    public String getAllPossibleHands(){
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < handsArray.length; i++){
            if(handsArray[i]) result.append(Hands.values()[i] + " ");
        }
        return result.toString();
    }

    public String getAllPossibleHands(Hands hands){
        StringBuilder result = new StringBuilder();
        for(int i = hands.ordinal() + 1; i < handsArray.length; i++){
            if(handsArray[i]) result.append(Hands.values()[i] + " ");
        }
        return result.toString();
    }

    public boolean call(Hands hand) {
        return handsArray[(Hands.valueOf(hand.name())).ordinal()];
    }


    public void compute(){
        int iter = 0;

        //call for HighCard 0-5
        for(Rank r : Rank.values()){
            handsArray[iter++] = isHighCard(r);
        }
        //call for Pair
        for(Rank r : Rank.values()){
            handsArray[iter++] = isPair(r);
        }
        //call for 2Pair
        for(int i = 1; i < 6; i++){
            for(int j = 0; j < i; j++){
                handsArray[iter++] = is2Pair(Rank.values()[i], Rank.values()[j]);
            }
        }
        //call for 3Kind
        for(Rank r : Rank.values()){
            handsArray[iter++] = is3Kind(r);
        }
        //call for Straight
        for(int i = 0; i < 2; i++){
            handsArray[iter++] = isStraight(i);
        }
        //call for Flush
        for(Suit s : Suit.values()){
            handsArray[iter++] = isFlush(s);
        }
        //call FullHause
        for(Rank r : Rank.values()){
            for(Rank p : Rank.values()){
                if(r == p) continue;
                handsArray[iter++] = isFull(r, p);
            }
        }
        //call 4Kind
        for(Rank r : Rank.values()){
            handsArray[iter++] = is4Kind(r);
        }
        //call StraightFlush
        for(Suit s : Suit.values()){
            handsArray[iter++] = isStraightFlush(s, 0);
        }
        //call RoyalFlush
        for(Suit s : Suit.values()){
            handsArray[iter++] = isStraightFlush(s, 1);
        }

    }

    public boolean isHighCard(Rank rank){
        for(Card c : cards){
            if(c.getRank() == rank) return true;
        }
        return false;
    }

    public boolean isPair(Rank rank){
        int i = 0;
        for(Card c : cards){
            if(c.getRank() == rank) i++;
        }
        return i >= 2 ? true : false;
    }

    public boolean is2Pair(Rank rank1, Rank rank2){
        if(isPair(rank1) && isPair(rank2)) return true;
        return false;
    }

    public boolean is3Kind(Rank rank){
        int i = 0;
        for(Card c : cards){
            if(c.getRank() == rank) i++;
        }
        return i >= 3 ? true : false;
    }

    //value can have only 0 or 1
    public boolean isStraight(int value){
        if(isHighCard(Rank.values()[value++])){
            if(isHighCard(Rank.values()[value++])){
                if(isHighCard(Rank.values()[value++])){
                    if(isHighCard(Rank.values()[value++])){
                        if(isHighCard(Rank.values()[value])){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean isFlush(Suit suit){
        int i = 0;
        for(Card c : cards){
            if(c.getSuit() == suit) i++;
        }
        if(i >= 5) return true;
        return false;
    }

    public boolean isFull(Rank rank1, Rank rank2){
        if(is3Kind(rank1) && isPair(rank2)) return true;
        return false;
    }

    public boolean is4Kind(Rank rank){
        int i = 0;
        for(Card c : cards){
            if(c.getRank() == rank) i++;
        }
        return i == 4 ? true : false;
    }

    //value can have only 0 or 1
    public boolean isStraightFlush(Suit suit, int value){
        int i = 0;
        for(Card c : cards){
            if(c.getSuit() == suit){
                if(c.getRank() == Rank.values()[0 + value] || c.getRank() == Rank.values()[1 + value]
                        || c.getRank() == Rank.values()[2 + value] || c.getRank() == Rank.values()[3 + value]
                        || c.getRank() == Rank.values()[4 + value]){
                    i++;
                }
            }
        }
        return i == 5 ? true : false;
    }


}
