package com.company;

import java.util.ArrayList;

public class PawnCreator
{
    public static ArrayList<Pawn> createPawns(boolean isWhite)
    {
        ArrayList<Pawn> pawns = new ArrayList<>();

        if(isWhite){
            for(int y = 1; y <= 3; y++)
            {
                addPawn(pawns, y, true);
            }
        }
        else{
            for(int y = 6; y <= 8; y++)
            {
                addPawn(pawns, y, false);
            }
        }
        return pawns;
    }

    private static void addPawn(ArrayList<Pawn> pawns, int y, boolean isWhite) {
        for(int x = 1; x <=8; x++)
        {
            if(y %2 != 0 && x%2 == 0)
            {
                Pawn p = new Pawn(x, y, isWhite, false);
                pawns.add(p);
            }
            if(y %2 == 0 && x%2 != 0){
                Pawn p = new Pawn(x, y, isWhite, false);
                pawns.add(p);
            }
        }
    }
}
