package com.martajastrzabek;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private String name;
    private int size;
    private List<Integer> shipPositionDown;
    private List<Integer> shipPositionAcross;

    public Ship(String name, int size) {
        if(size < 0 || size > 4){
            System.out.println("Error! Wrong size. " +
                    "\nShip must have size between 1 and 4. \nTry again.");
            return;
        }
        this.size = size;
        this.name = name;
        shipPositionDown = new ArrayList<>();
        shipPositionAcross = new ArrayList<>();

        //System.out.println("Created new " + name + " " + size + " field long ship");
    }

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getShipPositionDown() {
        return shipPositionDown;
    }

    public void addShipPositionDown(int shipPositionDown){
        this.shipPositionDown.add(shipPositionDown);
    }

    public void addShipPositionAcross(int shipPositionAcross){
        this.shipPositionAcross.add(shipPositionAcross);
    }
    public int getShipPositionDown(int arrayPosition){
        return shipPositionDown.get(arrayPosition);
    }
    public int getShipPositionAcross(int arrayPosition){
        return shipPositionAcross.get(arrayPosition);
    }

    public void removePosition(int index){
        shipPositionDown.remove(index);
        shipPositionAcross.remove(index);
    }

}
