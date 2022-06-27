package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BinaryConstraint implements Constraint{

    private boolean isEnoughInRow(Board board, int row, int chosenValue){
        int counter = 0;

        for(int i = 0; i<board.getSize(); i++){
            if(board.getBoard()[row][i].getValue() == chosenValue){
                counter++;
            }
        }
        return counter == board.getSize()/2;
    }

    private boolean isEnoughInCol(Board board, int col, int chosenValue){
        int counter = 0;

        for(int i = 0; i<board.getSize(); i++){
            if(board.getBoard()[i][col].getValue() == chosenValue){
                counter++;
            }
        }
        return counter == board.getSize()/2;
    }

    private boolean areThreeNumbersNextToInRow(Board board, int col, int row, int chosenValue){
        int sameNextTo = 0;
        for(int i = 0; i<board.getSize()-1; i++){
            if(board.getBoard()[row][i].getValue() == chosenValue){
                sameNextTo++;
            }
            else if(i == col){
                sameNextTo++;
            }
            else{
                sameNextTo = 0;
            }
            if(sameNextTo == 3){
                return true;
            }
        }
        return false;
    }

    private boolean areThreeNumbersNextToInCol(Board board, int col, int row, int chosenValue){
        int sameNextTo = 0;
        for(int i = 0; i<board.getSize()-1; i++){
            if(board.getBoard()[i][col].getValue() == chosenValue){
                sameNextTo++;
            }
            else if(i == row){
                sameNextTo++;
            }
            else{
                sameNextTo = 0;
            }
            if(sameNextTo == 3){
                return true;
            }
        }
        return false;
    }

    private boolean checkUniqueRowsAndCols(Board board, int row, int col, int chosenValue){
        board.getBoard()[row][col].setValue(chosenValue);
        boolean isFull = true;
        for(int i = 0; i < board.getSize(); i++)
        {
            for(int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    isFull = false;
                    break;
                }
            }
        }

        if(isFull)
        {
            Set<String> rows = new HashSet<>();
            Set<String> cols = new HashSet<>();
            for(int i = 0; i < board.getSize(); i++)
            {
                String currentRow = "";
                String currentCol = "";
                for(int j = 0; j < board.getSize(); j++)
                {
                    currentRow += board.getBoard()[i][j].getValue();
                    currentCol += board.getBoard()[j][i].getValue();
                }

                rows.add(currentRow);
                cols.add(currentCol);
            }
            board.getBoard()[row][col].setValue(-1);
            return rows.size() == board.getSize() && cols.size() == board.getSize();
        }
        board.getBoard()[row][col].setValue(-1);
        return true;
    }

    @Override
    public boolean isOk(Board board, int row, int col, int chosenValue)
    {
        return !isEnoughInCol(board, col, chosenValue) && !isEnoughInRow(board, row, chosenValue)
                && !areThreeNumbersNextToInRow(board, col, row, chosenValue)
                && !areThreeNumbersNextToInCol(board, col, row, chosenValue)
                && checkUniqueRowsAndCols(board,row,col,chosenValue);
    }
}
