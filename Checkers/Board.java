package com.company;

import java.util.ArrayList;

public class Board
{
    private ArrayList<Pawn> board;

    public ArrayList<Pawn> getBoard() {
        return board;
    }

    public void setBoard(Player whitePlayer, Player blackPlayer) {
        ArrayList<Pawn> board = new ArrayList<>(whitePlayer.getPawns());
        board.addAll(blackPlayer.getPawns());

        this.board = board;
    }

    public void printBoard()
    {
        String line = "";
        for(int i = 0; i <= 8; i++)
        {
            if(i == 0) line += "   ";
            else line += "|  " + i + " ";
        }
        System.out.println(line);

        for(int i = 1; i <= 8; i++)
        {
            line = "";
            for(int j = 0; j <= 8; j++)
            {
                if(j == 0) line +=" " + i + " ";
                else
                {
                    int finalI = i;
                    int finalJ = j;
                    Pawn currentPawn = board.stream()
                            .filter(pawn -> pawn.getX() == finalJ && pawn.getY() == finalI)
                            .findAny()
                            .orElse(null);

                    if(currentPawn == null) line += "|    ";
                    else
                    {
                        if(currentPawn.isWhite())
                        {
                            if(currentPawn.isKing()) line += "| WK ";
                            else line += "| WP ";
                        }
                        else
                        {
                            if(currentPawn.isKing()) line += "| BK ";
                            else line += "| BP ";
                        }
                    }
                }
            }
            System.out.println(line);
            System.out.println("---+----+----+----+----+----+----+----+----+");
        }
    }
}
