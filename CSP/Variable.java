package com.company;

import java.util.ArrayList;

public class Variable
{
    private ArrayList<Integer> domain;
    private ArrayList<Integer> currentDomain;
    private int value;

    private int x;
    private int y;

    public Variable(ArrayList<Integer> domain, ArrayList<Integer> currentDomain, int value) {
        this.domain = domain;
        this.currentDomain = currentDomain;
        this.value = value;
    }

    public ArrayList<Integer> getDomain() {
        return domain;
    }

    public void setDomain(ArrayList<Integer> domain) {
        this.domain = domain;
    }

    public ArrayList<Integer> getCurrentDomain() {
        return currentDomain;
    }

    public void setCurrentDomain(ArrayList<Integer> currentDomain) {
        this.currentDomain = currentDomain;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
}
