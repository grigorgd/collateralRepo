package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

    private ArrayList<Player> players;
    private Deck deck;
    private Scanner scanner;
    private Hand allPlayersCards;
    private int betValue = -1;
    private Hands bet;

    private int turn = 0;

    public Game(Scanner scanner){
        players = new ArrayList<>();
        this.scanner = scanner;
        allPlayersCards = new Hand();
    }

    public void set(){
        int numberOfPlayers = 0;
        System.out.println("This is Collateral Poker Game\nAll possible answers are printed below");

        for(int i = 0; i < Hands.values().length; i++){
            if(i % 30 == 0) System.out.println();
            System.out.print(String.format("%s ",Hands.values()[i]));
        }

        boolean correctInput = false;
        do{
            System.out.println("\nEnter a number of players from range[2-4]...");
            if(scanner.hasNextInt()){
                numberOfPlayers = scanner.nextInt();
                if(numberOfPlayers < 4 || numberOfPlayers > 2){
                    correctInput = true;
                }
                else{
                    scanner.nextLine();
                    System.out.println("incorrect integer" );
                }
            }
            else{
                scanner.nextLine();
                System.out.println("incorrect input" );
            }
        }while(!correctInput);

        for(int i = 0; i < numberOfPlayers; i++){
            addPlayer(i);
        }

    }


    public void start(){
        boolean endGame = false;

        //loop responsible for all deals in game
        do{
            deck = new Deck();
            deck.shuffle();
            for(Player pl : players){
                for(int i = 0; i < pl.getCardsNumber(); i++){
                    pl.addCard(deck.getCard());
                }
            }

            allPlayersCards.removeAll();
            for(Player pl : players){
                if(pl.getVisible()){
                    System.out.print("#" + pl.getName() + " - " + pl.getCards() + "        ");
                }
            }
            System.out.println();

            raise(getCurrentPlayer());//first player have to insert higher hand

            //loop responsible for individual deal
            boolean play = false;
            do{
                this.turn++;
                System.out.print(getCurrentPlayer().getName() + "...call/raise...");
                boolean correctOption = false;
                String option = "";
                do{
                    if(scanner.hasNext()){
                        option = scanner.next();
                        if(option.equals("call") || option.equals("raise")){
                            correctOption = true;
                        }
                        else{
                            scanner.nextLine();
                            System.out.println("incorrect input" );
                        }
                    }
                    else{
                        scanner.nextLine();
                        System.out.println("incorrect input" );
                    }
                }while(!correctOption);

                if(option.equals("raise")){
                    raise(getCurrentPlayer());
                }
                else{
                    play = true;
                }

            }while(!play);

            toFillCallingHand();//problem
            show();

            if(call()) {
                System.out.println(getCurrentPlayer().getName() + " lost");
                getCurrentPlayer().setCardNumber();
                getLooser(getCurrentPlayer());
            }
            else {
                System.out.println(getCurrentPlayer().getName() + " won");
                getPreviousPlayer().setCardNumber();
                getLooser(getPreviousPlayer());
            }
            System.out.println();
            betValue = -1;

            for(Player p : players){
                p.removeAll();
            }

            if (players.size() == 1) {
                System.out.println(players.get(0).getName() + " has WON!!!");
                endGame = true;
            }
        }while(!endGame);
    }

    private void show(){
        System.out.println("\n*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *");
        for(Player pl : players){
            System.out.println(pl.getName() + " " + pl.getCards());
        }
        System.out.println("possible hands : " + allPlayersCards.getAllPossibleHands());
        System.out.println("higher hands : " + allPlayersCards.getAllPossibleHands(bet));
        System.out.println("*  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *\n");
    }

    private void toFillCallingHand(){
        for(Player p : players){
            allPlayersCards.addAllCards(p.getCards());
        }
        allPlayersCards.compute();
    }

    private void getLooser(Player player){
        if(player.getCardsNumber() > 5){
            deck.returnCards(player.getCards());
            System.out.println(player.getName() + " has over 5 card and lost game");
            players.remove(player);
        }
    }

    //return true if calling hand can be made from all players cards
    private boolean call(){
        return allPlayersCards.call(bet);
    }

    private Player getCurrentPlayer(){
        return players.get(turn % players.size());
    }

    private Player getPreviousPlayer(){
        if(turn % players.size() == 0) return players.get(players.size() - 1);
        return players.get((turn % players.size()) - 1);
    }

    private void raise(Player player){
        System.out.print(player.getName() + "...raise...");
        String hand = "";
        boolean correctValue = false;
        do{
            if(scanner.hasNext()){
                hand = scanner.next();
                for(Hands h : Hands.values()){
                    if(h.name().equals(hand)){
                        if(h.getValue() > betValue){
                            correctValue = true;
                            betValue = h.getValue();
                            bet = Hands.valueOf(h.name());
                        }
                        else{
                            System.out.println("this hand is too low");
                            scanner.nextLine();
                        }
                    }
                }
            }
            else{
                scanner.nextLine();
                System.out.println("no input" );
            }
        }while(!correctValue);
    }

    private void addPlayer(int index){
        System.out.println("Insert player " + (index + 1) + " name...");
        String name = scanner.next();
        players.add(new Player(name));
    }

}
