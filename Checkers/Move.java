package com.company;

import java.util.ArrayList;

public class Move
{
    String startCoordinates;
    String endCoordinates;
    ArrayList<Pawn> capturedPawns = new ArrayList<>();
    int score;

    public String getStartCoordinates() {return startCoordinates;}

    public void setStartCoordinates(String startCoordinates) { this.startCoordinates = startCoordinates; }

    public String getCoordinates() {
        return endCoordinates;
    }

    public void setCoordinates(String coordinates) {
        this.endCoordinates = coordinates;
    }

    public ArrayList<Pawn> getCapturedPawns() {
        return capturedPawns;
    }

    public void setCapturedPawns(ArrayList<Pawn> capturedPawns) {
        this.capturedPawns = capturedPawns;
    }

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}
}
