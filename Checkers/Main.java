package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        Board board = new Board();

        BotPlayer whitePlayer = new BotPlayer("A", true, false);
        BotPlayer blackPlayer = new BotPlayer("B", false, true);
        HumanPlayer humanPlayer = new HumanPlayer("B", false);

        whitePlayer.setSearchDepth(3);
        blackPlayer.setSearchDepth(3);

        Game game = new Game();
        game.run(whitePlayer,humanPlayer,  board, 4);
        System.out.println("Average time per move for "+ whitePlayer.getName()+ ": " +
                String.format("%.4f", game.getAverageTimeForMoveOfWhitePlayer()) + " milli seconds");
        System.out.println("Average number of visited nodes per move for "+
                whitePlayer.getName()+ ": " + game.getAverageNodeVisitedForWhitePlayer());

        System.out.println("Average time per move for "+ blackPlayer.getName()+ ": " +
                String.format("%.4f", game.getAverageTimeForMoveOfBlackPlayer()) + " milli seconds");
        System.out.println("Average number of visited nodes per move for "+
                blackPlayer.getName()+ ": " + game.getAverageNodeVisitedForBlackPlayer());

    }
}
