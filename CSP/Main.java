package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException
    {
        backtrackingBinary();
        System.out.println();
        backtrackingFutushiki();
        System.out.println();
        forwardBinary();
        System.out.println();
        forwardFutushiki();
    }

    private static void backtrackingBinary() throws FileNotFoundException {
        System.out.println("Plansza na poczatku: ");
        BinaryBoard board = new BinaryBoard("src/com/company/Data/binary_10x10");
        //System.out.println(board.getBoard()[0][0].getDomain());
        BinaryConstraint binaryConstraint = new BinaryConstraint();

        System.out.println("Plansza po run: ");
        Backtracking backtracking = new Backtracking();
        ArrayList<Integer> cycles = new ArrayList<>();

        long time1 = System.currentTimeMillis();
        backtracking.chooseNextVariable(board, binaryConstraint, cycles, 3, time1);
        //backtracking.displayResults();

        System.out.println(cycles.size() + " to jest size");
        System.out.println(System.currentTimeMillis() - time1 + " czas");
    }

    private static void backtrackingFutushiki() throws FileNotFoundException {
        FutoshikiBoard board1 = new FutoshikiBoard("src/com/company/Data/futoshiki_6x6");
        FutoshikiConstraint constraint1 = new FutoshikiConstraint("src/com/company/Data/futoshiki_6x6");

        System.out.println("Plansza po run: ");
        Backtracking backtracking2 = new Backtracking();
        ArrayList<Integer> cycles = new ArrayList<>();
        long time1 = System.currentTimeMillis();

        backtracking2.chooseNextVariable(board1, constraint1, cycles, 2, time1);

        System.out.println((System.nanoTime() - time1) / 1_000_000_000);
        backtracking2.displayResults();
        System.out.println(cycles.size());
        System.out.println(System.currentTimeMillis() - time1 + " czas");
    }

    private static void forwardBinary() throws FileNotFoundException {
        System.out.println("Plansza na poczatku: ");
        BinaryBoard board = new BinaryBoard("src/com/company/Data/binary_10x10");
        BinaryConstraint binaryConstraint = new BinaryConstraint();

        System.out.println("Plansza po run: ");
        ForwardChecking forwardChecking = new ForwardChecking();
        ArrayList<Integer> cycles = new ArrayList<>();

        long time1 = System.currentTimeMillis();
        forwardChecking.runWithHeuritics(board, binaryConstraint, cycles, 3, time1);
        //forwardChecking.displayResults();
        System.out.println(cycles.size() + " to jest size");
        System.out.println(System.currentTimeMillis() - time1 + " czas");
    }

    private static void forwardFutushiki() throws FileNotFoundException {
        FutoshikiBoard board = new FutoshikiBoard("src/com/company/Data/futoshiki_6x6");
        FutoshikiConstraint constraint = new FutoshikiConstraint("src/com/company/Data/futoshiki_6x6");

        System.out.println("Plansza po run: ");
        ForwardChecking forwardChecking = new ForwardChecking();
        ArrayList<Integer> cycles = new ArrayList<>();
        long time1 = System.currentTimeMillis();
        forwardChecking.runWithHeuritics(board, constraint, cycles, 2,time1);
        System.out.println((System.currentTimeMillis() - time1)/1_000_000_000);
        forwardChecking.displayResults();
        System.out.println(cycles.size());
        System.out.println(System.currentTimeMillis() - time1 + " czas");
    }

}
