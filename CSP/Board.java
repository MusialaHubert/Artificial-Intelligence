package com.company;

public interface Board
{
    public int getSize();

    public Variable[][] getBoard();

    public void setBoard(Variable[][] board);

    public void displayBoard();

    public boolean isFull();
}
