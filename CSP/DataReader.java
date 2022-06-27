package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader
{
    public Variable[][] readBinaryBoard(String filePath) throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
        String line = sc.nextLine();
        int arraySize = line.length();

        Scanner sc2 = new Scanner(new BufferedReader(new FileReader(filePath)));
        Variable[][] board = new Variable[arraySize][arraySize];

        ArrayList<Integer> domain = new ArrayList<>();
        domain.add(0);
        domain.add(1);

        while(sc2.hasNextLine())
        {
            for (int i=0; i<board.length; i++)
            {
                String line2 = sc2.nextLine();
                for (int j=0; j<line2.length(); j++)
                {
                    Variable v;

                    if(line2.charAt(j) == 'x'){
                        v = new Variable(domain, domain, -1);
                    }
                    else{
                        v = new Variable(domain, domain, Integer.parseInt(String.valueOf(line2.charAt(j))));
                    }
                    board[i][j] = v;
                }
            }
        }
        for(int i = 0; i<board.length; i++){
            for(int j = 0; j< board.length; j++){
                board[i][j].setX(i);
                board[i][j].setY(j);
            }
        }
        return board;
    }
    public ArrayList<ArrayList<Integer>> readConstraintsForFutoshiki(String filePath) throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
        ArrayList<ArrayList<Integer>> constraints = new ArrayList<>();

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            ArrayList<Integer> constraint = new ArrayList<>();

            for (int j=0; j<line.length(); j++)
            {
                if(line.charAt(j) == '<')
                {
                    constraint.add(-1);
                }
                else if(line.charAt(j) == '>'){
                    constraint.add(1);
                }
                else if(line.charAt(j) == '-')
                {
                    constraint.add(0);
                }
            }
            constraints.add(constraint);
        }

        return constraints;
    }

    public Variable[][] readFutoshikiBoard(String filePath) throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(filePath)));
        String line = sc.nextLine();
        int arraySize = (line.length() / 2) +1;

        Scanner sc2 = new Scanner(new BufferedReader(new FileReader(filePath)));
        Variable[][] board = new Variable[arraySize][arraySize];

        ArrayList<Integer> domain = new ArrayList<>();
        for(int i = 1; i <= arraySize; i++)
        {
            domain.add(i);
        }

        int counter = 0;
        while(sc2.hasNextLine())
        {
            String line2 = sc2.nextLine();
            if(counter % 2 == 0)
            {
                for (int j=0; j<line2.length(); j++)
                {
                    Variable v;
                    if(line2.charAt(j) == 'x')
                    {
                        v = new Variable(domain, domain, -1);
                        board[counter/2][j/2] = v;
                    }
                    else if(line2.charAt(j) == '>' || line2.charAt(j) == '<' ||line2.charAt(j) == '-')
                    {

                    }
                    else
                    {
                        v = new Variable(domain,domain, Integer.parseInt(String.valueOf(line2.charAt(j))));
                        board[counter/2][j/2] = v;
                    }
                }
            }
            counter++;
        }

        for(int i = 0; i<board.length; i++){
            for(int j = 0; j< board.length; j++){
                board[i][j].setX(i);
                board[i][j].setY(j);
            }
        }
        return board;
    }
}
