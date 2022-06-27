package com.company;

import java.io.FileNotFoundException;

public class BinaryBoard implements Board
{
    private Variable[][] board;

    public BinaryBoard(String filePath) throws FileNotFoundException {
        DataReader dataReader = new DataReader();
        this.board = dataReader.readBinaryBoard(filePath);
    }

    @Override
    public int getSize() {
        return board.length;
    }

    @Override
    public Variable[][] getBoard() {
        return board;
    }

    @Override
    public void setBoard(Variable[][] board){ this.board = board; }

    @Override
    public void displayBoard() {
        for (Variable[] variables : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" " + variables[j].getValue());
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public boolean isFull() {
        int counter = 0;
        for (Variable[] variables : board) {
            for (int j = 0; j < board.length; j++) {
               if(variables[j].getValue() == -1)
               {
                   counter++;
               }
            }
        }
        return counter == 1;
    }
}
