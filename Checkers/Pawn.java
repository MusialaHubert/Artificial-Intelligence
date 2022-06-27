package com.company;

public class Pawn
{
    private int x;
    private int y;
    private boolean isWhite;
    private boolean isKing;

    public Pawn(int x, int y, boolean isWhite, boolean isKing) {
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
        this.isKing = isKing;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }
}
