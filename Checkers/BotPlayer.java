package com.company;

import java.util.ArrayList;

public class BotPlayer implements Player{
    private String name;
    private ArrayList<Pawn> pawns;
    private boolean isWhite;
    private int searchDepth;
    private boolean useMinMax;

    public BotPlayer(String name, boolean isWhite, boolean useMinMax)
    {
        this.name = name;
        this.pawns = PawnCreator.createPawns(isWhite);
        this.isWhite = isWhite;
        this.useMinMax = useMinMax;
    }
    @Override
    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    @Override
    public boolean isHuman(){
        return false;
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

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }

    public boolean isUseMinMax() {return useMinMax;}

    public void setUseMinMax(boolean useMinMax) {this.useMinMax = useMinMax;}
}
