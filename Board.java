package com.martajastrzabek;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Board {
    private int[][] board;
    private char[][] boardShots;
    private int boardHeight;
    private int boardLength;
    private List<String> shotList;
    private List<Ship> shipList;

    public List<Ship> getShipList() {
        return shipList;
    }


    public Board(int boardHeight, int boardLength) {
        this.boardHeight = boardHeight;
        this.boardLength = boardLength;
        board = new int[boardHeight][boardLength];
        boardShots = createShotsBoard(boardHeight, boardLength);
        shotList = new ArrayList<>();
        shipList = new ArrayList<>();
    }


    public void printBoardShots(){
        System.out.println("This is board with your shots.");
        System.out.println("    A B C D E F G H I J");
        for(int i=0; i<boardShots.length; i++){
            if(i<9)
                System.out.print((i+1) + ".  ");
            if(i==9)
                System.out.print((i+1) + ". ");

            for(int j=0; j<boardShots.length; j++){

                System.out.print(boardShots[i][j] + " ");
            }
            System.out.println();
        }

    }

    public char[][] createShotsBoard(int boardHeight, int boardLength){
        boardShots = new char[boardHeight][boardLength];
        for(int i=0; i<boardHeight; i++){
            for(int j=0; j<boardLength; j++)
                boardShots[i][j] = '0';
        }
        return boardShots;
    }

    public void printBoard(){
        System.out.println("---------------------------------");
        System.out.println("Board with randomly placed ships.");
        System.out.println("    A B C D E F G H I J");
        for(int i=0; i<board.length; i++){
            if(i<9)
                System.out.print((i+1) + ".  ");
            if(i==9)
                System.out.print((i+1) + ". ");

            for(int j=0; j<board.length; j++){

                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printShipList(List<Ship> shipList){
        int i = 1;
        for(Ship ship : shipList){
            System.out.println(i + ". " + ship.getName());
            i++;
        }
    }

    public void printShotList(List<String> shotList){
        int i = 1;
        if(!shotList.isEmpty()){
            System.out.println("Your shots: ");
        }
        for(String shot : shotList){
            System.out.println(i + ". " + shot);
            i++;
        }
    }

    public Ship createShip(String name, int size){
        return new Ship(name, size);
    }

    public void addToShipsList(Ship ship){
        shipList.add(ship);
    }

    public boolean placeShipsOnBoard(List<Ship> shipList) {
        int count;
            for (Ship ship : shipList) {
                int shipSize = ship.getSize();
                boolean placed = false;
                count = 0;
                int direction = (int) (Math.random() * 4);
                while (!placed) {
                    if (count > 1000)
                    {
                        System.out.println("Error! Impossible to find fields for \nship: " + ship.getName().toUpperCase() + ". Count : " + count);
                        shipList.remove(ship);
                        return false;
                    }
                    int downP = (int) (Math.random() * this.boardHeight);
                    int acrossP = (int) (Math.random() * this.boardLength);

                    if (board[downP][acrossP] == 0)
                    {
                        boolean isEmpty = false;
                        switch (direction)
                        {
                            case 0: // up
                                if (downP - shipSize < 0)
                                    break;
                                for (int i = 0; i < shipSize; i++)
                                {
                                    if (board[downP - i][acrossP] == 0 && isClearAround(downP - i, acrossP))
                                    {
                                        isEmpty = true;
                                    }
                                    else
                                    {
                                        isEmpty = false;
                                        break;
                                    }
                                }
                                if (isEmpty)
                                {
                                    for (int i = 0; i < shipSize; i++)
                                    {
                                        board[downP - i][acrossP] = shipSize;
                                        ship.addShipPositionDown(downP - i);
                                        ship.addShipPositionAcross(acrossP);
                                    }
                                    placed = true;
                                    //System.out.println(ship.getName() + " ship was placed in " + count + " turn");
                                }

                            case 1: //boardHeight
                                if (downP + shipSize > this.boardHeight)
                                    break;
                                for (int i = 0; i < shipSize; i++)
                                {
                                    if (board[downP + i][acrossP] == 0 && isClearAround(downP + i, acrossP))
                                    {
                                        isEmpty = true;
                                    }
                                    else
                                    {
                                        isEmpty = false;
                                        break;
                                    }
                                }
                                if (isEmpty)
                                {
                                    for (int i = 0; i < shipSize; i++)
                                    {
                                        board[downP + i][acrossP] = shipSize;
                                        ship.addShipPositionDown(downP + i);
                                        ship.addShipPositionAcross(acrossP);
                                    }
                                    placed = true;
                                    //System.out.println(ship.getName() + " ship was placed in " + count + " turn");
                                }

                            case 2: //right
                                if (acrossP + shipSize > this.boardLength)
                                    break;
                                for (int i = 0; i < shipSize; i++)
                                {
                                    if (board[downP][acrossP + i] == 0 && isClearAround(downP, acrossP + i))
                                    {
                                        isEmpty = true;
                                    }
                                    else
                                    {
                                        isEmpty = false;
                                        break;
                                    }
                                }
                                if (isEmpty)
                                {
                                    for (int i = 0; i < shipSize; i++)
                                    {
                                        board[downP][acrossP + i] = shipSize;
                                        ship.addShipPositionDown(downP);
                                        ship.addShipPositionAcross(acrossP + i);
                                    }
                                    placed = true;
                                    //System.out.println(ship.getName() + " ship was placed in " + count + " turn");
                                }

                            case 3: // left
                                if (acrossP - shipSize < 0)
                                    break;
                                for (int i = 0; i < shipSize; i++)
                                {
                                    if (board[downP][acrossP - i] == 0 && isClearAround(downP, acrossP - i))
                                    {
                                        isEmpty = true;
                                    }
                                    else
                                    {
                                        isEmpty = false;
                                        break;
                                    }
                                }
                                if (isEmpty)
                                {
                                    for (int i = 0; i < shipSize; i++)
                                    {
                                        board[downP][acrossP - i] = shipSize;
                                        ship.addShipPositionDown(downP);
                                        ship.addShipPositionAcross(acrossP - i);
                                    }
                                    placed = true;
                                    //System.out.println(ship.getName() + " ship was placed in " + count + " turn");
                                }
                        }

                    }
                    count++;

                }
            }
        return true;
    }

    // This method check if around randomly selected fields are existing ships
    private boolean isClearAround(int downP, int acrossP){
        // if boardHeight - 1 < 0 and boardLength - 1 < 0
        if(downP-1 < 0 && acrossP-1 < 0 && downP + 1 < boardHeight && acrossP + 1 < boardLength){
            if (board[downP][acrossP+1] == 0 )  {
                    if (board[downP + 1][acrossP] == 0 && board[downP + 1][acrossP + 1] == 0) {
                        return true;
                    }
            }
        }
        // if boardHeight - 1 < 0 and boardLength - 1 > 0
        if(downP-1 < 0 && acrossP-1 > 0 && downP + 1 < boardHeight && acrossP + 1 < boardLength){
            if (board[downP][acrossP-1] == 0 && board[downP][acrossP+1] == 0 ) {
                    if (board[downP + 1][acrossP - 1] == 0 && board[downP + 1][acrossP] == 0 && board[downP + 1][acrossP + 1] == 0) {
                        return true;
                    }
            }
        }

        // if across - 1 < 0
        if(downP-1 > 0 && acrossP-1 < 0 && downP + 1 < boardHeight && acrossP + 1 < boardLength){
            if (board[downP][acrossP+1] == 0 ) {
                if (board[downP-1][acrossP] == 0 && board[downP-1][acrossP+1] == 0) {
                    if (board[downP + 1][acrossP] == 0 && board[downP + 1][acrossP + 1] == 0) {
                        return true;
                    }
                }
            }
        }

        // if down + 1 > boardHeigh
        if(downP-1 > 0 && acrossP-1 > 0 && downP + 1 >= boardHeight && acrossP + 1 < boardLength) {
            if (board[downP][acrossP - 1] == 0 && board[downP][acrossP + 1] == 0) {
                if (board[downP - 1][acrossP - 1] == 0 && board[downP - 1][acrossP] == 0 && board[downP - 1][acrossP + 1] == 0) {
                        return true;
                }
            }
        }

        //if across + 1 > boardLength
        if(downP-1 > 0 && acrossP-1 > 0 && downP + 1 < boardHeight && acrossP + 1 > boardLength) {
            if (board[downP][acrossP - 1] == 0) {
                if (board[downP - 1][acrossP - 1] == 0 && board[downP - 1][acrossP] == 0) {
                    if (board[downP + 1][acrossP - 1] == 0 && board[downP + 1][acrossP] == 0) {
                        return true;
                    }
                }
            }
        }

        //if down + 1 > boardHeight and across + 1 > boardLength
        if(downP-1 > 0 && acrossP-1 > 0 && downP + 1 > boardHeight && acrossP + 1 > boardLength) {
            if (board[downP][acrossP - 1] == 0) {
                if (board[downP - 1][acrossP - 1] == 0 && board[downP - 1][acrossP] == 0) {
                        return true;
                }
            }
        }

        //if down - 1 < 0 and across + 1 > boardLength
        if(downP-1 < 0 && acrossP-1 > 0 && downP + 1 < boardHeight && acrossP + 1 > boardLength) {
                if (board[downP - 1][acrossP - 1] == 0 && board[downP - 1][acrossP] == 0) {
                    if (board[downP + 1][acrossP - 1] == 0 && board[downP + 1][acrossP] == 0) {
                        return true;
                    }
            }
        }

        //if down + 1 > boardHeight and across - 1 < 0
        if(downP-1 > 0 && acrossP-1 < 0 && downP + 1 > boardHeight && acrossP + 1 < boardLength) {
            if (board[downP][acrossP + 1] == 0) {
                if (board[downP - 1][acrossP] == 0 && board[downP - 1][acrossP + 1] == 0) {
                        return true;
                }
            }
        }

        //if everything is between 0 and boardHeight/boardLength
        if(downP-1 > 0 && acrossP-1 > 0 && downP + 1 < boardHeight && acrossP + 1 < boardLength) {
            if (board[downP][acrossP - 1] == 0 && board[downP][acrossP + 1] == 0) {
                if (board[downP - 1][acrossP - 1] == 0 && board[downP - 1][acrossP] == 0 && board[downP - 1][acrossP + 1] == 0) {
                    if (board[downP + 1][acrossP - 1] == 0 && board[downP + 1][acrossP] == 0 && board[downP + 1][acrossP + 1] == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void checkShot(String userInput){
        String input = userInput.toLowerCase();
        if(input.equalsIgnoreCase("0")){
            printBoardShots();
            printShotList(shotList);
            return;
        }
        if(input.equalsIgnoreCase("9")){
            printShipList(shipList);
            return;
        }
        if(input.equalsIgnoreCase("1")){
            printBoard();
            return;
        }
        Pattern pattern = Pattern.compile("[a-j][1-9]{1}0?");
        Matcher matcher = pattern.matcher(userInput);
        if(!matcher.matches()){
            System.out.println("Invalid input. Try again.");
            return;
        }
        if(!shotList.isEmpty()){
            if(shotList.contains(input)){
                System.out.println("You already shoot " + input +  " field. Try diffrent one.");
                return;
            }
        }

        //add shot to shotList
        shotList.add(input);

        char firstLetter = input.charAt(0);
        String numberStr = input.substring(1);
        int numberDown = (Integer.parseInt(numberStr))-1;
        //converting letter into boardLength number
        int numberAcross = 0;
        switch (firstLetter) {
            case 'a':
                numberAcross = 0;
                break;
            case 'b':
                numberAcross = 1;
                break;
            case 'c':
                numberAcross = 2;
                break;
            case 'd':
                numberAcross = 3;
                break;
            case 'e':
                numberAcross = 4;
                break;
            case 'f':
                numberAcross = 5;
                break;
            case 'g':
                numberAcross = 6;
                break;
            case 'h':
                numberAcross = 7;
                break;
            case 'i':
                numberAcross = 8;
                break;
            case 'j':
                numberAcross = 9;
                break;
        }

        // check if hit or miss
        isHitted(numberDown,numberAcross);
    }

    public boolean isHitted(int numberDown, int numberAcross){
        int checkedField = board[numberDown][numberAcross];
        if((checkedField == 1) || (checkedField == 2) || (checkedField == 3) || (checkedField == 4)){
            boardShots[numberDown][numberAcross] = (char) checkedField;
            afterHitted(numberDown, numberAcross);
            return true;
        }
        else{
            System.out.println("You missed. " + "It was your " + shotList.size() + " shot.");
            boardShots[numberDown][numberAcross] = 'X';
            return false;
        }

    }

    public void afterHitted(int numberDown, int numberAcross){
        // compare hitted point with ships positions
        for(int i = 0; i < shipList.size(); i++){
            for(int j = 0; j < shipList.get(i).getShipPositionDown().size(); j++){
                if(numberDown == shipList.get(i).getShipPositionDown(j)){
                    if(numberAcross == shipList.get(i).getShipPositionAcross(j)){
                       boardShots[numberDown][numberAcross] = (char) shipList.get(i).getSize();
                       int size = shipList.get(i).getShipPositionDown().size();
                       if(size == 1)
                       {
                           System.out.println("Lucky you! " + shipList.get(i).getName() + " is wrecked!");
                           System.out.println("It was your " + shotList.size() + " shot.");
                           shipList.get(i).removePosition(j);
                           shipList.remove(i);
                           return;
                       }
                       if(size > 1){
                           System.out.println("Lucky you! " + shipList.get(i).getName() + " is hitted!");
                           System.out.println("You need hit it " + (shipList.get(i).getShipPositionDown().size() - 1) +
                                   " more time to wreck it completely.");
                           System.out.println("It was your " + shotList.size() + " shot.");
                           shipList.get(i).removePosition(j);
                           return;
                       }

                    }
                }
            }

        }
    }

    public void endGame(){
        System.out.println("---------------------------------");
        System.out.println("Congratulations! You have won this battleship game in "
                + shotList.size() + " shots!");
        System.out.println("---------------------------------");

    }

}
