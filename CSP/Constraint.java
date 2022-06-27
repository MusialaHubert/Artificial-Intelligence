package com.company;

import java.util.ArrayList;

public interface Constraint
{
    public boolean isOk(Board board, int row, int col, int chosenValue);
}
