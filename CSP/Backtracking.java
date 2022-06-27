package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backtracking
{
    private ArrayList<Integer[][]> results = new ArrayList<>();
    Heuritics h = new Heuritics();

    public void chooseNextVariable(Board board, Constraint constraint, ArrayList<Integer> iterations, int heuriticOption, long startTime)
    {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < board.getBoard()[0][0].getDomain().size(); i++)
        {
            map.put(board.getBoard()[0][0].getDomain().get(i), 0);
        }
        Variable currentVariable;
        switch (heuriticOption) {
            case 0 -> currentVariable = h.getFirst(board);
            case 1 -> currentVariable = h.getRandom(board);
            case 2 -> currentVariable = h.getVariableWithLowestDomain(board);
            case 3 -> currentVariable = h.getVariableWithBiggestDomain(board);
            default -> currentVariable = h.getFirst(board);
        }
        if(currentVariable != null){
            ArrayList<Integer> usedNumbers = new ArrayList<>();
            for (int index = 0 ; index < board.getBoard()[currentVariable.getX()][currentVariable.getY()].getDomain().size() ; index++)
            {
                int value = board.getBoard()[currentVariable.getX()][currentVariable.getY()].getDomain().get(index);

                if (constraint.isOk(board, currentVariable.getX(),currentVariable.getY(), value))

                {
                    board.getBoard()[currentVariable.getX()][currentVariable.getY()].setValue(value);
                    iterations.add(3);
                    chooseNextVariable(board, constraint, iterations, heuriticOption, startTime);
                    board.getBoard()[currentVariable.getX()][currentVariable.getY()].setValue(-1);
                }
            }
        }
        else{
            Integer[][] newBoard = new Integer[board.getSize()][board.getSize()];
            for(int i = 0; i<board.getSize(); i++){
                for(int j = 0; j< board.getSize(); j++){
                    newBoard[i][j] = board.getBoard()[i][j].getValue();
                }
            }
            results.add(newBoard);
            if(results.size() == 1)
            {
                System.out.println("Liczba węzłów w pierwszym rozwiązaniu " + iterations.size());
                System.out.println("Ilość czasu w pierwszym rozwiązaniu " + (System.currentTimeMillis() - startTime));
            }
            //System.out.println(iterations.size());
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
            System.out.println(i+1);
        }
    }
}
