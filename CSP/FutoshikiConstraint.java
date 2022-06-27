package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FutoshikiConstraint implements Constraint{

    ArrayList<ArrayList<Integer>> constraints;

    public FutoshikiConstraint(String filePath) throws FileNotFoundException {
        DataReader dataReader = new DataReader();
        this.constraints = dataReader.readConstraintsForFutoshiki(filePath);
    }

    private boolean isInRow(Board board, int row, int chosenValue){
        for(int i = 0; i<board.getSize(); i++){
            if(board.getBoard()[row][i].getValue() == chosenValue){
                return true;
            }
        }
        return false;
    }

    private boolean isInCol(Board board, int col, int chosenValue){
        for(int i = 0; i<board.getSize(); i++){
            if(board.getBoard()[i][col].getValue() == chosenValue){
                return true;
            }
        }
        return false;
    }

    private boolean checkBigeerLesserConstraints(Board board, int row, int col, int chosenValue)
    {
        board.getBoard()[row][col].setValue(chosenValue);
        boolean isGood = true;
        for(int i = 0; i < constraints.size(); i++)
        {
            if(i%2 == 0)
            {
                for(int j = 0; j < constraints.get(i).size(); j++)
                {
                    if(board.getBoard()[i/2][j].getValue() != -1 && board.getBoard()[i/2][j+1].getValue() != -1)
                    {
                        if(constraints.get(i).get(j) == -1)
                        {
                            if(board.getBoard()[i/2][j].getValue() >= board.getBoard()[i/2][j+1].getValue()) isGood = false;
                        }
                        if(constraints.get(i).get(j) == 1)
                        {
                            if(board.getBoard()[i/2][j].getValue() <= board.getBoard()[i/2][j+1].getValue()) isGood = false;
                        }
                    }
                }
            }
            else
            {
                for(int j = 0; j < constraints.get(i).size(); j++)
                {
                    if(board.getBoard()[i/2][j].getValue() != -1 && board.getBoard()[(i/2) + 1][j].getValue() != -1)
                    {
                        if(constraints.get(i).get(j) == -1)
                        {
                            if(board.getBoard()[i/2][j].getValue() >= board.getBoard()[(i/2) + 1][j].getValue()) isGood = false;
                        }
                        if(constraints.get(i).get(j) == 1)
                        {
                            if(board.getBoard()[i/2][j].getValue() <= board.getBoard()[(i/2) + 1][j].getValue()) isGood = false;
                        }
                    }
                }
            }
        }
        board.getBoard()[row][col].setValue(-1);
        return isGood;
    }

    @Override
    public boolean isOk(Board board, int row, int col, int chosenValue) {
        return !isInRow(board, row, chosenValue) && !isInCol(board, col, chosenValue)
                && checkBigeerLesserConstraints(board, row, col, chosenValue);
    }

    public void displayConstraints()
    {
        for(int i = 0; i < constraints.size(); i++)
        {
            for(int j = 0; j < constraints.get(i).size(); j++)
            {
                System.out.print(" " + constraints.get(i).get(j));
            }

            System.out.println();
        }
    }
}
