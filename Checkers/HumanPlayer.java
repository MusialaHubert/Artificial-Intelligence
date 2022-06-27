package com.company;

import java.util.ArrayList;

public class HumanPlayer implements Player
{
    private String name;
    private ArrayList<Pawn> pawns;
    private boolean isWhite;

    public HumanPlayer(String name, boolean isWhite)
    {
        this.name = name;
        this.pawns = PawnCreator.createPawns(isWhite);
        this.isWhite = isWhite;
    }

    @Override
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    @Override
    public boolean isHuman(){
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPawns(ArrayList<Pawn> pawns) {
        this.pawns = pawns;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }
}
