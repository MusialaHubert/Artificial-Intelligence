package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ForwardChecking {
    private ArrayList<Integer[][]> results = new ArrayList<>();
    Heuritics heuritics = new Heuritics();
    public int counter = 1;

    public ArrayList<Integer[][]> getResults()
    {
        return results;
    }

    public void runWithHeuritics(Board board, Constraint constraint, ArrayList<Integer> cycles, int heuriticOption, long startTime)
    {
        if(!setCurrentDomains(board,constraint)) return;

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < board.getBoard()[0][0].getDomain().size(); i++)
        {
            map.put(board.getBoard()[0][0].getDomain().get(i), 0);
        }

        Variable currentVariable;
        switch (heuriticOption) {
            case 0 -> currentVariable = heuritics.getFirst(board);
            case 1 -> currentVariable = heuritics.getRandom(board);
            case 2 -> currentVariable = heuritics.getVariableWithLowestDomain(board);
            case 3 -> currentVariable = heuritics.getVariableWithBiggestDomain(board);
            default -> currentVariable = heuritics.getFirst(board);
        }

        if(currentVariable != null)
        {
            ArrayList<Integer> usedNumbers = new ArrayList<>();
            for(int i = 0; i < board.getBoard()[currentVariable.getX()][currentVariable.getY()].getCurrentDomain().size(); i++)
            {
                int value = board.getBoard()[currentVariable.getX()][currentVariable.getY()].getCurrentDomain().get(i);
                usedNumbers.add(value);

                if(constraint.isOk(board, currentVariable.getX(), currentVariable.getY(), value))
                {
                    board.getBoard()[currentVariable.getX()][currentVariable.getY()]
                            .setValue(value);
                    cycles.add(1);
                    runWithHeuritics(board, constraint, cycles, heuriticOption, startTime);
                    board.getBoard()[currentVariable.getX()][currentVariable.getY()].setValue(-1);
                }
            }
        }
        else
        {
            Integer[][] newBoard = new Integer[board.getSize()][board.getSize()];
            for(int i = 0; i<board.getSize(); i++){
                for(int j = 0; j< board.getSize(); j++){
                    newBoard[i][j] = board.getBoard()[i][j].getValue();
                }
            }
            results.add(newBoard);
            if(results.size() == 1)
            {
                System.out.println("Liczba węzłów w pierwszym rozwiązaniu " + cycles.size());
                System.out.println("Ilość czasu w pierwszym rozwiązaniu " + (System.currentTimeMillis() - startTime));
            }

        }
    }

    public void run(Board board, Constraint constraint, ArrayList<Integer> cycles)
    {
        if(!setCurrentDomains(board,constraint)) return;
        for (int row = 0; row < board.getSize(); row++)
        {
            for (int col = 0; col < board.getSize(); col++)
            {
                if (board.getBoard()[row][col].getValue() == -1)
                {
                    for(int i = 0; i < board.getBoard()[row][col].getCurrentDomain().size(); i++)
                    {
                        if(constraint.isOk(board, row, col, board.getBoard()[row][col].getCurrentDomain().get(i)))
                        {
                            board.getBoard()[row][col].setValue(board.getBoard()[row][col].getCurrentDomain().get(i));
                            cycles.add(1);
                            run(board, constraint, cycles);
                            board.getBoard()[row][col].setValue(-1);
                        }
                    }
                    return;
                }
            }
        }

        Integer[][] newBoard = new Integer[board.getSize()][board.getSize()];
        for(int i = 0; i<board.getSize(); i++){
            for(int j = 0; j< board.getSize(); j++){
                newBoard[i][j] = board.getBoard()[i][j].getValue();
            }
        }
        results.add(newBoard);
    }

    private void showDomains(Board board) {
        for (int row = 0; row < board.getSize(); row++)
        {
            for (int col = 0; col < board.getSize(); col++)
            {
                if (board.getBoard()[row][col].getValue() == -1)
                {
                    System.out.print(board.getBoard()[row][col].getCurrentDomain() + " ");
                }
                else
                {
                    System.out.print("*" +board.getBoard()[row][col].getValue() + "*" );
                }
            }
            System.out.println();
        }
    }

    private boolean setCurrentDomains(Board board, Constraint constraint)
    {
        for(int i = 0; i < board.getSize(); i++)
        {
            for(int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    ArrayList<Integer> newDomain = new ArrayList<>();
                    for(int k = 0; k < board.getBoard()[i][j].getDomain().size(); k++)
                    {
                        if(constraint.isOk(board, i, j, board.getBoard()[i][j].getDomain().get(k)))
                        {
                            newDomain.add(board.getBoard()[i][j].getDomain().get(k));
                        }
                    }
                    if(newDomain.size() == 0)
                    {
                        return false;
                    }
                    else
                    {
                        board.getBoard()[i][j].setCurrentDomain(newDomain);
                    }
                }
            }
        }
        return true;
    }

    private void setDefaultDomains(Board board)
    {
        for(int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                if (board.getBoard()[i][j].getValue() == -1)
                {
                    board.getBoard()[i][j].setCurrentDomain(board.getBoard()[i][j].getDomain());
                }
            }
        }
    }
    public void displayResults()
    {
        for(int i = 0; i < results.size(); i++)
        {
            for(int j = 0; j < results.get(i).length; j++)
            {
                for(int k = 0; k < results.get(i).length; k++)
                {
                    System.out.print(" " + results.get(i)[j][k]);
                }
                System.out.println();
            }
            System.out.println(i + 1);
            System.out.println();
        }
    }
}
