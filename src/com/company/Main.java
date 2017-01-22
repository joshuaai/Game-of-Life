package com.company;

public class Main {

    public static void main(String[] args) {
        GameBoard gb = new GameBoard(3);
        gb.print();
        gb.step();
        gb.print();
        //System.out.println(gb.getLivingNeighbors(1, 2));
    }
}
