package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game = new Game(scanner);
        game.set();
        game.start();


    }


}
