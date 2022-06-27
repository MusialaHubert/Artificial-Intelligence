package com.company;

import java.util.*;

public class Heuritics {
    public Variable getFirst(Board board)
    {
        for(int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    return board.getBoard()[i][j];
                }
            }
        }
        return null;
    }
    public Variable getRandom(Board board)
    {
        ArrayList<Variable> variables = new ArrayList<>();
        for(int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    variables.add(board.getBoard()[i][j]);
                }
            }
        }
        if(variables.size() == 0) return null;

        Random rand = new Random();
        int randomIndex = rand.nextInt(variables.size());
        return variables.get(randomIndex);
    }

    public Variable getVariableWithLowestDomain(Board board)
    {
        ArrayList<Variable> variables = new ArrayList<>();
        for(int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    variables.add(board.getBoard()[i][j]);
                }
            }
        }
        if(variables.size() == 0) return null;

        variables.sort(Comparator.comparingInt(o -> o.getCurrentDomain().size()));
        return variables.get(0);
    }
    public Variable getVariableWithBiggestDomain(Board board)
    {
        ArrayList<Variable> variables = new ArrayList<>();
        for(int i = 0; i < board.getSize(); i++)
        {
            for (int j = 0; j < board.getSize(); j++)
            {
                if(board.getBoard()[i][j].getValue() == -1)
                {
                    variables.add(board.getBoard()[i][j]);
                }
            }
        }
        if(variables.size() == 0) return null;

        variables.sort(Comparator.comparingInt(o -> o.getCurrentDomain().size()));
        return variables.get(variables.size() - 1);
    }

    public int leastFrequentlyAppearingValue(Board board, Map<Integer, Integer> values, ArrayList<Integer> usedValue)
    {
        cleanMap(values);

        for(int i = 0; i < board.getSize(); i++)
        {
            for(int j = 0; j < board.getSize(); j++)
            {
                for (Integer value : values.keySet())
                {
                    if(board.getBoard()[i][j].getValue() == value)
                    {
                        values.put(value, values.get(value) + 1);
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int key = 1;

        for (Integer integer : usedValue) {
            values.remove(integer);
        }

        for (Integer value : values.keySet())
        {
            if(values.get(value) > max){
                max = values.get(value);
                key = value;
            }
        }
        return key;
    }

    public void cleanMap(Map<Integer, Integer> values){
        values.replaceAll((k, v) -> 0);
    }
}
