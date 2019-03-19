package com.martajastrzabek;

import java.util.Scanner;

public class Main {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to BATTLESHIP game.");
        Board battleshipGame = new Board(10,10);

        //creating instance of Ship class and adding to the ships list
        battleshipGame.addToShipsList(battleshipGame.createShip("submarine", 1));
        battleshipGame.addToShipsList(battleshipGame.createShip("submarine", 1));
        battleshipGame.addToShipsList(battleshipGame.createShip("submarine", 1));
        battleshipGame.addToShipsList(battleshipGame.createShip("submarine", 1));
        battleshipGame.addToShipsList(battleshipGame.createShip("destroyer", 2));
        battleshipGame.addToShipsList(battleshipGame.createShip("destroyer", 2));
        battleshipGame.addToShipsList(battleshipGame.createShip("destroyer", 2));
        battleshipGame.addToShipsList(battleshipGame.createShip("cruiser", 3));
        battleshipGame.addToShipsList(battleshipGame.createShip("cruiser", 3));
        battleshipGame.addToShipsList(battleshipGame.createShip("battleship",4));

        //placing ships on board
        battleshipGame.placeShipsOnBoard(battleshipGame.getShipList());

        //print board for checks
        battleshipGame.printBoard();
        //battleshipGame.printBoardShots();
        battleshipGame.printShipList(battleshipGame.getShipList());

        while(!battleshipGame.getShipList().isEmpty()){
            System.out.println("---------------------------------");
            System.out.println("Your turn. Shot your type (eg.a5) \nor type 0 to display your shots " +
                    "\nor type 9 to print list of remaining ships: ");
            battleshipGame.checkShot(getUserInput());
        }
        if(battleshipGame.getShipList().isEmpty()){
            battleshipGame.endGame();
        }

    }

    public static String getUserInput(){
        String userInput = in.nextLine();

        return userInput;
    }


}
