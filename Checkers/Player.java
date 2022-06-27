package com.company;

import java.util.ArrayList;

public interface Player {
    ArrayList<Pawn> getPawns();
    void setPawns(ArrayList<Pawn> pawns);
    String getName();
    boolean isHuman();
    boolean isWhite();
}
